package com.example.OOP8.Service;


import com.example.OOP8.dto.CompositionWithAlbumDTO;
import com.example.OOP8.dto.CompositionsWithAlbumDTO;
import Model.Composition;

import java.util.List;

public interface CompositionService {
    CompositionsWithAlbumDTO getCompositionsByAlbumId(String id);
    boolean isNameExists(String compositionName, String compositionId);
    void  addCompositionToAlbumById(String id,String nameCom, String duration,String compId);
    Composition getCompositionByName(String compositionname);
    String removeCompositionFromAlbum(String albumId,String compId);
    List<CompositionWithAlbumDTO> getCompositionsWithAlbum();
}
