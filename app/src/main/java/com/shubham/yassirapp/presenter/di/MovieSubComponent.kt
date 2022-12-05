package com.shubham.yassirapp.presenter.di

import com.shubham.yassirapp.presenter.ui.moviedetails.MovieDetailsActivity
import com.shubham.yassirapp.presenter.ui.movielist.MovieListActivity
import dagger.Subcomponent

@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent{

    fun injectMovieList(movieListActivity : MovieListActivity)

    fun injectMovieDetails(movieDetailsActivity : MovieDetailsActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create():MovieSubComponent
    }

}