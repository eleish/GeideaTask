package com.eleish.geideatask

import android.app.Application
import com.eleish.geideatask.domain.DomainIntegration

class GeideaApp : Application() {
    override fun onCreate() {
        super.onCreate()
        DomainIntegration.with(this)
    }
}