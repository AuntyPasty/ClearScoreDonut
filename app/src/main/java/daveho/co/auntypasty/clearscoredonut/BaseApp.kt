package daveho.co.auntypasty.clearscoredonut

import android.app.Application
import daveho.co.auntypasty.clearscoredonut.di.component.ApplicationComponent
import daveho.co.auntypasty.clearscoredonut.di.component.DaggerApplicationComponent
import daveho.co.auntypasty.clearscoredonut.di.module.ApplicationModule

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

//        if (BuildConfig.DEBUG) {
//            // Do something if it's a debug build
//        }
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}