package com.missclick.easyref

import android.content.Context
import android.webkit.ValueCallback
import com.android.installreferrer.api.InstallReferrerClient
import com.android.installreferrer.api.InstallReferrerStateListener
import java.net.URLEncoder

class EasyRef(val context: Context) {

    fun start(callback: (String?) -> Unit){
        val installRef = InstallReferrerClient.newBuilder(context).build()
        installRef.startConnection(object :
            InstallReferrerStateListener {

            override fun onInstallReferrerSetupFinished(responseCode: Int) {
                val ref = if(responseCode == 0) URLEncoder.encode(installRef.installReferrer.installReferrer, "UTF-8") else null
                installRef.endConnection()
                callback.invoke(ref)
            }

            override fun onInstallReferrerServiceDisconnected() {
            }
        })
    }

}