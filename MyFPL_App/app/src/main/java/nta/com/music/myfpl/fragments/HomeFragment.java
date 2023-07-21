package nta.com.music.myfpl.fragments;

import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ViewPager2Adapter;

public class HomeFragment extends Fragment {

    ViewPager2 viewPager2;
    TabLayout tabs;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPager2Adapter adapter = new ViewPager2Adapter(requireActivity());
        viewPager2 = view.findViewById(R.id.viewpager2);
        tabs = view.findViewById(R.id.tab_layout);

        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Study");
                        break;
                    case 1:
                        tab.setText("Active");
                        break;
                    case 2:
                        tab.setText("Tuition");
                        break;
                }
            }
        }).attach();

        return view;
    }


}