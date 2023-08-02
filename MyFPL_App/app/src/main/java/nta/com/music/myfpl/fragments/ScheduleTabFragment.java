package nta.com.music.myfpl.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ScheduleTabAdapter;
import nta.com.music.myfpl.fragments.Schedule.ScheduleMonthFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleWeekFragment;
import nta.com.music.myfpl.interfaces.OnChangeSchedule;

public class ScheduleTabFragment extends Fragment implements OnChangeSchedule {

    ViewPager2 viewpager_schedule;
    List<Fragment> fragmentList = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_2, container, false);
        viewpager_schedule = view.findViewById(R.id.viewpager_schedule);

        fragmentList.add(new ScheduleWeekFragment());
        fragmentList.add(new ScheduleMonthFragment());

        ScheduleTabAdapter adapter = new ScheduleTabAdapter(requireActivity(), fragmentList);
        viewpager_schedule.setAdapter(adapter);
        viewpager_schedule.setUserInputEnabled(false);


        return view;
    }

    @Override
    public void onChange(int state) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        viewpager_schedule.setCurrentItem(state, true);
                    }
                });
            }
        }).start();
    }
}