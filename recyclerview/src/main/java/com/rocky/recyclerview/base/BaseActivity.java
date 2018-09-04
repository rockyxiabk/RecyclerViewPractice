package com.rocky.recyclerview.base;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;

/**
 * Description : com.rocky.recyclerview
 *
 * @author : rocky
 * @Create Time : 2018/8/29 下午12:04
 * @Modified Time : 2018/8/29 下午12:04
 */
public abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindAdapterScreen(this, getApplication());
        setContentView(getResourcesId());
        initView();
    }

    protected abstract int getResourcesId();

    protected abstract void initView();

    float sNoncompatDensity;
    float sNoncompatScaleDensity;

    private void bindAdapterScreen(@Nullable Activity activity, @Nullable final Application application) {
        DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (sNoncompatDensity == 0) {
            sNoncompatDensity = displayMetrics.density;
            sNoncompatScaleDensity = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration newConfig) {
                    if (newConfig != null && newConfig.fontScale > 0) {
                        sNoncompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }
        int targetDensity = displayMetrics.widthPixels / 360;//(360代表着360dp，屏幕宽度的最大限制，由设计稿决定)
        float targetScaleDensity = targetDensity * (sNoncompatScaleDensity / sNoncompatDensity);
        int targetDensityDpi = 160 * targetDensity;

        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetScaleDensity;
        displayMetrics.densityDpi = targetDensityDpi;

        DisplayMetrics activityDensity = activity.getResources().getDisplayMetrics();
        activityDensity.density = targetDensity;
        activityDensity.densityDpi = targetDensityDpi;
        activityDensity.scaledDensity = targetScaleDensity;
    }

    @Override
    protected void onDestroy() {
        cancelAdaptScreen(this);
        super.onDestroy();
    }

    public void cancelAdaptScreen(final Activity activity) {
        final DisplayMetrics systemDm = Resources.getSystem().getDisplayMetrics();
        final DisplayMetrics appDm = getApplication().getResources().getDisplayMetrics();
        final DisplayMetrics activityDm = activity.getResources().getDisplayMetrics();

        activityDm.density = systemDm.density;
        activityDm.scaledDensity = systemDm.scaledDensity;
        activityDm.densityDpi = systemDm.densityDpi;

        appDm.density = systemDm.density;
        appDm.scaledDensity = systemDm.scaledDensity;
        appDm.densityDpi = systemDm.densityDpi;
    }
}
