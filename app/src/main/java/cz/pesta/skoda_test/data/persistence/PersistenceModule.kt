package cz.pesta.skoda_test.data.persistence

import android.content.Context
import androidx.room.Room
import cz.pesta.skoda_test.data.persistence.dao.CarDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class PersistenceModule {
    @Provides
    @Singleton
    internal fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            // Should use constant or get from config
            "skoda_test_database"
        )
            // Fallback should never happen in production apps, migrations should be properly handled
            .fallbackToDestructiveMigration(true)
            .build()

    @Provides
    @Singleton
    internal fun provideCarDao(appDatabase: AppDatabase): CarDao =
        appDatabase.carDao()
}