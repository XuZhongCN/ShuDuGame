package com.xuzhong.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;


@SuppressLint("AppCompatCustomView")
public class ShowView extends TextView {


    private int row;
    private int col;
    private Paint paint;

    public ShowView(Context context) {
        super(context);
    }

    public ShowView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ShowView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setRowAndCol(int row,int col) {
        this.row = row;
        this.col = col;
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width=getWidth();
        int height=getHeight();
        if(paint!=null) {
            paint.setStrokeWidth(1);
            canvas.drawLine(0, 0, 0, height, paint);
            canvas.drawLine(0, 0, width, 0, paint);
            canvas.drawLine(width, height, width, 0, paint);
            canvas.drawLine(width, height, 0, height, paint);
            paint.setStrokeWidth(4);
            if (row % 3 == 0) {
                canvas.drawLine(0, 0, width, 0, paint);

            } else if (row % 3 == 2) {
                canvas.drawLine(0, height, width, height, paint);
            }
            if (col % 3 == 0) {
                canvas.drawLine(0, 0, 0, height, paint);
            } else if (col % 3 == 2) {
                canvas.drawLine(width, 0, width, height, paint);

            }
        }
    }
}
