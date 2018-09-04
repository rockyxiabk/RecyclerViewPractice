package com.rocky.recyclerview.adapter.callback;

/**
 * Description : com.rocky.recyclerview.adapter
 *
 * @author : rocky
 * @Create Time : 2018/8/31 上午9:26
 * @Modified Time : 2018/8/31 上午9:26
 */
public interface ItemTouchHelperAdapter {
    //数据交换
    void onItemMove(int fromPosition,int toPosition);
    //数据删除
    void onItemDissmiss(int position);
}
