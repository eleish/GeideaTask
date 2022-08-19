package com.eleish.geideatask.app.features.userdetails

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.CountDownTimer
import android.os.IBinder

class TimerService : Service() {

    private val binder: IBinder = TimerServiceBinder()

    private var timer: CountDownTimer? = null

    private var onTimerTick: ((remaining: Long) -> Unit)? = null
    private var onTimerCompleted: (() -> Unit)? = null

    fun setTimerListener(
        onTimerTick: (remainingMillis: Long) -> Unit,
        onTimerCompleted: () -> Unit,
    ) {
        this.onTimerTick = onTimerTick
        this.onTimerCompleted = onTimerCompleted
    }

    fun startTimer(millis: Long) {
        timer?.cancel()
        timer = object : CountDownTimer(millis, 1_000) {
            override fun onTick(p0: Long) {
                onTimerTick?.invoke(p0)
            }

            override fun onFinish() {
                onTimerCompleted?.invoke()
            }

        }.start()
    }

    override fun onBind(p0: Intent?): IBinder {
        return binder
    }

    inner class TimerServiceBinder : Binder() {
        val service: TimerService
            get() = this@TimerService
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
        timer = null
    }
}