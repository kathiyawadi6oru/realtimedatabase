package com.example.reltimedatabase;

public class post {
    String title;
    String name;
    String city;

    public post() {
    }

    @Override
    public String toString() {
        return "post{" +
                "title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
