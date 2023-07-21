package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nta.com.music.myfpl.fragments.Schedule.ScheduleWeekFragment;

public class ViewPagerScheduleWeek extends FragmentStateAdapter {
    public ViewPagerScheduleWeek(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return ScheduleWeekFragment.newInstance();
            case 1: return new ScheduleWeekFragment();
        }
        return ScheduleWeekFragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 7;
    }
}
