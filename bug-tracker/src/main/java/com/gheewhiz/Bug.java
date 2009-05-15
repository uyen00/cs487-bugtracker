package com.gheewhiz;

import java.util.HashSet;
import java.util.Set;

public class Bug implements Comparable<Bug> {

	Integer bugId;
	Integer productId;
	String state;
	String resolution="Not set";
	java.util.Date opened;

	Integer qaId;
	Integer devId;
	Integer managerId;
	
	//comments
	String shortdesc;
	String steps;
	
	Set<Comment> comments;
	

	public Bug() {
	}

	public void setBugId(Integer accountId) {
		this.bugId = accountId;
	}

	public Integer getBugId() {
		return bugId;
	}
	
	public Integer getProductId() {
		return productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
	
	public java.util.Date getOpened() {
		return opened;
	}

	public void setOpened(java.util.Date opened) {
		this.opened = opened;
	}

	public String getShortdesc() {
		return shortdesc;
	}

	public void setShortdesc(String shortdesc) {
		this.shortdesc = shortdesc;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public Set<Comment> getComments() {
		if (comments == null) {
			return new HashSet<Comment>();
		}
		return comments;
	}
	
	public void setResolution(String resolution) {
		this.resolution = resolution;
	}

	public String getResolution() {
		return resolution;
	}

	public void setSteps(String steps) {
		this.steps = steps;
	}
	
	public String getSteps() {
		return steps;
	}
	
	public int compareTo(Bug o) {
		return this.getBugId().compareTo(o.getBugId());
	}
	
	public Integer getQAId() {
		return qaId;
	}

	public void setQAId(Integer qaId) {
		this.qaId = qaId;
	}

	public Integer getDevId() {
		return devId;
	}

	public void setDevId(Integer devId) {
		this.devId = devId;
	}

	public Integer getManagerId() {
		return managerId;
	}

	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
}
