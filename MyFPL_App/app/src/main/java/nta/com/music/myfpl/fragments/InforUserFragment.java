package nta.com.music.myfpl.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.dd.ShadowLayout;

import nta.com.music.myfpl.R;
import nta.com.music.myfpl.utils.HingeTransformation;


public class InforUserFragment extends Fragment {
    ViewPager2 viewPager;
    static int Learning=1,Person=0;
    public InforUserFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_infor_user, container, false);
        viewPager = view.findViewById(R.id.pager);
        InforUserAdapter inforUserAdapter = new InforUserAdapter(requireActivity());
        viewPager.setAdapter(inforUserAdapter);
        HingeTransformation hingeTransformation = new HingeTransformation();
        viewPager.setPageTransformer(hingeTransformation);

        return view;

    }


    public static class  UserInfor extends Fragment{
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

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.inforuser_detail, container, false);
            Learning_Path = view.findViewById(R.id.Learning_Path);
            Personal_Path = view.findViewById(R.id.Personal_Path);
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
    public class InforUserAdapter extends FragmentStateAdapter {
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
