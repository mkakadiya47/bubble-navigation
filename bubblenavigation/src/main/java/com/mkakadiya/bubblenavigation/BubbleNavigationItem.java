package com.mkakadiya.bubblenavigation;

import androidx.annotation.DrawableRes;

public class BubbleNavigationItem {
    
    private String title;
    @DrawableRes
    private int iconResId;
    
    public BubbleNavigationItem(String title, @DrawableRes int iconResId) {
        this.title = title;
        this.iconResId = iconResId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public int getIconResId() {
        return iconResId;
    }
    
    public void setIconResId(int iconResId) {
        this.iconResId = iconResId;
    }
}
