package ir.topbest.objectdetection.data.remote.dto

data class CaseDetailDTO(
    val cpu_model: String,
    val gpu_memory_capacity: Int,
    val gpu_model: String,
    val memory_capacity: Int,
    val name: String
)