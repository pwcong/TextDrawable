package com.github.pwcong.textdrawabledemo.component.activity

import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

import com.github.pwcong.textdrawabledemo.R
import com.github.pwcong.textdrawabledemo.sample.DataItem
import com.github.pwcong.textdrawabledemo.sample.DataSource

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private var mDataSource: DataSource? = null
    private var mListView: ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mListView = findViewById<ListView>(R.id.list_view)!!
        mDataSource = DataSource(this)
        mListView!!.adapter = SampleAdapter()
        mListView!!.onItemClickListener = this
    }

    private inner class SampleAdapter : BaseAdapter() {
        override fun getCount(): Int {
            return mDataSource!!.count
        }

        override fun getItem(position: Int): DataItem {
            return mDataSource!!.getItem(position)
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            var currentConvertView = convertView
            val holder: ViewHolder
            if (currentConvertView == null) {
                currentConvertView = View.inflate(this@MainActivity, R.layout.list_item_layout, null)
                holder = ViewHolder(currentConvertView)
                currentConvertView.tag = holder
            } else {
                holder = currentConvertView.tag as ViewHolder
            }

            val item: DataItem = getItem(position)

            val drawable: Drawable = item.drawable
            holder.imageView.setImageDrawable(drawable)
            holder.textView.text = item.label

            holder.textView.setCompoundDrawablesWithIntrinsicBounds(
                null,
                null,
                null,
                null
            )

            // fix for animation not playing for some below 4.4 devices
            if (drawable is AnimationDrawable) {
                holder.imageView.post {
                    drawable.stop()
                    drawable.start()
                }
            }

            return currentConvertView!!
        }
    }

    private class ViewHolder(view: View) {
        val imageView: ImageView = view.findViewById<View>(R.id.imageView) as ImageView

        val textView: TextView = view.findViewById<View>(R.id.textView) as TextView
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.i("MainActivity", "position: $position, id: $id")
    }
}
