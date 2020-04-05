package ru.neura.iotable.classAdapter

class DataItems<HEADER, ELEMENT>(
    private val listHeader: List<HEADER> = mutableListOf(),
    private val listElement: List<ELEMENT> = mutableListOf()
) {
    fun count(): Int = listHeader.size + listElement.size

    fun type(position: Int): Int {
        return when {
            position < listHeader.size -> TypeItems.HEADER
            position >= listHeader.size && position < listHeader.size + listElement.size -> TypeItems.ELEMENT
            position >= listHeader.size + listElement.size && position < count() -> TypeItems.END
            else -> throw IllegalStateException("not right value position")
        }
    }

    fun headerItem(position: Int): HEADER {
        return listHeader[position]
    }

    fun elementType(position: Int) : ELEMENT {
        return listElement[position - listHeader.size]
    }
//

}