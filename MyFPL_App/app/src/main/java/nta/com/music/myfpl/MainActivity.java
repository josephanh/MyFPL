package nta.com.music.myfpl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import nta.com.music.myfpl.fragments.HomeFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleMonthFragment;
import nta.com.music.myfpl.fragments.ScheduleFragment;
import nta.com.music.myfpl.fragments.UserFragment;


public class MainActivity extends AppCompatActivity {

    private final String TAG_HOME = "TAG_HOME";
    private final String TAG_SCHEDULE = "TAG_SCHEDULE";
    private final String TAG_NOTIFICATION = "TAG_NOTIFICATION";
    private final String TAG_USER = "TAG_USER";
    ThreadPoolExecutor executor;
    private final Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
//            Fragment fragment = new HomeFragment();
            String tag = null;
            int idSelected = msg.arg1;
            if(idSelected == R.id.bt_home) {
//                fragment = new HomeFragment();
                addFragmentIfNeeded(TAG_SCHEDULE, new ScheduleFragment());
                addFragmentIfNeeded(TAG_USER, new UserFragment());
                tag = TAG_HOME;
            }
            if(idSelected == R.id.bt_schedule) {
//                fragment = new ScheduleFragment();
                tag = TAG_SCHEDULE;
            }
            if(idSelected == R.id.bt_user) {
//                fragment = new UserFragment();
                tag = TAG_USER;
            }
            showFragment(tag);
        }
    };


    private ChipNavigationBar bottomNavigation;
    private FragmentManager fragmentManager;
    private FrameLayout layout_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.menu);
        layout_fragment = findViewById(R.id.layout_fragment);

        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
//        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
//        getWindow().setStatusBarColor(Color.TRANSPARENT);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        fragmentManager = getSupportFragmentManager();

        addFragmentIfNeeded(TAG_HOME, new ScheduleMonthFragment());



        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);


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

    private void addFragmentIfNeeded(String tag, Fragment fragment) {
        Fragment existingFragment = fragmentManager.findFragmentByTag(tag);
        if (existingFragment == null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(R.id.layout_fragment, fragment, tag);
            transaction.addToBackStack(null);
            transaction.commit();
            layout_fragment.setVisibility(View.GONE);
        }
    }
    private void showFragment(String tag) {
        layout_fragment.setVisibility(View.VISIBLE);
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setCustomAnimations(R.animator.fade_in,R.animator.fade_out);
            transaction.replace(R.id.layout_fragment, fragment);
            transaction.commit();
        }
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