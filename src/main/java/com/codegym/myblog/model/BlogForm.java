package com.codegym.myblog.model;

import org.springframework.web.multipart.MultipartFile;

public class BlogForm {

    private Long id;
    private String title;
    private String summary;
    private String content;
    private MultipartFile feature;
    private String featureUrl;
    private Category category;

    public BlogForm(){
    }

    public BlogForm(Long id, String title, String summary, String content, MultipartFile feature, String featureUrl, Category category) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.feature = feature;
        this.featureUrl = featureUrl;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public MultipartFile getFeature() {
        return feature;
    }

    public void setFeature(MultipartFile feature) {
        this.feature = feature;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getFeatureUrl() {
        return featureUrl;
    }

    public void setFeatureUrl(String featureUrl) {
        this.featureUrl = featureUrl;
    }
}
