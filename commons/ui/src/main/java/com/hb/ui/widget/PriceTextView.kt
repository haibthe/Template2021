package com.hb.ui.widget

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.hb.ui.R
import java.text.DecimalFormat

class PriceTextView
@JvmOverloads
constructor(
  context: Context,
  attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {

  private val withSpace: Boolean
  private val showSign: Boolean
  private val showUnit: Boolean
  private val colorByPrice: Boolean
  private val colorPositive: Int
  private val colorNegative: Int

  init {
    val a = context.obtainStyledAttributes(attrs, R.styleable.PriceTextView, 0, 0)
    withSpace = a.getBoolean(R.styleable.PriceTextView_with_space, true)
    showSign = a.getBoolean(R.styleable.PriceTextView_show_sign, false)
    showUnit = a.getBoolean(R.styleable.PriceTextView_show_unit, true)
    colorByPrice = a.getBoolean(R.styleable.PriceTextView_color_by_price, false)
    colorPositive = a.getColor(R.styleable.PriceTextView_color_positive, textColors.defaultColor)
    colorNegative = a.getColor(R.styleable.PriceTextView_color_negative, Color.parseColor("#FF3D00"))
    val price = a.getInteger(R.styleable.PriceTextView_price, 0)
    val strikeThrough = a.getBoolean(R.styleable.PriceTextView_strike_through, false)
    if (strikeThrough) {
      strikeThroughEnabled()
    }
    setPrice(price.toLong())
    a.recycle()
  }

  fun strikeThroughEnabled() {
    paint.flags = paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
  }

  fun setPrice(price: Long) {
    if (colorByPrice) {
      if (price > 0) {
        setTextColor(colorPositive)
      } else {
        setTextColor(colorNegative)
      }
    }
    val text = formatPrice(price)
    setText(text)
  }

  fun setPrice(minPrice: Long, maxPrice: Long, onlyShowMinPrice: Boolean) {
    val min: CharSequence = formatPrice(minPrice)
    text = if (onlyShowMinPrice) {
      min
    } else {
      val max: CharSequence = formatPrice(maxPrice)
      String.format("%s - %s", min, max)
    }
  }

  private fun formatPrice(price: Long): CharSequence {
    val result = formatter.format(price).replace(",", ".")
    val unit = if (showUnit) "K" else ""
    val template = if (withSpace) "%s $unit" else "%s$unit"
    return if (showSign) {
      val sign = if (price > 0) "+" else ""
      String.format(sign + template, result)
    } else {
      String.format(template, result)
    }
  }

  companion object {
    private val formatter = DecimalFormat("#,###,###,###")
  }
}
