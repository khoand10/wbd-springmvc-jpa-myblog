package com.codegym.myblog.model;

import javax.persistence.*;

@Entity
@Table(name ="blogs")
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String title;

    private String summary;

    private String content;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    public Blog(){
    }

    public Blog(String title, String summary, String content, Category category) {
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
