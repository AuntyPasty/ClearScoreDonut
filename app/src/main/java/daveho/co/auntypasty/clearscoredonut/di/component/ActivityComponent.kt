package daveho.co.auntypasty.clearscoredonut.di.component

import dagger.Component
import daveho.co.auntypasty.clearscoredonut.di.module.ActivityModule
import daveho.co.auntypasty.clearscoredonut.ui.main.MainActivity

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}