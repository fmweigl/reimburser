package fm.weigl.reimburser

/**
 * Created by fweigl on 15.12.16.
 */

fun CustomerData.toCsv(): String {
    return "$subscriptionId,$token"
}