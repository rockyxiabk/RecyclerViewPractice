package com.rocky.recyclerview.ItemDecoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.rocky.recyclerview.R;
import com.rocky.recyclerview.adapter.ListViewAdapter;

/**
 * Description : recyclerView 条目间距
 *
 * @author : rocky
 * @Create Time : 2017/4/20 下午5:03
 * @Modified By: rocky
 * @Modified Time : 2017/4/20 下午5:03
 */
public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private Paint mPaint;
    private Drawable mDivider;
    private int mDividerHeight = 2;//分割线高度，默认为1px
    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    public SpaceItemDecoration(Context context, int space, int dividerColor) {
        this.space = space;
        mDividerHeight = 2;
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(context.getResources().getColor(dividerColor));
        mPaint.setStyle(Paint.Style.FILL);
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = space;
        outRect.right = space;
        outRect.bottom = space;
        outRect.top = space;
        int position = parent.getChildLayoutPosition(view);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager) {
            int spanCount = ((GridLayoutManager) layoutManager).getSpanCount();
            int positionOfGroup = position % spanCount;//计算位于第几列
            int itemGroup = position / spanCount;//计算位于第几列的第几个
            Log.d("TAG,","group:"+itemGroup+"--span:"+spanCount+"--positionGroup:"+positionOfGroup);
            outRect.left=0;
            if (positionOfGroup == 0) {
                if (itemGroup==0){
                   outRect.top=0;
               }
            }
            if (positionOfGroup==1){
                outRect.right=0;
                if (itemGroup==0){
                    outRect.top=0;
                }
            }
        } else if (layoutManager instanceof LinearLayoutManager) {
            outRect.left = 0;
            outRect.right = 0;
            outRect.top = 0;
            if (position == 0) {
                outRect.bottom = 0;
            }
        }
    }

    //绘制分割线
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        drawVertical(c, parent);
        drawHorizontal(c, parent);
    }

    //绘制横向 item 分割线
    private void drawHorizontal(Canvas canvas, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getMeasuredWidth() - parent.getPaddingRight();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom() + layoutParams.bottomMargin;
            final int bottom = top + space;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }

    //绘制纵向 item 分割线
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getMeasuredHeight() - parent.getPaddingBottom();
        final int childSize = parent.getChildCount();
        for (int i = 0; i < childSize; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int left = child.getRight() + layoutParams.rightMargin;
            final int right = left + space;
            if (mDivider != null) {
                mDivider.setBounds(left, top, right, bottom);
                mDivider.draw(canvas);
            }
            if (mPaint != null) {
                canvas.drawRect(left, top, right, bottom, mPaint);
            }
        }
    }
}