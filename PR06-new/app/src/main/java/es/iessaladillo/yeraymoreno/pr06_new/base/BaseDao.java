package es.iessaladillo.yeraymoreno.pr06_new.base;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

//Caso base, sirve para cualquier tabla en room si la implementas.
public interface BaseDao<M> {
    @Insert
    long insert(M model);

    @Update
    int update(M... model);

    @Delete
    int delete(M... model);
}
