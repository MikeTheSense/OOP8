package com.example.OOP8.dao;

import com.example.OOP8.HibernateUtil;
import Model.Singer;
import com.example.OOP8.dto.SingerWithGenreCountDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.*;

public class SingerDAO {
    public Singer getSingerById(Integer id) {
        try (Session session = HibernateUtil.configureSession()) {
            Singer Singer = session.get(Singer.class, id);
            return Singer;
        }
    }

    public List<Singer> getAllSingers() {
        try (Session session = HibernateUtil.configureSession()) {
            List<Singer> Singers = new ArrayList<>();
            Query getAlbumByNameQuery = session.createQuery("from Singer");
            return Singers = getAlbumByNameQuery.getResultList();
        }
    }

    public List<Singer> getAllSingersLike(String name) {
        try (Session session = HibernateUtil.configureSession()) {
            List<Singer> singers = new ArrayList<>();
            Query getAlbumByNameQuery = session.createQuery("from Singer where name like :name");
            getAlbumByNameQuery.setParameter("name", name);
            singers = getAlbumByNameQuery.getResultList();
            return singers;
        }
    }

    public Singer getSingerByName(String SingerName) {
        try (Session session = HibernateUtil.configureSession()) {
            Query getSingerByName = session.createQuery("from Singer where name=:name");
            getSingerByName.setParameter("name", SingerName);
            Object result = getSingerByName.uniqueResult();
            if (result != null) return (Singer) result;
            return null;
        }
    }

    public void insertSinger(Singer Singer) {
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            session.save(Singer);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void updateSingerById(int SingerId, String Singername) {
        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Singer set name =: Singername where id =: SingerId");
            query.setParameter("Singername", Singername);
            query.setParameter("SingerId", SingerId);
            query.executeUpdate();
            tx.commit();
        }
    }

    public void deleteSingerById(int SingerId) {
        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("delete Singer where id =: SingerId");
            query.setParameter("SingerId", SingerId);
            query.executeUpdate();
            tx.commit();

        }
    }

    public List<SingerWithGenreCountDTO> getTopVerstaliteSingers() {
        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            String queryBody = "select singer_name, (select count (album.album_genre) as col from album  where album.singer_id_singer = id_singer) as cot from singer order by cot desc limit 10";

            Query query = session.createSQLQuery(queryBody);
            List<Object[]> singersObject = query.getResultList();
            List<SingerWithGenreCountDTO> singers = new ArrayList<>();
            for(Object[] o : singersObject)
            {
                BigInteger bigInteger = (BigInteger) o[1];

                singers.add(new SingerWithGenreCountDTO((String)o[0],bigInteger.intValue()));
            }
            tx.commit();
            return singers;
        }
    }

}
