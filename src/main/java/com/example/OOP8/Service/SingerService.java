package com.example.OOP8.Service;
import Model.Singer;
import com.example.OOP8.dto.SingerWithGenreCountDTO;

import java.util.List;

public interface SingerService {
    Singer getSingerById(Integer id);

    String getAllSingerNamesLike(String name);

    List<Singer> getAllSingers();

    Boolean isSingerNameUnique(String singerId, String singerName);

    void insertOrUpdateSinger(String singerId, String singerName);

    void deleteSinger(String id);

    List<SingerWithGenreCountDTO> getTopVerstaliteSingers();
}
