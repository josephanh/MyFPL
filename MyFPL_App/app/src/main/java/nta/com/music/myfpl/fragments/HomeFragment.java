package nta.com.music.myfpl.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.muratozturk.click_shrink_effect.ClickShrinkEffect;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.MainActivity;
import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ViewPagerInformationAdapter;

public class HomeFragment extends Fragment {

    ViewPager2 viewPager2;
    LinearLayout btn_event, btn_wallet, btn_service, btn_form;
    TabLayout tabs;
    ImageView btn_search;
    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ViewPagerInformationAdapter adapter = new ViewPagerInformationAdapter(requireActivity());
        viewPager2 = view.findViewById(R.id.viewpager2);


        Utils(view);

        btn_search.setOnClickListener(view1 ->{
            ((MainActivity)requireContext()).goToExtension(4);
        });

        btn_event.setOnClickListener(view1 -> {
            ((MainActivity)requireContext()).goToExtension(0);
        });
        btn_wallet.setOnClickListener(view1 -> {
            ((MainActivity)requireContext()).goToExtension(1);
        });

        btn_service.setOnClickListener(view1 -> {
            ((MainActivity)requireContext()).goToExtension(2);
        });
        viewPager2.setAdapter(adapter);

        new TabLayoutMediator(tabs, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText(R.string.study);
                        break;
                    case 1:
                        tab.setText(R.string.active);
                        break;
                    case 2:
                        tab.setText(R.string.tuition);
                        break;
                }
            }
        }).attach();

        return view;
    }

    public void Utils(View view){
        tabs = view.findViewById(R.id.tab_layout);
        btn_event = view.findViewById(R.id.btn_event);
        btn_wallet = view.findViewById(R.id.btn_wallet);
        btn_service = view.findViewById(R.id.btn_service);
        btn_form = view.findViewById(R.id.btn_form);
        btn_search =view.findViewById(R.id.btn_search);

        new ClickShrinkEffect(btn_event, 0.95f, 50L);
        new ClickShrinkEffect(btn_wallet, 0.95f, 50L);
        new ClickShrinkEffect(btn_service, 0.95f, 50L);
        new ClickShrinkEffect(btn_form, 0.95f, 50L);
    }


}