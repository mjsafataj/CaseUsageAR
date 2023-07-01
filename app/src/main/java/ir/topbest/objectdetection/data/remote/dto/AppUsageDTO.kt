package ir.topbest.objectdetection.data.remote.dto

data class AppUsageDTO(
    val app_name: String,
    val cpu_usage: Int,
    val disk_usage: Int,
    val gpu_usage: Int,
    val memory_usage: Int,
    val network_usage: Int,
    val time_stamp: Long
)