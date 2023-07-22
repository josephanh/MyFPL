package nta.com.music.myfpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import nta.com.music.myfpl.fragments.HomeFragment;
import nta.com.music.myfpl.fragments.ScheduleFragment;
import nta.com.music.myfpl.fragments.UserFragment;


public class MainActivity extends AppCompatActivity {

    ThreadPoolExecutor executor;
    private Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Fragment fragment = new HomeFragment();
            int idSelected = msg.arg1;
            if(idSelected == R.id.bt_home) {
                fragment = new HomeFragment();
            }
            if(idSelected == R.id.bt_schedule) {
                fragment = new ScheduleFragment();
            }
            if(idSelected == R.id.bt_user) {
                fragment = new UserFragment();
            }
            getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.pop_enter,R.animator.fade_out)
                    .replace(R.id.layout_fragment, fragment)
                    .commit();
        }
    };


    ChipNavigationBar bottomNavigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);



        bottomNavigation = findViewById(R.id.menu);
        bottomNavigation.setItemSelected(R.id.bt_home, true);
        setMenuNavigation();

        bottomNavigation.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onItemSelected(int i) {
                setMenuNavigation();
            }
        });
    }


    public void setMenuNavigation(){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = bottomNavigation.getSelectedItemId();
                handler.sendMessage(message);
            }
        });
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