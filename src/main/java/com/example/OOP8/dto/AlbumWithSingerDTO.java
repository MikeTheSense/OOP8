package com.example.OOP8.dto;

public class AlbumWithSingerDTO {
    private Integer albumId;
    private String name;
    private String genre;
    private String singerName;
    private Integer singerId;


    public AlbumWithSingerDTO(Integer albumId, String name, String genre,Integer singerId,String singerName)
    {
        this.albumId = albumId;
        this.name = name;
        this.genre = genre;
        this.singerId = singerId;
        this.singerName = singerName;
    }

    public Integer getAlbumId() { return albumId; }
    public void setAlbumId(Integer albumId) { this.albumId = albumId; }
    public String getSingerName() { return singerName;}
    public void setSingerName(String singerName) { this.singerName = singerName; }
    public Integer getSingerId() { return singerId; }
    public void setSingerId(Integer singerId) { this.singerId = singerId; }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
}
