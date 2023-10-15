package co.za.kgopelang.bans.ModelView

import androidx.lifecycle.ViewModel
import co.za.kgopelang.bans.data.ConnectionInfo
import co.za.kgopelang.bans.service.LiquidService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (
    private val liquidService: LiquidService
) : ViewModel() {


    val connectionInfo = flow<ConnectionInfo> {

        val connectionInfo = liquidService.GetConnectedServiceDetails()
        emit(connectionInfo)
    }




}