package es.iessaladillo.yeraymoreno.pr06_new.ui.mainactivity.mainFragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;

import java.util.Objects;

import es.iessaladillo.yeraymoreno.pr06_new.R;
import es.iessaladillo.yeraymoreno.pr06_new.data.AppDatabaseStudents;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;
import es.iessaladillo.yeraymoreno.pr06_new.databinding.FragmentMainBinding;
import es.iessaladillo.yeraymoreno.pr06_new.ui.studentactivity.StudentActivity;
import es.iessaladillo.yeraymoreno.pr06_new.ui.studentactivity.studentFragment.StudentViewModel;

public class MainFragment extends Fragment {

    private static final String STUDENT = "STUDENT";
    private static final int EDIT_STUDENT = 1;
    private static final int ADD_STUDENT = 2;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private FragmentMainBinding mainFragmentBinding;
    private StudentViewModel pViewModel;
    public MainFragmentAdapter listAdapter;
    public MainFragmentViewModel mViewModel;
    public Intent studentIntent;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mainFragmentBinding = FragmentMainBinding.inflate(inflater, container, false);
        return mainFragmentBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(Objects.requireNonNull(this.getActivity()), new MainFragmentViewModelFactory(AppDatabaseStudents.getInstance(getContext()))).get(MainFragmentViewModel.class);
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
        sendIntent();
    }

    //Goes to StudentFragment to create a new student.
    private void addStudent() {
        Student item = null;
        pViewModel = ViewModelProviders.of(this).get(StudentViewModel.class);
        pViewModel.setStudent(null);
        pViewModel.isEdit = false;
        sendIntent();
    }

    //Sets the toolbar title.
    private void setupToolbar(View view) {
        Toolbar toolbar = ViewCompat.requireViewById(view, R.id.toolbar);
        toolbar.setTitle(R.string.fragment_main_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_list_black_48dp);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                DrawerLayout drawerLayout = ViewCompat.requireViewById(Objects.requireNonNull(getView()), R.id.drawer_layout);
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sendIntent() {
        studentIntent = new Intent(getContext(), StudentActivity.class);
        studentIntent.putExtra(STUDENT,pViewModel.getStudent());
        if(pViewModel.isEdit){
            startActivityForResult(studentIntent,EDIT_STUDENT);
            onActivityResult(EDIT_STUDENT, Activity.RESULT_OK, studentIntent);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == EDIT_STUDENT) {
            if (resultCode == Activity.RESULT_OK) {
                mViewModel.editStudent(pViewModel.getOldStudent(),Objects.requireNonNull(data).getParcelableExtra(STUDENT));
            }
        }
        if(requestCode == ADD_STUDENT){
            if(resultCode == Activity.RESULT_OK){
                mViewModel.addStudent(Objects.requireNonNull(data).getParcelableExtra(STUDENT));
            }
        }
    }
}
