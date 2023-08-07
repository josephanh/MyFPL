package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import nta.com.music.myfpl.fragments.Schedule.ScheduleFragment;

public class ViewPagerScheduleWeekAdapter extends FragmentStateAdapter {
    List<Fragment> fragmentList;


    public ViewPagerScheduleWeekAdapter(@NonNull FragmentActivity fragmentActivity, List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return fragmentList.get(0);
            case 1: return fragmentList.get(1);
            case 2: return fragmentList.get(2);
            case 3: return fragmentList.get(3);
            case 4: return fragmentList.get(4);
            case 5: return fragmentList.get(5);
            case 6: return fragmentList.get(6);
        }
        return ScheduleFragment.newInstance(0, 1);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }
}
