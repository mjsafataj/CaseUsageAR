package ir.topbest.objectdetection.di

import ir.topbest.objectdetection.data.remote.ARApi
import ir.topbest.objectdetection.data.repository.ARRepositoryImpl
import ir.topbest.objectdetection.domain.repository.ARRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DependencyInjectionModule {

    @Provides
    @Singleton
    fun providesApi(): ARApi {
        return Retrofit.Builder()
            .baseUrl("https://mman7798.pythonanywhere.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ARApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(api: ARApi): ARRepository {
        return ARRepositoryImpl(api)
    }
}