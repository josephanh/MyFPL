package nta.com.music.myfpl.helper;

import nta.com.music.myfpl.DTO.ListInformationResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;


// khai bao cac route
public interface IRetrofit {

    @GET("/api/post/get_all_posts.php?page=1")
    Call<ListInformationResponseDTO> posts();

}
