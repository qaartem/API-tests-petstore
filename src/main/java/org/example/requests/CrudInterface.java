package org.example.requests;

public interface CrudInterface<T> {
    Object create(T entity);
    Object read(int id);
    Object update(int id, T entity);
    Object delete(int id);
}
