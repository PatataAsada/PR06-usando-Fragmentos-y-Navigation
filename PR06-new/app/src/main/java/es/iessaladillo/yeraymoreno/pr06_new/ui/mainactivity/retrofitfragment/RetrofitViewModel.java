package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.retrofitfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import es.iessaladillo.yeraymoreno.pr06_new.base.Event;
import es.iessaladillo.yeraymoreno.pr06_new.base.Resource;
import es.iessaladillo.yeraymoreno.pr06_new.data.RepositoryJoke;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Joke;

public class RetrofitViewModel extends ViewModel {

    public final RepositoryJoke repository;

    private final MutableLiveData<Boolean> queryTrigger = new MutableLiveData<>();
    private final LiveData<Boolean> loading;
    private final MediatorLiveData<Event<String>> message = new MediatorLiveData<>();
    private final MediatorLiveData<Joke> joke = new MediatorLiveData<>();


    public RetrofitViewModel(RepositoryJoke repository){
        this.repository = repository;
        LiveData<Resource<Joke>> queryLiveData = Transformations.switchMap(queryTrigger,
                query -> repository.queryJoke());
        loading = Transformations.map(queryLiveData, Resource::isLoading);
        message.addSource(queryLiveData, resource -> {
            if (resource.hasError()) {
                message.setValue(new Event<>(resource.getException().getMessage()));
            }
        });
        joke.addSource(queryLiveData, resource -> {
            if (resource.hasSuccess()) {
                joke.setValue(resource.getData());
            }
        });
        refreshJoke();
    }

    LiveData<Boolean> getLoading() {
        return loading;
    }

    LiveData<Event<String>> getMessage() {
        return message;
    }

    LiveData<Joke> getJoke() {
        return joke;
    }

    void refreshJoke() {
        queryTrigger.setValue(true);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
