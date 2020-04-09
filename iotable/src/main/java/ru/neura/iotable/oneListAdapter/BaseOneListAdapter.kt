package ru.neura.iotable.oneListAdapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.neura.iotable.BaseAdapterCallback

/**
 * Created by agladkov on 25.12.17.
 * Use this adapter for any simple recycler view adapter you want
 */
/*
* Абстрактный класс для создания списка елементов с хедером. Адаптер использует список с {@link listItems}
* все элементы которого должны быть отнаследованы от {@link TypeItems}.
* Класс имеет два абстрактных метода для {@link createHeaderViewHolder} для реализации в наследнике
* вьюхолдера для хедера и {@link createElementViewHolder} для создания основных элементов.
* Класс использует композицию адаптера {@link RecyclerView.Adapter}, чтобы никто не переопределил методы и не поломал логику (к примеру
* {@link onCreateViewHolder})
* Проблемы:
* 1 - UnCheckedCast (проблема с контрвариантностью)   -    sealed class, однако как перебрать только используемые viewType в sealed class ?
* 2 - Как добавить слушатель на клик? Старое решение c интерфейсом и двумя функциями в нутри мне не очень нравится.      А других нормальных вариантов то и нет
* 3 - В {@link BaseViewHolder} функция {@link bind} имеет аргумент типа TypeItems.Header<*>. Что не очень удобно.
* */
abstract class BaseOneListAdapter<HEADER : TypeItems, ELEMENT : TypeItems, END : TypeItems> {

    private var mCallback: BaseAdapterCallback<TypeItems>? = null
    var hasItems = false

    fun attachCallback(callback: BaseAdapterCallback<TypeItems>) {
        this.mCallback = callback
    }

    fun detachCallback() {
        this.mCallback = null
    }

    fun addItem(newItem: TypeItems) {
        listItems.add(newItem)
        adapter.notifyItemInserted(listItems.size - 1)
    }

    fun addItemToTop(newItem: TypeItems) {
        listItems.add(0, newItem)
        adapter.notifyItemInserted(0)
    }

    fun updateItems(itemsList: List<TypeItems>) {
        listItems.clear()
        setList(itemsList)
    }

    fun setList(dataList: List<TypeItems>) {
        hasItems = dataList.isEmpty()
        listItems.addAll(dataList)
        adapter.notifyDataSetChanged()
    }

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

    abstract fun createEndViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<END>

    private fun getItemCount(): Int {
        return listItems.size
    }

    private fun onBindViewHolder(holder: BaseViewHolder<TypeItems>, position: Int) {
        holder.bind(listItems[position])

        holder.itemView.setOnClickListener {
            mCallback?.onItemClick(listItems[position], holder.itemView)
        }
        holder.itemView.setOnLongClickListener {
            if (mCallback == null) {
                false
            } else {
                mCallback!!.onLongClick(listItems[position], holder.itemView)
            }
        }
    }

    protected var listItems: MutableList<TypeItems> = mutableListOf()

    private fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<TypeItems> {
        return when (viewType) {
            1 -> createHeaderViewHolder(parent, viewType)
            2 -> createElementViewHolder(parent, viewType)
            else -> createEndViewHolder(parent, viewType)
        } as BaseViewHolder<TypeItems>
    }

    fun getItemViewType(position: Int): Int {
        return listItems[position].viewType
    }
}