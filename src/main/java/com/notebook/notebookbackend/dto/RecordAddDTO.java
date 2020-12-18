package com.notebook.notebookbackend.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class RecordAddDTO {
    @NotNull
    @Length(min = 1, max = 30)
    private String title;

    @Null
    @Length(min = 1, max = 30)
    private String sort;

    @NotNull
    @Length(min = 1)
    private String content;

    public RecordAddDTO(String title, String sort, String content) {
        this.title = title;
        this.sort = sort;
        this.content = content;
    }

    public RecordAddDTO() {}

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
}
