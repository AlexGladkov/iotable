package ru.neura.iotable

import android.view.View

/**
 * Created by agladkov on 10.01.18.
 */
interface BaseAdapterCallback<T> {
    fun onItemClick(model: T, view: View)
    fun onLongClick(model: T, view: View): Boolean
}