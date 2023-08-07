package nta.com.music.myfpl.fragments;

import static nta.com.music.myfpl.LoginActivity.student;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageSwitcher;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import nta.com.music.myfpl.InformationUser;
import nta.com.music.myfpl.LoginActivity;
import nta.com.music.myfpl.R;

public class UserFragment extends Fragment {

    ImageSwitcher imageSwitcher1;

    public static UserFragment newInstance() {

        Bundle args = new Bundle();

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }
    public UserFragment() {
        // Required empty public constructor
    }

    @SuppressLint({"MissingInflatedId", "LocalSuppress", "SetTextI18n"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        TextView user_fullname = view.findViewById(R.id.user_fullname);
        RoundedImageView img_avatar = view.findViewById(R.id.img_avatar);
        LinearLayout user_profile = view.findViewById(R.id.user_profile);
        LinearLayout btn_logout = view.findViewById(R.id.btn_logout);

        if(student != null) {
            user_fullname.setText(student.getName());
            Glide.with(requireContext()).load(student.getAvatar()).into(img_avatar);
        } else {
            user_fullname.setText("FPT Student");
        }
        user_profile.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext(), InformationUser.class);
            requireContext().startActivity(intent);
        });
        btn_logout.setOnClickListener(view1 -> {
            Intent intent = new Intent(requireContext(), LoginActivity.class);
            requireContext().startActivity(intent);
        });
        
        return view;
    }
}