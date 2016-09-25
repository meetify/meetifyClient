package database.service.core;

import java.util.List;

/**
 * Created by Maks on 25.09.2016.
 */
public interface Service<T> {
    long save(T t);
    List<T> getAll();

}
