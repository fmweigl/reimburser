package fm.weigl.reimburser

/**
 * Created by fweigl on 01.11.16.
 */
class Main {


    companion object {

        @JvmStatic fun main(args: Array<String>) {

            val fileLogger = FileLogger()
            val logger = Logger(fileLogger)
            val publisherProvider = PublisherProvider(logger)
            val fileReader = SubscriptionDataFileReader(logger)
            val revoker = SubscriptionRevoker(publisherProvider, fileReader, logger)

            revoker.revokeSubscriptions()

        }
    }
}
