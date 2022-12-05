package com.shubham.yassirapp.presenter.ui.movielist

import androidx.activity.compose.setContent
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.shubham.yassirapp.domain.models.MovieList
import com.shubham.yassirapp.presenter.ui.ui.theme.YassirAppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MovieListActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MovieListActivity>()


    @Test
    fun test_if_the_list_has_zero_vales(){
        composeTestRule.activity.runOnUiThread {
            composeTestRule.activity.setContent {
                MyApp {
                    mainContent(movieList = emptyList())
                }
            }
        }

        Thread.sleep(3000)


        composeTestRule.onNodeWithTag(TEST_TAG).onChildren().assertCountEquals(0)
    }

    @Test
    fun test_if_the_list_has_more_values(){
        composeTestRule.activity.runOnUiThread {
            composeTestRule.activity.setContent {
                MyApp {
                    mainContent(movieList = getList())
                }
            }
        }

        Thread.sleep(3000)

        composeTestRule.onNodeWithTag(TEST_TAG).onChildren().assertCountEquals(3)
    }

    fun getList(): List<MovieList>{
        return listOf(MovieList(
            "/bQXAqRx2Fgc46uCVWgoPz5L5Dtr.jpg",
            436270,
            "en",
            "Black adam",
            "Nearly 5,000 years after he was bestowed with the almighty powers of the Egyptian gods—and imprisoned just as quickly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
            13251.998,
            "/pFlaoHTZeyNkG83vxsAJiGzfSsa.jpg",
            "2022-10-19",
            "Black Adam"
        ),MovieList(
            "/bQX5L5Dtr.jpg",
            436470,
            "en",
            "sajashd",
            "ly—Black Adam is freed from his earthly tomb, ready to unleash his unique form of justice on the modern world.",
            132.998,
            "/pFlaoHTZe.jpg",
            "2023-11-19",
            "Black hsakh"
        ),MovieList(
            "/bQXAqRx2oPz5L5Dtr.jpg",
            437270,
            "en",
            "sahaskhd",
            "is frunique form of justice on the modern world.",
            151.998,
            "/pFlaoHGzfSsa.jpg",
            "2018-10-19",
            "Bjaksdhsad m"
        ))
    }

}