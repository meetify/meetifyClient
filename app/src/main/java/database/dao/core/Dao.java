package database.dao.core;

import android.database.Cursor;

import java.util.List;

/**
 * Created by Maks on 25.09.2016.
 */
public interface Dao<T> {
    //ПЕРЕЧЕНЬ ФУНКЦИЙ, КОТОРЫЕ НУЖНЫ ДЛЯ РАБОТЫ С БАЗОЙ ДАННЫХ
    long save(T t);
    List<T> getAll();
    List<T> parseCursor(Cursor cursor);
}
