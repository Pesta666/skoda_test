package cz.pesta.skoda_test.data.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            // Should be externalized to BuildConfig for prod; keeping inline for now to match ClientFactory
            .baseUrl("https://beegreen-hackathon.web.app/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}
