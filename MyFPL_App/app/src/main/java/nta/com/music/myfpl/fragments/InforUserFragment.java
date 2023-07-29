package nta.com.music.myfpl.fragments;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.os.Looper;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.ShadowLayout;
import com.makeramen.roundedimageview.RoundedImageView;

import nta.com.music.myfpl.R;
import render.animations.Fade;
import render.animations.Render;


public class InforUserFragment extends Fragment {
    ViewPager2 viewPager;
    ImageView img_gone_1;
    ImageView img_gone_2;
    LinearLayout layout_first, layout_third;
    TextView full_name;
    RoundedImageView img_avatar;

    private static final int Learning=1;
    private static final int Person=0;
    public InforUserFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_infor_user, container, false);

        img_gone_1 = view.findViewById(R.id.img_gone_1);
        img_gone_2 = view.findViewById(R.id.img_gone_2);
        img_avatar = view.findViewById(R.id.img_avatar);
        full_name = view.findViewById(R.id.full_name);
        layout_first = view.findViewById(R.id.layout_first);
        layout_third = view.findViewById(R.id.layout_third);


        Window window = requireActivity().getWindow();
        int newStatusBarColor = ContextCompat.getColor(requireContext(), R.color.black);
        window.setStatusBarColor(newStatusBarColor);


        viewPager = view.findViewById(R.id.pager);
        InforUserAdapter inforUserAdapter = new InforUserAdapter(requireActivity());
        viewPager.setAdapter(inforUserAdapter);
//        HingeTransformation hingeTransformation = new HingeTransformation();
//        viewPager.setPageTransformer(hingeTransformation);


        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case Learning: case Person:{
                        setVisibility();
                        break;
                    }
                }


            }
        });


        return view;

    }

    private void setVisibility(){
        int v = (viewPager.getCurrentItem() == 0) ? View.VISIBLE : View.GONE;

        img_gone_1.setVisibility(v);
        img_gone_2.setVisibility(v);
        layout_third.setVisibility(v);
        Render render = new Render(requireContext());
        if(v == View.VISIBLE) {
            render.setAnimation(Fade.InDown(img_avatar).setDuration(100L));
            img_avatar.setVisibility(v);
        } else {
            render.setAnimation(Fade.OutDown(img_avatar).setDuration(100L));
            img_avatar.setVisibility(v);
        }
        render.start();
        layout_first.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

    }


    public static class UserInfor extends Fragment{
        int state=0;
        ShadowLayout Learning_Path,Personal_Path;
        public static InforUserFragment.UserInfor newInstance(int i) {
            Bundle args = new Bundle();
            args.putInt("state",i);
            InforUserFragment.UserInfor fragment = new InforUserFragment.UserInfor();
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments()!=null){
                state = getArguments().getInt("state");

            }
        }

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.inforuser_detail, container, false);
            Learning_Path = view.findViewById(R.id.Learning_Path);
            Personal_Path = view.findViewById(R.id.Personal_Path);
//            ScrollView scrollview = view.findViewById(R.id.scrollview);
//            scrollview.setVerticalScrollBarEnabled(false);
            switch (state){
                case 0:{
                    Learning_Path.setVisibility(View.GONE);
                    Personal_Path.setVisibility(View.VISIBLE);
                    break;
                }
                case 1:{
                    Learning_Path.setVisibility(View.VISIBLE);
                    Personal_Path.setVisibility(View.GONE);
                    break;
                } default:{
                    Learning_Path.setVisibility(View.GONE);
                    Personal_Path.setVisibility(View.VISIBLE);
                }
            }
            return view;

        }

    }
    private class InforUserAdapter extends FragmentStateAdapter {
        public InforUserAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return UserInfor.newInstance(Person);
                case 1:
                    return UserInfor.newInstance(Learning);
            }
            return UserInfor.newInstance(Person);
        }

        @Override
        public int getItemCount() {
            return 2;
        }
    }
}
