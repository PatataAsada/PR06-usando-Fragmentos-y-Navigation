package es.iessaladillo.yeraymoreno.pr06_new.ui.MainActivity.mainFragment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import es.iessaladillo.yeraymoreno.pr06_new.data.AppDatabaseStudents;

public class MainFragmentViewModelFactory implements ViewModelProvider.Factory {

    private final AppDatabaseStudents databaseStudents;

    public MainFragmentViewModelFactory(AppDatabaseStudents databaseStudents) {
        this.databaseStudents = databaseStudents;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new MainFragmentViewModel(databaseStudents);
    }
}
