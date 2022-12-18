package com.example.OOP8.Service;


import com.example.OOP8.dto.AlbumWithSingerDTO;
import Model.Album;
import com.example.OOP8.dto.AlbumWithSongsQuantDTO;

import java.util.List;

public interface AlbumService {
    Album getAlbumByName(String name);

    List<AlbumWithSingerDTO> getAlbums();

    Album getAlbumById(Integer id);

    AlbumWithSingerDTO getAlbumWithSingerById(String id);

    boolean isNameExists(String name, String id);

    void updateAlbum(String id, String name, String genre, String singerName);

    void insertAlbum(String name, String genre, String singerName);

    void deleteAlbum(String id);

    List<Album> getAllAlbumsByAuthorId(String id);

    List<AlbumWithSongsQuantDTO> getTopAlubms();
}
