package dev.olog.data.service

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import dev.olog.core.dagger.ApplicationContext
import dev.olog.core.gateway.AlarmService
import javax.inject.Inject

internal class AlarmServiceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : AlarmService {

    companion object {
        const val ACTION_STOP_SLEEP_END = "action.stop_sleep_timer"
    }

    private val manager: AlarmManager by lazy {
        context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    }

    override fun set(nextSleep: Long) {
        val intent = stopMusicServiceIntent(context)
        manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, nextSleep, intent)
    }

    override fun resetTimer() {
        manager.cancel(stopMusicServiceIntent(context))
    }

    private fun stopMusicServiceIntent(context: Context): PendingIntent {
        val intent = Intent(context, Class.forName("dev.olog.service.music.MusicService"))
        intent.action = ACTION_STOP_SLEEP_END
        return intent.asServicePendingIntent(context, PendingIntent.FLAG_CANCEL_CURRENT)
    }

    private fun Intent.asServicePendingIntent(
        context: Context,
        flag: Int = PendingIntent.FLAG_CANCEL_CURRENT
    ): PendingIntent {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return PendingIntent.getForegroundService(context, 0, this, flag)
        }
        return PendingIntent.getService(context, 0, this, flag)
    }

}