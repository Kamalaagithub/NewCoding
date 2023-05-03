package com.fileUploadDownload.payload;

public class FileUploadResponse
{
   private String fileName;
   
   private String fileType;
   
   private String fileDownloadUri;
   
   private long size;
    


public FileUploadResponse(String fileName, String fileType, String fileDownloadUri, long size) {

	this.fileName = fileName;
	this.fileType = fileType;
	this.fileDownloadUri = fileDownloadUri;
	this.size = size;
}



public String getFileName() {
	return fileName;
}

public void setFileName(String fileName) {
	this.fileName = fileName;
}

public long getSize() {
	   return size;
}

public void setSize(long size) {
	   	this.size = size;
}



public String getFileType() {
	return fileType;
}



public void setFileType(String fileType) {
	this.fileType = fileType;
}



public String getFileDownloadUri() {
	return fileDownloadUri;
}



public void setFileDownloadUri(String fileDownloadUri) {
	this.fileDownloadUri = fileDownloadUri;
}

   
}
