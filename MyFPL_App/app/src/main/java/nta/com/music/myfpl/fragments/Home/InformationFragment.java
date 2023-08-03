package nta.com.music.myfpl.fragments.Home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.MainActivity;
import nta.com.music.myfpl.R;
import nta.com.music.myfpl.adapter.InformationAdapter;
import nta.com.music.myfpl.helper.IRetrofit;
import nta.com.music.myfpl.helper.RetrofitHelper;
import nta.com.music.myfpl.DTO.ListInformationResponseDTO;
import nta.com.music.myfpl.interfaces.OnClickInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationFragment extends Fragment {
    RecyclerView recyclerView;
    IRetrofit iRetrofit;
    List<ListInformationResponseDTO.InformationResponseDTO> list;
    InformationAdapter informationAdapter;
    LinearLayout ln_notfound;
    int staticType;

    public static InformationFragment newInstance(int type) {
        Bundle args = new Bundle();
        InformationFragment fragment = new InformationFragment();
        args.putInt("Type",type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            staticType = getArguments().getInt("Type",0);
        }
    }
    public InformationFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        recyclerView = view.findViewById(R.id.rcv_information);
        ln_notfound = view.findViewById(R.id.ln_notfound);
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        informationAdapter = new InformationAdapter(getContext(), list, new OnClickInformation() {
            @Override
            public void onClick(ListInformationResponseDTO.InformationResponseDTO informationResponseDTO) {
                ((MainActivity)requireContext()).goToDetailInformation(informationResponseDTO);
            }
        });
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
        recyclerView.setAdapter(informationAdapter);
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
                list.clear();
                for (int i =0;i<listInformationResponseDTO.getPosts().size();i++){
                    if (Integer.parseInt(listInformationResponseDTO.getPosts().get(i).getType())==staticType){
                        list.add(listInformationResponseDTO.getPosts().get(i));
                        informationAdapter.notifyDataSetChanged();
                    }

                }
                if (list.size()==0){
                    ln_notfound.setVisibility(View.VISIBLE);
                }else {
                    ln_notfound.setVisibility(View.GONE);
                }


            }
        }
        @Override
        public void onFailure(Call<ListInformationResponseDTO> call, Throwable t) {
            Log.d(">>> Post", "onFailure: " + t.getMessage());
        }
    };


}