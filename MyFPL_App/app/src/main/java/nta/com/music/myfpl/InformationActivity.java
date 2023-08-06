package nta.com.music.myfpl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import nta.com.music.myfpl.DTO.DetailInformationRequestDTO;
import nta.com.music.myfpl.DTO.DetailInformationResponseDTO;
import nta.com.music.myfpl.DTO.ListInformationResponseDTO;
import nta.com.music.myfpl.helper.IRetrofit;
import nta.com.music.myfpl.helper.RetrofitHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformationActivity extends AppCompatActivity {
    TextView tv_content,tv_title;
    ImageButton btn_back;
    IRetrofit iRetrofit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        tv_content = findViewById(R.id.tv_content);
        tv_title = findViewById(R.id.tv_title);
        btn_back = findViewById(R.id.btn_back);
        iRetrofit = RetrofitHelper.createService(IRetrofit.class);

//        String htmlText = "<div class=\"kt-portlet__body\"><p class=\"ql-align-justify\"><strong style=\"color: red;\">&nbsp;</strong></p><p class=\"ql-align-justify\">Phòng Đào Tạo thông báo yêu cầu các bạn sinh viên đang thiếu bằng tốt nghiệp THPT vui lòng bổ sung đầy đủ hồ sơ.&nbsp;Nộp bản sao/photo công chứng bằng THPT là yêu cầu bắt buộc để lưu trữ hồ sơ sinh viên trong suốt quá trình học tập đến khi được xét tốt nghiệp. Sau thời hạn bổ sung bên dưới, sinh viên chưa bổ sung bằng THPT sẽ bị đình chỉ học tập mức cao nhất:<strong>&nbsp;</strong><strong style=\"color: red; background-color: yellow;\">BUỘC THÔI HỌC</strong></p><p class=\"ql-align-justify\"><strong style=\"color: black;\">Hồ sơ nộp:</strong><span style=\"color: black;\"> 1 bản sao hoặc photo công chứng (trong vòng 6 tháng) bằng THPT. </span><em style=\"color: rgb(230, 0, 0);\">Trường hợp sinh viên học trung cấp thì có thể nộp bản sao hoặc photo công chứng (trong vòng 6 tháng) bằng tốt nghiệp trung cấp.</em></p><p class=\"ql-align-justify\"><strong style=\"color: black;\">Địa điểm nộp:</strong></p><p class=\"ql-align-justify\">-&nbsp;<span style=\"color: black;\">Cơ sở Nguyễn Kiệm: Phòng Đào Tạo - Tầng 1 - 778/B1 Nguyễn Kiệm, Phường 4, Quận Phú Nhuận, TP.HCM.</span></p><p class=\"ql-align-justify\">-&nbsp;<span style=\"color: black;\">Cơ sở Quang Trung: Phòng Đào Tạo - Tầng trệt&nbsp;Tòa nhà T (QTSC9) - Công viên phần mềm Quang Trung, Phường Tân Chánh Hiệp, Quận 12, TP.HCM.</span></p><p class=\"ql-align-justify\"><strong style=\"color: rgb(230, 0, 0); background-color: rgb(255, 255, 0);\">Hạn nộp:</strong><span style=\"color: rgb(230, 0, 0); background-color: rgb(255, 255, 0);\"></span><strong style=\"color: rgb(230, 0, 0); background-color: rgb(255, 255, 0);\">31/07/2023</strong></p><p><span style=\"color: black;\">Sinh viên có thể truy cập&nbsp;</span><a href=\"https://fptuniversity-my.sharepoint.com/:x:/g/personal/nhuntq20_fpt_edu_vn/EdCJE6NW4whHpR7jPxkeaCoB3Ttm1YLE8nn1ZozFsoBJAQ?e=GXe2yK\" rel=\"noopener noreferrer\" target=\"_blank\" style=\"color: rgb(68, 114, 196);\">TẠI ĐÂY</a><span style=\"color: black;\"> để kiểm tra thông tin cá nhân cũng như thông tin nộp bằng THPT </span><em style=\"color: rgb(230, 0, 0);\">(sinh viên chưa bổ sung bằng THPT sẽ được ghi chú \"Chưa nộp bằng THPT\")</em></p><p>Trân trọng,</p><p>Phòng TC&amp;QLDT.</p> <em>Người đăng: huynh43<br>Thời gian: 14:40:09 - 19/07/2023<br>Cập nhật lần cuối bởi huynh43 vào lúc 11:38:20 ngày 20/07/2023</em></div>";
//        Spanned spannedText = Html.fromHtml(htmlText);
//        tv_content.setText(spannedText);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
    @Override
    protected void onResume() {
        super.onResume();
        Intent i = getIntent();
        int id = i.getIntExtra("postId",0);
        iRetrofit.detailposts(id).enqueue(informationDetailCallback);
    }

    Callback<DetailInformationResponseDTO> informationDetailCallback = new Callback<DetailInformationResponseDTO>() {
        @Override
        public void onResponse(Call<DetailInformationResponseDTO> call, Response<DetailInformationResponseDTO> response) {
            if (response.isSuccessful()){
                DetailInformationResponseDTO detailInformationResponseDTO = response.body();
                if (detailInformationResponseDTO.isStatus()){
                    ListInformationResponseDTO.InformationResponseDTO posts =  detailInformationResponseDTO.getPosts();
                    tv_title.setText(posts.getTitle());
                    String htmlText = posts.getContent();
//                    htmlText = htmlText.replace("`","'");
                    Spanned spannedText = Html.fromHtml(htmlText);
                    tv_content.setText(spannedText);

                }


            }
        }



        @Override
        public void onFailure(Call<DetailInformationResponseDTO> call, Throwable t) {
            Log.d(">>> Detail", "onFailure: " + t.getMessage());
        }
    };

}