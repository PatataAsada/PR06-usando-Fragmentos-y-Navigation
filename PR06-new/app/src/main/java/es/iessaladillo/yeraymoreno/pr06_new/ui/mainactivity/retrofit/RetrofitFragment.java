package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.retrofit;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.iessaladillo.yeraymoreno.pr06_new.R;

public class RetrofitFragment extends Fragment {

    private RetrofitViewModel mViewModel;

    public static RetrofitFragment newInstance() {
        return new RetrofitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.retrofit_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(RetrofitViewModel.class);
        // TODO: Use the ViewModel
    }

}
