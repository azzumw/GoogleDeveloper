package com.example.lunchtray

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import com.example.lunchtray.ui.order.*
import org.junit.Assert.assertEquals
import org.junit.Test


@LargeTest
class NavigationTests : BaseTest(){


    @Test
    fun navigate_to_entree_menu_from_start_order() {

        //initialise nav controller
        val navController = TestNavHostController(context = context)

        // Launch StartOrderFragment
        val startOrderScenario = launchFragmentInContainer<StartOrderFragment>(themeResId = R.style.Theme_LunchTray)

        //Configure nav controller
        startOrderScenario.onFragment{
            fragment -> navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(),navController)
        }

        // Click start order
        onView(withId(R.id.startOrderBtn)).perform(click())

        //check destination is correct
        assertEquals(navController.currentDestination?.id,R.id.entreeMenuFragment)

    }

    @Test
    fun navigate_to_start_order_from_entree_menu() {
        val navController = TestNavHostController(context)

        val entreeScenario = launchFragmentInContainer<EntreeMenuFragment>(themeResId = R.style.Theme_LunchTray)

        //configure nav controller
        entreeScenario.onFragment{
            navController.setGraph(R.navigation.nav_graph)
            // Destination defaults to the home fragment, we have to explicitly set the current
            // destination
            navController.setCurrentDestination(destId = R.id.entreeMenuFragment)
            Navigation.setViewNavController(it.requireView(),navController)
        }

        //press cancel
        onView(withId(R.id.cancel_button)).perform(click())

        //assert
        assertEquals(navController.currentDestination?.id,R.id.startOrderFragment)
    }

    @Test
    fun navigate_to_side_manu_from_entree_menu() {
        val navController = TestNavHostController(context)

        //start fragment
        val entreeFragment = launchFragmentInContainer<EntreeMenuFragment>(themeResId = R.style.Theme_LunchTray)

        //configure navigation
        entreeFragment.onFragment{
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.entreeMenuFragment)
            Navigation.setViewNavController(it.requireView(),navController)
        }

        //navigate to side menu
        onView(withId(R.id.next_button)).perform(click())

        assertEquals(navController.currentDestination?.id,R.id.sideMenuFragment2)
    }

    @Test
    fun navigate_to_start_manu_from_side_menu() {
        val navController = TestNavHostController(context)

        //start the fragment
        val sideMenuFragment = launchFragmentInContainer<SideMenuFragment>(themeResId = R.style.Theme_LunchTray)

        //configure the nav controller
        sideMenuFragment.onFragment{
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.sideMenuFragment2)
            Navigation.setViewNavController(it.requireView(),navController)

        }

        //click on cancel
        onView(withId(R.id.cancel_button)).perform(click())

        assertEquals(navController.currentDestination?.id,R.id.startOrderFragment)
    }

    /**
     * Test navigation from [SideMenuFragment] to [AccompanimentMenuFragment]
     */

    @Test
    fun navigate_to_accompaniment_manu_from_side_menu() {
        val navHostController = TestNavHostController(context)

        //fire up fragment
        val sideMenuFragment = launchFragmentInContainer<SideMenuFragment>(themeResId = R.style.Theme_LunchTray)

        //configure the nav controller
        //configure the nav controller
        sideMenuFragment.onFragment{
            navHostController.setGraph(R.navigation.nav_graph)
            navHostController.setCurrentDestination(R.id.sideMenuFragment2)
            Navigation.setViewNavController(it.requireView(),navHostController)

        }

        //navigate to side menu
        onView(withId(R.id.next_button)).perform(click())

        assertEquals(navHostController.currentDestination?.id,R.id.accompanimentMenuFragment)
    }

    @Test
    fun navigate_to_start_fragment_from_accompaniment_menu() {
        val navHostController = TestNavHostController(context)

        val accompanimentMenuFragment = launchFragmentInContainer<AccompanimentMenuFragment>(themeResId = R.style.Theme_LunchTray)

        accompanimentMenuFragment.onFragment{
            navHostController.setGraph(R.navigation.nav_graph)
            navHostController.setCurrentDestination(R.id.accompanimentMenuFragment)
            Navigation.setViewNavController(it.requireView(),navHostController)
        }

        onView(withId(R.id.cancel_button)).perform(click())

        assertEquals(navHostController.currentDestination?.id,R.id.startOrderFragment)
    }

    /**
     * Test navigation from [AccompanimentMenuFragment] to [CheckoutFragment]
     */

    @Test
    fun navigate_to_checkout_from_accompaniment_menu() {
        val navHostController = TestNavHostController(context)

        val accompanimentMenuFragment = launchFragmentInContainer<AccompanimentMenuFragment>(themeResId = R.style.Theme_LunchTray)

        accompanimentMenuFragment.onFragment{
            navHostController.setGraph(R.navigation.nav_graph)
            navHostController.setCurrentDestination(R.id.accompanimentMenuFragment)
            Navigation.setViewNavController(it.requireView(),navHostController)
        }

        onView(withId(R.id.next_button)).perform(click())

        assertEquals(navHostController.currentDestination?.id,R.id.checkoutFragment)
    }

    @Test
    fun navigate_to_start_fragment_from_checkout() {
        val navHostController = TestNavHostController(context)

        val checkoutFragment = launchFragmentInContainer<CheckoutFragment>(themeResId = R.style.Theme_LunchTray)

        checkoutFragment.onFragment{
            navHostController.setGraph(R.navigation.nav_graph)
            navHostController.setCurrentDestination(R.id.checkoutFragment)
            Navigation.setViewNavController(it.requireView(),navHostController)
        }

        onView(withId(R.id.cancel_button)).perform(click())

        assertEquals(navHostController.currentDestination?.id,R.id.startOrderFragment)
    }

    @Test
    fun navigate_to_start_fragment_from_checkout_via_submit() {
        val navHostController = TestNavHostController(context)

        val checkoutFragment = launchFragmentInContainer<CheckoutFragment>(themeResId = R.style.Theme_LunchTray)

        checkoutFragment.onFragment{
            navHostController.setGraph(R.navigation.nav_graph)
            navHostController.setCurrentDestination(R.id.checkoutFragment)
            Navigation.setViewNavController(it.requireView(),navHostController)
        }

        onView(withId(R.id.submit_button)).perform(click())

        assertEquals(navHostController.currentDestination?.id,R.id.startOrderFragment)
    }
}