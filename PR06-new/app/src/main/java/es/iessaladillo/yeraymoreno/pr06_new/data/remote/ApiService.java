package es.iessaladillo.yeraymoreno.pr06_new.data.remote;

import androidx.lifecycle.MutableLiveData;

import es.iessaladillo.yeraymoreno.pr06_new.base.Resource;
import es.iessaladillo.yeraymoreno.pr06_new.data.remote.dto.JokeDto;

public interface ApiService {
    MutableLiveData<Resource<JokeDto>> getJoke();
}
