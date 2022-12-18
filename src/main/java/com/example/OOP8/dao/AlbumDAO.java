package com.example.OOP8.dao;

import com.example.OOP8.HibernateUtil;
import Model.Album;
import com.example.OOP8.dto.AlbumWithSongsQuantDTO;
import com.example.OOP8.dto.SingerWithGenreCountDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {
    public Album getAlbumByName(String name) {
        try (Session session = HibernateUtil.configureSession()) {
            Query getAlbumByNameQuery = session.createQuery("from Album where albumName=:name");
            getAlbumByNameQuery.setParameter("name", name);
            Object result = getAlbumByNameQuery.uniqueResult();
            if (result != null) return (Album) result;
            return null;
        }
    }

    //    public void setAlbum(AlbumDTO albumDto)
//    {
//        List<Album> albums = new ArrayList<>();
//
//        try (Session session = HibernateUtil.configureSession()) {
//            Singer singer = session.get(Singer.class, albumDto.getAuthorName());
//            albums = singer.getAlbums();
//        }
//    }
    public void updateAlbum(Album album) {
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            session.update(album);
            session.getTransaction().commit();
            session.close();
        }
    }

    public List<Album> getAlbums() {
        try (Session session = HibernateUtil.configureSession()) {
            List<Album> albums = new ArrayList<>();
            Query getAlbumByNameQuery = session.createQuery("from Album");
            return albums = getAlbumByNameQuery.getResultList();
        }
    }

    public void insertAlbum(Album album) {
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            session.save(album);
            session.getTransaction().commit();
            session.close();
        }
    }

    public void deleteAlbum(int id) {
        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("delete Album where id =: id");
            query.setParameter("id", id);
            query.executeUpdate();
            tx.commit();
        }
    }

    //    public void deleteAlbum(int id)
//    {
//        try (Session session = HibernateUtil.configureSession()) {
//            session.beginTransaction();
//            Album album = session.load(Album.class, id);
//            session.delete(album);
//            session.getTransaction().commit();
//            session.close();
//        }
//    }
//    public List<Compostition> getCompositions()
//    {
//        try (Session session = HibernateUtil.configureSession()) {
//            List<Comp> albums = new ArrayList<>();
//            Query getAlbumByNameQuery = session.createQuery("from album");
//            return albums = getAlbumByNameQuery.getResultList();
//        }
//    }
    public Album getAlbumById(int id) {
        try (Session session = HibernateUtil.configureSession()) {
            Album album = session.get(Album.class, id);
            return album;
        }
    }

    public List<Album> getAllAlbumsByAuthorId(int singerId) {
        List<Album> compositions;
        try (Session session = HibernateUtil.configureSession()) {
            session.beginTransaction();
            Query getCompositionByNameQuery = session.createQuery("from Album where singer.id =:id");
            getCompositionByNameQuery.setParameter("id", singerId);
            compositions = getCompositionByNameQuery.getResultList();
            session.getTransaction().commit();
            return compositions;
        }
    }

    public List<AlbumWithSongsQuantDTO> albumWithSongs() {
        try (Session session = HibernateUtil.configureSession()) {
            Transaction tx = session.beginTransaction();
            String queryBody = "select album_name,id_album, (select count (id_composition) as col from composition  where composition.album_id_album = id_album) as cot from album order by cot desc limit 5";
            Query query = session.createSQLQuery(queryBody);
            List<Object[]> albumsObj = query.getResultList();
            List<AlbumWithSongsQuantDTO> albums = new ArrayList<>();
            for (Object[] o : albumsObj) {
                BigInteger quantity = (BigInteger) o[2];
                albums.add(new AlbumWithSongsQuantDTO((String) o[0], (int)o[1],quantity.intValue()));
            }
            tx.commit();
            return albums;
        }
    }
}
