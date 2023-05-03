package com.fileUploadDownload.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

@Entity
@Table(name="files")
public class DatabaseFile
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private String id;
	
	private String fileName;
	
	private String fileType;
	
	@Lob
	private byte[] data;

	public DatabaseFile() {
		
	}

	public DatabaseFile(String fileName, String fileType, byte[] data) 
	{
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	
}
