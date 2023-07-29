package nta.com.music.myfpl.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import nta.com.music.myfpl.fragments.Home.InformationFragment;
import nta.com.music.myfpl.fragments.Notification.AttendanceNoticationFragment;
import nta.com.music.myfpl.fragments.Notification.SchoolNotificationFragment;

public class NotificationControllerAdapter extends FragmentStateAdapter {
    public NotificationControllerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return new SchoolNotificationFragment();
            }
            case 1: {
                return new AttendanceNoticationFragment();
            }
        }
        return new SchoolNotificationFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
