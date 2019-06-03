package es.iessaladillo.yeraymoreno.pr06_new.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import es.iessaladillo.yeraymoreno.pr06_new.base.Resource;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;

public interface RepositoryStudents {
    LiveData<List<Student>> queryStudents();

    LiveData<Student> queryStudent(long id);

    LiveData<Resource<Long>> insertStudent(Student student);

    LiveData<Resource<Integer>> updateStudent(Student student);

    LiveData<Resource<Integer>> deleteStudent(Student student);
}
