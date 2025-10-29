package com.mkakadiya.bubblenavigation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;

public class BubbleToggleView extends LinearLayout {
    
    private ImageView iconView;
    private TextView titleView;
    private LinearLayout bubbleContainer;
    private boolean isActive = false;
    
    @ColorInt
    private int colorActive = Color.parseColor("#2196F3");
    @ColorInt
    private int colorInactive = Color.parseColor("#757575");
    
    private Drawable icon;
    private String title;
    private Drawable shape;
    private int iconWidth = 24;
    private int iconHeight = 24;
    
    public BubbleToggleView(Context context) {
        super(context);
        init(context, null);
    }

    public BubbleToggleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BubbleToggleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BubbleToggleView);
            colorActive = typedArray.getColor(R.styleable.BubbleToggleView_bt_colorActive, colorActive);
            colorInactive = typedArray.getColor(R.styleable.BubbleToggleView_bt_colorInactive, colorInactive);
            icon = typedArray.getDrawable(R.styleable.BubbleToggleView_bt_icon);
            title = typedArray.getString(R.styleable.BubbleToggleView_bt_title);
            shape = typedArray.getDrawable(R.styleable.BubbleToggleView_bt_shape);
            iconWidth = (int) typedArray.getDimension(R.styleable.BubbleToggleView_bt_iconWidth, dpToPx(24));
            iconHeight = (int) typedArray.getDimension(R.styleable.BubbleToggleView_bt_iconHeight, dpToPx(24));
            typedArray.recycle();
        }
        
        setupView();
    }
    
    private void setupView() {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER);
        int padding = dpToPx(12);
        setPadding(padding, padding, padding, padding);
        
        // Create bubble container
        bubbleContainer = new LinearLayout(getContext());
        bubbleContainer.setOrientation(HORIZONTAL);
        bubbleContainer.setGravity(Gravity.CENTER);
        
        if (shape != null) {
            bubbleContainer.setBackground(shape);
        } else {
            GradientDrawable bubbleBackground = new GradientDrawable();
            bubbleBackground.setCornerRadius(dpToPx(20));
            bubbleBackground.setColor(Color.parseColor("#E3F2FD"));
            bubbleContainer.setBackground(bubbleBackground);
        }
        
        int bubblePadding = dpToPx(8);
        bubbleContainer.setPadding(bubblePadding, bubblePadding, bubblePadding, bubblePadding);
        
        // Create icon
        iconView = new ImageView(getContext());
        iconView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        if (icon != null) {
            iconView.setImageDrawable(icon);
        }
        iconView.setColorFilter(colorInactive);
        
        LayoutParams iconParams = new LayoutParams(iconWidth, iconHeight);
        iconParams.setMargins(0, 0, dpToPx(4), 0);
        
        // Create title
        titleView = new TextView(getContext());
        titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        titleView.setTextColor(colorActive);
        if (title != null) {
            titleView.setText(title);
        }
        titleView.setAlpha(0f);
        titleView.setMaxLines(1);
        
        bubbleContainer.addView(iconView, iconParams);
        bubbleContainer.addView(titleView);
        
        addView(bubbleContainer);
        
        // Initially hide bubble
        bubbleContainer.setScaleX(0f);
        bubbleContainer.setScaleY(0f);
    }
    
    public void setActive(boolean active) {
        if (isActive == active) return;
        
        isActive = active;
        
        if (active) {
            animateBubbleExpand();
            iconView.setColorFilter(colorActive);
        } else {
            animateBubbleCollapse();
            iconView.setColorFilter(colorInactive);
        }
    }
    
    public boolean isActive() {
        return isActive;
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
    
    private int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp,
            getContext().getResources().getDisplayMetrics()
        );
    }
}
