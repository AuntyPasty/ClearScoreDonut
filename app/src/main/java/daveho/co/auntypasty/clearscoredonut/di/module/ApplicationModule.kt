package daveho.co.auntypasty.clearscoredonut.di.module

import android.app.Application
import android.content.Context
import android.content.res.Resources
import dagger.Module
import dagger.Provides
import daveho.co.auntypasty.clearscoredonut.BaseApp
import daveho.co.auntypasty.clearscoredonut.di.scope.PerApplication
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }


}