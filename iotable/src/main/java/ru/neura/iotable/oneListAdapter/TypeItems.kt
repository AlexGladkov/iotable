package ru.neura.iotable.oneListAdapter

open class TypeItems(val viewType: Int){
    class Header<HEADER>(val item: HEADER) : TypeItems(1)
    class Element<ELEMENT>(val item: ELEMENT): TypeItems(2)
}
