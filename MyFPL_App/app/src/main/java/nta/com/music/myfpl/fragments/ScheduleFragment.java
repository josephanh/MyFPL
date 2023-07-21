package nta.com.music.myfpl.fragments;

import static nta.com.music.myfpl.fragments.Schedule.ScheduleClassTabFragment.viewPager_schedule;
import static nta.com.music.myfpl.fragments.Schedule.ScheduleExamTabFragment.viewPager_schedule_exam;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ViewPagerSchedule;
import q.rorbin.verticaltablayout.TabAdapter;
import q.rorbin.verticaltablayout.VerticalTabLayout;

public class ScheduleFragment extends Fragment {

    ViewPager2 viewPager;
    public static VerticalTabLayout tabsWeek;
    public int currentItem = 0;
    TabLayout tabSchedule;
    TextView day;

//    private int currentIndex = 0;

    public ScheduleFragment() {
        // Required empty public constructor
    }

    public static ScheduleFragment newInstance(String param1, String param2) {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }


    List<String> titleTab = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        tabsWeek = view.findViewById(R.id.tab_layout);
        tabSchedule = view.findViewById(R.id.tab_schedule);

        ViewPagerSchedule adapter = new ViewPagerSchedule(requireActivity());
        viewPager.setAdapter(adapter);


        tabsWeek.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return 7;
            }

            @SuppressLint("InflateParams")
            @Override
            public View getTabItemView(int position) {
                View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_tab_vertical, null);
                day = itemView.findViewById(R.id.day_name);
                switch (position) {
                    case 0: {
                        day.setText("Mo");
                        break;
                    }
                    case 1: {
                        day.setText("Tu");
                        break;
                    }
                    case 2: {
                        day.setText("We");
                        break;
                    }
                    case 3: {
                        day.setText("Th");
                        break;
                    }
                    case 4: {
                        day.setText("Fr");
                        break;
                    }
                    case 5:  {
                        day.setText("Sa");
                        break;
                    }
                    case 6:  {
                        day.setText("Su");
                        break;
                    }
                }

                return itemView;
            }
        });
        setBackgroundItemNavigation(0);
        tabsWeek.setOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @SuppressLint({"ResourceAsColor", "UseCompatLoadingForDrawables"})
            @Override
            public void onTabSelected(View tab, int position) {
                currentItem = viewPager.getCurrentItem();
                if(currentItem == 0) {
                    viewPager_schedule.setCurrentItem(position);


                } else {
                    viewPager_schedule_exam.setCurrentItem(position);
                }
                setBackgroundItemNavigation(position);

            }

            @Override
            public void onTabReselected(View tab, int position) {

            }
        });
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

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


        return view;
    }

    private void setBackgroundItemNavigation(int position){
        ViewGroup vg = (ViewGroup) tabsWeek.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            Log.d("TAG>>>", "CustomFont: "+j);
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                // Get TextView Element
                if (tabViewChild instanceof TextView) {
                    // change color
                    if(j == position) {
                        ((TextView) tabViewChild).setTextColor(getResources().getColor(R.color.white));
                        ((TextView) tabViewChild).setBackground(getResources().getDrawable(R.drawable.item_day_vertical_slt));
                    } else {
                        ((TextView) tabViewChild).setTextColor(getResources().getColor(R.color.black));
                        ((TextView) tabViewChild).setBackground(getResources().getDrawable(R.drawable.item_day_vertical));
                    }
                }
            }
        }
    }
}