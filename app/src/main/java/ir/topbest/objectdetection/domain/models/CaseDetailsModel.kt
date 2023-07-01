package ir.topbest.objectdetection.domain.models

data class CaseDetailsModel(
    val name: String,
    val cpuModel: String,
    val gpuModel: String,
    val cpuTemperature: Int,
    val gpuTemperature: Int,
    val cpuUsage: Int,
    val gpuUsage: Int,
    val memoryCapacity: Float,
    val memoryCurrent: Float,
    val gpuMemoryCapacity: Float,
    val gMemoryCurrent: Float,
) {

    val cpuTemperatureString get() = "CPU ${cpuTemperature}°"
    val cpuUsageString get() = "${cpuUsage}%"

    val gpuTemperatureString get() = "GPU ${gpuTemperature}°"
    val gpuUsageString get() = "${gpuUsage}%"

    val memoryUsage get() = ((memoryCurrent*100)/memoryCapacity).toInt()
    val memoryUsageString get() = "$memoryCurrent/$memoryCapacity GB"

    val gMemoryUsage get() = ((gMemoryCurrent*100)/gpuMemoryCapacity).toInt()
    val gMemoryUsageString get() = "$gMemoryCurrent/$gpuMemoryCapacity GB"


//
//    val memoryMaxString get() = "$memoryMax GB"
//    val memoryCurrentString get() = "$memoryCurrent GB"
//
//    val gMemoryMaxString get() = "$gMemoryMax GB"
//    val gMemoryCurrentString get() = "$gMemoryCurrent GB"

}
