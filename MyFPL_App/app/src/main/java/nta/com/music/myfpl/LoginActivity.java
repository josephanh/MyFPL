package nta.com.music.myfpl;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.lang.reflect.Parameter;

import render.animations.Bounce;
import render.animations.Render;


public class LoginActivity extends AppCompatActivity {

    LinearLayout login_linear;
    ImageView logoFPT;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Utils();


    }

    @SuppressLint({"ObsoleteSdkInt", "ResourceAsColor"})
    private void Utils() {
//      full screen
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        

        // anh xa
        login_linear = findViewById(R.id.login_linear);
        logoFPT = findViewById(R.id.logoFPT);


//      animation
        Render render = new Render(getApplicationContext());
        //      Set Animation
        render.setAnimation(Bounce.InUp(login_linear).setDuration(5000));

        render.start();
        Render render1 = new Render(getApplicationContext());
        render1.setAnimation(Bounce.In(logoFPT).setDuration(5));
        render1.start();
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}