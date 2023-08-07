package nta.com.music.myfpl;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import render.animations.Bounce;
import render.animations.Render;

@SuppressLint("CustomSplashScreen")
public class SplashScreenActivity extends AppCompatActivity {
    ImageView logoFPT;
    Render render;
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        logoFPT = findViewById(R.id.logoFPT);

        render = new Render(SplashScreenActivity.this);
        render.setAnimation(Bounce.InUp(logoFPT).setDuration(5000));
        logoFPT.setVisibility(View.GONE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                logoFPT.setVisibility(View.VISIBLE);
                render.start();
            }
        }, 100);


        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.bottom_slide, R.anim.top_slide);
                finishAfterTransition();
                finish();
            }
        }, 3000);
    }
    @Override
    protected void onPause() {
        super.onPause();
//        render.setAnimation(Slide.OutUp(logoFPT).setDuration(500));
//        render.start();
    }
}