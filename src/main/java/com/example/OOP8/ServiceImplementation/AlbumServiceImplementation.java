package com.example.OOP8.ServiceImplementation;

import com.example.OOP8.Service.AlbumService;
import com.example.OOP8.dao.AlbumDAO;
import com.example.OOP8.dao.SingerDAO;
import com.example.OOP8.dto.AlbumWithSingerDTO;
import Model.Album;
import Model.Singer;
import com.example.OOP8.dto.AlbumWithSongsQuantDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AlbumServiceImplementation implements AlbumService {
    private AlbumDAO albumDao = new AlbumDAO();
    private SingerDAO singerDao = new SingerDAO();

    public Album getAlbumByName(String name) {
        return albumDao.getAlbumByName(name);
    }

    public List<AlbumWithSingerDTO> getAlbums() {
        List<Album> album = albumDao.getAlbums();
        List<AlbumWithSingerDTO> albumWithSingerList;
        albumWithSingerList = album.stream().map(l -> new AlbumWithSingerDTO(l.getId(), l.getAlbumName(), l.getGenre(), l.getsinger().getId(), l.getsinger().getName())).collect(Collectors.toList());
        return albumWithSingerList;
    }

    public Album getAlbumById(Integer id) {
        return albumDao.getAlbumById(id);
    }

    public AlbumWithSingerDTO getAlbumWithSingerById(String id) {
        int idAlbumParsed = 0;
        try {
            idAlbumParsed = Integer.parseInt(id);
        } catch (Exception e) {

            e.printStackTrace();
        }
        Album album = albumDao.getAlbumById(idAlbumParsed);
        Singer singer = album.getsinger();
        AlbumWithSingerDTO awsd = new AlbumWithSingerDTO(album.getId(), album.getAlbumName(), album.getGenre(), singer.getId(), singer.getName());
        return awsd;
    }

    public boolean isNameExists(String name, String id) {
        int idAlbumParsed = 0;
        try {
            idAlbumParsed = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Album album = albumDao.getAlbumByName(name);
        if (album != null) return album.getId() != idAlbumParsed;
        return false;
    }

    public void updateAlbum(String id, String name, String genre, String singerName) {
        int idAlbumParsed = 0;
        try {
            idAlbumParsed = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Album album = albumDao.getAlbumById(idAlbumParsed);
        album.setAlbumName(name);
        album.setGenre(genre);
        Object singerObj = singerDao.getSingerByName(singerName);
        if (singerObj != null) {
            album.setsinger((Singer) singerObj);
        } else {
            Singer singer = new Singer(singerName);
            singerDao.insertSinger(singer);
            album.setsinger(singer);
        }
        albumDao.updateAlbum(album);
    }

    public void insertAlbum(String name, String genre, String singerName) {
        Object albumObj = albumDao.getAlbumByName(name);
        if (albumObj != null) return;
        Album album = new Album();
        Object singerObj = singerDao.getSingerByName(singerName);
        if (singerObj != null) {
            album.setsinger((Singer) singerObj);
        } else {
            Singer singer = new Singer(singerName);
            singerDao.insertSinger(singer);
            album.setsinger(singer);
        }
        album.setGenre(genre);
        album.setAlbumName(name);
        albumDao.insertAlbum(album);
    }

    public void deleteAlbum(String id) {
        try {
            Integer parsedId = Integer.parseInt(id);
            albumDao.deleteAlbum(parsedId);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
        }
    }
    public List<Album> getAllAlbumsByAuthorId(String id)
    {
        int idAlbumParsed = 0;
        try {
            idAlbumParsed = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return albumDao.getAllAlbumsByAuthorId(idAlbumParsed);
    }
    public List<AlbumWithSongsQuantDTO> getTopAlubms()
    {
        return albumDao.albumWithSongs();
    }

}
