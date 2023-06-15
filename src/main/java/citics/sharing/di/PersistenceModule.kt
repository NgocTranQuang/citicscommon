package citics.sharing.di

import android.content.Context
import citics.sharing.data.datasource.local.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ChinhQT on 25/09/2022.
 */
@InstallIn(SingletonComponent::class)
@Module
object PersistenceModule {
    @Singleton
    @Provides
    fun providePreferenceManager(@ApplicationContext context: Context) = PreferenceManager(context)
}