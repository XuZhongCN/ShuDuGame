package com.xuzhong.game;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.EditText;

import androidx.annotation.RequiresApi;

@SuppressLint("AppCompatCustomView")
public class EditView extends EditText {
    private int row;
    private int col;
    public EditView(Context context) {
        super(context);
        init();
    }

    public EditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public EditView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private Paint paint;
    private TextPaint textPaint;
    private void init(){
        textPaint=new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(this.getTextSize());
        textPaint.setColor(0xff666666);
    }
    public void setRowAndCol(int row,int col) {
        this.row = row;
        this.col = col;
        paint=new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        this.setPadding(0,0,0,0);
        invalidate();
    }
    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        int width=getMeasuredWidth();
        int height=getMeasuredHeight();
        canvas.drawText(getText()+"",width/2-textPaint.measureText(getText()+"")/2,textPaint.getFontMetrics().bottom*5.5f,textPaint);

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
