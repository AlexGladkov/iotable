package ru.neura.fastrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ru.neura.iotable.classAdapter.BaseClassAdapter
import ru.neura.iotable.classAdapter.BaseViewHolder

class ClassClassAdapter : BaseClassAdapter<String, Int>() {
    override fun createHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<String> {
        return object : BaseViewHolder<String>(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_string, parent, false)
        ) {
            private val textView = itemView.findViewById<TextView>(R.id.itemTextView)
            override fun bind(data: String) {
                textView.setText(data)
            }
        }
    }

    override fun createElementViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<Int> {
        return object : BaseViewHolder<Int>(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_string, parent, false)
        ) {
            private val textView = itemView.findViewById<TextView>(R.id.itemTextView)
            override fun bind(data: Int) {
                textView.setText(data.toString())
            }
        }
    }
}