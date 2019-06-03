package es.iessaladillo.yeraymoreno.pr06_new.data;

import androidx.lifecycle.LiveData;

import es.iessaladillo.yeraymoreno.pr06_new.base.Resource;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Joke;


public interface RepositoryJoke {
    LiveData<Resource<Joke>> queryJoke(String tag);
}
