package nta.com.music.myfpl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import nta.com.music.myfpl.fragments.Home.InformationFragment;
import nta.com.music.myfpl.fragments.HomeFragment;
import nta.com.music.myfpl.fragments.ScheduleFragment;
import nta.com.music.myfpl.fragments.UserFragment;
import nta.com.music.myfpl.model.Informations;


public class MainActivity extends AppCompatActivity {
    FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        frameLayout = findViewById(R.id.layout_fragment);
//        InformationFragment firstFragment = new InformationFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.layout_fragment, firstFragment);
//        fragmentTransaction.commit();

        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_fragment, new ScheduleFragment())
                .commit();

        ChipNavigationBar menu = findViewById(R.id.menu);
        menu.setItemSelected(R.id.bt_home, true);
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