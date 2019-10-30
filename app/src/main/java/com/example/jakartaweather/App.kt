package com.example.jakartaweather

import android.app.Application
import android.content.Context
import java.lang.ref.WeakReference


class App : Application() {

    companion object{
        private lateinit var mContext:WeakReference<Context>
        private fun setContext(con: Context) {
            mContext = WeakReference(con)
        }

        @Synchronized
        fun getContext(): Context? {
            return mContext.get()
        }
    }

    override fun onCreate() {
        super.onCreate()
        setContext(this)
    }
}
