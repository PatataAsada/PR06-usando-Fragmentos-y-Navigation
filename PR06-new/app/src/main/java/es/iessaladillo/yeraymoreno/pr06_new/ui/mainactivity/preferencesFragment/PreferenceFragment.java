package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.preferencesFragment;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.iessaladillo.yeraymoreno.pr06_new.R;

public class PreferenceFragment extends Fragment {

    private PreferenceViewModel mViewModel;

    public static PreferenceFragment newInstance() {
        return new PreferenceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.preference_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PreferenceViewModel.class);
        // TODO: Use the ViewModel
    }

}