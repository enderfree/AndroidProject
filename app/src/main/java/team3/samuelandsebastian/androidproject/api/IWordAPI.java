package team3.samuelandsebastian.androidproject.api;

import com.google.gson.JsonObject;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Path;

public interface IWordAPI {

    @GET("words/{word}")
    Call<JsonObject> getData(@Path("word") String word, @HeaderMap Map<String, String> headers);
}
