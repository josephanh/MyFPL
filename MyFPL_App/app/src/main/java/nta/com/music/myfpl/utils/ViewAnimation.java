package nta.com.music.myfpl.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;

public class ViewAnimation {
    public void hide(final View view) {
        view.animate()
                .alpha(0f) // Giảm độ trong suốt
                .scaleX(0.8f) // Giảm tỷ lệ chiều ngang
                .scaleY(0.8f) // Giảm tỷ lệ chiều dọc
                .setDuration(200) // Thời gian animation (millisecond)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.GONE);
                    }
                })
                .start();
    }
    public void show(final View view) {
        view.animate()
                .alphaBy(1f) // Giảm độ trong suốt
                .scaleX(1f) // Giảm tỷ lệ chiều ngang
                .scaleY(1f) // Giảm tỷ lệ chiều dọc
                .setDuration(500) // Thời gian animation (millisecond)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        view.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

}
