package com.fileUploadDownload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fileUploadDownload.entity.DatabaseFile;
import com.fileUploadDownload.service.FileStorageService;
import jakarta.servlet.http.HttpServletRequest;
/*
 * FileDownloadController is used to download a file.
 */
@RestController
@RequestMapping("/downloadfile")
public class FileDownloadController 
{
	
	private FileStorageService fileStorageService;
	
	@Autowired
	public FileDownloadController(FileStorageService thefileStorageService){
		this.fileStorageService = thefileStorageService;
	}	
	
	 @GetMapping("/downloadFile/{fileId:.+}")
     public ResponseEntity<Resource> downloadFile(@PathVariable String fileId, HttpServletRequest request)
     {
		 //Load File as a Resource
    	 DatabaseFile databaseFile = fileStorageService.getFile(fileId);
    	    return ResponseEntity.ok()
    			 .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
    			 .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:fileName=\""+ databaseFile.getFileName()+"\"")	
    			 .body(new ByteArrayResource(databaseFile.getData()));    	 
    	 
     }
	
}
