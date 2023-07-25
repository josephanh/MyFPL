package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nta.com.music.myfpl.fragments.Schedule.ScheduleClassTabFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleExamTabMonthFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleExamTabFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleClassTabMonthFragment;

public class ViewPagerSchedule extends FragmentStateAdapter {

    public static int CALENDAR_WEEK = 0;
    public static int CALENDAR_MONTH = 1;

    int status;

    public ViewPagerSchedule(@NonNull FragmentActivity fragmentActivity, int status) {
        super(fragmentActivity);
        this.status = status;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (status){
            case 0: {
                switch (position) {
                    case 0: return ScheduleClassTabFragment.newInstance();
                    case 1: return ScheduleExamTabFragment.newInstance();
                }
            }
            case 1: {
                switch (position) {
                    case 0: return ScheduleClassTabMonthFragment.newInstance();
                    case 1: return ScheduleExamTabMonthFragment.newInstance();
                }
            }
        }

        return new ScheduleFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
