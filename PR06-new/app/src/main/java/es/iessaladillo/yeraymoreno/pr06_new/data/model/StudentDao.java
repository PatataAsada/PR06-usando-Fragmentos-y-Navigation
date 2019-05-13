package es.iessaladillo.yeraymoreno.pr06_new.data.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

import es.iessaladillo.yeraymoreno.pr06_new.base.BaseDao;

@Dao
public interface StudentDao extends BaseDao<Student> {

    @Query("SELECT * FROM Student WHERE id = :studentId")
    LiveData<Student> queryStudent(long studentId);

    @Query("SELECT * FROM Student ORDER BY name")
    LiveData<List<Student>> queryStudents();
}
