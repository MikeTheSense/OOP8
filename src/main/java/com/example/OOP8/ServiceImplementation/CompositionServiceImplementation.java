package com.example.OOP8.ServiceImplementation;

import com.example.OOP8.Service.CompositionService;
import com.example.OOP8.dao.AlbumDAO;
import com.example.OOP8.dao.CompositionDAO;
import com.example.OOP8.dao.SingerDAO;
import com.example.OOP8.dto.AlbumWithSingerDTO;
import com.example.OOP8.dto.CompositionWithAlbumDTO;
import com.example.OOP8.dto.CompositionsWithAlbumDTO;
import Model.Album;
import Model.Composition;

import java.util.List;
import java.util.stream.Collectors;

public class CompositionServiceImplementation implements CompositionService {
    private AlbumDAO albumDao = new AlbumDAO();

    private SingerDAO singerDao = new SingerDAO();
    private CompositionDAO compositionDAO = new CompositionDAO();
    public CompositionsWithAlbumDTO getCompositionsByAlbumId(String id)
    {
        int idParsed = 0;
        try {
            idParsed = Integer.parseInt(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Album album = albumDao.getAlbumById(idParsed);
        if(album!=null)
        return new CompositionsWithAlbumDTO(album.getAlbumName(),compositionDAO.getAllCompositionFromAlbum(album.getId()));
        else return null;
    }


    public boolean isNameExists(String compositionName, String compositionId) {
        int idParsed = 0;
        try {
            idParsed = Integer.parseInt(compositionId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Composition comp = compositionDAO.getCompositionByName(compositionName);
        if (comp != null) return comp.getId() != idParsed;
        return false;
    }
    public void addCompositionToAlbumById(String id,String nameCom, String duration,String compId) {
        int parsedId = 0;
        int parsedDuration = 1;
        int parsedCompId = 0;
        try {
            parsedId = Integer.parseInt(id);
            parsedDuration = Integer.parseInt(duration);
            parsedCompId = Integer.parseInt(compId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        final int i = parsedCompId;
        Object albumObject = albumDao.getAlbumById(parsedId);
        if (albumObject != null) {
            Composition composition = compositionDAO.getCompositionById(parsedCompId);
            if (composition != null) {
                composition.setName(nameCom);
                composition.setDuration(parsedDuration);
                compositionDAO.updateComposition(composition);
            } else {
                compositionDAO.insertComposition(new Composition(parsedDuration, nameCom,(Album)albumObject));
            }
//            List<Composition> CompositionList = album.getCompositions();
//            Object compObject = CompositionList.stream().filter(l->l.getId()==i).findAny().orElse(null);
//            if  (compObject!=null)
//            {
//                Composition comp = (Composition) compObject;
//                comp.setDuration(parsedDuration);
//                comp.setName(nameCom);
//            }
//            else album.getCompositions().add(new Composition(parsedDuration,nameCom,album));
//            albumDao.updateAlbum(album);
//            return new CompositionsWithAlbumDTO(album.getAlbumName(),album.getCompositions());
        }
    }
    public Composition getCompositionByName(String compositionname){
        Object compObj = compositionDAO.getCompositionByName(compositionname);
        if(compObj!=null)
            return (Composition) compObj;
        else return null;
    }
    public String removeCompositionFromAlbum(String name,String compName)
    {
        Object albumObject = albumDao.getAlbumByName(name);
        if (albumObject!=null) {
            Album album = (Album) albumObject;
            compositionDAO.deleteCompositionByName(compName);
            return Integer.toString(album.getId());
        }
        else return null;
    }
    public List<CompositionWithAlbumDTO> getCompositionsWithAlbum() {
        List<Composition> compositions = compositionDAO.getAllCompositions();
        List<CompositionWithAlbumDTO> albumWithSingerList;
        albumWithSingerList = compositions.stream().map(l -> new CompositionWithAlbumDTO(l.getAlbumName(),l.getName(),l.getDuration())).collect(Collectors.toList());
        return albumWithSingerList;
    }

}
