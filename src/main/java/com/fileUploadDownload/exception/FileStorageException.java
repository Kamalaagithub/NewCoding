package com.fileUploadDownload.exception;

public class FileStorageException extends RuntimeException 
{
	/**Custom Exception is created.
	 * To handle if any file needs to be stored causes any exception
	 * during at that it  will be useful.
	 */
	private static final long serialVersionUID = 1L;

	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public FileStorageException(String message) {
		super(message);
		
	}
	
}
