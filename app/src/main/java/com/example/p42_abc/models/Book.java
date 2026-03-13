package com.example.p42_abc.models;

import java.util.List;

public class Book {
    private String _id;
    private String _title;
    private String _description;
    private List<String> _tags;

    public Book(String id, String title, String description, List<String> tags){
        this._id = id;
        this._title = title;
        this._description = description;
        this._tags = tags;
    }

    public String getId() {
        return _id;
    }

    public void setId(String id) {
        this._id = id;
    }

    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        this._title = title;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(String description) {
        this._description = description;
    }

    public List<String> getTags() {
        return _tags;
    }

    public void setTags(List<String> tags) {
        this._tags = tags;
    }

}
