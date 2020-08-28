package org.formation.model;

import java.io.Serializable;
import java.util.Date;


public class Document implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6590486482810196501L;


	
	private String name,contentType;
	

	private byte[] data;
	
	private Date uploadedDate;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public Date getUploadedDate() {
		return uploadedDate;
	}

	public void setUploadedDate(Date uploadedDate) {
		this.uploadedDate = uploadedDate;
	}
	
	
}
