package com.bg.plzSeatdown.community.model.vo;

import java.sql.Date;

public class Community {

	private int communityNo;
	private String communityTitle;
	private String communityContent;
	private Date communityCreateDate;
	private Date communityModifyDate;
	private int communityCount;
	private String communityStatus;
	private int communityWriter;
	private String memberNickname;
	private int categoryCode;
	private String categoryName;
	private String profilePath;
	private int memberReporter;
	private String replyReportContent;
	private int reportCategory;
	
	public Community() {}

	public Community(int communityNo, String communityTitle, String communityContent, Date communityCreateDate,
			Date communityModifyDate, int communityCount, String communityStatus, int communityWriter,
			int categoryCode) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
		this.communityCreateDate = communityCreateDate;
		this.communityModifyDate = communityModifyDate;
		this.communityCount = communityCount;
		this.communityStatus = communityStatus;
		this.communityWriter = communityWriter;
		this.categoryCode = categoryCode;
	}

	public Community(int communityNo, String communityTitle, String communityContent, Date communityCreateDate,
			Date communityModifyDate, int communityCount, String communityStatus, int communityWriter,
			String memberNickname, int categoryCode, String categoryName, String profilePath) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
		this.communityCreateDate = communityCreateDate;
		this.communityModifyDate = communityModifyDate;
		this.communityCount = communityCount;
		this.communityStatus = communityStatus;
		this.communityWriter = communityWriter;
		this.memberNickname = memberNickname;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.profilePath = profilePath;
	}
	

	public Community(int communityNo, String communityTitle, String communityContent, Date communityCreateDate,
			Date communityModifyDate, int communityCount, String communityStatus, int communityWriter,
			String memberNickname, int categoryCode, String categoryName, String profilePath, int memberReporter,
			String replyReportContent, int reportCategory) {
		super();
		this.communityNo = communityNo;
		this.communityTitle = communityTitle;
		this.communityContent = communityContent;
		this.communityCreateDate = communityCreateDate;
		this.communityModifyDate = communityModifyDate;
		this.communityCount = communityCount;
		this.communityStatus = communityStatus;
		this.communityWriter = communityWriter;
		this.memberNickname = memberNickname;
		this.categoryCode = categoryCode;
		this.categoryName = categoryName;
		this.profilePath = profilePath;
		this.memberReporter = memberReporter;
		this.replyReportContent = replyReportContent;
		this.reportCategory = reportCategory;
	}

	public int getCommunityNo() {
		return communityNo;
	}

	public void setCommunityNo(int communityNo) {
		this.communityNo = communityNo;
	}

	public String getCommunityTitle() {
		return communityTitle;
	}

	public void setCommunityTitle(String communityTitle) {
		this.communityTitle = communityTitle;
	}

	public String getCommunityContent() {
		return communityContent;
	}

	public void setCommunityContent(String communityContent) {
		this.communityContent = communityContent;
	}

	public Date getCommunityCreateDate() {
		return communityCreateDate;
	}

	public void setCommunityCreateDate(Date communityCreateDate) {
		this.communityCreateDate = communityCreateDate;
	}

	public Date getCommunityModifyDate() {
		return communityModifyDate;
	}

	public void setCommunityModifyDate(Date communityModifyDate) {
		this.communityModifyDate = communityModifyDate;
	}

	public int getCommunityCount() {
		return communityCount;
	}

	public void setCommunityCount(int communityCount) {
		this.communityCount = communityCount;
	}

	public String getCommunityStatus() {
		return communityStatus;
	}

	public void setCommunityStatus(String communityStatus) {
		this.communityStatus = communityStatus;
	}

	public int getCommunityWriter() {
		return communityWriter;
	}

	public void setCommunityWriter(int communityWriter) {
		this.communityWriter = communityWriter;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public int getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(int categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getProfilePath() {
		return profilePath;
	}

	public void setProfilePath(String profilePath) {
		this.profilePath = profilePath;
	}
	
	public int getMemberReporter() {
		return memberReporter;
	}

	public void setMemberReporter(int memberReporter) {
		this.memberReporter = memberReporter;
	}

	public String getReplyReportContent() {
		return replyReportContent;
	}

	public void setReplyReportContent(String replyReportContent) {
		this.replyReportContent = replyReportContent;
	}

	public int getReportCategory() {
		return reportCategory;
	}

	public void setReportCategory(int reportCategory) {
		this.reportCategory = reportCategory;
	}

	@Override
	public String toString() {
		return "Community [communityNo=" + communityNo + ", communityTitle=" + communityTitle + ", communityContent="
				+ communityContent + ", communityCreateDate=" + communityCreateDate + ", communityModifyDate="
				+ communityModifyDate + ", communityCount=" + communityCount + ", communityStatus=" + communityStatus
				+ ", communityWriter=" + communityWriter + ", memberNickname=" + memberNickname + ", categoryCode="
				+ categoryCode + ", categoryName=" + categoryName + ", profilePath=" + profilePath + ", memberReporter="
				+ memberReporter + ", replyReportContent=" + replyReportContent + ", reportCategory=" + reportCategory
				+ "]";
	}

}
