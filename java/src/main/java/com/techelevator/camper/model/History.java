package com.techelevator.camper.model;

public class History {

	private int historyId;
	private String changeType;
	private String changeSpecifics;
	private String changeDateAndTime;
	private String userName;
	private int updateStatusId;

	
	public History() {
		
	}
	
	public int getHistoryId() {
		return historyId;
	}

	public void setHistoryId(int historyId) {
		this.historyId = historyId;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getChangeSpecifics() {
		return changeSpecifics;
	}

	public void setChangeSpecifics(String changeSpecifics) {
		this.changeSpecifics = changeSpecifics;
	}

	public String getChangeDateAndTime() {
		return changeDateAndTime;
	}

	public void setChangeDateAndTime(String changeDateAndTime) {
		this.changeDateAndTime = changeDateAndTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public int getUpdateStatusId() {
		return updateStatusId;
	}

	public void setUpdateStatusId(int updateStatusId) {
		this.updateStatusId = updateStatusId;
	}
	
}
