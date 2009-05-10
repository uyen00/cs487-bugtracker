package com.gheewhiz;

import java.util.HashSet;
import java.util.Set;

public class Bug {

	Integer bugId;
	String state;
	String prodCategory;
	String prodVersion;
	String resolution="Not set";
	java.util.Date opened;
	
	//comments
	String shortdesc;
	String steps;
	String morecomments;
	
	Set<Comment> comments;
	

	public Bug() {
	}

	public void setBugId(Integer accountId) {
		this.bugId = accountId;
	}

	public Integer getBugId() {
		return bugId;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
	
	public void setProdVersion(String prodVersion) {
		this.prodVersion = prodVersion;
	}

	public String getProdVersion() {
		return prodVersion;
	}
	
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}

	public String getProdCategory() {
		return prodCategory;
	}
	
	public void setDateOpened(java.util.Date opened) {
		this.opened = opened;
	}

	public java.util.Date getDateOpened() {
		return opened;
	}
	
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getResolution() {
		return resolution;
	}

	public Set<Comment> getComments() {
		if (comments == null) {
			return new HashSet<Comment>();
		}
		return comments;
	}

	public void setShortDesc(String shortdesc) {
		this.shortdesc = shortdesc;
	}
	
	public String getShortDesc() {
		return shortdesc;
	}
	
	public void setSteps(String steps) {
		this.steps = steps;
	}
	
	public String getSteps() {
		return steps;
	}
	
	public void setMoreComments(String morecomments) {
		this.morecomments = morecomments;
	}
	
	public String getMoreComments() {
		return morecomments;
	}
	
}
