package com.example.admin.kresnol;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;
//import android.support.v7.widget.AppCompatButton;

/**
 * Created by admin on 14.08.18.
 */

    public class SquareButton extends Button{
        public SquareButton(Context context) {
            super(context);
        }

        public SquareButton(Context context, AttributeSet attrs) {
            super(context, attrs);
        }

        public SquareButton(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
        }

        @Override
        protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
            final int width = getDefaultSize(getSuggestedMinimumWidth(),widthMeasureSpec);
            setMeasuredDimension(width, width);
        }

        /*@Override
        protected void onSizeChanged(final int w, final int h, final int oldw, final int oldh) {
            super.onSizeChanged(w, w, oldw, oldh);
        }*/
    }


