package es.iessaladillo.yeraymoreno.pr06_new.data.remote;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import es.iessaladillo.yeraymoreno.pr06_new.base.Resource;
import es.iessaladillo.yeraymoreno.pr06_new.data.remote.dto.JokeDto;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public class ApiServiceImpl implements ApiService {
    private static ApiServiceImpl instance;
    private final OkHttpClient okHttpClient;
    private final Api api;

    private ApiServiceImpl(Api api, OkHttpClient okHttpClient) {
        this.api = api;
        this.okHttpClient = okHttpClient;
    }

    public static synchronized ApiServiceImpl getInstance(Api api, OkHttpClient okHttpClient) {
        if (instance == null) {
            synchronized (ApiServiceImpl.class) {
                if (instance == null) {
                    instance = new ApiServiceImpl(api, okHttpClient);
                }
            }
        }
        return instance;
    }


    @Override
    public MutableLiveData<Resource<JokeDto>> getJoke(String tag) {
        MutableLiveData<Resource<JokeDto>> result = new MutableLiveData<>();
        result.setValue(Resource.loading());
        Call<JokeDto> studentsCall = api.getJoke();
        String[] tagsArray = (String[]) studentsCall.request().tag();
        if (tagsArray != null) tagsArray[0] = tag;
        studentsCall.enqueue(new retrofit2.Callback<JokeDto>() {
            @Override
            public void onResponse(@NonNull Call<JokeDto> call,
                                   @NonNull Response<JokeDto> response) {
                if (response.body() != null && response.isSuccessful()) {
                    result.setValue(Resource.success(response.body()));
                } else {
                    result.setValue(Resource.error(new Exception(response.message())));
                }
            }

            @Override
            public void onFailure(@NonNull Call<JokeDto> call, @NonNull Throwable t) {
                result.setValue(Resource.error(new Exception(t.getMessage())));
            }
        });
        return result;
    }


    public interface Api {

        @GET("Joke")
        Call<JokeDto> getJoke();

    }
}
