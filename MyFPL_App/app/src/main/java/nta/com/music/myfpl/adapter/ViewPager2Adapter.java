package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nta.com.music.myfpl.fragments.Home.InfomationFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {
    public ViewPager2Adapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return new InfomationFragment();
            }
            case 1: {
                return new InfomationFragment();
            }
            case 2: {
                return new InfomationFragment();
            }
        }
        return new InfomationFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
