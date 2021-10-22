package daveho.co.auntypasty.clearscoredonut.di.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import daveho.co.auntypasty.clearscoredonut.api.ApiModule

import daveho.co.auntypasty.clearscoredonut.ui.main.MainContract
import daveho.co.auntypasty.clearscoredonut.ui.main.PojoScoreResultPresenter
import daveho.co.auntypasty.clearscoredonut.usecase.ClearScoreDataFetcher
import daveho.co.auntypasty.clearscoredonut.usecase.PojoClearScoreDataFetcher


@Module(
    includes = [
        ApiModule::class
    ]
)
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providesClearScoreDataFetcher(implementation: PojoClearScoreDataFetcher): ClearScoreDataFetcher = implementation


    @Provides
    fun providesScoreResultPresenter(
        dataFetcher: ClearScoreDataFetcher
    ): MainContract.Presenter {
        return PojoScoreResultPresenter(dataFetcher)
    }


}