package ru.cse.utils

import android.R
import android.app.Activity
import android.content.Context
import android.support.annotation.StringRes
import android.support.design.widget.Snackbar
import android.support.v4.widget.DrawerLayout
import android.view.View
import android.view.ViewGroup
import java.text.SimpleDateFormat
import java.util.*


class AppUtilities private constructor() {
    init {
        throw AssertionError()
    }

    companion object {

        fun showSnackbar(context: Context, @StringRes stringRes: Int, isLong: Boolean) {
            showSnackbar(context, context.getString(stringRes), isLong)
        }

        fun showSnackbar(context: Context, text: String, isLong: Boolean) {
            if (context is Activity) {
                val rootView = (context.findViewById<View>(R.id.content) as ViewGroup).getChildAt(0)
                if (rootView is DrawerLayout)
                    showSnackbar(rootView, text, isLong)
            }
        }

        fun showSnackbar(layout: DrawerLayout, @StringRes stringRes: Int, isLong: Boolean) {
            showSnackbar(layout, layout.resources.getString(stringRes), isLong)
        }

        fun showSnackbar(layout: DrawerLayout, text: String, isLong: Boolean) {
            val snack = Snackbar.make(layout, text,
                    if (isLong) Snackbar.LENGTH_LONG else Snackbar.LENGTH_SHORT)
            snack.setAction(R.string.ok) { v -> }
            snack.show()
        }

    }
}

object DateConverter {

    fun convert(timestamp: Long): String {
        val date = Calendar.getInstance()
        val simpleDateFormat = SimpleDateFormat("dd.MM.yyyy")

        date.timeInMillis = (timestamp * 1000)

        return simpleDateFormat.format(timestamp)
    }
}
