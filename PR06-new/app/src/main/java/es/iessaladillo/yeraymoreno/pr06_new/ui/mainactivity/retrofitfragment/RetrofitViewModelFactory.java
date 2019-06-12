package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.retrofitfragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.iessaladillo.yeraymoreno.pr06_new.data.RepositoryJoke;

public class RetrofitViewModelFactory implements ViewModelProvider.Factory {

    private final RepositoryJoke repository;

    public RetrofitViewModelFactory(RepositoryJoke repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(RetrofitViewModel.class)) {
            return (T) new RetrofitViewModel(repository);
        } else {
            throw new IllegalArgumentException(("Wrong modelViewClass"));
        }
    }
}
