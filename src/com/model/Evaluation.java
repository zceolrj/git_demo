package com.model;

import java.sql.Date;

public class Evaluation 
{
    private int evalueID;
    
    private int docID;
    
    private int userID;
    
    private String evalueType;
    
    private String content;
    
    private Date uploadTime;

	public int getEvalueID() {
		return evalueID;
	}

	public void setEvalueID(int evalueID) {
		this.evalueID = evalueID;
	}

	public int getDocID() {
		return docID;
	}

	public void setDocID(int docID) {
		this.docID = docID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getEvalueType() {
		return evalueType;
	}

	public void setEvalueType(String evalueType) {
		this.evalueType = evalueType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
    
}
