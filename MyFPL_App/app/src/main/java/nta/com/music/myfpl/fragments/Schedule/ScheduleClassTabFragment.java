package nta.com.music.myfpl.fragments.Schedule;

import static nta.com.music.myfpl.fragments.ScheduleFragment.tabsWeek;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ViewPagerScheduleWeek;
import nta.com.music.myfpl.utils.VerticalFlipTransformation;

public class ScheduleClassTabFragment extends Fragment {
    public static ViewPager2 viewPager_schedule;
    int currentItem = 0;
    public ScheduleClassTabFragment() {
        // Required empty public constructor
    }

    public static ScheduleClassTabFragment newInstance(int position) {
        ScheduleClassTabFragment fragment = new ScheduleClassTabFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
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
        viewPager_schedule = view.findViewById(R.id.viewpager_schedule);
        ViewPagerScheduleWeek pagerSchedule = new ViewPagerScheduleWeek(requireActivity());
        viewPager_schedule.setAdapter(pagerSchedule);
        viewPager_schedule.setPageTransformer(new VerticalFlipTransformation());



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
}