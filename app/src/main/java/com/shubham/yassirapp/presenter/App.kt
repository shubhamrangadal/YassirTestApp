package com.shubham.yassirapp.presenter

import android.app.Application
import com.shubham.yassirapp.BuildConfig
import com.shubham.yassirapp.data.di.NetModule
import com.shubham.yassirapp.data.di.RemoteDataModule
import com.shubham.yassirapp.presenter.di.*

class App: Application(), Injector {


    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
            .remoteDataModule(RemoteDataModule()).build()


    }

    override fun createMovieSubComponent() : MovieSubComponent {
        return appComponent.movieSubComponent().create()
    }

}