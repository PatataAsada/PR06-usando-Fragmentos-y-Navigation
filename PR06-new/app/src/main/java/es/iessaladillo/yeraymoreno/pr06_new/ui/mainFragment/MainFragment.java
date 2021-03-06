package es.iessaladillo.yeraymoreno.pr06_new.ui.mainFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.Objects;

import es.iessaladillo.yeraymoreno.pr06_new.R;
import es.iessaladillo.yeraymoreno.pr06_new.data.DatabaseStudents;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;
import es.iessaladillo.yeraymoreno.pr06_new.databinding.FragmentMainBinding;
import es.iessaladillo.yeraymoreno.pr06_new.ui.studentFragment.StudentViewModel;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private FragmentMainBinding mainFragmentBinding;
    private StudentViewModel pViewModel;
    public MainFragmentAdapter listAdapter;
    public MainViewModel mViewModel;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = FragmentMainBinding.inflate(inflater, container, false);
        return mainFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        navController = NavHostFragment.findNavController(this);
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()), new MainFragmentViewModelFactory(new DatabaseStudents())).get(MainViewModel.class);
        observeStudents();
        setupViews();
        setupToolbar(getView());
    }

    //Gets an observable to look for the students.
    private void observeStudents() {
        mViewModel.getStudents(true).observe(this, students -> {
            listAdapter.submitList(students);
            mainFragmentBinding.lblEmptyView.setVisibility(students.size() == 0 ? View.VISIBLE : View.INVISIBLE);
            mainFragmentBinding.btnAdd.setVisibility(students.size() == 0 ? View.INVISIBLE : View.VISIBLE);
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
        mainFragmentBinding.lblEmptyView.setOnClickListener(v -> addStudent());
    }

    //Sets the fab to send to profile fragment to create new student.
    private void setupFAB() {
        mainFragmentBinding.btnAdd.setOnClickListener(v -> addStudent());
    }

    //Sets the observabel and the reciclerview.
    private void setupRecyclerView() {
        listAdapter = new MainFragmentAdapter(position -> editStudent(listAdapter.getItem(position)), position -> deleteStudent(listAdapter.getItem(position)));
        mainFragmentBinding.lstStudents.setHasFixedSize(true);
        mainFragmentBinding.lstStudents.setLayoutManager(new GridLayoutManager(this.getContext(), getResources().getInteger(R.integer.main_lstStudents_columns)));
        mainFragmentBinding.lstStudents.setItemAnimator(new DefaultItemAnimator());
        mainFragmentBinding.lstStudents.setAdapter(listAdapter);
    }

    //Deletes chosen student.
    private void deleteStudent(Student item) {
        mViewModel.deleteStudent(item);
    }

    //Goes to StudentFragment with selected student to edit.
    private void editStudent(Student item) {
        pViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity())).get(StudentViewModel.class);
        pViewModel.setStudent(item);
        pViewModel.isEdit = true;
        navController.navigate(R.id.action_mainFragment_to_profileFragment);
    }

    //Goes to StudentFragment to create a new student.
    private void addStudent() {
        Student item = null;
        pViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        pViewModel.setStudent(null);
        pViewModel.isEdit = false;
        navController.navigate(R.id.action_mainFragment_to_profileFragment);
    }

    //Sets the toolbar title.
    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        toolbar.setTitle(R.string.fragment_main_toolbar);
    }
}
