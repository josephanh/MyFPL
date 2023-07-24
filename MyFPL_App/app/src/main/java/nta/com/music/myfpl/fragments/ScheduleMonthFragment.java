package nta.com.music.myfpl.fragments;

import static nta.com.music.myfpl.adapter.ViewPagerSchedule.CALENDAR_MONTH;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ViewPagerSchedule;
import nta.com.music.myfpl.model.Schedule;

public class ScheduleMonthFragment extends Fragment {
    ViewPager2 viewPager;
    TabLayout tabSchedule;
    Handler handler = new Handler(Looper.getMainLooper());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_month,container,false);

        Utils(view);


        
        return view;
    }

    private void Utils(View view){
        viewPager = view.findViewById(R.id.viewpager);
        tabSchedule = view.findViewById(R.id.tab_schedule);

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        ViewPagerSchedule adapter = new ViewPagerSchedule(requireActivity(), CALENDAR_MONTH);
                        viewPager.setAdapter(adapter);
                        setTabSchedule();
                    }
                });
            }
        }).start();
    }
    private void setTabSchedule(){
        new TabLayoutMediator(tabSchedule, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Class schedule");
                        break;
                    case 1:
                        tab.setText("Exam schedule");
                        break;
                }
            }
        }).attach();

    }
}
