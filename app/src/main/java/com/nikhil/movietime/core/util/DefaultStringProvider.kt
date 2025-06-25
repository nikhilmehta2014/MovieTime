package com.nikhil.movietime.core.util

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class DefaultStringProvider @Inject constructor(
    @ApplicationContext private val context: Context
) : StringProvider {
    override fun getString(resId: Int): String = context.getString(resId)
}
