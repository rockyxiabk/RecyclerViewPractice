package com.rocky.recyclerview.itemAnimator;

import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Description : com.rocky.recyclerview.itemAnimator
 *
 * @author : rocky
 * @Create Time : 2018/8/30 下午7:58
 * @Modified Time : 2018/8/30 下午7:58
 */
public class CustomItemAnimator extends BaseItemAnimator {

    @Override
    public void setRemoveAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.alpha(0);

    }

    @Override
    public void removeAnimationEnd(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView, 1);
    }

    @Override
    public void addAnimationInit(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView, 0);
    }

    @Override
    public void setAddAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
     animator.alpha(1);
    }

    @Override
    public void addAnimationCancel(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView,1);
    }

    @Override
    public void setOldChangeAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.alpha(0);
    }

    @Override
    public void oldChangeAnimationEnd(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView,1);
    }

    @Override
    public void newChangeAnimationInit(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView,0);
    }

    @Override
    public void setNewChangeAnimation(RecyclerView.ViewHolder holder, ViewPropertyAnimatorCompat animator) {
        animator.alpha(1);
    }

    @Override
    public void newChangeAnimationEnd(RecyclerView.ViewHolder holder) {
        ViewCompat.setAlpha(holder.itemView,1);
    }
}
