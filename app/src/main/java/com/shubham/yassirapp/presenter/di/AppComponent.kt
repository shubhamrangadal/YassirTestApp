package com.shubham.yassirapp.presenter.di

import com.shubham.yassirapp.data.di.NetModule
import com.shubham.yassirapp.data.di.RemoteDataModule
import com.shubham.yassirapp.data.di.RepositoryModule
import com.shubham.yassirapp.data.di.UseCaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, UseCaseModule::class, RepositoryModule::class, RemoteDataModule::class,])
interface AppComponent{

    fun movieSubComponent():MovieSubComponent.Factory

}