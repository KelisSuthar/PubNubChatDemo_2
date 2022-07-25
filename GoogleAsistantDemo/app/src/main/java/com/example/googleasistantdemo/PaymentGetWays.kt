package com.example.googleasistantdemo

import android.Manifest
import android.R.attr
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.googleasistantdemo.databinding.ActivityPaymentGetWaysBinding
import com.razorpay.Checkout
import com.razorpay.PaymentData
import com.razorpay.PaymentResultListener
import com.razorpay.PaymentResultWithDataListener
import com.shreyaspatil.EasyUpiPayment.EasyUpiPayment
import com.shreyaspatil.EasyUpiPayment.listener.PaymentStatusListener
import com.shreyaspatil.EasyUpiPayment.model.TransactionDetails
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.app.ActivityCompat

import android.content.pm.PackageManager

import androidx.core.content.ContextCompat
import java.io.InputStreamReader

import java.io.BufferedReader

import java.io.InputStream

import java.io.DataOutputStream

import java.net.HttpURLConnection

import java.net.URL
import android.content.pm.PackageInfo
import android.content.pm.PackageManager.NameNotFoundException
import androidx.core.app.ActivityCompat.startActivityForResult

import android.content.ComponentName
import android.content.ContentValues.TAG
import android.util.Log
import androidx.annotation.Size
import com.android.billingclient.api.*
import com.google.android.gms.fido.fido2.api.common.RequestOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.android.gms.wallet.IsReadyToPayRequest
import com.google.android.gms.wallet.PaymentsClient
import com.google.android.gms.wallet.Wallet
import com.google.android.gms.wallet.WalletConstants
import com.stripe.android.*
import com.stripe.android.model.ConfirmPaymentIntentParams
import com.stripe.android.model.StripeIntent
import com.stripe.android.view.PaymentMethodsActivityStarter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

import java.util.HashMap
import androidx.core.app.ActivityCompat.startActivityForResult

import android.R.attr.name

import android.net.Uri





