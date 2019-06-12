package es.iessaladillo.yeraymoreno.pr06_new.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import es.iessaladillo.yeraymoreno.pr06_new.base.Resource;
import es.iessaladillo.yeraymoreno.pr06_new.data.mapper.JokeMapper;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Joke;
import es.iessaladillo.yeraymoreno.pr06_new.data.remote.ApiService;

public class RepositoryJokeImpl implements RepositoryJoke{

    private static RepositoryJokeImpl instance;

    private ApiService apiservice;

    public static RepositoryJokeImpl getInstance(ApiService apiservice) {
        if (instance == null) {
            synchronized (RepositoryJokeImpl.class) {
                if (instance == null) {
                    instance = new RepositoryJokeImpl(apiservice);
                }
            }
        }
        return instance;
    }

    public RepositoryJokeImpl(ApiService apiservice){
        this.apiservice = apiservice;
    }

    @Override
    public LiveData<Resource<Joke>> queryJoke() {
        return Transformations.map(apiservice.getJoke(), resource -> {
            if (resource.isLoading()) {
                return Resource.loading();
            } else if (resource.hasError()) {
                return Resource.error(resource.getException());
            } else {
                return Resource.success(JokeMapper.map(resource.getData()));
            }
        });
    }
}
