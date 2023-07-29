package nta.com.music.myfpl.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nta.com.music.myfpl.R;


public class InforUserFragment extends Fragment {

    public InforUserFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_infor_user, container, false);


//        HingeTransformation hingeTransformation = new HingeTransformation();
//        viewPager.setPageTransformer(hingeTransformation);





        return view;

    }


}
