package app.fabianomello.calorietracker.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import app.fabianomello.core.data.preferences.DefaultPreferences
import app.fabianomello.core.domain.preferences.Preferences
import app.fabianomello.core.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSharedPreferences(app: Application): SharedPreferences =
        app.getSharedPreferences("shared_prefs", MODE_PRIVATE)

    @Provides
    @Singleton
    fun providePreferences(sharedPreferences: SharedPreferences): Preferences =
        DefaultPreferences(sharedPreferences)

    @Provides
    @Singleton
    fun providesFilterOutDigitsUseCase(): FilterOutDigitsUseCase =
        FilterOutDigitsUseCase()
}
