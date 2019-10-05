package com.android.location.utils.fileUtils;

import java.io.InputStream;

public class FileHolder {

	
	private String fileName;
	private InputStream imageInputStream;
	
	public FileHolder() {
		
	}
	
	public FileHolder(String fileName, InputStream imageInputStream) {
		this.fileName = fileName;
		this.imageInputStream = imageInputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getImageInputStream() {
		return imageInputStream;
	}

	public void setImageInputStream(InputStream imageInputStream) {
		this.imageInputStream = imageInputStream;
	}


	
}
