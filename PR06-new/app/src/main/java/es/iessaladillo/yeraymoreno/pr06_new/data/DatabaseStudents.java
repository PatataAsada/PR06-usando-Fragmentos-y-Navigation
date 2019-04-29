package es.iessaladillo.yeraymoreno.pr06_new.data;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;

public class DatabaseStudents {
    private static DatabaseStudents instance;
    private ArrayList<Student> students = new ArrayList<>();
    private MutableLiveData<List<Student>> studentsLiveData = new MutableLiveData<>();


    // TODO: Define DatabaseStudents as a singleton.
    public DatabaseStudents() {
        students.add(new Student( 1, 1,"Baldo", "baldo@hotmail.com", 999999, "casa", "www.miweb.es"));
        students.add(new Student( 2, 2,"Albedo", "baldo@hotmail.com", 999999, "casa", "www.miweb.es"));
        students.add(new Student( 3, 3,"Pipo", "baldo@hotmail.com", 999999, "casa", "www.miweb.es"));
        students.add(new Student( 4, 4,"German", "baldo@hotmail.com", 999999, "casa", "www.miweb.es"));
        updateStudentsLiveData();
    }

    public static DatabaseStudents getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new DatabaseStudents();
                }
            }
        }
        return instance;
    }

    public LiveData<List<Student>> getStudents() {
        return studentsLiveData;
    }

    public void deleteStudent(Student student) {
        students.remove(student);
        updateStudentsLiveData();
    }

    private void updateStudentsLiveData() {
        studentsLiveData.setValue(new ArrayList<>(students));
    }

    public void addStudent(Student newStudent) {
        students.add(newStudent);
        updateStudentsLiveData();
    }

    public void editStudent(int oldstudentindex, Student newStudent) {
        students.set(oldstudentindex,newStudent);
        updateStudentsLiveData();
    }

}
