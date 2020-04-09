package ru.neura.fastrecycler

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.neura.iotable.BaseAdapterCallback
import ru.neura.iotable.oneListAdapter.TypeItems

class MainActivity : AppCompatActivity() {

    private val oneListAdapter = OneListOneListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        oneListAdapter.attachCallback(object : BaseAdapterCallback<TypeItems> {
            override fun onItemClick(model: TypeItems, view: View) {
                Toast.makeText(this@MainActivity, "Click $model", Toast.LENGTH_LONG).show()
            }

            override fun onLongClick(model: TypeItems, view: View): Boolean {
                Toast.makeText(this@MainActivity, "Long click $model", Toast.LENGTH_LONG).show()
                return true
            }
        })

        val listTypeItems = listOf(
            TypeItems.Header("asdfafsdf"),
            TypeItems.Header("asdasdasdasd"),
            TypeItems.Header("asdasdasdasdas"),
            TypeItems.Header("asdfafsdf"),
            TypeItems.Element(1),
            TypeItems.Header("asdfafsdf"),
            TypeItems.Element(2),
            TypeItems.Header("asdasdasdasd"),
            TypeItems.Element(3),
            TypeItems.Header("asdasdasdasdas"),
            TypeItems.Element(4),
            TypeItems.Header("asdasdasdasd"),
            TypeItems.Header("asdasdasdasdas"),
            TypeItems.Element(5),
            TypeItems.Element(6),
            TypeItems.End(5)
        )
        initListAdapter(listTypeItems)
    }

    private fun initListAdapter(listTypeItems: List<TypeItems>) {
        rvIOtable.adapter = oneListAdapter.adapter
        oneListAdapter.setList(listTypeItems)
    }
}
