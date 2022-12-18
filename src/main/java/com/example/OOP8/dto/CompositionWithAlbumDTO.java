package com.example.OOP8.dto;


public class CompositionWithAlbumDTO {
    public CompositionWithAlbumDTO(String albumName, String compositionName, int duration) {
        this.albumName = albumName;
        this.compositionName = compositionName;
        this.duration = duration;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getCompositionName() {
        return compositionName;
    }

    public void setCompositionName(String compositionName) {
        this.compositionName = compositionName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    private String albumName;
    private String compositionName;
    private int duration;
}
