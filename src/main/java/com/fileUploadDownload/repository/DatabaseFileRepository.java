package com.fileUploadDownload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fileUploadDownload.entity.*;

public interface DatabaseFileRepository extends JpaRepository<DatabaseFile,String>
{

}
