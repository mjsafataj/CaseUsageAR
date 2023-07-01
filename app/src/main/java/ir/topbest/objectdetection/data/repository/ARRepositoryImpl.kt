package ir.topbest.objectdetection.data.repository

import ir.topbest.objectdetection.data.remote.ARApi
import ir.topbest.objectdetection.domain.models.*
import ir.topbest.objectdetection.domain.repository.ARRepository
import ir.topbest.objectdetection.domain.models.CaseUsageModel as CaseUsageModel1

class ARRepositoryImpl(
    private val api: ARApi,
) : ARRepository {

    override suspend fun getCaseDetails(
        identifier: String,
    ): CaseDetailModel {
        val data = api.getCaseDetails(identifier)
        return CaseDetailModel(
            name = data.name,
            cpuModel = data.cpu_model,
            gpuModel = data.gpu_model,
            memoryCapacity = data.memory_capacity,
            gpuMemoryCapacity = data.gpu_memory_capacity,
        )
    }

    override suspend fun getCaseUsage(identifier: String): CaseUsageModel1 {
        val data = api.getCaseUsage(identifier)
        return CaseUsageModel1(
            cpuUsage = data.cpu_usage,
            gpuUsage = data.gpu_usage,
            cpuTemperature = data.cpu_temperature,
            gpuTemperature = data.gpu_temperature,
            memoryUsage = data.memory_usage,
            gpuMemoryUsage = data.gpu_memory_usage,
        )
    }

    override suspend fun getAppUsage(identifier: String): List<AppUsageModel> {
        val dto = api.getAppUsages(identifier)
        return dto.map { data ->
            AppUsageModel(
                appName = data.app_name,
                timeStamp = data.time_stamp,
                cpuUsage = data.cpu_usage,
                gpuUsage = data.gpu_usage,
                memoryUsage = data.memory_usage,
                diskUsage = data.disk_usage,
                networkUsage = data.network_usage,
            )
        }
    }
}