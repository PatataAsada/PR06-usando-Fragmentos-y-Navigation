package es.iessaladillo.yeraymoreno.pr06_new.ui.mainFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import es.iessaladillo.yeraymoreno.pr06_new.data.DatabaseStudents;

public class MainFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final DatabaseStudents databaseStudents;

    public MainFragmentViewModelFactory(DatabaseStudents databaseStudents) {
        this.databaseStudents = databaseStudents;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainViewModel(databaseStudents);
    }
}
