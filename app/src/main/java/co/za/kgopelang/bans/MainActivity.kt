package co.za.kgopelang.bans

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import co.za.kgopelang.bans.ModelView.MainViewModel
import co.za.kgopelang.bans.data.ConnectionInfo
import co.za.kgopelang.bans.ui.theme.BansTheme
import com.google.accompanist.permissions.rememberPermissionState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.descriptors.SerialDescriptor
import java.security.Permission

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            BansTheme {

                val liquidViewModel = hiltViewModel<MainViewModel>()
                val count = liquidViewModel.connectionInfo.collectAsState(initial = null)

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    if (count.value != null)
                        MainApp(count?.value)
                    else
                        CircularProgressIndicator()

                }
            }
        }
    }
}

@Composable
@Preview
fun MainApp(connectionInfo: ConnectionInfo? = ConnectionInfo())
{


    Column (modifier = Modifier
        .fillMaxSize()
        .padding(12.dp)){

        val service = connectionInfo?.result?.service

        ServiceName(connectionInfo)
        Spacer(modifier = Modifier.height(12.dp))
        litID(connectionInfo)
        Spacer(modifier = Modifier.height(12.dp))
        StringInfo("Top UP", service?.TopUp)
        Spacer(modifier = Modifier.height(12.dp))
        StringInfo("Usage", service?.Usage)
        Spacer(modifier = Modifier.height(12.dp))
        IpAddress(connectionInfo)
    }

}

@Composable
fun StringInfo(name: String, value: Double?) {

    val context = LocalContext.current


    Row {
        ElevatedCard {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .padding(5.dp)
                ,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .padding(10.dp),
                ) {

                    Column(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(.5f),

                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(text = name, fontWeight = FontWeight.Bold,color = Color.Black)
                    }

                    Column (
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(text = String.format("%.1f GB",value), fontSize = 18.sp, color = Color.Black)
                    }

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(80.dp)
                        .padding(10.dp),
                ) {
                    Button(
                        onClick = {
                           Intent(context, RemainingDataService::class.java).also {
                               it.action = RemainingDataService.Actions.START.toString()
                               context.startService(it)
                           }

                        },
                        modifier = Modifier.fillMaxWidth(),
                        content = {
                            Text(text = "Track")
                        }
                        )
                }

            }
        }

    }

}

@Composable
@Preview
fun litID(connectionInfo: ConnectionInfo? = ConnectionInfo()) {

    val litId = connectionInfo?.result?.service?.LitID
    val clippy = LocalClipboardManager.current

    Row {
        ElevatedCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.5f),

                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = String.format("LIT-%s", litId), fontWeight = FontWeight.Bold,color = Color.Black)
                }

                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = {
                        clippy.setText(AnnotatedString(String.format("LIT-%s", litId)))
                    }) {
                        Text(text = "Copy")
                    }
                }


            }
        }

    }

}

@Composable
@Preview
fun ServiceName(connectionInfo: ConnectionInfo? = ConnectionInfo()) {

    val title = connectionInfo?.result?.service?.Title
    val clippy = LocalClipboardManager.current

    Row {
        ElevatedCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.5f),

                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "Service Name", fontWeight = FontWeight.Bold,color = Color.Black)
                }

                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = String.format("%s", title))
                }


            }
        }

    }

}


@Composable
@Preview
fun IpAddress(connectionInfo: ConnectionInfo? = ConnectionInfo()) {

    val litId = connectionInfo?.result?.service?.IPAddress
    val clippy = LocalClipboardManager.current

    println("Connnection: " + connectionInfo)

    Row {
        ElevatedCard {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(10.dp),
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(.5f),

                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = "IP Address", fontWeight = FontWeight.Bold,color = Color.Black)
                }

                Column (
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                    ,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = String.format("%s", litId))
                }


            }
        }

    }

}
