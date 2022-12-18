package com.example.OOP8.dto;

public class AlbumWithSongsQuantDTO {
    public Integer getAlbumId() {
        return albumId;
    }

    public void setAlbumId(Integer albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private Integer albumId;
    private String name;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    private Integer quantity;

    public AlbumWithSongsQuantDTO(String name,Integer albumId, Integer quantity) {
        this.albumId = albumId;
        this.name = name;
        this.quantity = quantity;
    }


}
