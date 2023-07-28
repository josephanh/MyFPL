package nta.com.music.myfpl.utils;

import android.view.View;


import androidx.annotation.NonNull;
import androidx.viewpager2.widget.ViewPager2;

public class HingeTransformation implements ViewPager2.PageTransformer{
    @Override
    public void transformPage(View page, float position) {

        page.setTranslationX(-position*page.getWidth());

        page.setAlpha(1-Math.abs(position));


    }

}
