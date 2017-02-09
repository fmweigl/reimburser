package fm.weigl.reimburser

import java.io.*

/**
 * Reads customer data from file customer_data.csv located in resources folder
 *
 * CSV file assumptions:
 *    - column[0] is subscriptionId; it is the ID of the product the user bought, e.g. 'monthly001')
 *    - column[1] is token provided to the device by the In-App-Billing API
 */

class SubscriptionDataFileReader(private val logger: Logger) {

    val tag = javaClass.simpleName

    var separator = ""

    companion object {
        val COMMA = ","
        val SEMICOLON = ";"
    }

    fun readCustomerDataFromFile(): List<CustomerData> {

        val subscriptionDataList = mutableListOf<CustomerData>()

        val dataResource = javaClass.classLoader.getResource(Values.CUSTOMER_DATA_PATH)
        val dataFile = File(dataResource.file)

        dataFile.readLines().forEachIndexed { index, line ->

            try {

                if (index == 0) {
                    initSeparator(line)
                }

                val fields = line.split(separator)
                val productId = fields[0]
                val token = fields[1]
                subscriptionDataList.add(CustomerData(subscriptionId = productId, token = token))

            } catch(e: Exception) {
                logger.log("TAG", "error reading line $index ${e.toString()}")
            }

        }

        logger.log(tag, "read ${subscriptionDataList.size} subscription data sets from ${Values.CUSTOMER_DATA_PATH}")

        return subscriptionDataList


    }

    private fun initSeparator(line: String) {

        if (separator.isNotEmpty()) {
            return
        }

        if (line.contains(COMMA)) {
            separator = COMMA
        } else {
            separator = SEMICOLON
        }

    }

}

data class CustomerData(val subscriptionId: String, val token: String)