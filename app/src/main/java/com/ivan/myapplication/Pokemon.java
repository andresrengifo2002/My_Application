package com.ivan.myapplication;

public class Pokemon {
    private int number;
    private String name;
    private String url;

    public int getNumber() {
        String[] urlPartes=url.split("/");
        int id= Integer.parseInt( urlPartes[urlPartes.length-1]);
        return id;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
