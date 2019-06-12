package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.retrofitfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import es.iessaladillo.yeraymoreno.pr06_new.base.EventObserver;
import es.iessaladillo.yeraymoreno.pr06_new.data.di.Injector;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Joke;
import es.iessaladillo.yeraymoreno.pr06_new.databinding.FragmentRetrofitBinding;

public class RetrofitFragment extends Fragment {

    private RetrofitViewModel retrofitViewModel;
    private FragmentRetrofitBinding fragmentRetrofitBinding;

    public static RetrofitFragment newInstance() {
        return new RetrofitFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        fragmentRetrofitBinding = FragmentRetrofitBinding.inflate(inflater,container,false);
        return fragmentRetrofitBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        retrofitViewModel = ViewModelProviders.of(this,
                Injector.provideMainFragmentViewModelFactory(requireContext())).get(
                RetrofitViewModel.class);

        setupLoading();
        observeJoke();
        setupButton();

    }

    private void setupLoading() {
        fragmentRetrofitBinding.pbLoad.setIndeterminate(true);
    }

    private void setupButton() {
        fragmentRetrofitBinding.btnJoke.setOnClickListener(v -> retrofitViewModel.refreshJoke());
    }

    private void observeJoke() {
        retrofitViewModel.getLoading().observe(getViewLifecycleOwner(),
                loading -> fragmentRetrofitBinding.pbLoad.setVisibility(retrofitViewModel.getLoading().getValue() ? View.VISIBLE : View.INVISIBLE));
        retrofitViewModel.getMessage().observe(getViewLifecycleOwner(),
                new EventObserver<>(this::showErrorLoadingJoke));
        retrofitViewModel.getJoke().observe(getViewLifecycleOwner(), this::showJoke);
    }

    private void showJoke(Joke joke) {
        fragmentRetrofitBinding.lblJoke.setText(joke.getValue());
    }

    private void showErrorLoadingJoke(String s) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show();
    }

}
