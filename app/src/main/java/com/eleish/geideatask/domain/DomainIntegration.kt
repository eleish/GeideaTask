package com.eleish.geideatask.domain

import android.app.Application
import android.content.Context

object DomainIntegration {
    private lateinit var application: Application

    fun with(application: Application) {
        this.application = application
    }

    fun getApplication(): Application {
        return application
    }

    fun getApplicationContext(): Context {
        return application.applicationContext
    }
}