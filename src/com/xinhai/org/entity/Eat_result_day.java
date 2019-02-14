package com.xinhai.org.entity;

import java.sql.Timestamp;

/**
* @author Tony
* @version 创建时间：2018年7月20日 下午8:26:19
* @ClassName 类名称
* @Description 类描述
*/
public class Eat_result_day {
private int id;
private Timestamp firstAddTime;
private   int eatNumber;
private int trueEatNumber;
private double riceNumber;
private double trueRiceNumber;
private double money;
private int status;
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
public int getEatNumber() {
	return eatNumber;
}
public void setEatNumber(int eatNumber) {
	this.eatNumber = eatNumber;
}
public int getTrueEatNumber() {
	return trueEatNumber;
}
public void setTrueEatNumber(int trueEatNumber) {
	this.trueEatNumber = trueEatNumber;
}
public double getRiceNumber() {
	return riceNumber;
}
public void setRiceNumber(double riceNumber) {
	this.riceNumber = riceNumber;
}
public double getTrueRiceNumber() {
	return trueRiceNumber;
}
public void setTrueRiceNumber(double trueRiceNumber) {
	this.trueRiceNumber = trueRiceNumber;
}
public double getMoney() {
	return money;
}
public void setMoney(double money) {
	this.money = money;
}
public int getStatus() {
	return status;
}
public void setStatus(int status) {
	this.status = status;
}



}
