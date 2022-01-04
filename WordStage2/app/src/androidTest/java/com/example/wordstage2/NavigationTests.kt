package com.example.wordstage2

import android.content.Context
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * JUnit also provides the @BeforeClass and @AfterClass annotations.
 * The difference here is that methods with this annotation execute once,
 * but the executed code still applies to every method.
 * If your setup or teardown methods contain expensive operations,
 * it may be preferable to use these annotations instead.
 * Methods annotated with @BeforeClass and @AfterClass must be placed in a companion object
 * and annotated with @JvmStatic*/

@RunWith(AndroidJUnit4::class)
class NavigationTests {

    private val appContext: Context = InstrumentationRegistry.getInstrumentation().targetContext

    private lateinit var navController: TestNavHostController

    private lateinit var letterListScenario: FragmentScenario<LetterListFragment>

    @Before
    fun setUp() {
        // 1. get a reference to TestNavHostController
        navController = TestNavHostController(appContext)

        // 2. launch fragment passing in the theme Id so the test does not crash
        letterListScenario = launchFragmentInContainer(themeResId = R.style.Theme_WordStage2)

        // 3. which navGraph to use
        letterListScenario.onFragment{
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(it.requireView(),navController)
        }
    }

    @Test
    fun navigate_to_words_nav_component() {


        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions
                .actionOnItemAtPosition<LetterAdapter.LetterViewHolder>(2,click()))

        /**
         * When using the launchFragmentInContainer() method, actual navigation is not possible
         * because the container is not aware of other fragments or activities that we might be
         * navigating to. It only knows the fragment that we specified to launch in it.
         * Therefore, when you run this test on a device or emulator, you will not see any actual navigation.
         * This may seem unintuitive, but it allows us to make a much more direct assertion regarding the
         * current destination. Instead of looking for a UI component that we know displays on a particular screen,
         * we can check to make sure that the current navigation controller's destination has the ID of the fragment
         * we expect to be in. This approach is significantly more reliable than the aforementioned.
         * */

        assertEquals(navController.currentDestination?.id,R.id.wordListFragment)
    }

    @Test
    fun navigate_to_word_3_nav_component() {
        onView(withId(R.id.recycler_view))
            .perform(RecyclerViewActions.actionOnItemAtPosition<LetterAdapter.LetterViewHolder>(3,
                click()))

        assertEquals(navController.currentDestination?.id,R.id.wordListFragment)
    }
}