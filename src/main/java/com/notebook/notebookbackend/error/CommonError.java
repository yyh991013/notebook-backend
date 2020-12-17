package com.notebook.notebookbackend.error;

/**
 * @author 22454
 */
public interface CommonError {
    public int getErrCode();

    public String getErrMsg();

    public void setErrMsg(String msg);
}
