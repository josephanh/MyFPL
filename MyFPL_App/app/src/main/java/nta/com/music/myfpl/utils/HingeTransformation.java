package nta.com.music.myfpl.utils;

import android.view.View;


import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class HingeTransformation implements ViewPager2.PageTransformer{
    @Override
    public void transformPage(@NonNull View page, float position) {
        if (position < -1) {
            page.setAlpha(0f);
        } else if (position <= 1) {
            page.setAlpha(1f);
            page.setPivotX(page.getWidth() / 2f);
            page.setPivotY(page.getHeight());
            page.setRotation(15 * position);
        } else {
            page.setAlpha(0f);
        }
    }

}
