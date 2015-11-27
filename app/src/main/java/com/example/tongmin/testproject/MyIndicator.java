package com.example.tongmin.testproject;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by TongMin on 2015/11/27.
 */
public class MyIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private LinearLayout linearContain;
    private ViewPager viewPager;
    private float lineHeight ;
    private Paint linePaint ;
    private int lineColor ;
    private CustomeTextViewInter customeTextViewInter;
    private Context context;
    public MyIndicator(Context context) {
        this(context, null, 0);
    }

    public interface CustomeTextViewInter {
        TextView unSelect(TextView tv);

        TextView selected(TextView tv);
    }

    public void setCustomeTextViewInter(CustomeTextViewInter customeTextViewInter) {
        this.customeTextViewInter = customeTextViewInter;
    }

    public MyIndicator(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyIndicator, defStyleAttr, 0);
        lineHeight = a.getDimension(R.styleable.MyIndicator_lineHeight, 36);
        Log.e("xhc","看看单位是什么"+lineHeight);
        lineColor = a.getColor(R.styleable.MyIndicator_lineColor, Color.BLACK);

        a.recycle();

        init(context);
    }

    private void init(Context context) {
        this.context = context;
        this.setHorizontalScrollBarEnabled(false);
        linearContain = new LinearLayout(context);
        linearContain.setOrientation(LinearLayout.HORIZONTAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.addView(linearContain, params);
        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(lineColor);
    }

    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
        setHeader();
    }

    public void setHeader() {
        if (this.viewPager != null) {
            for (int i = 0; i < viewPager.getChildCount(); ++i) {
                addTextView(viewPager.getAdapter().getPageTitle(i).toString());
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    private void addTextView(String text) {
        //判断用户是否自定义了textview
        TextView textView = new Text(context);
        if (customeTextViewInter != null) {
            textView = customeTextViewInter.unSelect(textView);
            if(textView == null){
                throw new RuntimeException("customeTextViewInter textview == null");
            }
        }
        textView.setText(text);
        linearContain.addView(textView);
    }

    private class Text extends TextView {

        public Text(Context context) {
            super(context);
            this.init(context);
        }

        private void init(Context context) {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            this.setLayoutParams(params);
            int size = dip2px(context,10);
            this.setPadding(size,0,size,0);

        }

    }
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
