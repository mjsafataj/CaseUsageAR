package ir.topbest.objectdetection.domain.models

data class CaseDetailModel(
    val name: String,
    val cpuModel: String,
    val gpuModel: String,
    val memoryCapacity: Int,
    val gpuMemoryCapacity: Int,
){

    val memoryMaxString get() = "${memoryCapacity.intToFloatString()} GB"
    val gpuMemoryMaxString get() = "${gpuMemoryCapacity.intToFloatString()} GB"


    fun Int.intToFloatString(): String {
        return when{
            this>1000->String.format("%.1f", this/1000F)
            this>100->String.format("%.2f", this/1000F)
            else ->""
        }
    }
}