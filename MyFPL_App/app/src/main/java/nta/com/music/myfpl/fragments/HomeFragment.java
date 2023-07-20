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

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                CustomFont(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {


            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                CustomFont(position);
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                CustomFont(position);
            }
        });

        return view;
    }

    private void CustomFont(int position){
        // init your font
        Typeface tf = ResourcesCompat.getFont(requireContext(), R.font.mulish_bold);
        ViewGroup vg = (ViewGroup) tabs.getChildAt(0);
        int tabsCount = vg.getChildCount();
        for (int j = 0; j < tabsCount; j++) {
            Log.d("TAG>>>", "CustomFont: "+j);
            ViewGroup vgTab = (ViewGroup) vg.getChildAt(j);
            int tabChildsCount = vgTab.getChildCount();
            for (int i = 0; i < tabChildsCount; i++) {
                View tabViewChild = vgTab.getChildAt(i);
                // Get TextView Element
                if (tabViewChild instanceof TextView) {
                    // change font
                    ((TextView) tabViewChild).setTypeface(tf);
                    // change size
                    ((TextView) tabViewChild).setTextSize(20);
                    // change color
                    if(j == position) {
                        ((TextView) tabViewChild).setTextColor(getResources().getColor(R.color.primary));
                    } else {
                        ((TextView) tabViewChild).setTextColor(getResources().getColor(R.color.grey_light));
                    }
                    // change padding
                    tabViewChild.setPadding(0, 0, 0, 0);
                    //..... etc...
                }
            }
        }

    }


}