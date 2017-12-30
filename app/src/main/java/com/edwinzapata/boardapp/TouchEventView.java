package com.edwinzapata.boardapp;

/**
 * Created by edwinzapata on 22/12/17.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;

public class TouchEventView extends View{
    private SparseArray<PointF> mActivePointers;
    private Paint paint = new Paint();
    private Path path = new Path();
    Context context;
    private Paint textPaint;
    private Tiempo timerCounter = new Tiempo();

    public TouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;

        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        mActivePointers = new SparseArray<PointF>();
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(20);
    }

    private void initView() {

    }

    public void setColor(int r, int g, int b) {
        int rgb = Color.rgb(r, g, b);
        paint.setColor(rgb);
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawPath(path, paint);
        canvas.drawText("Total pointers: " + mActivePointers.size() + "tiempo: " + timerCounter.getSegundos(), 10, 40 , textPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();
        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);
        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
                timerCounter.Contar();
                path.moveTo(eventX, eventY);

                return true;
            case MotionEvent.ACTION_POINTER_DOWN: {
                // We have a new pointer. Lets add it to the list of pointers
                PointF f = new PointF();
                f.x = event.getX(pointerIndex);
                f.y = event.getY(pointerIndex);
                mActivePointers.put(pointerId, f);
                break;
            }

            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                if (timerCounter.getSegundos()==1.0){
                    System.out.println("un segundo");
                }
                break;
            case MotionEvent.ACTION_UP:
                timerCounter.Detener();
                break;
            case MotionEvent.ACTION_CANCEL: {
                mActivePointers.remove(pointerId);
                break;
            }
            default:
                return false;
        }
        invalidate();
        return true;

    }



}
