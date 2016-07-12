package com.oriaxx77.java8play.util.model;

public class Car {
	
	private String color;
	private Long price;
	public String getColor(){ return color;}
	public boolean isRed(){ return "red".equals( this.color ); }
	public Long getPrice(){ return price;}
	public void setPrice( Long price ){ this.price = price;}
}
