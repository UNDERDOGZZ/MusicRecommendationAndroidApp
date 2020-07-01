package pe.edu.musicrecommender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface EmotionService {

    @Headers({

            "Content-type: application/json"

    })
    @POST("recommendatione")
    Call<EmotionResponse> sendPost(@Body EmotionSent emotionSent);

}

