package com.codegym.myblog.repository;

import com.codegym.myblog.model.Myblog;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.lang.annotation.Annotation;
import java.util.List;

@Transactional
@Repository
public class MyblogRepositoryImpl implements MyblogRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<Myblog> getAllBlog() {
        return em.createQuery("from Myblog").getResultList();
    }

    @Override
    public void save(Myblog myblog) {
        em.persist(myblog);
    }

    @Override
    public void remove(int id) {
        em.remove(em.find(Myblog.class,id));
    }

    @Override
    public void update(Myblog myblog) {
        em.merge(myblog);
    }

    @Override
    @SuppressWarnings("unchecked")
    public Myblog getBlog(int id) {
        TypedQuery<Myblog> query = em.createQuery("select c from Myblog c where  c.id=:id", Myblog.class);
        query.setParameter("id", id);
        try {
            return query.getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }
}
