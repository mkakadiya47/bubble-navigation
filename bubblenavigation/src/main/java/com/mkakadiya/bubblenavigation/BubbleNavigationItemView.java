package com.mkakadiya.bubblenavigation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.core.content.ContextCompat;

public class BubbleNavigationItemView extends LinearLayout {
    
    private ImageView iconView;
    private TextView titleView;
    private LinearLayout bubbleContainer;
    private BubbleNavigationItem item;
    private boolean isSelected = false;
    
    @ColorInt
    private int iconColor = Color.GRAY;
    @ColorInt
    private int textColor = Color.BLACK;
    
    public BubbleNavigationItemView(Context context) {
        super(context);
        init();
    }
    
    private void init() {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);
        int padding = dpToPx(12);
        setPadding(padding, padding, padding, padding);
        
        // Create bubble container
        bubbleContainer = new LinearLayout(getContext());
        bubbleContainer.setOrientation(HORIZONTAL);
        bubbleContainer.setGravity(Gravity.CENTER);
        
        GradientDrawable bubbleBackground = new GradientDrawable();
        bubbleBackground.setCornerRadius(dpToPx(20));
        bubbleBackground.setColor(Color.parseColor("#E3F2FD"));
        bubbleContainer.setBackground(bubbleBackground);
        
        int bubblePadding = dpToPx(8);
        bubbleContainer.setPadding(bubblePadding, bubblePadding, bubblePadding, bubblePadding);
        
        // Create icon
        iconView = new ImageView(getContext());
        iconView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        LayoutParams iconParams = new LayoutParams(dpToPx(24), dpToPx(24));
        iconParams.setMargins(0, 0, dpToPx(4), 0);
        
        // Create title
        titleView = new TextView(getContext());
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        titleView.setTextColor(textColor);
        titleView.setAlpha(0f);
        titleView.setMaxLines(1);
        
        bubbleContainer.addView(iconView, iconParams);
        bubbleContainer.addView(titleView);
        
        addView(bubbleContainer);
        
        // Initially hide bubble
        bubbleContainer.setScaleX(0f);
        bubbleContainer.setScaleY(0f);
    }
    
    public void setItem(BubbleNavigationItem item) {
        this.item = item;
        iconView.setImageResource(item.getIconResId());
        titleView.setText(item.getTitle());
    }
    
    public void setSelected(boolean selected) {
        if (isSelected == selected) return;
        
        isSelected = selected;
        
        if (selected) {
            animateBubbleExpand();
        } else {
            animateBubbleCollapse();
        }
    }
    
    private void animateBubbleExpand() {
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(0f, 1f);
        scaleAnimator.setDuration(400);
        scaleAnimator.setInterpolator(new OvershootInterpolator());
        scaleAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            bubbleContainer.setScaleX(value);
            bubbleContainer.setScaleY(value);
        });
        scaleAnimator.start();
        
        titleView.animate()
            .alpha(1f)
            .setDuration(300)
            .setStartDelay(100)
            .start();
    }
    
    private void animateBubbleCollapse() {
        titleView.animate()
            .alpha(0f)
            .setDuration(200)
            .start();
        
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1f, 0f);
        scaleAnimator.setDuration(300);
        scaleAnimator.setStartDelay(100);
        scaleAnimator.addUpdateListener(animation -> {
            float value = (float) animation.getAnimatedValue();
            bubbleContainer.setScaleX(value);
            bubbleContainer.setScaleY(value);
        });
        scaleAnimator.start();
    }
    
    public void setIconColor(@ColorInt int color) {
        this.iconColor = color;
        iconView.setColorFilter(color);
    }
    
    public void setTextColor(@ColorInt int color) {
        this.textColor = color;
        titleView.setTextColor(color);
    }
    
    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            getContext().getResources().getDisplayMetrics()
        );
    }
}
