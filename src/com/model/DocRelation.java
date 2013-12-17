package com.model;

public class DocRelation 
{
    private int drID;
    
    private int docAID;
    
    private int docBID;
    
    private String relationType;

	public int getDrID() {
		return drID;
	}

	public void setDrID(int drID) {
		this.drID = drID;
	}

	public int getDocAID() {
		return docAID;
	}

	public void setDocAID(int docAID) {
		this.docAID = docAID;
	}

	public int getDocBID() {
		return docBID;
	}

	public void setDocBID(int docBID) {
		this.docBID = docBID;
	}

	public String getRelationType() {
		return relationType;
	}

	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}
}
