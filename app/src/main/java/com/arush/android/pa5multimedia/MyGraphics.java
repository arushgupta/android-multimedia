package com.arush.android.pa5multimedia;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by arushgupta on 05/06/16.
 */
public class MyGraphics extends View {

    public MyGraphics(Context context) {
        super(context);
    }

    public MyGraphics(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Setup Paint
        Paint paint = new Paint();
        paint.setStrokeWidth(2.0f);

        //Change color
        paint.setColor(Color.WHITE);

        //Draw Circle
        canvas.drawCircle(getWidth()/2.0f, getHeight()/2.0f, getWidth()/4.0f, paint);

        //Draw a line from middle to touch point
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(2.0f);
        canvas.drawLine(getWidth()/2.0f, getHeight()/2.0f, endX, endY, paint);

    }

    private float endX,endY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            endX = event.getX();
            endY = event.getY();
            invalidate();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
