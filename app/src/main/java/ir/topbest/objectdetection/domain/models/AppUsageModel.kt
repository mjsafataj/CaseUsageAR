package ir.topbest.objectdetection.domain.models

data class AppUsageModel(
    val appName: String,
    val timeStamp: Long,
    val cpuUsage: Int,
    val gpuUsage: Int,
    val memoryUsage: Int,
    val diskUsage: Int,
    val networkUsage: Int,
){

    val cpuUsageString get() = "${cpuUsage}%"

    val gpuUsageString get() = "${gpuUsage}%"

    val memoryUsageString get() = "${memoryUsage.memoryUsageToString()} MB"

    val diskUsageString get() = "${diskUsage.intToFloatString()} MB/s"
    val networkUsageString get() = "${networkUsage.intToFloatString()} MB/s"


    private fun Int.intToFloatString(): String {
        return when{
            this>1000->String.format("%.1f", this/1000F)
            this>100->String.format("%.2f", this/1000F)
            else ->""
        }
    }

    private fun Int.memoryUsageToString(): String {
        return when{
            this>1000->String.format("%.3f", this/1000F).replace(".",",")
            this>100->String.format("%.2f", this/1000F).replace(".",",")
            else ->""
        }
    }
}