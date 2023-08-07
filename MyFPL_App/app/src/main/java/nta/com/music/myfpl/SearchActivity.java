package nta.com.music.myfpl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spanned;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import nta.com.music.myfpl.DTO.DetailInformationResponseDTO;
import nta.com.music.myfpl.DTO.ListInformationResponseDTO;
import nta.com.music.myfpl.adapter.InformationAdapter;
import nta.com.music.myfpl.helper.IRetrofit;
import nta.com.music.myfpl.helper.RetrofitHelper;
import nta.com.music.myfpl.interfaces.OnClickInformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {
    ImageButton btn_back;
    IRetrofit iRetrofit;
    TextView edt_search;
    List<ListInformationResponseDTO.InformationResponseDTO> list;

    RecyclerView recyclerView;

    InformationAdapter informationAdapter;
    MainActivity mainActivity;
    LinearLayout linearLayout,linearLayout2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        edt_search = findViewById(R.id.edt_search);
        btn_back = findViewById(R.id.btn_back);
        linearLayout = findViewById(R.id.linearLayout);
        linearLayout2 = findViewById(R.id.linearLayout2);
        recyclerView = findViewById(R.id.rcv_searchPost);
        list = new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(SearchActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        informationAdapter = new InformationAdapter(SearchActivity.this, list, new OnClickInformation() {
            @Override
            public void onClick(ListInformationResponseDTO.InformationResponseDTO information) {
                Intent intent = new Intent(SearchActivity.this, InformationActivity.class);
                intent.putExtra("postId", information.getId());
                startActivity(intent);
            }
        });
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
        recyclerView.setAdapter(informationAdapter);
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);
        btn_back.setOnClickListener(view -> {
            onBackPressed();
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String keyword = charSequence.toString().trim();

                // Kiểm tra xem từ khóa có rỗng hay không
                if (!keyword.isEmpty()) {
                    // Gửi yêu cầu tìm kiếm đến API bên ngoài thông qua Retrofit
                    iRetrofit.searchPosts(keyword).enqueue(searchPostCallback);
                    linearLayout.setVisibility(View.GONE);
                    linearLayout2.setVisibility(View.VISIBLE);
                } else {
                    // Nếu từ khóa rỗng, xóa danh sách bài viết trong RecyclerView
                    list.clear();
                    informationAdapter.notifyDataSetChanged();
                    linearLayout.setVisibility(View.VISIBLE);
                    linearLayout2.setVisibility(View.GONE);

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }


    Callback<ListInformationResponseDTO> searchPostCallback = new Callback<ListInformationResponseDTO>() {
        @Override
        public void onResponse(Call<ListInformationResponseDTO> call, Response<ListInformationResponseDTO> response) {
            if (response.isSuccessful()){
                ListInformationResponseDTO listInformationResponseDTO = response.body();
                list.clear();
                if (listInformationResponseDTO.isStatus()){
                    list.addAll(listInformationResponseDTO.getPosts());
                    informationAdapter.notifyDataSetChanged();
                }


            }
        }



        @Override
        public void onFailure(Call<ListInformationResponseDTO> call, Throwable t) {
            Log.d(">>> Search ", "onFailure: " + t.getMessage());
        }
    };
}