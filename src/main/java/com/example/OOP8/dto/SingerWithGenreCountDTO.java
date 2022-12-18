package com.example.OOP8.dto;

import java.io.Serializable;

public class SingerWithGenreCountDTO implements Serializable {
    public SingerWithGenreCountDTO() {

    }
    public SingerWithGenreCountDTO(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }

    private String name;
    private int count;



}
