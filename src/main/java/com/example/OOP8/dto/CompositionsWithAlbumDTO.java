package com.example.OOP8.dto;

import Model.Composition;

import java.util.List;

public class CompositionsWithAlbumDTO {
    private String albumName;

    private List<Composition> compostitionList;

    public CompositionsWithAlbumDTO(String albumName, List<Composition> compostitionList) {
        this.compostitionList = compostitionList;
        this.albumName = albumName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public List<Composition> getCompostitionList() {
        return compostitionList;
    }

    public void setCompostitionList(List<Composition> compostitionList) {
        this.compostitionList = compostitionList;
    }

}
