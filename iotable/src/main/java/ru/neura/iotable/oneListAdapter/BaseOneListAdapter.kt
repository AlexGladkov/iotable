package ru.neura.iotable.oneListAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by agladkov on 25.12.17.
 * Use this adapter for any simple recycler view adapter you want
 */
/*
* Абстрактный класс для создания списка елементов с хедером. Адаптер использует список с {@link listItems}
* все элементы которого должны быть отнаследованы от {@link TypeItems}.
* Класс имеет два абстрауктных метода для {@link createHeaderViewHolder} для реализации в наследнике
* вьюхолдера для хедера и {@link createElementViewHolder} для создания основных элементов.
* Класс использует композицию адаптера {@link RecyclerView.Adapter}, чтобы никто не переопределил методы и не поломал логику (к примеру
* {@link onCreateViewHolder})
* Проблемы:
* 1 - UnCheckedCast (проблема с контрвариантностью)
* 2 - Как добавить слушатель на клик? Старое решение c интерфейсом и двумя функциями в нутри мне не очень нравится.
* 3 - В {@link BaseViewHolder} функция {@link bind} имеет аргумент типа TypeItems.Header<*>. Что не очень удобно.
* */
abstract class BaseOneListAdapter<HEADER : TypeItems, ELEMENT : TypeItems> {

    val adapter = object : RecyclerView.Adapter<BaseViewHolder<TypeItems>>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): BaseViewHolder<TypeItems> {
            return this@BaseOneListAdapter.onCreateViewHolder(parent, viewType)
        }

        override fun getItemCount(): Int {
            return this@BaseOneListAdapter.getItemCount()
        }

        override fun getItemViewType(position: Int): Int {
            return this@BaseOneListAdapter.getItemViewType(position)
        }

        override fun onBindViewHolder(holder: BaseViewHolder<TypeItems>, position: Int) {
            this@BaseOneListAdapter.onBindViewHolder(holder, position)
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
        return listItems.size
    }

    private fun onBindViewHolder(holder: BaseViewHolder<TypeItems>, position: Int) {
        holder.bind(listItems[position])
    }

    protected var listItems: MutableList<TypeItems> = mutableListOf()

    private fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TypeItems> {
        return when (viewType) {
            1 -> createHeaderViewHolder(parent, viewType)
            else -> createElementViewHolder(parent, viewType)
        } as BaseViewHolder<TypeItems>
    }

    fun getItemViewType(position: Int): Int {
        return listItems[position].viewType
    }

    fun setList(dataList: List<TypeItems>) {
        listItems.addAll(dataList)
        adapter.notifyDataSetChanged()
    }
}