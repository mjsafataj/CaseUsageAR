package ir.topbest.objectdetection.domain.repository

import ir.topbest.objectdetection.domain.models.*

interface ARRepository {

    suspend fun getCaseDetails(identifier: String): CaseDetailModel

    suspend fun getCaseUsage(identifier: String): CaseUsageModel

    suspend fun getAppUsage(identifier: String): List<AppUsageModel>

}