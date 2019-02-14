package com.xinhai.org.entity;

import java.sql.Timestamp;

/**
* @author Tony
* @version 创建时间：2018年7月16日 下午3:52:23
* @ClassName 类名称
* @Description 类描述
*/
public class Eat_day_log {
  private int id;
  private int userId;
  private int isEat;
  private double riceNumber;
  private int userOpenId;
  private String dictionaryName;
  private String userName;
  private Timestamp firstAddTime;
  private String eatName;
  private String information;
public String getInformation() {
	return information;
}
public void setInformation(String information) {
	this.information = information;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public int getUserId() {
	return userId;
}
public void setUserId(int userId) {
	this.userId = userId;
}
public int getIsEat() {
	return isEat;
}
public void setIsEat(int isEat) {
	this.isEat = isEat;
}
public double getRiceNumber() {
	return riceNumber;
}
public void setRiceNumber(double riceNumber) {
	this.riceNumber = riceNumber;
}
public int getUserOpenId() {
	return userOpenId;
}
public void setUserOpenId(int userOpenId) {
	this.userOpenId = userOpenId;
}
public String getDictionaryName() {
	return dictionaryName;
}
public void setDictionaryName(String dictionaryName) {
	this.dictionaryName = dictionaryName;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public Timestamp getFirstAddTime() {
	return firstAddTime;
}
public void setFirstAddTime(Timestamp firstAddTime) {
	this.firstAddTime = firstAddTime;
}
public String getEatName() {
	return eatName;
}
public void setEatName(String eatName) {
	this.eatName = eatName;
}

  

}
