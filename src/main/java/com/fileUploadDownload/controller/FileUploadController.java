package com.fileUploadDownload.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fileUploadDownload.entity.DatabaseFile;
import com.fileUploadDownload.payload.FileUploadResponse;
import com.fileUploadDownload.service.FileStorageService;

@RestController
@RequestMapping("/uploadfile")
public class FileUploadController
{
	@Autowired
	private FileStorageService fileStorageService;
	
	@PostMapping("/uploadSinglefile")
    public FileUploadResponse uploadSingleFile(@RequestParam("file") MultipartFile file)
    {
   	 DatabaseFile fileName = fileStorageService.storeFile(file);
   	 
   	 
   	 ///http://localhost:8082/download/abc.pdf
   	 String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
   			 .path("/downloadfile/")
   			 .path(fileName.getFileName())
   			 .toUriString();
   	 
   	 
   	 
   	 return new FileUploadResponse(fileName.getFileName(),fileDownloadUri,file.getContentType(),file.getSize());
    }
	
	@PostMapping("/uploadMultiplefiles")
	public List<FileUploadResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile files)
	{
		return Arrays.asList(files).stream().map(file -> uploadSingleFile(file)).collect(Collectors.toList());
	}
	
}

