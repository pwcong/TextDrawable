package com.github.pwcong.textdrawabledemo.sample

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.AnimationDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.LayerDrawable
import android.util.TypedValue
import com.github.pwcong.textdrawable.util.ColorGenerator
import com.github.pwcong.textdrawable.view.TextDrawable

class DrawableProvider(private val mContext: Context) {
    private val mGenerator = ColorGenerator.DEFAULT

    fun getRect(text: String?): TextDrawable? {
        return TextDrawable.builder()
            .buildRect(text, mGenerator.getColor(text!!))
    }

    fun getRound(text: String?): TextDrawable? {
        return TextDrawable.builder()
            .buildRound(text, mGenerator.getColor(text!!))
    }

    fun getRoundRect(text: String?): TextDrawable? {
        return TextDrawable.builder()
            .buildRoundRect(text, mGenerator.getColor(text!!), toPx(10))
    }

    fun getRectWithBorder(text: String?): TextDrawable? {
        return TextDrawable.builder()
            .beginConfig()
            ?.withBorder(toPx(2))
            ?.endConfig()
            ?.buildRect(text, mGenerator.getColor(text!!))
    }

    fun getRoundWithBorder(text: String?): TextDrawable? {
        return TextDrawable.builder()
            .beginConfig()
            ?.withBorder(toPx(2))
            ?.endConfig()
            ?.buildRound(text, mGenerator.getColor(text!!))
    }

    fun getRoundRectWithBorder(text: String?): TextDrawable? {
        return TextDrawable.builder()
            .beginConfig()
            ?.withBorder(toPx(2))
            ?.endConfig()
            ?.buildRoundRect(text, mGenerator.getColor(text!!), toPx(10))
    }

    val rectWithMultiLetter: TextDrawable?
        get() {
            val text = "AK"
            return TextDrawable.builder()
                .beginConfig()
                ?.fontSize(toPx(20))
                ?.toUpperCase()
                ?.endConfig()
                ?.buildRect(text, mGenerator.getColor(text))
        }

    val roundWithCustomFont: TextDrawable?
        get() {
            val text = "Bold"
            return TextDrawable.builder()
                .beginConfig()
                ?.useFont(Typeface.DEFAULT)
                ?.fontSize(toPx(15))
                ?.textColor(-0xa7aa7)
                ?.bold()
                ?.endConfig()
                ?.buildRect(text, Color.DKGRAY /*toPx(5)*/)
        }

    val rectWithCustomSize: Drawable
        get() {
            val leftText = "I"
            val rightText = "J"

            val builder = TextDrawable.builder()
                .beginConfig()
                ?.width(toPx(29))
                ?.withBorder(toPx(2))
                ?.endConfig()
                ?.rect()

            val left = builder
                ?.build(leftText, mGenerator.getColor(leftText))

            val right = builder
                ?.build(rightText, mGenerator.getColor(rightText))

            val layerList = arrayOf<Drawable>(
                InsetDrawable(left, 0, 0, toPx(31), 0),
                InsetDrawable(right, toPx(31), 0, 0, 0)
            )
            return LayerDrawable(layerList)
        }

    val rectWithAnimation: Drawable
        get() {
            val builder = TextDrawable.builder()
                .rect()

            val animationDrawable = AnimationDrawable()
            for (i in 10 downTo 1) {
                val frame = builder!!.build(i.toString(), mGenerator.randomColor)
                animationDrawable.addFrame(frame!!, 1200)
            }
            animationDrawable.isOneShot = false
            animationDrawable.start()

            return animationDrawable
        }

    fun toPx(dp: Int): Int {
        val resources = mContext.resources
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            resources.displayMetrics
        ).toInt()
    }

    companion object {
        const val SAMPLE_RECT: Int = 1
        const val SAMPLE_ROUND_RECT: Int = 2
        const val SAMPLE_ROUND: Int = 3
        const val SAMPLE_RECT_BORDER: Int = 4
        const val SAMPLE_ROUND_RECT_BORDER: Int = 5
        const val SAMPLE_ROUND_BORDER: Int = 6
        const val SAMPLE_MULTIPLE_LETTERS: Int = 7
        const val SAMPLE_FONT: Int = 8
        const val SAMPLE_SIZE: Int = 9
        const val SAMPLE_ANIMATION: Int = 10
        const val SAMPLE_MISC: Int = 11
    }
}
