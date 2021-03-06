package com.example.affirmations.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * To ensure consistency and meaningful behavior of the generated code, data classes have to fulfill the following requirements:
 * The primary constructor needs to have at least one parameter.
 * All primary constructor parameters need to be marked as val or var.
 * Data classes cannot be abstract, open, sealed, or inner.
 * */
data class Affirmation(@StringRes val stringResourceId: Int, @DrawableRes val imageResId:Int)
