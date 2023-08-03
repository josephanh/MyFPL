package nta.com.music.myfpl.fragments;

import static nta.com.music.myfpl.LoginActivity.student;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.DTO.ScheduleResponseDTO;
import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.ScheduleTabAdapter;
import nta.com.music.myfpl.dialog.DialogLoading;
import nta.com.music.myfpl.fragments.Schedule.ScheduleMonthFragment;
import nta.com.music.myfpl.fragments.Schedule.ScheduleWeekFragment;
import nta.com.music.myfpl.helper.IRetrofit;
import nta.com.music.myfpl.helper.RetrofitHelper;
import nta.com.music.myfpl.interfaces.OnChangeSchedule;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleTabFragment extends Fragment implements OnChangeSchedule {

    DialogLoading loading;
    ScheduleTabAdapter adapter;

    ViewPager2 viewpager_schedule;
    List<Fragment> fragmentList = new ArrayList<>();

    IRetrofit retrofit;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_schedule_2, container, false);
        viewpager_schedule = view.findViewById(R.id.viewpager_schedule);

        fragmentList.add(new ScheduleWeekFragment());
        fragmentList.add(new ScheduleMonthFragment());


        adapter = new ScheduleTabAdapter(requireActivity(), fragmentList);
        viewpager_schedule.setAdapter(adapter);
        viewpager_schedule.setOffscreenPageLimit(2);
        viewpager_schedule.setUserInputEnabled(false);

        retrofit = RetrofitHelper.createService(IRetrofit.class);
        if(student != null){
            retrofit.getScheduleByTime(student.getId(), "2023-08-01", "2023-08-08").enqueue(getScheduleByTime);
        }


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onChange(int state, String name_subject, int time) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        viewpager_schedule.setCurrentItem(state, true);
                        if(state == 1){
                            ((ScheduleMonthFragment) fragmentList.get(1)).onChange(state,name_subject, time);
                        }
                    }
                });
            }
        }).start();
    }

    Callback<ScheduleResponseDTO> getScheduleByTime = new Callback<ScheduleResponseDTO>() {
        @Override
        public void onResponse(@NonNull Call<ScheduleResponseDTO> call, Response<ScheduleResponseDTO> response) {
            if (response.isSuccessful()) {

            }
        }

        @Override
        public void onFailure(@NonNull Call<ScheduleResponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
}