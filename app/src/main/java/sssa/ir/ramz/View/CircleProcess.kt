package sssa.ir.ramz.View

/*
#Made by s.seyedalian
#gmail:seyedalian10@gmail.com
*/

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.support.annotation.ColorInt
import android.util.AttributeSet
import android.view.View
import android.os.Parcel
import android.os.Parcelable


class CircleProcess : View {
    // getters and setters --------------------------------------------
    var max = 100
        set(max) {
            field = if (max > this.min) max else 100
            invalidate()
        }
    var min = 0
        set(min) {
            field = if (min > 0) min else 0
            invalidate()
        }
    private var progress = 0
    private var color = Color.rgb(255, 100, 0)
    var textsize = 40
        set(textsize) {
            field = textsize
            invalidate()
        }
    private var strokeWidth = 26f
    private var backGroupPaint: Paint? = null
    private var foreGroupPaint: Paint? = null
    private var percentPaint: Paint? = null
    private var rectF: RectF? = null
    internal var animatation = true
    private val valueDRAW = 0 // animate
    var isAutoColor = false
        set(autoColor) {
            field = autoColor
            invalidate()
        }


    //constractor -----------------------------------------------------------------------
    constructor(context: Context) : super(context) {
        init(context, null, 0)

    }

    @JvmOverloads
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {
        init(context, attrs, defStyleAttr)

    }

    //init -------------------------------------------------------------------------------
    private fun init(context: Context, attrs: AttributeSet?, defStyleAttr: Int) {


        //set paints
        //set backGroupPaint
        backGroupPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        backGroupPaint!!.color = adjustAlpha(color, 0.2f)
        backGroupPaint!!.style = Paint.Style.STROKE
        backGroupPaint!!.strokeWidth = strokeWidth
        //set foreGroupPaint
        foreGroupPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        foreGroupPaint!!.color = color
        foreGroupPaint!!.style = Paint.Style.STROKE
        foreGroupPaint!!.strokeWidth = strokeWidth
        //percent paint ------------------------------------------------in init
        percentPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        percentPaint!!.color = color
        percentPaint!!.typeface = Typeface.create(Typeface.MONOSPACE, Typeface.BOLD)
        val density = context.resources.displayMetrics.density
        percentPaint!!.textSize = this.textsize * density
        percentPaint!!.textAlign = Paint.Align.CENTER
        //rectf ----------------------------------------------------------
        rectF = RectF()
        //


    }

    fun setProgress(progress: Int) {
        if (this.max <= progress)
            this.progress = this.max
        else if (progress < this.min) {
            this.progress = this.min
        } else
            this.progress = progress
        invalidate()
    }

    fun setColor(@ColorInt color: Int) {
        this.color = color
        foreGroupPaint!!.color = color
        backGroupPaint!!.color = adjustAlpha(color, 0.2f)
        percentPaint!!.color = color
        invalidate()
    }

    fun setStrokeWidth(strokeWidth: Float) {
        if (strokeWidth < 0 || strokeWidth == this.strokeWidth) return
        this.strokeWidth = strokeWidth
        backGroupPaint!!.strokeWidth = strokeWidth
        foreGroupPaint!!.strokeWidth = strokeWidth
        invalidate()
        requestLayout()
    }

    fun getProgress(): Int {
        return progress
    }

    fun getColor(): Int {
        return color
    }

    fun getStrokeWidth(): Float {
        return strokeWidth
    }
    // on measure ------------------------------------------------------------

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = View.getDefaultSize(suggestedMinimumWidth, widthMeasureSpec)
        val height = View.getDefaultSize(suggestedMinimumHeight, heightMeasureSpec)
        val min = Math.min(width, height)
        setMeasuredDimension(min, min)
        rectF!!.set(strokeWidth / 2, strokeWidth / 2, min - strokeWidth / 2, min - strokeWidth / 2)
    }
    //Ondraw-----------------------------------------------------------------------------------------------------

    override fun onDraw(canvas: Canvas) {
        val percent = (progress - this.min) * 100 / (this.max - this.min)
        if (isAutoColor) {
            val red = 255 - 255 * percent / this.max
            val green = 255 * percent / this.max
            foreGroupPaint!!.color = Color.rgb(red, green, 0)
            backGroupPaint!!.color = adjustAlpha(Color.rgb(red, green, 0), 0.2f)
            percentPaint!!.color = Color.rgb(red, green, 0)
            invalidate()

        } else {
            setColor(color)
        }



        canvas.drawOval(rectF!!, backGroupPaint!!)
        val sweepAngle = (progress - this.min) * 360 / (this.max - this.min)
        canvas.drawArc(rectF!!, -90f, sweepAngle.toFloat(), false, foreGroupPaint!!)
        val percentLabale = "$percent%"
        val w = width
        val h = height
        val x = java.lang.Float.valueOf((w / 2).toFloat())
        var y: Float = java.lang.Float.valueOf((h / 2).toFloat())
        val bounds = Rect()
        percentPaint!!.getTextBounds(percentLabale, 0, percentLabale.length, bounds)

            y = y+ (bounds.height() / 2).toFloat()

        canvas.drawText(percentLabale, x, y!!, percentPaint!!)

    }

    private fun adjustAlpha(@ColorInt color: Int, factor: Float?): Int {
        if (factor != null) {
            if (factor > 1f || factor < 0f) {
                return color
            }
        }
        val alpha = Math.round(Color.alpha(color) * factor!!)
        return Color.argb(alpha, Color.red(color), Color.green(color), Color.blue(color))

    }

    fun setAprogressWithTime(toPROGRESS: Int, timeOfProgress: Long) {
        if (toPROGRESS < this.progress) return


        val prevProgress = this.progress
        val valueAnimator = ValueAnimator.ofInt(prevProgress, toPROGRESS)
        val delta = Math.abs(this.progress - prevProgress)

        valueAnimator.duration = timeOfProgress
        valueAnimator.start()
        valueAnimator.addUpdateListener { animation ->
            progress = animation.animatedValue as Int
            setProgress(progress)
        }

    }

    //save state ----------------------------------------------------------------------- in  orientation

    override fun onSaveInstanceState(): Parcelable? {
        val vss = valueSaveState(super.onSaveInstanceState())
        vss.value = getProgress()
        vss.valueDRAW = valueDRAW
        return vss
    }

    override fun onRestoreInstanceState(state: Parcelable) {

        val vss = state as valueSaveState
        super.onRestoreInstanceState(vss.superState)
        animatation = false
        setProgress(vss.value)
        animatation = true


    }

    class valueSaveState : View.BaseSavedState {
        internal var valueDRAW: Int = 0
        internal var value: Int = 0

        constructor(source: Parcel) : super(source) {
            value = source.readInt()
            valueDRAW = source.readInt()

        }

        constructor(superState: Parcelable) : super(superState) {}


        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeInt(value)
            out.writeInt(valueDRAW)
        }

        companion object {
            @SuppressLint("ParcelCreator")
            val CREATOR: Parcelable.Creator<valueSaveState> = object : Parcelable.Creator<valueSaveState> {
                override fun createFromParcel(source: Parcel): valueSaveState {
                    return valueSaveState(source)
                }

                override fun newArray(size: Int): Array<valueSaveState?> {
                    return arrayOfNulls(size)
                }
            }
        }

        override fun describeContents(): Int {
            return 0
        }

        object CREATOR : Parcelable.Creator<valueSaveState> {
            override fun createFromParcel(parcel: Parcel): valueSaveState {
                return valueSaveState(parcel)
            }

            override fun newArray(size: Int): Array<valueSaveState?> {
                return arrayOfNulls(size)
            }
        }
    }


}