package es.iessaladillo.yeraymoreno.pr06_new.ui.mainFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.Objects;

import es.iessaladillo.yeraymoreno.pr06_new.R;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;
import es.iessaladillo.yeraymoreno.pr06_new.databinding.FragmentMainBinding;
import es.iessaladillo.yeraymoreno.pr06_new.ui.profileFragment.ProfileViewModel;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private FragmentMainBinding mainFragmentBinding;
    private ProfileViewModel pViewModel;
    public MainFragmentAdapter listAdapter;
    public MainViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = FragmentMainBinding.inflate(inflater,container,false);
        return mainFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        pViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getTargetFragment())).get(ProfileViewModel.class);
        observeStudents();
        setupViews();
    }
    //Gets an observable to look for the students.
    private void observeStudents() {
        mViewModel.getStudents(true).observe(this, students -> {
            listAdapter.submitList(students);
            mainFragmentBinding.lblEmptyView.setVisibility(students.size() == 0 ? View.VISIBLE : View.INVISIBLE);
        });
    }

    //Sets the views.
    private void setupViews() {
        setupRecyclerView();
        setupFAB();
        setupEmptyView();
    }

    //Sets the emptyview to send to profile fragment to create new student.
    private void setupEmptyView() {
        mainFragmentBinding.lblEmptyView.setOnClickListener(this::addStudent);
    }

    //Sets the fab to send to profile fragment to create new student.
    private void setupFAB() {
        mainFragmentBinding.lblEmptyView.setOnClickListener(this::addStudent);
    }

    private void setupRecyclerView() {
        listAdapter = new MainFragmentAdapter(position -> editStudent(listAdapter.getItem(position)), position -> deleteStudent(listAdapter.getItem(position)));
        mainFragmentBinding.lstStudents.setHasFixedSize(true);
        mainFragmentBinding.lstStudents.setLayoutManager(new GridLayoutManager(this.getContext(), getResources().getInteger(R.integer.main_lstStudents_columns)));
        mainFragmentBinding.lstStudents.setItemAnimator(new DefaultItemAnimator());
        mainFragmentBinding.lstStudents.setAdapter(listAdapter);
    }

    private void deleteStudent(Student item) {
        mViewModel.deleteStudent(item);
    }

    private void editStudent(Student item) {
        //TODO send to fragment with student in profileviewmodel.
    }
    private void addStudent(View v) {
        //TODO mandar a profile fragment con estudiante null en viewmodel.
    }
}
