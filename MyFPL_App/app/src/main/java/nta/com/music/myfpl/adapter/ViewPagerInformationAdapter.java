package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nta.com.music.myfpl.fragments.Home.InformationFragment;

public class ViewPagerInformationAdapter extends FragmentStateAdapter {
    public ViewPagerInformationAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return new InformationFragment();
            }
            case 1: {
                return new InformationFragment();
            }
            case 2: {
                return new InformationFragment();
            }
        }
        return new InformationFragment();
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
