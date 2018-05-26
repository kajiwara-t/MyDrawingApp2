package com.example.koros.mydrawingapp2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;


public class DrawView extends View implements View.OnTouchListener{

    private Paint paint;

    private ArrayList<Path> PathList = new ArrayList<Path>();
    private Path path;

    public DrawView(Context context){
        super(context);
        setOnTouchListener(this);

        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        for(Path path : PathList){
            canvas.drawPath(path,paint);
        }
    }


    @Override
    public boolean onTouch(View v, MotionEvent event){
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                path = new Path();
                path.moveTo(event.getX(),event.getY());
                PathList.add(path);
                break;

            case MotionEvent.ACTION_MOVE:
                path.lineTo(event.getX(),event.getY());
                break;

            case MotionEvent.ACTION_UP:
                path.lineTo(event.getX(),event.getY());
                break;
            default:
        }
        invalidate();
        return true;
    }
}
