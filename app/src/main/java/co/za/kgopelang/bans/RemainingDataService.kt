package co.za.kgopelang.bans

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import co.za.kgopelang.bans.data.ConnectionInfo
import co.za.kgopelang.bans.service.LiquidService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


@AndroidEntryPoint
class RemainingDataService : Service() {


    @Inject lateinit var liquidService: LiquidService
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.IO + job)


    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        when(intent?.action)
        {

            Actions.START.toString() -> start()
            Actions.STOP.toString() -> stopSelf()

        }


        return super.onStartCommand(intent, flags, startId)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun start() {

        val context = this

        scope.launch {

            var connectionInfo: ConnectionInfo? = null

            try {
                connectionInfo = liquidService.GetConnectedServiceDetails()
            } catch (e: Exception) {
                e.printStackTrace()
            }



            withContext(Dispatchers.Main) {

                val notification = NotificationCompat.Builder(context, "DataUsage")
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentTitle("Data Usage remaining")
                    .setContentText(String.format("%.1f GB", connectionInfo?.result?.service?.TopUp))
                    .build()

                startForeground(1, notification)
            }


        }


    }


    enum class Actions {
        START, STOP
    }


}

