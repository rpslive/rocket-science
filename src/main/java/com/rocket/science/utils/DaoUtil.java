package com.rocket.science.utils;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class DaoUtil<T> {

    private HibernateTemplate hibernateTemplate;

    public HibernateTemplate getHibernateTemplate() {
        return hibernateTemplate;
    }

    private Class<T> persistentClass;

    public DaoUtil() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.persistentClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];

    }

    public Class<T> getPersistentClass() {
        return persistentClass;
    }

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Transactional(readOnly = false)
    public void save(T entity){
        hibernateTemplate.save(entity);
    }

    @Transactional
    public List<T> findAllMatching(T entity){
        return hibernateTemplate.findByExample(entity);
    }

    @Transactional
    public List<T> findAll(){
        return (List<T>) hibernateTemplate.findByCriteria(DetachedCriteria.forClass(getPersistentClass()));
    }

    public T findFirst(T entity){
        List<T> results = findAllMatching(entity);
        if(results.size()>0){
            return results.get(0);
        }else{
            return null;
        }
    }

    @Transactional
    public void deleteFirst(T entity){
        hibernateTemplate.delete(findFirst(entity));
    }

    @Transactional
    public void deleteAll(T entity){
        hibernateTemplate.deleteAll(findAllMatching(entity));
    }

    @Transactional
    public T update(T entity){
        hibernateTemplate.update(entity);
        List<T> results = hibernateTemplate.findByExample(entity);
        if(results.size()>0){
            return results.get(0);
        }else{
            return null;
        }
    }

}