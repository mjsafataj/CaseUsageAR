package ir.topbest.objectdetection.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ir.topbest.objectdetection.domain.usecases.GetCaseDetailUseCase
import ir.topbest.objectdetection.utils.Res
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.topbest.objectdetection.domain.models.AppUsageModel
import ir.topbest.objectdetection.domain.models.CaseDetailModel
import ir.topbest.objectdetection.domain.models.CaseUsageModel
import ir.topbest.objectdetection.domain.usecases.GetCaseUsageUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val getCaseDetailUseCase: GetCaseDetailUseCase,
    private val getCaseUsageUseCase: GetCaseUsageUseCase,
) : ViewModel() {

    private var job1: Job? = null
    private var job2: Job? = null
    private var job3: Job? = null

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    private val _caseDetail = MutableLiveData<CaseDetailModel>()
    val caseDetails: LiveData<CaseDetailModel> get() = _caseDetail

    private val _caseUsage = MutableLiveData<CaseUsageModel>()
    val caseUsage: LiveData<CaseUsageModel> get() = _caseUsage

    private val _appsUsage = MutableLiveData<List<AppUsageModel>>()
    val appsUsage: LiveData<List<AppUsageModel>> get() = _appsUsage


    var identifier: String = ""
        set(value) {
            field = value
            getData()
        }

    private fun getData() {
        job1?.cancel()
        job2?.cancel()

        job1 = getCaseDetailUseCase(identifier).onEach {
            when (it) {
                is Res.Loading -> {
                    _loading.value = it.isLoading
                }
                is Res.Success -> {
                    it.data.let { data ->
                        _caseDetail.value = data
                    }
                }
                is Res.Failure -> {
                    _error.value = it.error
                }
            }
        }.launchIn(viewModelScope)

        job2 = viewModelScope.launch {
            while (true) {
                getCaseUsageUseCase(identifier).collect() {
                    when (it) {
                        is Res.Loading -> {
                            _loading.value = it.isLoading
                        }
                        is Res.Success -> {
                            it.data.let { data ->
                                _caseUsage.value = data
                            }
                        }
                        is Res.Failure -> {
                            _error.value = it.error
                        }
                    }
                }
                delay(5000)
            }
        }
    }
}