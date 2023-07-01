package ir.topbest.objectdetection.utils

sealed class Res<out T> {
    data class Loading(val isLoading: Boolean) : Res<Nothing>()
    data class Success<T>(val data: T) : Res<T>()
    data class Failure(val error: String) : Res<Nothing>()
}
