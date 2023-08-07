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

public class ScheduleExamTabFragment extends Fragment implements OnChangeScheduleWeek {
    public static ViewPager2 viewPager_schedule_exam;

    List<Fragment> fragmentList = new ArrayList<>();
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
        setFragmentList();
        viewPager_schedule_exam = view.findViewById(R.id.viewpager_schedule);
        ViewPagerScheduleWeekAdapter pagerSchedule = new ViewPagerScheduleWeekAdapter(requireActivity(), fragmentList);
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

        viewPager_schedule_exam.setOffscreenPageLimit(7);

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
        fragmentList.add(ScheduleFragment.newInstance(0, 1));
        fragmentList.add(ScheduleFragment.newInstance(1, 1));
        fragmentList.add(ScheduleFragment.newInstance(2, 1));
        fragmentList.add(ScheduleFragment.newInstance(3, 1));
        fragmentList.add(ScheduleFragment.newInstance(4, 1));
        fragmentList.add(ScheduleFragment.newInstance(5, 1));
        fragmentList.add(ScheduleFragment.newInstance(6, 1));
    }
}