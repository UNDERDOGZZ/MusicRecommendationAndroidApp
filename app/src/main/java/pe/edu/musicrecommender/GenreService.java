package pe.edu.musicrecommender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface GenreService {
    @Headers({

            "Content-type: application/json"

    })
    @POST("recommendationg")
    Call<GenreResponse> sendPost(@Body GenreSent genreSentSent);

}
