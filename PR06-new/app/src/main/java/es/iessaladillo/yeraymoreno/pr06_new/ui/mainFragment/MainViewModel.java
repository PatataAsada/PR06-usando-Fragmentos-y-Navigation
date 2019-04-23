package es.iessaladillo.yeraymoreno.pr06_new.ui.mainFragment;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.iessaladillo.yeraymoreno.pr06_new.data.DatabaseStudents;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;

public class MainViewModel extends ViewModel {
    private final DatabaseStudents databaseStudents;
    private LiveData<List<Student>> students;

    public MainViewModel(DatabaseStudents databaseStudents) {
        this.databaseStudents = databaseStudents;
    }


    public LiveData<List<Student>> getStudents(boolean forceload) {
        if (students == null || forceload) {
            students = databaseStudents.getStudents();
        }
        return students;
    }

    public void deleteStudent(Student item) {
        databaseStudents.deleteStudent(item);
    }

    public void addStudent(Student newStudent) {
        databaseStudents.addStudent(newStudent);
    }

    public void editStudent(Student oldStudent, Student newStudent) {
        int oldstudentindex = students.getValue().indexOf(oldStudent);
        databaseStudents.editStudent(oldstudentindex,newStudent);
    }
}
