package nta.com.music.myfpl;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.muratozturk.click_shrink_effect.ClickShrinkEffect;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nta.com.music.myfpl.DTO.LoginRequestDTO;
import nta.com.music.myfpl.DTO.StudentResponseDTO;
import nta.com.music.myfpl.adapter.ChooseCampusItemAdapter;
import nta.com.music.myfpl.helper.IRetrofit;
import nta.com.music.myfpl.helper.RetrofitHelper;
import nta.com.music.myfpl.model.Campus;
import nta.com.music.myfpl.model.Student;
import render.animations.Bounce;
import render.animations.Render;
import render.animations.Zoom;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    LinearLayout login_linear;
    ImageView logoFPT; LinearLayout choice_campus, login_google;
    GoogleSignInClient gsc;

    TextView txt_chosenCampus;

    IRetrofit retrofit;
    String email;

    public static Student student;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Utils();
        retrofit = RetrofitHelper.createService(IRetrofit.class);

    }

    @SuppressLint({"ObsoleteSdkInt", "ResourceAsColor"})
    private void Utils() {
//      full screen
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        

        // anh xa
        login_linear = findViewById(R.id.login_linear);
        logoFPT = findViewById(R.id.logoFPT);
        choice_campus = findViewById(R.id.choiceCampus);
        login_google = findViewById(R.id.loginGoogle);
        txt_chosenCampus = findViewById(R.id.txt_chosenCampus);

        // yêu cầu người dùng cung cấp gmail và request gmail luôn
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this, gso);
        googleSignInClient.signOut();
        gsc = GoogleSignIn.getClient(LoginActivity.this, gso);

        login_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent googleIntent = gsc.getSignInIntent();
                googleLauncher.launch(googleIntent);

            }
        });



//      animation
        Render render = new Render(getApplicationContext());
        //      Set Animation
        render.setAnimation(Bounce.InUp(login_linear).setDuration(5000));

        render.start();
        Render render1 = new Render(getApplicationContext());
        render1.setAnimation(Bounce.In(logoFPT).setDuration(5));
        render1.start();

        new ClickShrinkEffect(choice_campus, 0.98f, 50);
        new ClickShrinkEffect(login_google, 0.98f, 50);


        //
        choice_campus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialogWithMultipleChoice();
            }
        });
    }

    // result google
    ActivityResultLauncher<Intent> googleLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Intent data = result.getData();
                    Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

                    try {
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        String email = account.getEmail();
                        String name = account.getDisplayName();
                        String image = String.valueOf(account.getPhotoUrl());

                        if(isEmailFPT(email)){
                            LoginRequestDTO requestDTO = new LoginRequestDTO(email, name, image);
                            retrofit.login(requestDTO).enqueue(login);
                        } else {
                            Toast.makeText(LoginActivity.this, "Phải đăng nhập bằng email FPT", Toast.LENGTH_SHORT).show();
                        }



//                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                        startActivity(intent);

                    } catch (Exception e) {
//                        Log.d(">>>>>>>>>>>>TAG", "onActivityResult Error: " + e.getMessage());
                    }
                }
            });
    // end result google

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    //test
    @SuppressLint({"MissingInflatedId", "LocalSuppress"})
    private void showCustomDialogWithMultipleChoice() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        // Inflate the custom layout for the dialog
        LayoutInflater inflater = getLayoutInflater();
        View customView = inflater.inflate(R.layout.custom_dialog_layout, null);

        // Find views in the custom layout
         LinearLayout layout_dialog = customView.findViewById(R.id.layout_dialog);

        ListView listView = customView.findViewById(R.id.dialog_listview);

        // Set the title and items for the ListView

        List<Campus> campusList = new ArrayList<>();
        campusList.add(new Campus(1, "Hồ Chí Minh"));
        campusList.add(new Campus(2, "Hà Nội"));
        campusList.add(new Campus(3, "Đà Nẵng"));
        campusList.add(new Campus(4, "Cần Thơ"));
        campusList.add(new Campus(4, "Tây Nguyên"));

        ChooseCampusItemAdapter adapter = new ChooseCampusItemAdapter(this,
                campusList);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(adapter);

        // Set the custom view to the dialog
        builder.setView(customView);


        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
        Render render = new Render(LoginActivity.this);
        render.setAnimation(Bounce.In(layout_dialog).setDuration(500));
        render.start();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showToast(campusList.get(i).getCampusName());
                txt_chosenCampus.setText(campusList.get(i).getCampusName());
                Render render = new Render(LoginActivity.this);
                render.setAnimation(Zoom.Out(layout_dialog).setDuration(500));
                render.start();
                dialog.dismiss();
            }
        });
    }
    private void showToast(String message) {
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    Callback<StudentResponseDTO> login = new Callback<StudentResponseDTO>() {
        @Override
        public void onResponse(@NonNull Call<StudentResponseDTO> call, Response<StudentResponseDTO> response) {
            if (response.isSuccessful()) {
                StudentResponseDTO loginResponse = response.body();
                if (loginResponse != null && loginResponse.isStatus()) {
                    student = loginResponse.getStudent();
                    Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else{
                    Toast.makeText(LoginActivity.this, "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }


        }

        @Override
        public void onFailure(@NonNull Call<StudentResponseDTO> call, Throwable t) {
            Log.d(">>> login", "onFailure: " + t.getMessage());
        }
    };
    public static boolean isEmailFPT(String email) {
        String patternString = "\\b[\\w.%-]+@fpt\\.edu\\.vn\\b";

        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);

        Matcher matcher = pattern.matcher(email);

        // Kiểm tra xem email có khớp với biểu thức chính quy hay không
        return matcher.find();
    }
}