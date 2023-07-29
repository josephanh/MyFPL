package nta.com.music.myfpl.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.muratozturk.click_shrink_effect.ClickShrinkEffect;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.NotificationControllerAdapter;
import nta.com.music.myfpl.adapter.ViewPagerInformationAdapter;

public class NotificationFragment extends Fragment {

    ViewPager2 viewpagerNotification;
    TabLayout tabNotification;
    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance(String param1, String param2) {
        NotificationFragment fragment = new NotificationFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    List<String> titleTab = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        NotificationControllerAdapter adapter = new NotificationControllerAdapter(requireActivity());
        viewpagerNotification = view.findViewById(R.id.viewpagerNotification);
        tabNotification = view.findViewById(R.id.tabNotification);
        new ClickShrinkEffect(tabNotification, 0.7f, 100L);
        viewpagerNotification.setAdapter(adapter);

        new TabLayoutMediator(tabNotification, viewpagerNotification, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("School");
                        break;
                    case 1:
                        tab.setText("Attendance");
                        break;
                }
            }
        }).attach();
        return view;
    }
}