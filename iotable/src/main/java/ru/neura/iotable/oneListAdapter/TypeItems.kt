package ru.neura.iotable.oneListAdapter

sealed class TypeItems(val viewType: Int) {
    class Header<HEADER>(val item: HEADER) : TypeItems(1)
    class Element<ELEMENT>(val item: ELEMENT) : TypeItems(2)
    class End<END>(val item: END) : TypeItems(3)
}
