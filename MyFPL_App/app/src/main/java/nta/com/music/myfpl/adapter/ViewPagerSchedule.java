package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import nta.com.music.myfpl.fragments.Schedule.ScheduleClassTabFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleExamTabMonthFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleExamTabFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleClassTabMonthFragment;
import nta.com.music.myfpl.model.Schedule;

public class ViewPagerSchedule extends FragmentStateAdapter {

    public static int CALENDAR_WEEK = 0;
    public static int CALENDAR_MONTH = 1;

    int status;

    private List<Fragment> fragmentList;
    public ViewPagerSchedule(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return fragmentList.get(0);
            case 1: return fragmentList.get(1);
        }

        return new ScheduleFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
