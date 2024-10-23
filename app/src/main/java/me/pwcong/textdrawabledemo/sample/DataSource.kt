package me.pwcong.textdrawabledemo.sample

import android.content.Context
import android.graphics.drawable.Drawable

class DataSource(context: Context) {
    private val mDataSource: MutableList<DataItem> = mutableListOf()
    private val mProvider: DrawableProvider = DrawableProvider(context)

    init {
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_RECT))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_ROUND_RECT))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_ROUND))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_RECT_BORDER))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_ROUND_RECT_BORDER))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_ROUND_BORDER))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_MULTIPLE_LETTERS))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_FONT))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_SIZE))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_ANIMATION))
        mDataSource.add(itemFromType(DrawableProvider.SAMPLE_MISC))
    }

    val count: Int
        get() = mDataSource.size

    fun getItem(position: Int): DataItem {
        return mDataSource[position]
    }

    private fun itemFromType(type: Int): DataItem {
        var type = type
        var label: String? = null
        var drawable: Drawable? = null
        when (type) {
            DrawableProvider.SAMPLE_RECT -> {
                label = "Rectangle with Text"
                drawable = mProvider.getRect("A")
            }

            DrawableProvider.SAMPLE_ROUND_RECT -> {
                label = "Round Corner with Text"
                drawable = mProvider.getRoundRect("B")
            }

            DrawableProvider.SAMPLE_ROUND -> {
                label = "Round with Text"
                drawable = mProvider.getRound("C")
            }

            DrawableProvider.SAMPLE_RECT_BORDER -> {
                label = "Rectangle with Border"
                drawable = mProvider.getRectWithBorder("D")
            }

            DrawableProvider.SAMPLE_ROUND_RECT_BORDER -> {
                label = "Round Corner with Border"
                drawable = mProvider.getRoundRectWithBorder("E")
            }

            DrawableProvider.SAMPLE_ROUND_BORDER -> {
                label = "Round with Border"
                drawable = mProvider.getRoundWithBorder("F")
            }

            DrawableProvider.SAMPLE_MULTIPLE_LETTERS -> {
                label = "Support multiple letters"
                drawable = mProvider.rectWithMultiLetter
                type = NO_NAVIGATION
            }

            DrawableProvider.SAMPLE_FONT -> {
                label = "Support variable font styles"
                drawable = mProvider.roundWithCustomFont
                type = NO_NAVIGATION
            }

            DrawableProvider.SAMPLE_SIZE -> {
                label = "Support for custom size"
                drawable = mProvider.rectWithCustomSize
                type = NO_NAVIGATION
            }

            DrawableProvider.SAMPLE_ANIMATION -> {
                label = "Support for animations"
                drawable = mProvider.rectWithAnimation
                type = NO_NAVIGATION
            }

            DrawableProvider.SAMPLE_MISC -> {
                label = "Miscellaneous"
                drawable = mProvider.getRect("\u03c0")
                type = NO_NAVIGATION
            }
        }
        return DataItem(label!!, drawable!!, type)
    }

    companion object {
        const val NO_NAVIGATION: Int = -1
    }
}
