package es.iessaladillo.yeraymoreno.pr06_new.data;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import javax.annotation.Nonnull;

import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.StudentDao;

@Database(entities = {Student.class}, version = 1)
public abstract class AppDatabaseStudents extends RoomDatabase {
    private static final String DATABASE_NAME = "perfiles";
    private static AppDatabaseStudents instance;

    public static AppDatabaseStudents getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabaseStudents.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabaseStudents.class, DATABASE_NAME).addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@Nonnull SupportSQLiteDatabase db) {
                            insertInitialData();
                        }
                    }).build();
                }
            }
        }
        return instance;
    }
    public static void insertInitialData() {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(()->instance.studentDao()
                .insert(new Student(1,"Baldo","baldo@hotmail.com",12345,"su casa","www.baldo.com")));
    }

    public abstract StudentDao studentDao();
}
