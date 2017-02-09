# google-abo-revoker

### Small (Kotlin) program to 'revoke' active subscriptions on the Google PlayStore

Subscriptions means subscriptions made via Google Play In App Billing

Revoke here means 

1. cancel the subscription and 
2. refund the user for his subscription

as described in the [method documentation](https://developers.google.com/android-publisher/api-ref/purchases/subscriptions/revoke).

This was developed as a quick one-shot-solution for the aforementioned specific problem, thus it will not be developed any further and does not contain tests.

#### How-To:

1. Install Kotlin-plugin for IntelliJ

2. Provide data in the `customer_data.csv` file as a CSV containing (per line): `subscriptionId,token`

 `subscriptionId` it is the ID of the product the user bought, e.g. 'monthly001')

 `token` is provided to the device by the In-App-Billing API and probably be saved to your backend

3. Provide the `key.p12` key file needed for authentication with the [Google Play service account](https://developers.google.com/android-publisher/getting_started#using_a_service_account). This file can be downloaded from the Google Api console. Has to be put in the
resources folder alongside `customer_data.csv`

4. Provide `app name`, `app package name`, `service account email` (can be found in your Google API console) in `Values.kt`

5. Run `Main.main()`. The script will read from `customer_data.csv` and revoke them one by one. Logs can be found in `revoker_logs.txt` in the project folder.


#### Uses: 

[Google Play Api](https://developers.google.com/android-publisher/)

[API Client Library (Java)](https://developers.google.com/api-client-library/java/apis/androidpublisher/v2)

