package com.eleish.geideatask.app.features.userdetails

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder
import kotlin.math.roundToLong

class TimerService : Service() {

    private val binder: IBinder = TimerServiceBinder()

    private var onTimerTick: ((remaining: Long) -> Unit)? = null
    private var onTimerCompleted: (() -> Unit)? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        object : CountDownTimer(5_000, 1_000) {
            override fun onTick(p0: Long) {
                onTimerTick?.invoke((p0 / 1000.0).roundToLong())
            }

            override fun onFinish() {
                onTimerCompleted?.invoke()
            }

        }.start()

        return super.onStartCommand(intent, flags, startId)
    }

    fun setTimerListener(onTimerTick: (remaining: Long) -> Unit, onTimerCompleted: () -> Unit) {
        this.onTimerTick = onTimerTick
        this.onTimerCompleted = onTimerCompleted
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    inner class TimerServiceBinder : Binder() {
        val service: TimerService
            get() = this@TimerService
    }
}