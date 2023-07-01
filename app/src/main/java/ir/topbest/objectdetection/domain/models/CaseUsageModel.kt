package ir.topbest.objectdetection.domain.models

data class CaseUsageModel(
    val cpuUsage: Int,
    val gpuUsage: Int,
    val cpuTemperature: Int,
    val gpuTemperature: Int,
    val memoryUsage: Int,
    val gpuMemoryUsage: Int,
){

    val cpuUsageString get() = "${cpuUsage}%"
    val cpuTemperatureString get() = "CPU ${cpuTemperature}°"

    val gpuUsageString get() = "${gpuUsage}%"
    val gpuTemperatureString get() = "GPU ${gpuTemperature}°"

//    val memoryUsage get() = ((memoryCurrent*100)/memoryCapacity).toInt()
    val memoryUsageString get() = "${memoryUsage.intToFloatString()}/"

//    val gMemoryUsage get() = ((gMemoryCurrent*100)/gpuMemoryCapacity).toInt()
    val gpuMemoryUsageString get() = "${gpuMemoryUsage.intToFloatString()}/"


    fun Int.intToFloatString(): String {
        return when{
            this>1000->String.format("%.1f", this/1000F)
            this>100->String.format("%.2f", this/1000F)
            else ->""
        }
    }

}