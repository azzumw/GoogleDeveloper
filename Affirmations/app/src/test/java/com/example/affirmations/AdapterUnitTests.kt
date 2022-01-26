package com.example.affirmations

import android.content.Context
import com.example.affirmations.adapter.ItemAdapter
import com.example.affirmations.model.Affirmation
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito.mock

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class AdapterUnitTests {

    private val context = mock(Context::class.java)

    private val data = listOf(
        Affirmation(R.string.affirmation1, R.drawable.image1),
        Affirmation(R.string.affirmation2, R.drawable.image2)
    )

    @Test
    fun adapter_size() {
        val adapter = ItemAdapter(context, data)

        assertEquals("ItemAdapter is not the correct size",2,adapter.itemCount)
    }
}