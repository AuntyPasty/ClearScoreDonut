package daveho.co.auntypasty.clearscoredonut.api

import dagger.Module
import dagger.Provides
import dagger.Reusable
import daveho.co.auntypasty.clearscoredonut.util.Constants
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApiModule {

    @Provides
    @Reusable
    internal fun providesServerInterface(retrofit: Retrofit): ServerInterface =
        retrofit.create(ServerInterface::class.java)

    @Provides
    internal fun providesRetrofit(): Retrofit = with(Retrofit.Builder()) {
        addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        addConverterFactory(GsonConverterFactory.create())
        baseUrl(Constants.BASE_URL)
        build()
    }
}