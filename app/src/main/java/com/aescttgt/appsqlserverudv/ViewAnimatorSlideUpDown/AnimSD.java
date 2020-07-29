package com.aescttgt.appsqlserverudv.ViewAnimatorSlideUpDown;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;

public class AnimSD {

    public static void mostrar(final View view) {
        view.setVisibility(View.VISIBLE);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = 1;
        view.setLayoutParams(layoutParams);

        view.measure(View.MeasureSpec.makeMeasureSpec(Resources.getSystem().getDisplayMetrics().widthPixels,
                View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0,
                        View.MeasureSpec.UNSPECIFIED));

        final int height = view.getMeasuredHeight();
        ValueAnimator valueAnimator = ObjectAnimator.ofInt(1, height);
        valueAnimator.addUpdateListener(animation -> {
            int value = (int) animation.getAnimatedValue();
            if (height > value) {
                ViewGroup.LayoutParams layoutParams1 = view.getLayoutParams();
                layoutParams1.height = value;
                view.setLayoutParams(layoutParams1);
            } else {
                ViewGroup.LayoutParams layoutParams1 = view.getLayoutParams();
                layoutParams1.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                view.setLayoutParams(layoutParams1);
            }
        });
        valueAnimator.start();
    }


    public static void ocultar(final View view) {
        view.post(() -> {
            final int height = view.getHeight();
            ValueAnimator valueAnimator = ObjectAnimator.ofInt(height, 0);
            valueAnimator.addUpdateListener(animation -> {
                int value = (int) animation.getAnimatedValue();
                if (value > 0) {
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.height = value;
                    view.setLayoutParams(layoutParams);
                } else {
                    view.setVisibility(View.GONE);
                }
            });
            valueAnimator.start();
        });

    }

}
