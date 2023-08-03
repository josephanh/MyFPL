package nta.com.music.myfpl.helper;

import nta.com.music.myfpl.DTO.LoginRequestDTO;
import nta.com.music.myfpl.DTO.ScheduleResponseDTO;
import nta.com.music.myfpl.DTO.StudentResponseDTO;
import nta.com.music.myfpl.DTO.StudyingResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

// khai bao cac route
public interface IRetrofit {
    @GET("/api/get_student_by_id.php")
    Call<StudentResponseDTO> getStudentById(@Query("student_id") int student_id);

//  https://tuananhfpt.id.vn/api/scheduler/get_all_schedule_by_time.php?student_id=1&date_start=2023-8-1&date_end=2023-08-08
    @GET("/api/scheduler/get_all_schedule_by_time.php")
    Call<ScheduleResponseDTO> getScheduleByTime(@Query("student_id") int student_id, @Query("date_start") String date_start, @Query("date_end") String date_end);

//  https://tuananhfpt.id.vn/api/studing/get_studing.php?student_id=1
    @GET("/api/studing/get_studing.php")
    Call<StudyingResponseDTO> getStudying(@Query("student_id") int student_id);

    @POST("/api/login.php")
    Call<StudentResponseDTO> login(@Body LoginRequestDTO body);
}
