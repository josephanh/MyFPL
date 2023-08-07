package nta.com.music.myfpl.fragments.Notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.DTO.ListInformationResponseDTO;
import nta.com.music.myfpl.MainActivity;
import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.InformationAdapter;
import nta.com.music.myfpl.adapter.NotificationSchoolAdapter;
import nta.com.music.myfpl.fragments.NotificationFragment;
import nta.com.music.myfpl.helper.IRetrofit;
import nta.com.music.myfpl.helper.RetrofitHelper;
import nta.com.music.myfpl.interfaces.OnClickInformation;
import nta.com.music.myfpl.model.NotificationSchool;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SchoolNotificationFragment extends Fragment {

    RecyclerView rc_schoolNotification;
    IRetrofit iRetrofit;
    List<ListInformationResponseDTO.InformationResponseDTO> list2;
    NotificationSchoolAdapter notificationSchoolAdapter;
    LinearLayout ln_notfound;

    public SchoolNotificationFragment() {
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
    List<NotificationSchool> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_school_notification, container, false);
        rc_schoolNotification = view.findViewById(R.id.rc_schoolNotification);
        ln_notfound = view.findViewById(R.id.ln_notfound);
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
//        rc_schoolNotification.setLayoutManager(linearLayoutManager);
//        list.add(new NotificationSchool(1,"Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023", "Content nè", "29/07/2023"));
//        list.add(new NotificationSchool(2,"Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023", "Content nè", "28/07/2023"));
//        list.add(new NotificationSchool(3,"Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023", "Content nè", "29/06/2023"));
//        list.add(new NotificationSchool(4,"Thông báo đăng ký thực hiện dự án tốt nghiệp học kỳ Fall 2023", "Content nè", "15/06/2023"));
//        NotificationSchoolAdapter adapter = new NotificationSchoolAdapter(getContext(), list);
//
//        rc_schoolNotification.setAdapter(adapter);
//        adapter.setOnItemClickListener(new NotificationSchoolAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                NotificationSchool item = list.get(position);
//                Toast.makeText(getContext(), "nhan o cho school"+item.getId(), Toast.LENGTH_LONG).show();
//            }
//        });
        list2 = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_schoolNotification.setLayoutManager(linearLayoutManager);
        notificationSchoolAdapter = new NotificationSchoolAdapter(getContext(), list2);
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
        rc_schoolNotification.setAdapter(notificationSchoolAdapter);
        return view;
    }
    @Override
    public void onResume() {
        super.onResume();
        iRetrofit.posts().enqueue(postscallback);
    }

    Callback<ListInformationResponseDTO> postscallback = new Callback<ListInformationResponseDTO>() {
        @Override
        public void onResponse(Call<ListInformationResponseDTO> call, Response<ListInformationResponseDTO> response) {
            if (response.isSuccessful()) {
                ListInformationResponseDTO listInformationResponseDTO = response.body();
                list2.clear();
                list2.addAll(listInformationResponseDTO.getPosts());
                notificationSchoolAdapter.notifyDataSetChanged();
                if (list2.size()==0){
                    ln_notfound.setVisibility(View.VISIBLE);
                    rc_schoolNotification.setVisibility(View.GONE);
                }



            }
        }
        @Override
        public void onFailure(Call<ListInformationResponseDTO> call, Throwable t) {
            Log.d(">>> Post", "onFailure: " + t.getMessage());
        }
    };

}