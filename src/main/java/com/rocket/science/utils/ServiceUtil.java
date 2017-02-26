package com.rocket.science.utils;

import java.util.List;


public class ServiceUtil<T> {


    public void book(T t){

    }
    private DaoUtil<T> dao;

    public DaoUtil<T> getDao() {
        return dao;
    }

    public void setDao(DaoUtil<T> dao) {
        this.dao = dao;
    }

    public boolean add(T entity) {
        try {
            dao.save(entity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public T update(T entity) {
        try {
            return dao.update(entity);
        }catch (Exception e){
            return null;
        }
    }

    public boolean delete(T entity){
        try {
            dao.deleteFirst(entity);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public T get(T entity) {
        try {
            return dao.findFirst(entity);
        }catch (Exception e){
            return null;
        }
    }

    public List<T> getAll(){
        try{
            return dao.findAll();
        }catch (Exception e){
            return null;
        }
    }
}