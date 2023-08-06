package nta.com.music.myfpl.helper;

import nta.com.music.myfpl.DTO.DetailInformationResponseDTO;
import nta.com.music.myfpl.DTO.ListInformationResponseDTO;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;


// khai bao cac route
public interface IRetrofit {

    @GET("/api/post/get_all_posts.php?page=1")
    Call<ListInformationResponseDTO> posts();
    @GET("/api/post/get_posts_by_id.php")
    Call<DetailInformationResponseDTO> detailposts(@Query("id") int id) ;

    @GET("/api/post/get_posts_by_keyword.php")
    Call<ListInformationResponseDTO> searchPosts (@Query("keyword") String keyword) ;

}
