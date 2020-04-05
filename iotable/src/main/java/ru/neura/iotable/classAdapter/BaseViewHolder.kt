package ru.neura.iotable.classAdapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by agladkov on 25.12.17.
 * Base view holder
 */
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(data: T)
}
