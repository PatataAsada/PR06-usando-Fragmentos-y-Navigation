package es.iessaladillo.yeraymoreno.pr06_new.data;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import es.iessaladillo.yeraymoreno.pr06_new.base.Resource;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.Student;
import es.iessaladillo.yeraymoreno.pr06_new.data.model.StudentDao;

public class RepositoryStudentsImpl implements RepositoryStudents {
    private final StudentDao studentDao;

    public RepositoryStudentsImpl(StudentDao studentDao){
        this.studentDao = studentDao;
    }

    @Override
    public LiveData<List<Student>> queryStudents() {
        return studentDao.queryStudents();
    }

    @Override
    public LiveData<Student> queryStudent(long id) {
        return studentDao.queryStudent(id);
    }

    @Override
    public LiveData<Resource<Long>> insertStudent(Student student) {
        MutableLiveData<Resource<Long>> result = new MutableLiveData<>();
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() ->{
            result.postValue(Resource.loading());
            try{
                long id = studentDao.insert(student);
                result.postValue(Resource.success(id));
            }catch (Exception e){
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }

    @Override
    public LiveData<Resource<Integer>> updateStudent(Student student) {
        MutableLiveData<Resource<Integer>> result = new MutableLiveData<>();
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            result.postValue(Resource.loading());
            try {
                int updated = studentDao.update(student);
                result.postValue(Resource.success(updated));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }

    @Override
    public LiveData<Resource<Integer>> deleteStudent(Student student) {
        MutableLiveData<Resource<Integer>> result = new MutableLiveData<>();
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> {
            result.postValue(Resource.loading());
            try {
                int deleted = studentDao.delete(student);
                result.postValue(Resource.success(deleted));
            } catch (Exception e) {
                result.postValue(Resource.error(e));
            }
        });
        return result;
    }
}
