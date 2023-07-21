package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nta.com.music.myfpl.fragments.Schedule.ScheduleClassTabFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleWeekFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleExamTabFragment;

public class ViewPagerSchedule extends FragmentStateAdapter {

    public ViewPagerSchedule(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return ScheduleClassTabFragment.newInstance(position);
            case 1: return new ScheduleExamTabFragment();
        }
        return new ScheduleWeekFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
