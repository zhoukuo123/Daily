package com.zk.spring.ioc.entity;

public class Apple {
    private String title;
    private String color;
    private String origin;

    public Apple() {
        System.out.println("构造方法");
        build();
    }

    public Apple build() {
        System.out.println("build方法");
        return this;
    }

    public Apple(String title, String color, String origin) {
        this.title = title;
        this.color = color;
        this.origin = origin;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        System.out.println("setTitle");
        this.title = title;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
