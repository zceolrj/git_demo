package com.model;

import java.sql.Date;

public class Attachment 
{
    private int attID;
    
    private int docID;
    
    private String attType;
    
    private String attPath;
    
    private int uploaderID;
    
    private Date uploadTime;

	public int getAttID() {
		return attID;
	}

	public void setAttID(int attID) {
		this.attID = attID;
	}

	public int getDocID() {
		return docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public String getAttType() {
		return attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public String getAttPath() {
		return attPath;
	}

	public void setAttPath(String attPath) {
		this.attPath = attPath;
	}

	public int getUploaderID() {
		return uploaderID;
	}

	public void setUploaderID(int uploaderID) {
		this.uploaderID = uploaderID;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
    
}
