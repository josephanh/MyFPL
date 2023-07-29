package nta.com.music.myfpl.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nta.com.music.myfpl.fragments.HomeFragment;
import nta.com.music.myfpl.fragments.ScheduleWeekFragment;
import nta.com.music.myfpl.fragments.UserFragment;

public class AdapterHome extends FragmentPagerAdapter{

    public AdapterHome(@NonNull FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public int getCount() {
        return 4;
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0: {
                fragment = HomeFragment.newInstance();
                break;
            }
            case 1: {
                fragment = ScheduleWeekFragment.newInstance();
                break;
            }
            case 2: {
                fragment = new HomeFragment();
                break;
            }
            case 3: {
                fragment = UserFragment.newInstance();
                break;
            }
            default: fragment = HomeFragment.newInstance();
        }
        return fragment;
    }
}
