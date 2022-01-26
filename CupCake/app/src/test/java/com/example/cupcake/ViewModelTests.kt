package com.example.cupcake

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cupcake.viewModel.OrderViewModel
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.internal.matchers.Or

class ViewModelTests {

    private lateinit var viewModel: OrderViewModel
    /*
    * o specify that LiveData objects should not call
    * the main thread we need to provide a specific
    * test rule any time we are testing a LiveData object
    * */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = OrderViewModel()
    }

    @Test
    fun quantity12Cupcakes(){

        viewModel?.setQuantity(12)

        assertEquals(12,viewModel.quantity.value)
    }

    @Test
    fun price_twelve_cakes() {
        //GIVEN
        viewModel?.setQuantity(12)

        // The code will only be called if we observe the object for changes.
        // Of course, this is done in our app,
        // but we also need to do the same for the test.
        viewModel?.price.observeForever {  }
        val priceToday = 12 * 2 + 3.00

        assertEquals(priceToday,viewModel.price.value)
    }
}