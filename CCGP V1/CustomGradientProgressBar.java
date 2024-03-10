package com.anasbex.cgp;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import com.anasbex.cgp.R;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.ProgressBar;
/*
Copyright by AnasBex / AnazuDev 2024 CCGP-ProgressBar
EDITING RIGHTS OF CODE CREATORS IS PROHIBITED
*/
public class CustomGradientProgressBar extends ProgressBar {

    private int startColor;
    private int endColor;

    public CustomGradientProgressBar(Context context) {
        super(context);
        init(null);
    }

    public CustomGradientProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CustomGradientProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CustomGradientProgressBar);
            startColor = a.getColor(R.styleable.CustomGradientProgressBar_progressStartColor, 0xFF00FF00); // Default start color is green
            endColor = a.getColor(R.styleable.CustomGradientProgressBar_progressEndColor, 0xFFFF0000); // Default end color is red
            a.recycle();
        } else {
            startColor = 0xFF00FF00;
            endColor = 0xFFFF0000;
        }
    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);

        // Creating linear gradient shader
        LinearGradient shader = new LinearGradient(0, 0, getWidth(), 0, startColor, endColor, Shader.TileMode.CLAMP);

        // Setting shader to paint
        paint.setShader(shader);

        // Drawing rectangle according to progress bar
        canvas.drawRect(0, 0, getWidth() * getProgress() / getMax(), getHeight(), paint);
    }

    public void setGradientColors(int startColor, int endColor) {
        this.startColor = startColor;
        this.endColor = endColor;
        invalidate(); // Trigger view redraw with new colors
    }
}
