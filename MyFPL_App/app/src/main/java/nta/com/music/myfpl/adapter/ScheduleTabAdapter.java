package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

import nta.com.music.myfpl.fragments.Schedule.ScheduleWeekFragment;

public class ScheduleTabAdapter extends FragmentStateAdapter {

    List<Fragment> fragmentList;
    public ScheduleTabAdapter(@NonNull FragmentActivity fragmentActivity,  List<Fragment> fragmentList) {
        super(fragmentActivity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment;
        switch (position){
            case 0: {
                fragment = fragmentList.get(0);
                break;
            }
            case 1: {
                fragment = fragmentList.get(1);
                break;
            }
            default: {
                fragment = new ScheduleWeekFragment();
                break;
            }
        }
        return  fragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
