package nta.com.music.myfpl;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.dd.ShadowLayout;
import com.makeramen.roundedimageview.RoundedImageView;

import render.animations.Fade;
import render.animations.Render;

public class InformationUser extends AppCompatActivity {

    ViewPager2 viewPager;
    ImageView img_gone_1;
    ImageView img_gone_2;
    LinearLayout layout_first, layout_third;
    TextView full_name;
    RoundedImageView img_avatar;

    private static final int Learning=1;
    private static final int Person=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_user);

        img_gone_1 = findViewById(R.id.img_gone_1);
        img_gone_2 = findViewById(R.id.img_gone_2);
        img_avatar = findViewById(R.id.img_avatar);
        full_name = findViewById(R.id.full_name);
        layout_first = findViewById(R.id.layout_first);
        layout_third = findViewById(R.id.layout_third);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);



//        Window window = getWindow();
//        int newStatusBarColor = ContextCompat.getColor(InformationUser.this, R.color.black);
//        window.setStatusBarColor(newStatusBarColor);


        viewPager = findViewById(R.id.pager);
        InforUserAdapter inforUserAdapter = new InforUserAdapter(InformationUser.this);
        viewPager.setAdapter(inforUserAdapter);

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case Learning: case Person:{
//                        setVisibility();
                        break;
                    }
                }


            }
        });
    }

    private void setVisibility(){
        int v = (viewPager.getCurrentItem() == 0) ? View.VISIBLE : View.GONE;

        img_gone_1.setVisibility(v);
        img_gone_2.setVisibility(v);
        layout_third.setVisibility(v);
        Render render = new Render(InformationUser.this);
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


    public static class UserInfor extends Fragment {
        int state=0;
        ShadowLayout Learning_Path,Personal_Path;
        public static UserInfor newInstance(int i) {
            Bundle args = new Bundle();
            args.putInt("state",i);
            UserInfor fragment = new UserInfor();
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
