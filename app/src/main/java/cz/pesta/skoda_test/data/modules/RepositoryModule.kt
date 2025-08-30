package cz.pesta.skoda_test.data.modules

import cz.pesta.skoda_test.data.network.helpers.api
import cz.pesta.skoda_test.data.persistence.dao.CarDao
import cz.pesta.skoda_test.data.repository.CarDataRepository
import cz.pesta.skoda_test.data.repository.CarRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun provideCarRepository(retrofit: Retrofit, carDao: CarDao): CarRepository =
        CarDataRepository(retrofit.api(), carDao)

}