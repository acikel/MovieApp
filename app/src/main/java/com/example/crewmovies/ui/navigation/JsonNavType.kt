package com.example.crewmovies.ui.navigation

import android.os.Bundle
import androidx.navigation.NavType

//Class to implement passing custom types as arguement in navigation graph by
//parsing the custom type into a json, then passing it as a string for the navigation
//and ultimately parsing it back to the initial type
//See Jetpack Compose Navigation with custom Object: https://medium.com/@metaxas4/jetpack-compose-navigation-with-custom-object-3f9f06c0f69

abstract class JsonNavType<T> : NavType<T>(isNullableAllowed = false) {
    abstract fun fromJsonParse(value: String): T
    abstract fun T.getJsonParse(): String

    override fun get(bundle: Bundle, key: String): T? =
        bundle.getString(key)?.let { parseValue(it) }

    override fun parseValue(value: String): T = fromJsonParse(value)

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, value.getJsonParse())
    }
}