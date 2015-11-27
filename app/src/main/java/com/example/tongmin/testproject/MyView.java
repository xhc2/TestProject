package com.example.tongmin.testproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.tongmin.testproject.debugutil.DensityUtils;

/**
 * Created by xhc on 2015/11/26.
 */
public class MyView extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private ViewPager.OnPageChangeListener pageChangeListener;
    private LinearLayout linear;
    private Paint linePaint ;
    private Context context;
    private int currentPosition ;
    private ViewPager viewPager;
    public MyView(Context context) {
        super(context);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
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

    private void init(Context context) {
        this.context = context;
        linePaint = new Paint();
        linePaint.setColor(Color.parseColor("#1A3DFF"));
        linePaint.setAntiAlias(true);
        linePaint.setStyle(Paint.Style.FILL);

        this.setHorizontalScrollBarEnabled(false);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        linear = new LinearLayout(context);
        linear.setOrientation(LinearLayout.HORIZONTAL);
        this.addView(linear, params);
    }

    public void setViewPager(ViewPager viewPager){
        this.viewPager = viewPager;
        this.viewPager.addOnPageChangeListener(this);
        linear.removeAllViews();
        PagerAdapter adapter = viewPager.getAdapter();
        for(int i = 0 ;i < adapter.getCount() ; i++){
            addText(adapter.getPageTitle(i).toString());
        }
    }

    private void addText(String text){
        Text t = new Text(context);
        linear.addView(t);
    }

    public void scrollToChild(int position , int offset) {
        if (position >= 0 && position < linear.getChildCount()) {
            int w = linear.getChildAt(position).getLeft()+offset;
//            scrollTo(w, 0);
            currentPosition = position;
            smoothScrollTo(w,0);
            invalidate();
        }

    }

    class Text extends TextView {

        public Text(Context context) {
            super(context);
            initText(context);
        }

        void initText(Context context) {
            int size = DensityUtils.dip2px(context, 5);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            this.setText("什么鬼东西");
            this.setLayoutParams(params);
            this.setPadding(size, 0, size, 0);

        }

    }

    private float[] getCurrentViewPosition(int position){
        float[] positions = new float[2];
        if (position >= 0 && position < linear.getChildCount()) {
            positions[0] = linear.getChildAt(position).getLeft();
            positions[1] = linear.getChildAt(position).getRight();
        }
        return positions;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float height = this.getHeight();
        float[] a = getCurrentViewPosition(currentPosition);
        canvas.drawRect(a[0],(float)(height - 10) , a[1] , (float)(height- 5),linePaint);

    }
}











