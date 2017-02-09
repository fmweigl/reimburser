package fm.weigl.reimburser

import com.google.api.client.auth.oauth2.Credential
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.http.HttpTransport
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.jackson2.JacksonFactory
import com.google.api.services.androidpublisher.AndroidPublisher
import com.google.api.services.androidpublisher.AndroidPublisherScopes
import java.io.File

/**
 * Created by fweigl on 02.11.16.
 */
class PublisherProvider(private val logger: Logger) {

    val tag = javaClass.simpleName

    var httpTansport: HttpTransport
    var jsonFactory: JsonFactory

    init {
        httpTansport = GoogleNetHttpTransport.newTrustedTransport()
        jsonFactory = JacksonFactory.getDefaultInstance()
    }

    fun getPublisher(applicationName: String, serviceAccountEmail: String): AndroidPublisher {

        logger.log(tag, "creating service for application $applicationName, serviceAccount $serviceAccountEmail")

        val credential = createCredential(serviceAccountEmail)

        return AndroidPublisher.Builder(httpTansport, jsonFactory, credential)
                .setApplicationName(applicationName)
                .build()

    }

    private fun createCredential(serviceAccountEmail: String): Credential {


        val p12Resource = javaClass.classLoader.getResource(Values.P12_PATH)
        val p12File = File(p12Resource.file)

        val credential = GoogleCredential.Builder()
                .setTransport(httpTansport).setJsonFactory(jsonFactory)
                .setServiceAccountId(serviceAccountEmail)
                .setServiceAccountScopes(setOf(AndroidPublisherScopes.ANDROIDPUBLISHER))
                .setServiceAccountPrivateKeyFromP12File(p12File).build()

        return credential

    }

}