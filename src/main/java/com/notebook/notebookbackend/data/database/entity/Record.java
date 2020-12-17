package com.notebook.notebookbackend.data.database.entity;

public class Record {
    private int recordId;
    private int userId;
    private String sortName;
    private String recordTitle;
    private String recordContent;

    public Record(int recordId, int userId, String sortName, String recordTitle, String recordContent) {
        this.recordId = recordId;
        this.userId = userId;
        this.sortName = sortName;
        this.recordTitle = recordTitle;
        this.recordContent = recordContent;
    }

    public Record() {}

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getRecordContent() {
        return recordContent;
    }

    public void setRecordContent(String recordContent) {
        this.recordContent = recordContent;
    }
}
