package com.kyrodevs.bottomviewexample;

import android.graphics.Bitmap;

public class BitmapState {
    private Bitmap m_icon;
    private Bitmap m_icon_pressed;

    public BitmapState(Bitmap icon, Bitmap iconPressed) {
        m_icon = icon;
        m_icon_pressed = iconPressed;
    }

    public Bitmap getM_icon() {
        return m_icon;
    }

    public Bitmap getM_icon_pressed() {
        return m_icon_pressed;
    }
}
