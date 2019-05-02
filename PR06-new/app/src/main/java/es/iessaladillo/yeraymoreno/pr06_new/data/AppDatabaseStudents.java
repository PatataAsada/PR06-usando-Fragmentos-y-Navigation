package es.iessaladillo.yeraymoreno.pr06_new.data;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;

@Database(entities = {Student.class}, version=1)
public abstract class AppDatabaseStudents extends RoomDatabase {

}
