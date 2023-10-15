package co.za.kgopelang.bans.di

import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.rememberTextMeasurer
import co.za.kgopelang.bans.service.LiquidService
import co.za.kgopelang.bans.service.LiquidServiceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DataConversion
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.gson.gson
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLiquidAPI(): HttpClient {
        return HttpClient(
            Android
        ) {
            install(Logging) {
                level = LogLevel.ALL
            }
            install(ContentNegotiation) {
                gson() {
                    serializeNulls()
                }
            }


        }
    }

    @Provides
    @Singleton
    fun provideLiquidService(client: HttpClient): LiquidService {

        return LiquidServiceImpl(client)

    }


}