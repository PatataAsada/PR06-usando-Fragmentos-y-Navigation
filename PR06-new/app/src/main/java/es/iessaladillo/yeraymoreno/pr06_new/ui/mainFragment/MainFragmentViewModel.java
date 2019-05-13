package es.iessaladillo.yeraymoreno.pr06_new.ui.mainFragment;

import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import es.iessaladillo.yeraymoreno.pr06_new.data.AppDatabaseStudents;
import es.iessaladillo.yeraymoreno.pr06_new.data.DatabaseStudents;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;

public class MainFragmentViewModel extends ViewModel {
    private final AppDatabaseStudents databaseStudents;
    private LiveData<List<Student>> students;

    public MainFragmentViewModel(AppDatabaseStudents databaseStudents) {
        this.databaseStudents = databaseStudents;
    }


    public LiveData<List<Student>> getStudents(boolean forceload) {
        if (students == null || forceload) {
            students = databaseStudents.studentDao().queryStudents();
        }
        return students;
    }

    public void deleteStudent(Student item) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> databaseStudents.studentDao().delete(item));
    }

    public void addStudent(Student newStudent) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> databaseStudents.studentDao().insert(newStudent));
    }

    public void editStudent(Student oldStudent, Student newStudent) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> databaseStudents.studentDao().update(oldStudent, newStudent));
    }

    public void deleteAllStudents(){
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->databaseStudents.clearAllTables());
    }
}
