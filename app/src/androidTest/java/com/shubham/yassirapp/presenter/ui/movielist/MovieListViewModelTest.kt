package com.shubham.yassirapp.presenter.ui.movielist

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shubham.yassirapp.domain.models.MovieList
import com.shubham.yassirapp.domain.usecases.GetMoviesUseCase
import com.shubham.yassirapp.test.BuildConfig
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito

@ExperimentalComposeUiApi
@RunWith(AndroidJUnit4::class)
internal class MovieListViewModelTest {

    private lateinit var movieListViewModel: MovieListViewModel
    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val movieList = listOf<MovieList>(
            MovieList(
                "/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                436270,
                "en",
                "Black adam",
                "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
                13251.998,
                "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
                "2022-10-19",
                "Black Adam"
            ),
            MovieList(
                "/kmzppWh7ljL6K9fXW72bPN3gKwu.jpg",
                1013860,
                "en",
                "R.I.P.D. 2: Rise of the Damned",
                "When Sheriff Roy Pulsipher finds himself in the afterlife, he joins a special police force and returns to Earth to save humanity from the undead.",
                4612.792,
                "/g4yJTzMtOBUTAR2Qnmj8TYIcFVq.jpg",
                "2022-11-15",
                "R.I.P.D. 2: Rise of the Damned"
            )
        )
        getMoviesUseCase = Mockito.mock(GetMoviesUseCase::class.java)
        Mockito.`when`(suspend {  getMoviesUseCase.execute(BuildConfig.API_KEY)}).thenReturn { movieList }
        movieListViewModel = MovieListViewModel(getMoviesUseCase)
    }

    @Test
    fun check_if_the_returned_data_is_right_from_getMovies_function(){

        val movielist:List<MovieList>? = movieListViewModel.getMoviesList().value

        Assert.assertEquals(2,movielist?.size)


    }


}