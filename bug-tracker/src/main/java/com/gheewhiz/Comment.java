package com.gheewhiz;

public class Comment {
	private Integer commentId;
	private String comment;
	private Integer bugId;
	private Account commenter;
	
	public Integer getCommentId() {
		return commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Account getCommenter() {
		return commenter;
	}

	public void setCommenter(Account commenter) {
		this.commenter = commenter;
	}

	public Integer getBugId() {
		return bugId;
	}

	public void setBugId(Integer bugId) {
		this.bugId = bugId;
	}
}
