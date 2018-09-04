package com.rocky.recyclerview.adapter.callback;

import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rocky.recyclerview.adapter.SwipeDeleteAdapter;
import com.rocky.recyclerview.adapter.callback.ItemTouchHelperAdapter;

import static android.support.v7.widget.helper.ItemTouchHelper.ACTION_STATE_DRAG;

/**
 * Description : com.rocky.recyclerview.adapter
 *
 * @author : rocky
 * @Create Time : 2018/8/31 上午9:30
 * @Modified Time : 2018/8/31 上午9:30
 */
public class SwipeItemTouchHelperCallback extends ItemTouchHelper.Callback {
    private ItemTouchHelperAdapter mAdapter;

    public SwipeItemTouchHelperCallback(ItemTouchHelperAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    /**
     * 该方法用于返回可以滑动的方向，比如说允许从右到左侧滑，允许上下拖动等
     *
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
//        int dragFlag = ItemTouchHelper.UP | ItemTouchHelper.DOWN;//允许上下的拖动
        int swipeFlag = ItemTouchHelper.START;//只允许从右向左侧滑
        return makeMovementFlags(0, swipeFlag);//使用makeMovementFlags(int,int)或makeFlag(int, int)来构造我们的返回值
    }

    /**
     * 当用户拖动一个Item进行上下移动从旧的位置到新的位置的时候会调用该方法，在该方法内，我们可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，最后返回true，表示被拖动的ViewHolder已经移动到了目的位置
     *
     * @param recyclerView
     * @param viewHolder
     * @param targetViewHolder
     * @return
     */
    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder targetViewHolder) {
        //onItemMove是接口方法
        mAdapter.onItemMove(viewHolder.getAdapterPosition(), targetViewHolder.getAdapterPosition());
        return true;
    }

    /**
     * 当用户左右滑动Item达到删除条件时，会调用该方法，一般手指触摸滑动的距离达到RecyclerView宽度的一半时，再松开手指，此时该Item会继续向原先滑动方向滑过去并且调用onSwiped方法进行删除，否则会反向滑回原来的位置
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        //onItemDissmiss是接口方法
        mAdapter.onItemDissmiss(viewHolder.getAdapterPosition());
    }

    /**
     * 该方法返回true时，表示支持长按拖动，即长按ItemView后才可以拖动，我们遇到的场景一般也是这样的
     *
     * @return 默认true
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * 该方法返回true时，表示如果用户触摸并左右滑动了View，那么可以执行滑动删除操作，即可以调用到onSwiped()方法
     *
     * @return 默认是返回true
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    /**
     * 从静止状态变为拖拽或者滑动的时候会回调该方法，参数actionState表示当前的状态
     *
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 当用户操作完毕某个item并且其动画也结束后会调用该方法，一般我们在该方法内恢复ItemView的初始状态，防止由于复用而产生的显示错乱问题
     *
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    //限制ImageView长度所能增加的最大值
    private double ICON_MAX_SIZE = 50;
    //ImageView的初始长宽
    private int fixWidth = 150;
    /**
     * 实现我们自定义的交互规则或者自定义的动画效果
     *
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState==ItemTouchHelper.ACTION_STATE_SWIPE){//左右滑动
            //如果dX小于等于删除方块的宽度，那么我们把该方块滑出来
            if (Math.abs(dX) <= getSlideLimitation(viewHolder)){
                viewHolder.itemView.scrollTo(-(int) dX,0);
            } else if (Math.abs(dX) <= recyclerView.getWidth() / 2){
                //如果dX还未达到能删除的距离，此时慢慢增加“眼睛”的大小，增加的最大值为ICON_MAX_SIZE
                double distance = (recyclerView.getWidth() / 2 -getSlideLimitation(viewHolder));
                double factor = ICON_MAX_SIZE / distance;
                double diff =  (Math.abs(dX) - getSlideLimitation(viewHolder)) * factor;
                ((SwipeDeleteAdapter.ViewHolder)viewHolder).tvDelete.setText("删除");   //把文字去掉
            }
        }else {
            //其他操作不做处理
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    }
    /**
     * 获取删除方块的宽度
     */
    public float getSlideLimitation(RecyclerView.ViewHolder viewHolder){
        SwipeDeleteAdapter.ViewHolder holder = (SwipeDeleteAdapter.ViewHolder) viewHolder;
        return holder.getDeleteActionWidth();
    }
}
