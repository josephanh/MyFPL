package nta.com.music.myfpl.fragments.Schedule;


import static nta.com.music.myfpl.fragments.Schedule.ScheduleWeekFragment.tabsWeek;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ViewPagerScheduleWeekAdapter;
import nta.com.music.myfpl.interfaces.OnChangeScheduleWeek;
import nta.com.music.myfpl.model.Schedule;

public class ScheduleClassTabFragment extends Fragment implements OnChangeScheduleWeek {
    public static ViewPager2 viewPager_schedule;
    int currentItem = 0;
    List<Fragment> fragmentList = new ArrayList<>();
    public ScheduleClassTabFragment() {
        // Required empty public constructor
    }

    public static ScheduleClassTabFragment newInstance() {
        ScheduleClassTabFragment fragment = new ScheduleClassTabFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_tab, container, false);
        setFragmentList();
        viewPager_schedule = view.findViewById(R.id.viewpager_schedule);
        ViewPagerScheduleWeekAdapter pagerSchedule = new ViewPagerScheduleWeekAdapter(requireActivity(), fragmentList);
        viewPager_schedule.setAdapter(pagerSchedule);
        viewPager_schedule.setOffscreenPageLimit(7);



        viewPager_schedule.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                currentItem = position;
                tabsWeek.setTabSelected(position);

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        tabsWeek.setTabSelected(currentItem);

    }

    @Override
    public void onChangeSchedule(List<Schedule> list) {
        ((ScheduleFragment)fragmentList.get(0)).onChangeSchedule(list);
        ((ScheduleFragment)fragmentList.get(1)).onChangeSchedule(list);
        ((ScheduleFragment)fragmentList.get(2)).onChangeSchedule(list);
        ((ScheduleFragment)fragmentList.get(3)).onChangeSchedule(list);
        ((ScheduleFragment)fragmentList.get(4)).onChangeSchedule(list);
        ((ScheduleFragment)fragmentList.get(5)).onChangeSchedule(list);
        ((ScheduleFragment)fragmentList.get(6)).onChangeSchedule(list);
    }

    public void setFragmentList(){
        fragmentList.add(ScheduleFragment.newInstance(0, 0));
        fragmentList.add(ScheduleFragment.newInstance(1, 0));
        fragmentList.add(ScheduleFragment.newInstance(2, 0));
        fragmentList.add(ScheduleFragment.newInstance(3, 0));
        fragmentList.add(ScheduleFragment.newInstance(4, 0));
        fragmentList.add(ScheduleFragment.newInstance(5, 0));
        fragmentList.add(ScheduleFragment.newInstance(6, 0));
    }
}