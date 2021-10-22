package daveho.co.auntypasty.clearscoredonut.di.component

import dagger.Component
import daveho.co.auntypasty.clearscoredonut.BaseApp
import daveho.co.auntypasty.clearscoredonut.di.module.ApplicationModule

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: BaseApp)
}