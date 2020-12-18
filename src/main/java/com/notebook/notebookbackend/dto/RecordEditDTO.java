package com.notebook.notebookbackend.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class RecordEditDTO {
    @NotNull
    private int id;

    @NotNull
    @Length(min = 1, max = 30)
    private String title;

    @Null
    @Length(min = 1, max = 30)
    private String sort;

    @NotNull
    @Length(min = 1)
    private String content;

    public RecordEditDTO(int id, String title, String sort, String content) {
        this.id = id;
        this.title = title;
        this.sort = sort;
        this.content = content;
    }

    public RecordEditDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "RecordAddDTO {" +
                "title=" + title + "', " +
                "sort=" + sort + "', " +
                "content=" + content + "}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
