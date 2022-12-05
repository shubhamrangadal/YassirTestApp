package com.shubham.yassirapp.presenter.ui.moviedetails

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.compose.rememberAsyncImagePainter
import com.shubham.yassirapp.domain.models.MovieDetails
import com.shubham.yassirapp.presenter.di.Injector
import com.shubham.yassirapp.presenter.ui.ui.theme.YassirAppTheme
import javax.inject.Inject


const val TEST_TAG_TITLE = "TEST_TAG_TITLE_SCREEN_2"

class MovieDetailsActivity : ComponentActivity() {

    @Inject
    lateinit var movieDetailsViewModel: MovieDetailsViewModel

    @Inject
    lateinit var factory: MovieDetailsViewModelFactory

    var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra("movieId")) {
            movieId = intent.extras!!.getInt("movieId")
        }

        (application as Injector).createMovieSubComponent().injectMovieDetails(this)

        movieDetailsViewModel =
            ViewModelProvider(this, factory).get(MovieDetailsViewModel::class.java)

        val responseData = movieDetailsViewModel.getMovieDetails(movieId = movieId)

        responseData.observe(this, Observer {
            setContent {
                MyApp {
                    if (it != null) {
                        detailsScreen(movie = it)
                    }
                }
            }
        })


    }

    @Composable
    fun MyApp(content: @Composable () -> Unit) = YassirAppTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun detailsScreen(movie: MovieDetails) {
        val scrollstate = rememberScrollState()
        Surface {
            Column(modifier = Modifier.scrollable(state = scrollstate, orientation = Orientation.Vertical)) {
                TopAppBar(title = { Text(text = "Movie details") }, navigationIcon = {
                    IconButton(onClick = { finish() }) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                }, modifier = Modifier.shadow(elevation = 5.dp, ambientColor = Color.Black))
                Image(
                    rememberAsyncImagePainter("https://image.tmdb.org/t/p/w1280/${movie.posterPath}"),
                    contentDescription = "Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 5.dp)
                        .fillMaxHeight(0.4f)
                        .clip(shape = RoundedCornerShape(size = 10.dp)),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = movie.originalTitle,
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp).testTag(
                        TEST_TAG_TITLE)
                )
                Text(
                    text = movie.overview,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily.SansSerif,
                        fontWeight = FontWeight.Medium
                    ),
                )
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(5.dp),
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .wrapContentWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Text(
                                text = "Imdb rating", style = TextStyle(
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold, color = Color.Black
                                ), modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
                            )
                            Text(
                                text = movie.imdbId,
                                modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                    }

                    Card(
                        elevation = CardDefaults.cardElevation(5.dp),
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .wrapContentWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Text(
                                text = "Released date", style = TextStyle(
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold, color = Color.Black
                                ), modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
                            )
                            Text(
                                text = movie.releaseDate, style = TextStyle(
                                    fontSize = 18.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Medium
                                ), modifier = Modifier.padding(horizontal = 10.dp)
                            )
                        }
                    }

                    Card(
                        elevation = CardDefaults.cardElevation(5.dp),
                        modifier = Modifier
                            .padding(vertical = 10.dp, horizontal = 10.dp)
                            .wrapContentWidth()
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier.wrapContentWidth(),
                        ) {
                            Text(
                                text = "Status", style = TextStyle(
                                    fontSize = 25.sp,
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Bold, color = Color.Black
                                ), modifier = Modifier.padding(vertical = 4.dp, horizontal = 10.dp)
                            )
                            Text(text = movie.status, style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily.SansSerif,
                                fontWeight = FontWeight.Medium
                            ), modifier = Modifier.padding(horizontal = 10.dp))
                        }
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MyApp {

            detailsScreen(
                movie = MovieDetails(

                    "/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                    200000000,
                    "https://www.dc.com/BlackAdam",
                    436270,
                    "tt6443346",
                    "en",
                    "Black Adam",
                    "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
                    13251.998,
                    "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
                    "2022-10-19",
                    368000000,
                    125,
                    "Released",
                    "The world needed a hero. It got Black Adam.",
                    "Black Adam"
                )
            )

        }
    }

}