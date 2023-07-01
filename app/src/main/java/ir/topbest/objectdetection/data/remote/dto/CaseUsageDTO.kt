package ir.topbest.objectdetection.data.remote.dto

data class CaseUsageDTO(
    val cpu_temperature: Int,
    val cpu_usage: Int,
    val gpu_memory_usage: Int,
    val gpu_temperature: Int,
    val gpu_usage: Int,
    val memory_usage: Int
)