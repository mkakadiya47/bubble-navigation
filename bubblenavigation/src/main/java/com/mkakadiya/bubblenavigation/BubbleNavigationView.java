package com.mkakadiya.bubblenavigation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BubbleNavigationView extends FrameLayout {
    
    private LinearLayout navigationContainer;
    private List<BubbleNavigationItem> items;
    private int selectedItemPosition = 0;
    private OnNavigationItemSelectedListener listener;
    
    @ColorInt
    private int backgroundColor = Color.WHITE;
    @ColorInt
    private int selectedIconColor = Color.parseColor("#2196F3");
    @ColorInt
    private int unselectedIconColor = Color.parseColor("#757575");
    @ColorInt
    private int selectedTextColor = Color.parseColor("#2196F3");
    
    private float elevation = 8f;
    
    public BubbleNavigationView(@NonNull Context context) {
        super(context);
        init(context, null);
    }

    public BubbleNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BubbleNavigationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        items = new ArrayList<>();
        
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.BubbleNavigationView);
            backgroundColor = typedArray.getColor(R.styleable.BubbleNavigationView_backgroundColor, backgroundColor);
            selectedIconColor = typedArray.getColor(R.styleable.BubbleNavigationView_selectedIconColor, selectedIconColor);
            unselectedIconColor = typedArray.getColor(R.styleable.BubbleNavigationView_unselectedIconColor, unselectedIconColor);
            selectedTextColor = typedArray.getColor(R.styleable.BubbleNavigationView_selectedTextColor, selectedTextColor);
            elevation = typedArray.getDimension(R.styleable.BubbleNavigationView_navigationElevation, elevation);
            typedArray.recycle();
        }
        
        setupContainer();
    }
    
    private void setupContainer() {
        navigationContainer = new LinearLayout(getContext());
        navigationContainer.setOrientation(LinearLayout.HORIZONTAL);
        navigationContainer.setBackgroundColor(backgroundColor);
        navigationContainer.setElevation(elevation);
        
        LayoutParams params = new LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.WRAP_CONTENT
        );
        params.gravity = Gravity.BOTTOM;
        
        addView(navigationContainer, params);
    }
    
    public void addNavigationItem(BubbleNavigationItem item) {
        items.add(item);
        
        BubbleNavigationItemView itemView = new BubbleNavigationItemView(getContext());
        itemView.setItem(item);
        itemView.setIconColor(unselectedIconColor);
        itemView.setTextColor(selectedTextColor);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1.0f
        );
        
        final int position = items.size() - 1;
        itemView.setOnClickListener(v -> setSelectedItem(position));
        
        navigationContainer.addView(itemView, params);
        
        if (items.size() == 1) {
            itemView.setSelected(true);
            itemView.setIconColor(selectedIconColor);
        }
    }
    
    public void setSelectedItem(int position) {
        if (position < 0 || position >= items.size() || position == selectedItemPosition) {
            return;
        }
        
        // Deselect previous item
        BubbleNavigationItemView previousView = (BubbleNavigationItemView) navigationContainer.getChildAt(selectedItemPosition);
        previousView.setSelected(false);
        previousView.setIconColor(unselectedIconColor);
        
        // Select new item
        selectedItemPosition = position;
        BubbleNavigationItemView currentView = (BubbleNavigationItemView) navigationContainer.getChildAt(selectedItemPosition);
        currentView.setSelected(true);
        currentView.setIconColor(selectedIconColor);
        
        if (listener != null) {
            listener.onNavigationItemSelected(position, items.get(position));
        }
    }
    
    public int getSelectedItemPosition() {
        return selectedItemPosition;
    }
    
    public void setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener listener) {
        this.listener = listener;
    }
    
    public interface OnNavigationItemSelectedListener {
        void onNavigationItemSelected(int position, BubbleNavigationItem item);
    }
}
