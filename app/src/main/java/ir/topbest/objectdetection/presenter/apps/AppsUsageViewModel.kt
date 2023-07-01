package ir.topbest.objectdetection.presenter.apps

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.topbest.objectdetection.domain.models.AppUsageModel
import ir.topbest.objectdetection.domain.usecases.GetAppsUsageUseCase
import ir.topbest.objectdetection.utils.Res
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppsUsageViewModel @Inject constructor(
    private val getAppsUsageUseCase: GetAppsUsageUseCase,
) : ViewModel() {

    private val _liveData = MutableLiveData<List<AppUsageModel>>()
    val liveData: LiveData<List<AppUsageModel>> get() = _liveData

    private var job: Job? = null

    fun getData(identifier: String) {
        job?.cancel()
        job =  viewModelScope.launch {
            while (true) {
                getAppsUsageUseCase(identifier).collect {
                    when (it) {
                        is Res.Loading -> {}
                        is Res.Success -> {
                            it.data.let { list ->
                                _liveData.value = list
                            }
                        }
                        is Res.Failure -> {}
                    }
                }
                delay(5000)
            }
        }
    }
}