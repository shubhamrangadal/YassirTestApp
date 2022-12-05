package com.shubham.yassirapp.presenter.ui.movielist

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import coil.compose.rememberAsyncImagePainter
import com.shubham.yassirapp.domain.models.Movie
import com.shubham.yassirapp.domain.models.MovieList
import com.shubham.yassirapp.presenter.di.Injector
import com.shubham.yassirapp.presenter.theme.yassirappTheme
import com.shubham.yassirapp.presenter.ui.moviedetails.MovieDetailsActivity
import com.shubham.yassirapp.presenter.ui.ui.theme.YassirAppTheme
import java.text.DecimalFormat
import java.util.Stack
import javax.inject.Inject
import androidx.compose.foundation.layout.Column as Column

const val TEST_TAG = "TEST_TAG_MOVIE_LIST"

class MovieListActivity : ComponentActivity() {

    @Inject
    lateinit var movieListviewModel: MovieListViewModel

    @Inject
    lateinit var factory: MovieListViewModelFactory





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as Injector).createMovieSubComponent().injectMovieList(this)

        movieListviewModel =
            ViewModelProvider(this, factory).get(MovieListViewModel::class.java)

        val responseData = movieListviewModel.getMoviesList()

        responseData.observe(this, Observer {
            it?.let {
                setContent {
                    MyApp {
                        mainContent(movieList = it)
                    }
                }
            }
        })
    }
}


@Composable
fun MyApp(content: @Composable () -> Unit) {
    YassirAppTheme() {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            content()
        }
    }
}


@Composable
fun mainContent(movieList: List<MovieList>) {
    val context = LocalContext.current

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Movie List",
                modifier = Modifier.padding(10.dp),

                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                ), fontWeight = FontWeight.Black
            )
            LazyColumn(modifier = Modifier.testTag(TEST_TAG)) {
                items(movieList){
                    Box(Modifier.clickable {
                        val intent = Intent(context,MovieDetailsActivity::class.java)
                        intent.putExtra("movieId",it.id)
                        context.startActivity(intent)
                    }) {
                        itemHolder(movie = it)
                    }

                }
            }
        }
    }
}


@Composable
fun itemHolder(movie: MovieList) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp, pressedElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter("https://image.tmdb.org/t/p/w1280/${movie.posterPath}"),
                contentDescription = "Image",
                modifier = Modifier
                    .width(100.dp)
                    .height(120.dp)
                    .clip(shape = RoundedCornerShape(size = 8.dp)), contentScale = ContentScale.FillBounds
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = movie.originalTitle,
                    style = TextStyle(fontWeight = FontWeight.SemiBold),
                    modifier = Modifier.padding(5.dp)
                )

                Box(modifier = Modifier.padding(5.dp)) {
                    Text(
                        modifier = Modifier
                            .height(50.dp),
                        text = movie.overview,
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                    )

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(height = 50.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent, Color.White
                                    )
                                )
                            )
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Box(modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 8.dp))
                    ) {

                        Text(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(size = 10.dp))
                                .padding(all = 5.dp),
                            text = movie.releaseDate,
                            style = TextStyle(fontSize = 10.sp)
                        )
                        
                    }

                    Box(modifier = Modifier
                        .clip(shape = RoundedCornerShape(size = 4.dp))
                        .border(border = BorderStroke(width = 1.dp, color = Color.Black))
                    ) {

                        Text(
                            modifier = Modifier
                                .clip(shape = RoundedCornerShape(size = 10.dp))
                                .padding(all = 5.dp),
                            text = movie.popularity.toString(),
                            style = TextStyle(fontSize = 10.sp)
                        )
                    }
                }
            }
        }
    }

}



@ExperimentalComposeUiApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    yassirappTheme {
        itemHolder(
            movie = MovieList(
                "/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
                436270,
                "en",
                "Black adam",
                "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
                13251.998,
                "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
                "2022-10-19",
                "Black Adam"
            )
        )
    }
}