package com.example.OOP8.ServiceImplementation;

import com.example.OOP8.Service.SingerService;
import com.example.OOP8.dao.SingerDAO;
import com.example.OOP8.dto.SingerWithGenreCountDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.Singer;

import java.util.List;
import java.util.stream.Collectors;

public class SingerServiceImplementation implements SingerService {
    private SingerDAO singerDao = new SingerDAO();
    public Singer getSingerById(Integer id)
    {
        return singerDao.getSingerById(id);
    }
    public List<Singer> getAllSingers()
    {
        return singerDao.getAllSingers();
    }


    public Boolean isSingerNameUnique(String singerId, String singerName) {
        Object singerObject = singerDao.getSingerByName(singerName);
        if (singerObject != null)
        {
            int parsedId = 0;
            try{
                parsedId = Integer.parseInt(singerId);
            }
            catch(NumberFormatException ex) {
                ex.printStackTrace();
            }
            Singer singer = (Singer) singerObject;
            return  (singer.getId() != parsedId);
        }
        return null;
    }

    public String getAllSingerNamesLike(String name)
    {
        List<Singer> singres = singerDao.getAllSingersLike(name);
        List<String> singerNames = getAllSingers().stream().map(singer -> singer.getName()).collect(Collectors.toList());
        ObjectMapper obj = new ObjectMapper();
        String names = "";
        try {
            names = obj.writeValueAsString(singerNames);
        }
        catch (JsonProcessingException e) { e.printStackTrace(); }
        return names;
    }
    public void insertOrUpdateSinger(String singerId, String singerName)
    {
        int parsedId = 0;
        try{
            parsedId = Integer.parseInt(singerId);
        }
        catch(NumberFormatException ex) {
            ex.printStackTrace();
        }
        if (parsedId > 0) {
            singerDao.updateSingerById(parsedId,singerName);
        }
        else singerDao.insertSinger(new Singer(singerName));
        return;
    }
    public void deleteSinger(String id){
        int parsedId = 0;
        try{
            parsedId = Integer.parseInt(id);
        }
        catch(NumberFormatException ex) {
            ex.printStackTrace();
        }
        singerDao.deleteSingerById(parsedId);
    }
    public List<SingerWithGenreCountDTO> getTopVerstaliteSingers()
    {
        return  singerDao.getTopVerstaliteSingers();
    }

}
