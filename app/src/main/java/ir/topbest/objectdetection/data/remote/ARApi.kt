package ir.topbest.objectdetection.data.remote

import ir.topbest.objectdetection.data.remote.dto.AppUsageDTO
import ir.topbest.objectdetection.data.remote.dto.CaseDetailDTO
import ir.topbest.objectdetection.data.remote.dto.CaseUsageDTO
import ir.topbest.objectdetection.domain.models.AppUsageModel
import ir.topbest.objectdetection.domain.models.CaseDetailsModel
import ir.topbest.objectdetection.domain.models.CaseUsageModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ARApi {

    @GET("/case/{id}")
    suspend fun getCaseDetails(@Path(value = "id") id:String): CaseDetailDTO

    @GET("/usage/{id}")
    suspend fun getCaseUsage(@Path(value = "id") id:String): CaseUsageDTO

    @GET("/app/{id}")
    suspend fun getAppUsages(@Path(value = "id") id:String): List<AppUsageDTO>
}