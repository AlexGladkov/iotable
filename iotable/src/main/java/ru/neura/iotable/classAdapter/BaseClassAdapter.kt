package ru.neura.iotable.classAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by agladkov on 25.12.17.
 * Use this adapter for any simple recycler view adapter you want
*/
/*
* Абстрактный класс для создания списка елементов с хедером. Адаптер использует класс {@link DataItems}
* в котором скрыты все два типа списков (хеадеров и основных элементов). {@link DataItems} инкапсулирует логику размера всего списка и
* определения viewType.
* Класс имеет два абстрауктных метода для {@link createHeaderViewHolder} для реализации в наследнике
* вьюхолдера для хедера и {@link createElementViewHolder} для создания основных элементов.
* Класс использует композицию адаптера {@link RecyclerView.Adapter}, чтобы никто не переопределил методы и не поломал логику (к примеру
* {@link onCreateViewHolder})
* Проблемы:
* 1 - UnCheckedCast
* 2 - Как добавить слушатель на клик? Старое решение c интерфейсом и двумя функциями в нутри мне не очень нравится.
* */
abstract class BaseClassAdapter<HEADER, ELEMENT> {

    val adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): RecyclerView.ViewHolder {
            return this@BaseClassAdapter.onCreateViewHolder(parent, viewType)
        }

        override fun getItemCount(): Int {
            return this@BaseClassAdapter.getItemCount()
        }

        override fun getItemViewType(position: Int): Int {
            return this@BaseClassAdapter.getItemViewType(position)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            this@BaseClassAdapter.onBindViewHolder(holder, position)
        }
    }

    abstract fun createHeaderViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<HEADER>

    abstract fun createElementViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<ELEMENT>

    private fun getItemCount(): Int {
        return dataItems.count()
    }

    private var dataItems = DataItems<HEADER, ELEMENT>()

    private fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TypeItems.HEADER -> {
                (holder as? BaseViewHolder<HEADER>)?.bind(dataItems.headerItem(position))
            }
            else -> {
                (holder as? BaseViewHolder<ELEMENT>)?.bind(dataItems.elementType(position))
            }
        }
    }

    private fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TypeItems.HEADER -> createHeaderViewHolder(parent, viewType)
            else -> createElementViewHolder(parent, viewType)
        }
    }

    fun getItemViewType(position: Int): Int {
        return dataItems.type(position)
    }

    fun setData(data: DataItems<HEADER, ELEMENT>) {
        dataItems = data
        adapter.notifyDataSetChanged()
    }
}