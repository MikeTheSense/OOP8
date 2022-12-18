package com.example.OOP8.dao;

import Model.Album;
import com.example.OOP8.HibernateUtil;
import Model.Composition;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class CompositionDAO {
    public Composition getCompositionByName(String name) {
        try (Session session = HibernateUtil.configureSession()) {
            Query getCompositionByNameQuery = session.createQuery("from Composition where name=:name");
            getCompositionByNameQuery.setParameter("name", name);
            Object result = getCompositionByNameQuery.uniqueResult();
            if (result != null) return (Composition) result;
            return null;
        }
    }

    public void insertComposition(Composition Composition) {
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            session.save(Composition);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void deleteComposition(Composition Composition) {
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            session.delete(Composition);
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Composition> getAllCompositionFromAlbum(int albumId) {
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            Query getCompositionByNameQuery = session.createQuery("from Composition where album.id=:albumId");
            getCompositionByNameQuery.setParameter("albumId", albumId);
            session.getTransaction().commit();
            List<Composition> compositions = getCompositionByNameQuery.getResultList();
            session.close();
            return compositions;
        }
    }


    public Composition getCompositionById(int parsedCompId) {
        Object composition;
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            composition = session.get(Composition.class, parsedCompId);
            session.getTransaction().commit();
            session.close();
        }
        return (Composition) composition;
    }

    public void updateComposition(Composition composition) {
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            session.update(composition);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void deleteCompositionByName(String compName) {
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            Query getCompositionByNameQuery = session.createQuery("delete Composition where name =: compName ");
            getCompositionByNameQuery.setParameter("compName", compName);
            getCompositionByNameQuery.executeUpdate();
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Composition> getAllCompositions() {
        List<Composition> compositions;
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            Query getCompositionByNameQuery = session.createQuery("from Composition");
            //getCompositionByNameQuery.executeUpdate();
            compositions = getCompositionByNameQuery.getResultList();
            session.getTransaction().commit();
            return compositions;
        }
    }

}

