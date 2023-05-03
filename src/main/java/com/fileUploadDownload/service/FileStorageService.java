package com.fileUploadDownload.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.fileUploadDownload.entity.DatabaseFile;
import com.fileUploadDownload.exception.FileNotFoundException;
import com.fileUploadDownload.exception.FileStorageException;
import com.fileUploadDownload.repository.DatabaseFileRepository;



@Service
public class FileStorageService 
{
    @Autowired
	public DatabaseFileRepository dbFileRepository;
    
	public DatabaseFile storeFile(MultipartFile file)
	 {
		//Normalize the fileName
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			//Check if fileName have invalid characters.
			if(fileName.contains("..")) {
				throw new FileStorageException("Sorry fileName contains invalid path sequence"+fileName);
			}
			
			DatabaseFile dbFile = new DatabaseFile(fileName,file.getContentType(),file.getBytes());
			
			return dbFileRepository.save(dbFile);
		}catch(IOException io)
		{
			throw new FileStorageException("Could not store the file"+fileName+",Sorry Please try again",io);
		}
		 
	 }
	
	public DatabaseFile getFile(String fileId) 
	{
		return dbFileRepository.findById(fileId).orElseThrow(()-> new FileNotFoundException("File  not found with fileId" + fileId));
	}

	
}
