package com.addedfooddelivery_user.home.model;

import android.graphics.drawable.Drawable;

public class ProfileItem {
    String itemTitle;
    String itemSubTitle;
    Drawable activeIcon;
    Drawable inActiveIcon;
    private Class intentClass;

    public ProfileItem(String itemTitle, String itemSubTitle, Drawable activeIcon, Drawable inActiveIcon, Class intentClass) {
        this.itemTitle = itemTitle;
        this.itemSubTitle = itemSubTitle;
        this.activeIcon = activeIcon;
        this.inActiveIcon = inActiveIcon;
        this.intentClass = intentClass;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemSubTitle() {
        return itemSubTitle;
    }

    public void setItemSubTitle(String itemSubTitle) {
        this.itemSubTitle = itemSubTitle;
    }

    public Drawable getActiveIcon() {
        return activeIcon;
    }

    public void setActiveIcon(Drawable activeIcon) {
        this.activeIcon = activeIcon;
    }

    public Drawable getInActiveIcon() {
        return inActiveIcon;
    }

    public void setInActiveIcon(Drawable inActiveIcon) {
        this.inActiveIcon = inActiveIcon;
    }

    public Class getIntentClass() {
        return intentClass;
    }

    public void setIntentClass(Class intentClass) {
        this.intentClass = intentClass;
    }
}
