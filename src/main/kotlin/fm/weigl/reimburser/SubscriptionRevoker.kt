package fm.weigl.reimburser

import com.google.api.services.androidpublisher.model.SubscriptionPurchase
import java.util.*

/**
 * Created by fweigl on 01.11.16.
 */
class SubscriptionRevoker(publisherProvider: PublisherProvider,
                          private val dataFileReader: SubscriptionDataFileReader,
                          private val logger: Logger) {

    val TAG = javaClass.simpleName

    val apiService = publisherProvider.getPublisher(Values.APP_NAME, Values.SERVICE_ACCOUNT_EMAIL)


    fun revokeSubscriptions() {

        logger.log(TAG, "REVOKING SUBSCRIPTIONS")

        val customerData: List<CustomerData> = getCustomerDataSet()

        customerData.forEachIndexed { index, customerData ->

            logger.log(TAG, "revoking abo for customerData ${customerData.toString()}")

            try {
                apiService.purchases().subscriptions().revoke(Values.PACKAGE_NAME, customerData.subscriptionId, customerData.token).execute()
                logger.log(TAG, "$index,OK,${customerData.toCsv()}", true)

            } catch (e: Exception) {
                logger.log("ERROR", "Error revoking customer ${customerData.toString()} at index $index, ${e.toString()}")
                logger.log(TAG, "$index,ERROR,${customerData.toCsv()}", true)

            }
        }
    }

    private fun getCustomerDataSet(): List<CustomerData> {

        val customerDataList: List<CustomerData> = dataFileReader.readCustomerDataFromFile()

        return customerDataList
    }
}