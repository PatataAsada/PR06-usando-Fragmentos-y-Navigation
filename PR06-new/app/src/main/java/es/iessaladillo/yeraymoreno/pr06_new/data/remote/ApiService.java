package es.iessaladillo.yeraymoreno.pr06_new.data.remote;

import androidx.lifecycle.MutableLiveData;

import es.iessaladillo.yeraymoreno.pr06_new.base.Resource;
import es.iessaladillo.yeraymoreno.pr06_new.data.remote.dto.JokeDto;
import retrofit2.http.GET;

public interface ApiService {
    @GET("https://api.chucknorris.io/jokes/random")
    MutableLiveData<Resource<JokeDto>> getJoke(String tag);
}
