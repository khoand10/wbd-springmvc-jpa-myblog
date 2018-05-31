package com.codegym.myblog.model;

import javax.persistence.*;

@Entity
@Table(name = "myblogs")
public class Myblog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String content;
    private String header;
    private String about;
    private String author;
    public Myblog(){}

    public Myblog(String content, String header, String about, String author) {
        this.content = content;
        this.header = header;
        this.about = about;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Myblog{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", header='" + header + '\'' +
                ", about='" + about + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
