package ru.neura.fastrecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import ru.neura.iotable.classAdapter.DataItems
import ru.neura.iotable.oneListAdapter.TypeItems

class MainActivity : AppCompatActivity() {

    private val oneListAdapter = OneListOneListAdapter()
    private val classAdapter = ClassClassAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listTypeItems = listOf<TypeItems>(
            TypeItems.Header("asdfafsdf"),
            TypeItems.Header("asdasdasdasd"),
            TypeItems.Header("asdasdasdasdas"),
            TypeItems.Header("asdfafsdf"),
            TypeItems.Header("asdasdasdasd"),
            TypeItems.Header("asdasdasdasdas"),
            TypeItems.Header("asdfafsdf"),
            TypeItems.Header("asdasdasdasd"),
            TypeItems.Header("asdasdasdasdas"),
            TypeItems.Element(1),
            TypeItems.Element(2),
            TypeItems.Element(3),
            TypeItems.Element(4),
            TypeItems.Element(5),
            TypeItems.Element(6)
        )
        val dataItems = DataItems<String, Int>(
            listOf<String>(
                "aaaaaa",
                "bbbbbb",
                "cccccc",
                "dddddd",
                "eeeeee",
                "ffffff",
                "gggggg",
                "hhhhhh"
            ),
            listOf(
                1,
                2,
                3,
                4,
                5,
                6,
                7
            )
        )
//        initListAdapter(listTypeItems)
//        initClassAdapter(dataItems)
    }

    private fun initClassAdapter(dataItems: DataItems<String, Int>) {
        rvIOtable.adapter = classAdapter.adapter
        classAdapter.setData(dataItems)
    }

    private fun initListAdapter(listTypeItems: List<TypeItems>) {
        rvIOtable.adapter = oneListAdapter.adapter
        oneListAdapter.setList(listTypeItems)
    }
}
