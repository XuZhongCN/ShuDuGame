package com.xuzhong.game;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spanned;
import android.text.method.NumberKeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

import static com.xuzhong.game.ShuDu.generateShuDu;

public class MainActivity extends AppCompatActivity {

    private int[][] shuDu=generateShuDu();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        onButtonClick(null);


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    public void onButtonClick(View view) {
        shuDu=generateShuDu();
        LinearLayout linear_main=findViewById(R.id.linear_main);
        linear_main.removeAllViews();
        WindowManager wm = this.getWindowManager();
        int width = wm.getDefaultDisplay().getWidth();
        Random random=new Random();
        for (int j = 0; j < 9; j++) {
            View item= LayoutInflater.from(this).inflate(R.layout.item,linear_main,false);
            LinearLayout linear_item=item.findViewById(R.id.linear_item);
            for (int i = 0; i < linear_item.getChildCount(); i++) {
                ShowView showView= (ShowView) linear_item.getChildAt(i);
                showView.setTextSize(20);
                showView.setText(shuDu[j][i]+"");
                showView.setRowAndCol(j,i);
                showView.setTextColor(Color.BLACK);
                int r=random.nextInt(1000);
                if(r>400){
                    EditView editView=new EditView(this);
                    ViewGroup.LayoutParams lp= showView.getLayoutParams();
                    editView.setLayoutParams(lp);
                    editView.setBackground(null);
                    editView.setRowAndCol(j,i);
//                    editView.setY(editView.getY()+10);
                    editView.setKeyListener(new NumberKeyListener() {
                        /**
                         *
                         * @return 返回哪些希望可以被输入的字符, 默认不允许输入
                         */
                        @Override
                        protected char[] getAcceptedChars() {
                            //chars 数组中包含了可以EditText可接受得字符。
                            char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                            return chars;
                            //return new char[0];
                        }
                        /**
                         *
                         * @return 0：无键盘,键盘弹不出来
                         *          1：英文键盘
                         *          2：模拟键盘
                         *          3：数字键盘
                         */
                        @Override
                        public int getInputType() {
                            return 3;
                        }

                    });
                    linear_item.removeViewAt(i);
                    linear_item.addView(editView,i);
                }
            }
            linear_main.addView(item);
            LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) linear_item.getLayoutParams();
            layoutParams.height=width/9;
            linear_item.setLayoutParams(layoutParams);
        }
    }

    public void onShowAnswerClick(View view) {
        LinearLayout linear_main=findViewById(R.id.linear_main);
        for (int j = 0; j < 9; j++) {
            LinearLayout linear_item= (LinearLayout) linear_main.getChildAt(j);
            for (int i = 0; i < linear_item.getChildCount(); i++) {
                    View child= linear_item.getChildAt(i);
                    if(child instanceof EditView){
                        ((EditView)child).setText(shuDu[j][i]+"");
                    }
            }
        }
    }

}