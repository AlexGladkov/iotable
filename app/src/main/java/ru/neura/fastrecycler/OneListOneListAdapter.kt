package ru.neura.fastrecycler

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import ru.neura.iotable.oneListAdapter.BaseOneListAdapter
import ru.neura.iotable.oneListAdapter.BaseViewHolder
import ru.neura.iotable.oneListAdapter.TypeItems


class OneListOneListAdapter :
    BaseOneListAdapter<TypeItems.Header<String>, TypeItems.Element<Int>, TypeItems.End<Int>>() {

    override fun createHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TypeItems.Header<String>> {
        return object : BaseViewHolder<TypeItems.Header<String>>(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_string, parent, false)
        ) {
            private val textView = itemView.findViewById<TextView>(R.id.itemTextView)
            override fun bind(data: TypeItems.Header<String>) {
                textView.text = data.item
            }
        }
    }

    override fun createElementViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TypeItems.Element<Int>> {
        return object : BaseViewHolder<TypeItems.Element<Int>>(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_string, parent, false)
        ) {
            private val textView = itemView.findViewById<TextView>(R.id.itemTextView)
            override fun bind(data: TypeItems.Element<Int>) {
                textView.setTypeface(textView.typeface, Typeface.BOLD_ITALIC)
                textView.text = data.item.toString()
            }
        }
    }

    override fun createEndViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<TypeItems.End<Int>> {
        return object : BaseViewHolder<TypeItems.End<Int>>(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_string, parent, false)
        ) {
            private val textView = itemView.findViewById<TextView>(R.id.itemTextView)
            override fun bind(data: TypeItems.End<Int>) {
                textView.setTypeface(textView.typeface, Typeface.BOLD_ITALIC)
                textView.text = data.item.toString()
            }
        }
    }
}