class PaymentGetWays : AppCompatActivity(), View.OnClickListener, PaymentResultListener,
    EphemeralKeyProvider,
    PaymentResultWithDataListener, PurchasesUpdatedListener {
    lateinit var mBinding: ActivityPaymentGetWaysBinding
    val c: Date = Calendar.getInstance().getTime()
    val PAYTM_REQ_CODE = 102
    var stripe: Stripe? = null
    var GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user"
    var GOOGLE_PAY_REQUEST_CODE = 123
    val UPI_PAYMENT = 0
    val skuList = ArrayList<String>()
    var paymentClients: PaymentsClient? = null
    val df = SimpleDateFormat("ddMMyyyyHHmmss", Locale.getDefault())
    val transcId: String = df.format(c)

    //    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
//    private val backendApi: BackendApi =
//        RetrofitFactory.instance.create(BackendApi::class.java)
    private var paymentMethodId: String = "pm_1HJJ6nAKcjKtlZOHN79JOkPe"
    private val clientSecret: String =
        "pi_1HJNNtAKcjKtlZOHbeYMvCLU_secret_FzF0mL6a94RkebcdJ5gvtHr0n"
    private var paymentSession: PaymentSession? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityPaymentGetWaysBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnupi.setOnClickListener(this)
        mBinding.btnStrip.setOnClickListener(this)
        mBinding.btnRaxorPay.setOnClickListener(this)
        mBinding.btnGoolgePay.setOnClickListener(this)
        mBinding.btnPaytm.setOnClickListener(this)
        mBinding.btnPaypal.setOnClickListener(this)
        mBinding.btnInAppPurchase.setOnClickListener(this)

        //Strip
        PaymentConfiguration.init(
            applicationContext,
            "pk_test_51BTUDGJAJfZb9HEBwDg86TN1KNprHjkfipXmEDMb0gSCassK5T3ZfxsAbcgKVmAIXF7oZ6ItlZZbXO6idTHE67IM007EwQ4uN3"
        )
        //Strip
        PaymentMethodsActivityStarter(this)
            .startForResult(
                PaymentMethodsActivityStarter.Args.Builder()
                    .build()
            )
        //gpay
        val wallet: Wallet.WalletOptions = Wallet.WalletOptions.Builder().setEnvironment(WalletConstants.ENVIRONMENT_TEST).build()
        paymentClients = Wallet.getPaymentsClient(this, wallet)

        if (ContextCompat.checkSelfPermission(
                this@PaymentGetWays,
                Manifest.permission.READ_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this@PaymentGetWays,
                arrayOf(Manifest.permission.READ_SMS, Manifest.permission.RECEIVE_SMS),
                101
            )
        }
    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnupi -> {
                upiPayment()
            }
            R.id.btnStrip -> {
                stripPayment()
            }
            R.id.btnRaxorPay -> {
                rozorPayment()
            }
            R.id.btnGoolgePay -> {
//                val isReadyToPayJson = PaymentsUtil.isReadyToPayRequest() ?: return
//                val readyTOPay:IsReadyToPayRequest = IsReadyToPayRequest.fromJson(isReadyToPayJson.toString())
//
//                val task: Task<Boolean> = paymentClients!!.isReadyToPay(readyTOPay)
//                task.addOnCompleteListener {
//                    if(it.isSuccessful){
                        googlepayPayment()
//                    }else{
//                        Toast.makeText(this, "went Wrong", Toast.LENGTH_SHORT).show()
//                    }
//                }


            }
            R.id.btnPaytm -> {
                paytmPayment()
            }
            R.id.btnPaypal -> {
                paypalPayment()
            }
            R.id.btnInAppPurchase -> {
                inAppPurchase()
            }
        }
    }

    private fun paypalPayment() {

    }

    private fun inAppPurchase() {


        val purchasesUpdatedListener =
            PurchasesUpdatedListener { billingResult, purchases ->
                // To be implemented in a later section.
            }

        val billingClient = BillingClient.newBuilder(this)
            .setListener(purchasesUpdatedListener)
            .enablePendingPurchases()
            .build()
        billingClient.startConnection(object : BillingClientStateListener {
            //to recive call back this is require
            override fun onBillingSetupFinished(billingResult: BillingResult) {
                if (billingResult.responseCode == BillingClient.BillingResponseCode.OK) {
                    billingClient.queryPurchaseHistoryAsync(
                        BillingClient.SkuType.INAPP
                    )
                    { billingResult, purchasesList ->

                        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchasesList != null) {
                            for (purchase in purchasesList) {
                                val params = AcknowledgePurchaseParams.newBuilder()
                                    .setPurchaseToken(purchase.purchaseToken)
                                    .build()
                                billingClient.acknowledgePurchase(params) { billingResult ->
                                    Toast.makeText(
                                        this@PaymentGetWays,
                                        billingResult.responseCode.toString(),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                }
            }

            override fun onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
            }
        })

    }


    private fun paytmPayment() {
        //for Allin One SDK
//        val paytmParams = JSONObject()
//
//        val body = JSONObject()
//        body.put("requestType", "Payment")//use this FOr one time trasection
//        body.put("mid", "INTEGR7769XXXXXX9383")//this mid is ex but if any one needs to login to merchant account on paytm
//        body.put("websiteName", "WEBSTAGING")//this is ex
//        body.put("orderId", "ORDERID_98765")//this is ex need
//        body.put("callbackUrl", "https://<callback URL to be used by merchant>")
//
//        val txnAmount = JSONObject()
//        txnAmount.put("value", "100.00")
//        txnAmount.put("currency", "INR")
//
//        val userInfo = JSONObject()
//        userInfo.put("custId", "CUST_001")
//        body.put("txnAmount", txnAmount)
//        body.put("userInfo", userInfo)
//
///*
//* Generate checksum by parameters we have in body
//* You can get Checksum JAR from https://developer.paytm.com/docs/checksum/
//* Find your Merchant Key in your Paytm Dashboard at https://dashboard.paytm.com/next/apikeys
//*/
//
//        val checksum: String = PaytmChecksum.generateSignature(body.toString(), "YOUR_MERCHANT_KEY")
//
//        val head = JSONObject()
//        head.put("signature", checksum)
//
//        paytmParams.put("body", body)
//        paytmParams.put("head", head)
//
//        val post_data = paytmParams.toString()
//
///* for Staging */
//        val url =
//            URL("https://securegw-stage.paytm.in/theia/api/v1/initiateTransaction?mid=YOUR_MID_HERE&orderId=ORDERID_98765")
//
///* for Production */
//// URL url = new URL("https://securegw.paytm.in/theia/api/v1/initiateTransaction?mid=YOUR_MID_HERE&orderId=ORDERID_98765");
//
//        try {
//            val connection = url.openConnection() as HttpURLConnection
//            connection.requestMethod = "POST"
//            connection.setRequestProperty("Content-Type", "application/json")
//            connection.doOutput = true
//            val requestWriter = DataOutputStream(connection.outputStream)
//            requestWriter.writeBytes(post_data)
//            requestWriter.close()
//            var responseData = ""
//            val `is` = connection.inputStream
//            val responseReader = BufferedReader(InputStreamReader(`is`))
//            if (responseReader.readLine().also { responseData = it } != null) {
//                System.out.append("Response: $responseData")
//            }
//            responseReader.close()
//        } catch (exception: Exception) {
//            exception.printStackTrace()
//        }

        //For Non SDK Based
        val paytmIntent = Intent()
        intent.component =
            ComponentName("net.one97.paytm", "net.one97.paytm.AJRRechargePaymentActivity")
        paytmIntent.putExtra("paymentmode", 2)
        paytmIntent.putExtra("paytm_invoke", true)
        paytmIntent.putExtra("price", "100.00")
        paytmIntent.putExtra("nativeSdkEnabled", true)
        paytmIntent.putExtra("orderid", 101)
        paytmIntent.putExtra("txnToken", "21234w4aesw23e")
        paytmIntent.putExtra("mid", "INTEGR7769XXXXXX9383")
        startActivityForResult(paytmIntent, PAYTM_REQ_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PAYTM_REQ_CODE && data != null) {
            Toast.makeText(
                this,
                data.getStringExtra("nativeSdkForMerchantMessage") + data.getStringExtra("response"),
                Toast.LENGTH_SHORT
            ).show();
        }
        //Strip
        if (requestCode == PaymentMethodsActivityStarter.REQUEST_CODE) {

            data?.let {
                val result = PaymentMethodsActivityStarter.Result.fromIntent(data)

                val paymentMethod = result?.paymentMethod
                Log.e(TAG, "paymentMethodId : " + paymentMethod?.id)
                paymentMethodId = paymentMethod?.id.toString()
                paymentSession?.handlePaymentData(requestCode, resultCode, it)
            }
        } else {
            stripe?.onPaymentResult(
                requestCode,
                data,
                object : ApiResultCallback<PaymentIntentResult> {
                    override fun onSuccess(result: PaymentIntentResult) {
                        val paymentIntent = result.intent
                        when (paymentIntent.status) {
                            StripeIntent.Status.Succeeded -> {
                                Log.e(TAG, "Payment Success")
                            }
                            StripeIntent.Status.RequiresPaymentMethod -> {
                                Log.e(
                                    TAG,
                                    "Payment Failed " + paymentIntent.lastPaymentError?.message
                                )
                            }
                            else -> {
                                Log.e(TAG, "Payment status unknown " + paymentIntent.status)

                            }
                        }
                    }

                    override fun onError(e: Exception) {
                        Log.e(TAG, "Payment Error " + e.localizedMessage)
                    }
                })
        }
    }

    private fun googlepayPayment() {

        val uri = Uri.parse("upi://pay").buildUpon()
            .appendQueryParameter("pa", "11111")
            .appendQueryParameter("pn", "Kelis Suthar") //.appendQueryParameter("mc", "")
            //.appendQueryParameter("tid", "02125412")
            //.appendQueryParameter("tr", "25584584")
            .appendQueryParameter("tn", "Useing Foe Test")
            .appendQueryParameter("am", "100")
            .appendQueryParameter("cu", "INR") //.appendQueryParameter("refUrl", "blueapp")
            .build()
        val upiPayIntent = Intent(Intent.ACTION_VIEW)
        upiPayIntent.data = uri
        val chooser = Intent.createChooser(upiPayIntent, "Pay with")
        if (null != chooser.resolveActivity(packageManager)) {
            startActivityForResult(chooser, UPI_PAYMENT)
        } else {
            Toast.makeText(
                this@PaymentGetWays,
                "No UPI app found, please install one to continue",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    //Google pay
    private fun gatewayTokenizationSpecification(): JSONObject {
        return JSONObject().apply {
            put("type", "PAYMENT_GATEWAY")
            put("parameters", JSONObject(mapOf(
                "gateway" to "example",
                "gatewayMerchantId" to "exampleGatewayMerchantId")))
        }
    }
    private fun getTransactionInfo(price: String): JSONObject {
        return JSONObject().apply {
            put("totalPrice", price)
            put("totalPriceStatus", "FINAL")
            put("countryCode", "IN")
            put("currencyCode", "INR")
        }
    }

    private fun rozorPayment() {

        val checkout = Checkout()

        checkout.setKeyID("rzp_test_rcZoRVgnQJ5BOh")//key From rozaro pay dashbord(key id)
        checkout.setImage(R.mipmap.ic_launcher)

        val jObject = JSONObject()
        try {

            jObject.put("name", "Kelis Suthar Test")
            jObject.put("description", "Test payment")
            jObject.put("theme.color", "")
            jObject.put("currency", "INR")
            jObject.put("amount", "100.00")
            jObject.put("prefill.contact", "121212121212")
            jObject.put("prefill.email", "kelis@gmail.com")

            checkout.open(this@PaymentGetWays, jObject)

            //Other Types
//            val co = Checkout()
//
//            try {
//                val options = JSONObject()
//                options.put("name","Razorpay Corp")
//                options.put("description","Demoing Charges")
//                //You can omit the image option to fetch the image from dashboard
//                options.put("image","https://s3.amazonaws.com/rzp-mobile/images/rzp.png")
//                options.put("theme.color", "#3399cc");
//                options.put("currency","INR");
//                options.put("order_id", "order_DBJOWzybf0sJbb");
//                options.put("amount","50000")//pass amount in currency subunits
//
//                val retryObj = new JSONObject();
//                retryObj.put("enabled", true);
//                retryObj.put("max_count", 4);
//                options.put("retry", retryObj);
//
//                val prefill = JSONObject()
//                prefill.put("email","gaurav.kumar@example.com")
//                prefill.put("contact","9876543210")
//
//                options.put("prefill",prefill)
//                co.open(activity,options)
//            }catch (e: Exception){
//                Toast.makeText(activity,"Error in payment: "+ e.message,Toast.LENGTH_LONG).show()
//                e.printStackTrace()
//            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    private fun stripPayment() {
        Toast.makeText(this, "Strip Payment", Toast.LENGTH_SHORT).show()
//        val requestOptions: RequestOptions = RequestOptionsBuilder()
//            .setStripeVersion("{{API_VERSION}}")
//            .build()
//        val options: MutableMap<String, Any> = HashMap()
//        options["customer"] = "{{CUSTOMER_ID}}"
//        val key: EphemeralKey = create(options, requestOptions)
//        return key.getRawJson()
        initializeCustomerSession(this, "")
        paymentSession = PaymentSession(
            this,
            createPaymentSessionConfig()
        )

        paymentSession?.init(createPaymentSessionListener())

        PaymentMethodsActivityStarter(this)
            .startForResult(
                PaymentMethodsActivityStarter.Args.Builder()
                    .build()
            )

    }

    //Strip
    private fun confirmPayment(
        clientSecret: String,
        paymentMethodId: String
    ) {

        stripe = Stripe(
            applicationContext,
            PaymentConfiguration.getInstance(this).publishableKey
        )

        stripe?.confirmPayment(
            this,
            ConfirmPaymentIntentParams.createWithPaymentMethodId(
                paymentMethodId,
                clientSecret
            )
        )

    }

    //Strip
    private fun createPaymentSessionConfig(): PaymentSessionConfig {
        return PaymentSessionConfig.Builder()
            .setShippingMethodsRequired(false)
            .setShippingInfoRequired(false)
            .build()
    }

    //Strip
    private fun createPaymentSessionListener(): PaymentSession.PaymentSessionListener {
        return object : PaymentSession.PaymentSessionListener {
            override fun onCommunicatingStateChanged(isCommunicating: Boolean) {
                Log.e(TAG, "onCommunicatingStateChanged " + isCommunicating)
            }

            override fun onError(errorCode: Int, errorMessage: String) {
                Log.e(TAG, "onError " + errorCode + "  " + errorMessage)
            }

            override fun onPaymentSessionDataChanged(data: PaymentSessionData) {
                Log.e(
                    TAG,
                    "onPaymentSessionDataChanged " + data.isPaymentReadyToCharge + "  " + data
                )

                if (data.isPaymentReadyToCharge) {
                    confirmPayment(
                        clientSecret, paymentMethodId
                    )
                }
            }
        }
    }

    private fun upiPayment() {

        val upiPayment: EasyUpiPayment = EasyUpiPayment.Builder().with(this)
            .setPayeeVpa("TESts@upi")
            .setPayeeName("TEst Person")
            .setTransactionId(transcId)
            .setTransactionRefId(transcId)
            .setDescription("Dummy Transactions")
            .setAmount("90.00")
            .build()
        upiPayment.setPaymentStatusListener(object : PaymentStatusListener {
            override fun onTransactionCompleted(transactionDetails: TransactionDetails?) {
                println("||||||||||||||||||||||||||||||||||$transactionDetails")
                upiPayment.detachListener()
            }

            override fun onTransactionSuccess() {
                Toast.makeText(this@PaymentGetWays, "Success", Toast.LENGTH_SHORT).show()
            }

            override fun onTransactionSubmitted() {

            }

            override fun onTransactionFailed() {
                upiPayment.detachListener()
            }

            override fun onTransactionCancelled() {

            }

            override fun onAppNotFound() {
                upiPayment.detachListener()
            }

        })
        upiPayment.startPayment()
    }

    //Rozaro Pay
    override fun onPaymentSuccess(p0: String?) {
        Toast.makeText(this, "Payment is successful : " + p0, Toast.LENGTH_SHORT).show();
    }

    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment Failed due to error : " + p1, Toast.LENGTH_SHORT).show();
    }

    override fun onPaymentSuccess(p0: String?, p1: PaymentData?) {
        Toast.makeText(this, "Payment is successful for data : " + p0, Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Payment is successful for data: " + p1, Toast.LENGTH_SHORT).show();
    }

    override fun onPaymentError(p0: Int, p1: String?, p2: PaymentData?) {
        Toast.makeText(this, "Payment Failed due to error for data: " + p1, Toast.LENGTH_SHORT)
            .show();
        Toast.makeText(this, "Payment Failed due to error for data: " + p2, Toast.LENGTH_SHORT)
            .show();
    }

    //for in app purchase
    override fun onPurchasesUpdated(p0: BillingResult, p1: MutableList<Purchase>?) {
        if (p0.responseCode == BillingClient.BillingResponseCode.OK && p1 != null) {
            p1.forEach {
                Log.v("acknowledgePurchase", p1.toString())

            }
        }
        Toast.makeText(this, p1.toString(), Toast.LENGTH_SHORT).show()

    }

    //For Strip
    override fun createEphemeralKey(
        apiVersion: String,
        keyUpdateListener: EphemeralKeyUpdateListener
    ) {
//        compositeDisposable.add(
//            backendApi.createEphemeralKey(hashMapOf("api_version" to apiVersion))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe { responseBody ->
//                    try {
//                        val ephemeralKeyJson = responseBody.string()
//                        keyUpdateListener.onKeyUpdate(ephemeralKeyJson)
//                    } catch (e: IOException) {
//                        keyUpdateListener
//                            .onKeyUpdateFailure(0, e.message ?: "")
//                    }
//                })
    }

    fun initializeCustomerSession(context: Context?, EphemeralKeyRawJson: String) {

        context?.let { context ->

            CustomerSession.initCustomerSession(
                context,
                StripeDemoEphemeralKeyProvider(EphemeralKeyRawJson)
            )
        }
    }


    class StripeDemoEphemeralKeyProvider(private val ephemeralKeyRawJson: String) :
        EphemeralKeyProvider {

        override fun createEphemeralKey(
            @Size(min = 4) apiVersion: String,
            keyUpdateListener: EphemeralKeyUpdateListener
        ) {

            keyUpdateListener.onKeyUpdate(ephemeralKeyRawJson)

        }
    }
}