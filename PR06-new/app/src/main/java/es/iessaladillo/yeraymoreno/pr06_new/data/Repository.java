package es.iessaladillo.yeraymoreno.pr06_new.data;

import androidx.lifecycle.LiveData;

import com.google.common.io.Resources;

import java.util.List;

import es.iessaladillo.yeraymoreno.pr06_new.base.Resource;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;

public interface Repository {
    LiveData<List<Student>> queryStudents();

    LiveData<Student> queryStudent();

    LiveData<Resource<Long>> insertStudent(Student student);

    LiveData<Resource<Integer>> updateStudent(Student student);

    LiveData<Resource<Integer>> deleteStudent(Student student);
}
