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

    public static RepositoryJokeImpl getInstance() {
        if (instance == null) {
            synchronized (RepositoryJokeImpl.class) {
                if (instance == null) {
                    instance = new RepositoryJokeImpl();
                }
            }
        }
        return instance;
    }

    public RepositoryJokeImpl(){}

    @Override
    public LiveData<Resource<Joke>> queryJoke(String tag) {
        return Transformations.map(apiservice.getJoke(tag), resource -> {
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
