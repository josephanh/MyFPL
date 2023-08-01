package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nta.com.music.myfpl.fragments.HomeFragment;
import nta.com.music.myfpl.fragments.NotificationFragment;
import nta.com.music.myfpl.fragments.ScheduleWeekFragment;
import nta.com.music.myfpl.fragments.UserFragment;

public class AdapterHome extends FragmentStateAdapter{

    public AdapterHome(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
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
                fragment = new NotificationFragment();
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

    @Override
    public int getItemCount() {
        return 4;
    }
}
