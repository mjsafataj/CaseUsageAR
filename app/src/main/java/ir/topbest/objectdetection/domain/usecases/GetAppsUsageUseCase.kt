package ir.topbest.objectdetection.domain.usecases

import ir.topbest.objectdetection.domain.models.AppUsageModel
import ir.topbest.objectdetection.domain.models.CaseDetailModel
import ir.topbest.objectdetection.domain.repository.ARRepository
import ir.topbest.objectdetection.utils.Res
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetAppsUsageUseCase @Inject constructor(
    private val repository: ARRepository,
) {

    operator fun invoke(string: String): Flow<Res<List<AppUsageModel>>> = flow {
        emit(Res.Loading(true))
        try {
            val data = repository.getAppUsage(string)
            emit(Res.Success(data))
        } catch (e: HttpException) {
            emit(Res.Failure("An error occurred"))
        } catch (e: IOException) {
            emit(Res.Failure("Check internet connection"))
        } catch (e:Exception) {
            emit(Res.Failure(e.message.toString()))
        } finally {
            emit(Res.Loading(false))
        }
    }
}