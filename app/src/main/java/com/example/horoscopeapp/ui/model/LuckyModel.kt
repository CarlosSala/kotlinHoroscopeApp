package com.example.horoscopeapp.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

// this annotations forces to use drawable and string resources references of type Int
class LuckyModel(
    @DrawableRes val image: Int,
    @StringRes val text: Int
) {


}