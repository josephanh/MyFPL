package nta.com.music.myfpl;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.navigation.NavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import nta.com.music.myfpl.DTO.ListInformationResponseDTO;
import nta.com.music.myfpl.adapter.AdapterHome;
import nta.com.music.myfpl.adapter.DropDownAdapter;
import nta.com.music.myfpl.dialog.DialogLoading;
import nta.com.music.myfpl.fragments.HomeFragment;
import nta.com.music.myfpl.fragments.NotificationFragment;
import nta.com.music.myfpl.fragments.ScheduleTabFragment;
import nta.com.music.myfpl.fragments.UserFragment;
import nta.com.music.myfpl.interfaces.OnChangeSchedule;
import nta.com.music.myfpl.model.Information;
import nta.com.music.myfpl.receiver.NetworkReceiver;


public class MainActivity extends AppCompatActivity implements OnChangeSchedule {

    private final String TAG_HOME = "TAG_HOME";
    private final String TAG_SCHEDULE = "TAG_SCHEDULE";
    private final String TAG_NOTIFICATION = "TAG_NOTIFICATION";
    private final String TAG_USER = "TAG_USER";
    DropDownAdapter adapterDropDownType, adapterDropDownSubject, adapterDropDownTime;
    Button btn_apply;

    private int type = 0;
    ThreadPoolExecutor executor;
    BroadcastReceiver broadcastReceiver;

    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @SuppressLint("NonConstantResourceId")
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int position = msg.arg1;
            if (position == R.id.bt_home) {
                viewpager2Home.setCurrentItem(0);
            }
            if (position == R.id.bt_schedule) {
                viewpager2Home.setCurrentItem(1);

            }
            if (position == R.id.bt_user) {
                viewpager2Home.setCurrentItem(3);

            }
            if (position == R.id.bt_notification) {
                viewpager2Home.setCurrentItem(2);
            }
        }

    };


    private ChipNavigationBar bottomNavigation;
    FragmentManager fragmentManager;
    NavigationView navigation_choice_schedule;
    DrawerLayout drawerLayout;
    ImageButton btn_cancel;
    ViewPager2 viewpager2Home;
    AdapterHome adapterHome;

    Spinner spinner_choice_type, spinner_choice_subject, spinner_choice_time;
    DialogLoading dialogLoading;

    List<Fragment> fragmentList = new ArrayList<>();


    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        broadcastReceiver = new NetworkReceiver();


        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        fragmentList.add(new HomeFragment());
        fragmentList.add(new ScheduleTabFragment());
        fragmentList.add(new NotificationFragment());
        fragmentList.add(new UserFragment());


        bottomNavigation = findViewById(R.id.menu);
        navigation_choice_schedule = findViewById(R.id.navigation_choice_schedule);
        drawerLayout = findViewById(R.id.drawer_layout);
//        chặn không cho vuốt
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        btn_cancel = findViewById(R.id.btn_cancel);
        viewpager2Home = findViewById(R.id.viewpager2Home);
        spinner_choice_type = findViewById(R.id.spinner_choice_type);
        spinner_choice_subject = findViewById(R.id.spinner_choice_subject);
        spinner_choice_time = findViewById(R.id.spinner_choice_time);
        btn_apply = findViewById(R.id.btn_apply);

//        new Custom_dialog_NotConnection(MainActivity.this).Show();


        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

        fragmentManager = getSupportFragmentManager();

        new Thread(new Runnable() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        adapterHome = new AdapterHome(MainActivity.this, fragmentList);
                        viewpager2Home.setAdapter(adapterHome);
                        viewpager2Home.setUserInputEnabled(false);
                        viewpager2Home.setOffscreenPageLimit(2);
                    }
                });
            }
        }).start();

        bottomNavigation.setItemSelected(R.id.bt_home, true);
        setMenuNavigation();
        dialogLoading = new DialogLoading(MainActivity.this);
        dialogLoading.show();


        viewpager2Home.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                dialogLoading.hide();
            }
        });

        bottomNavigation.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onItemSelected(int i) {
                setMenuNavigation();
            }
        });

        btn_cancel.setOnClickListener(view -> {
            hideNavigationChoiceSchedule();
        });
        btn_apply.setOnClickListener(view -> {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            hideNavigationChoiceSchedule();
                            ((ScheduleTabFragment) fragmentList.get(1)).onChange(type);
                        }
                    });
                }
            }).start();
        });
    }


    public void setMenuNavigation() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.arg1 = bottomNavigation.getSelectedItemId();
                handler.sendMessage(message);
            }
        });
    }

    public void goToDetailInformation(ListInformationResponseDTO.InformationResponseDTO information) {
        Intent intent = new Intent(MainActivity.this, InformationActivity.class);
        intent.putExtra("postId", information.getId());
        startActivity(intent);
    }

    public void goToExtension(int i) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        switch (i) {
            case 0: {
                intent = new Intent(MainActivity.this, EventActivity.class);
                break;
            }
            case 1: {
                intent = new Intent(MainActivity.this, WalletActivity.class);
                break;
            }
            case 4: {
                intent = new Intent(MainActivity.this, SearchActivity.class);
                break;
            }
        }
        startActivity(intent);
    }


    public void hideNavigationChoiceSchedule() {
        drawerLayout.closeDrawer(GravityCompat.END);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void showNavigationChoiceSchedule() {
        drawerLayout.openDrawer(GravityCompat.END);


        adapterDropDownType = new DropDownAdapter(MainActivity.this, R.layout.item_spinner_selected, getListTypes(1));
        spinner_choice_type.setAdapter(adapterDropDownType);
        spinner_choice_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        adapterDropDownSubject = new DropDownAdapter(MainActivity.this, R.layout.item_spinner_selected, getListTypes(2));
        spinner_choice_subject.setAdapter(adapterDropDownSubject);
        spinner_choice_subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        adapterDropDownTime = new DropDownAdapter(MainActivity.this, R.layout.item_spinner_selected, getListTypes(3));
        spinner_choice_time.setAdapter(adapterDropDownTime);
        spinner_choice_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        setMenuNavigation();
        registerReceiver(broadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(broadcastReceiver);
    }

    private void setTimeSchedule() {

    }

    private List<String> getListTypes(int type) {
        List<String> list = new ArrayList<>();
        switch (type) {
            case 1: {
                list.add(getString(R.string.calendar_month));
                list.add(getString(R.string.calendar_week));
                break;
            }
            case 2: {
                list.add("Lập trình React Native");
                list.add("Lập trình Mobile");
                break;
            }
            case 3: {
                list.add("Tuần này");
                list.add("Tuần trước");
                list.add("Tuần sau");
                list.add("Tháng này");
                list.add("Tháng trước");
                list.add("Tháng sau");
                break;
            }
        }
        return list;
    }

    @Override
    public void onChange(int state) {
        Toast.makeText(this, "" + state, Toast.LENGTH_SHORT).show();
    }
}