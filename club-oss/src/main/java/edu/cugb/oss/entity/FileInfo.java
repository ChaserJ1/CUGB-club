package edu.cugb.oss.entity;

/**
 * @Author pengjia
 * @Data 2024/11/5 13:39
 * @Description: 文件类
 */
public class FileInfo {
    private String fileName;

    private Boolean directoryFlag;

    private String etag;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Boolean getDirectoryFlag() {
        return directoryFlag;
    }

    public void setDirectoryFlag(Boolean directoryFlag) {
        this.directoryFlag = directoryFlag;
    }

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }
}
