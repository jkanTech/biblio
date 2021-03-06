package com.jkantech.biblioispt.ui.utils

import android.app.Application
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class Api : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    private val requestQueue: RequestQueue? = null
        get() {
            if (field == null) {
                return Volley.newRequestQueue(applicationContext)
            }
            return field
        }

    fun <T> addToRequestQueue(request: Request<T>) {
        request.tag = TAG
        requestQueue?.add(request)
    }

    companion object {
        private val TAG = Api::class.java.simpleName
        @get:Synchronized var instance: Api? = null
            private set
    }
}