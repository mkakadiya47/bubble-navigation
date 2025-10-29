package com.mkakadiya.bubblenavigation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;
import java.util.List;

public class BubbleNavigationConstraintView extends ConstraintLayout {
    
    private List<BubbleToggleView> items;
    private int currentActiveItemPosition = 0;
    private BubbleNavigationChangeListener navigationChangeListener;
    
    public BubbleNavigationConstraintView(@NonNull Context context) {
        super(context);
        init();
    }

    public BubbleNavigationConstraintView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BubbleNavigationConstraintView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        items = new ArrayList<>();
    }
    
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        
        // Collect all BubbleToggleView children
        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);
            if (child instanceof BubbleToggleView) {
                BubbleToggleView toggleView = (BubbleToggleView) child;
                items.add(toggleView);
                
                final int position = items.size() - 1;
                toggleView.setOnClickListener(v -> {
                    setCurrentActiveItem(position);
                    if (navigationChangeListener != null) {
                        navigationChangeListener.onNavigationChanged(v, position);
                    }
                });
            }
        }
        
        // Set first item as active
        if (!items.isEmpty()) {
            items.get(0).setActive(true);
        }
    }
    
    public void setCurrentActiveItem(int position) {
        if (position < 0 || position >= items.size()) {
            return;
        }
        
        if (currentActiveItemPosition != position) {
            // Deactivate previous item
            if (currentActiveItemPosition >= 0 && currentActiveItemPosition < items.size()) {
                items.get(currentActiveItemPosition).setActive(false);
            }
            
            // Activate new item
            currentActiveItemPosition = position;
            items.get(currentActiveItemPosition).setActive(true);
        }
    }
    
    public int getCurrentActiveItemPosition() {
        return currentActiveItemPosition;
    }
    
    public void setNavigationChangeListener(BubbleNavigationChangeListener listener) {
        this.navigationChangeListener = listener;
    }
}
