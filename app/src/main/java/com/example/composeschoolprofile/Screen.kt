package com.example.composeschoolprofile

import androidx.compose.ui.text.input.TextFieldValue

sealed class Screen(val route:String)
{
    object Home:Screen("Home")
    object ViewProfile:Screen("View Profile")

    fun withArgs(vararg args: TextFieldValue):String{
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}
