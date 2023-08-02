package nta.com.music.myfpl.fragments.Schedule;

import static nta.com.music.myfpl.fragments.Schedule.ScheduleWeekFragment.tabsWeek;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ViewPagerScheduleWeek;

public class ScheduleExamTabFragment extends Fragment {
    public static ViewPager2 viewPager_schedule_exam;
    public ScheduleExamTabFragment() {
        // Required empty public constructor
    }

    public static ScheduleExamTabFragment newInstance() {
        ScheduleExamTabFragment fragment = new ScheduleExamTabFragment();
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
        viewPager_schedule_exam = view.findViewById(R.id.viewpager_schedule);
        ViewPagerScheduleWeek pagerSchedule = new ViewPagerScheduleWeek(requireActivity());
        viewPager_schedule_exam.setAdapter(pagerSchedule);
//        viewPager_schedule_exam.setPageTransformer(new VerticalFlipTransformation());

        viewPager_schedule_exam.setCurrentItem(ScheduleWeekFragment.currentItem, false);


        viewPager_schedule_exam.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                tabsWeek.setTabSelected(position);

            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}