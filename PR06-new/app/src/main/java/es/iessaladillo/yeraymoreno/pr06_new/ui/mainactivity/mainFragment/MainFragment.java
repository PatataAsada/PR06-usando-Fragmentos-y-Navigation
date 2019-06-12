package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.mainFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.Objects;

import es.iessaladillo.yeraymoreno.pr06_new.R;
import es.iessaladillo.yeraymoreno.pr06_new.data.AppDatabaseStudents;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;
import es.iessaladillo.yeraymoreno.pr06_new.databinding.FragmentMainBinding;
import es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.studentFragment.StudentViewModel;

public class MainFragment extends Fragment {

    private static final String STUDENT = "STUDENT";
    private static final int EDIT_STUDENT = 1;
    private static final int ADD_STUDENT = 2;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private FragmentMainBinding mainFragmentBinding;
    private NavController navController;
    private StudentViewModel studentViewModel;
    private MainFragmentAdapter listAdapter;
    public MainFragmentViewModel mainFragmentViewModel;
    private Intent studentIntent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = FragmentMainBinding.inflate(inflater, container, false);
        return mainFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainFragmentViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()),
                new MainFragmentViewModelFactory(AppDatabaseStudents.getInstance(getContext()))).get(MainFragmentViewModel.class);
        navController = NavHostFragment.findNavController(this);
        observeStudents();
        setupViews();
    }

    //Gets an observable to look for the students.
    private void observeStudents() {
        mainFragmentViewModel.getStudents(true).observe(this, students -> {
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
        mainFragmentViewModel.deleteStudent(item);
    }

    //Goes to StudentFragment with selected student to edit.
    private void editStudent(Student item) {
        studentViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity())).get(StudentViewModel.class);
        studentViewModel.setStudent(item);
        studentViewModel.isEdit = true;
        navigateToStudent();
    }

    //Goes to StudentFragment to create a new student.
    private void addStudent() {
        Student item = null;
        studentViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        studentViewModel.setStudent(item);
        studentViewModel.isEdit = false;
        navigateToStudent();
    }

    private void navigateToStudent() {
        navController.navigate(R.id.action_mainFragment_to_studentFragment);
    }
}
