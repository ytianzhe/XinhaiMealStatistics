package com.xinhai.org.entity;

import java.sql.Timestamp;

/**
* @author Tony
* @version 创建时间：2018年7月16日 下午1:20:31
* @ClassName 类名称
* @Description 类描述
*/
public class Eat_food {
private int id;
private Timestamp firstAddTime;
private String foodName;
private int foodType;
private int firstAddPerson;
private int status;
private String foodtypeName;
private int isLast;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public Timestamp getFirstAddTime() {
	return firstAddTime;
}
public void setFirstAddTime(Timestamp firstAddTime) {
	this.firstAddTime = firstAddTime;
}
public String getFoodName() {
	return foodName;
}
public void setFoodName(String foodName) {
	this.foodName = foodName;
}
public int getFoodType() {
	return foodType;
}
public void setFoodType(int foodType) {
	this.foodType = foodType;
}
public int getFirstAddPerson() {
	return firstAddPerson;
}
public void setFirstAddPerson(int firstAddPerson) {
	this.firstAddPerson = firstAddPerson;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}
public String getFoodtypeName() {
	return foodtypeName;
}
public void setFoodtypeName(String foodtypeName) {
	this.foodtypeName = foodtypeName;
}

public int getIsLast() {
	return isLast;
}
public void setIsLast(int isLast) {
	this.isLast = isLast;
}







}
