package com.oriaxx77.java8play.util.model;

public class Car {
	
	private static final String DEFAULT_COLOR = "red";
	private static final Long DEFAULT_PRICE = 0L;
	
	private String color;
	private Long price;
	
	public Car( Long price, String color ){
		this.price = price;
		this.color = color;
	}
	
	public Car( String color ){
		this( DEFAULT_PRICE, color );
	}
	
	public Car( Long price ){
		this( price, DEFAULT_COLOR );
	}
	
	
	public Car(){
		this( DEFAULT_PRICE, DEFAULT_COLOR );
	}
	
	public String getColor(){ return color;}
	public boolean isRed(){ return "red".equals( this.color ); }
	public Long getPrice(){ return price;}
	public void setPrice( Long price ){ this.price = price;}

	@Override
	public String toString() {
		return "Car [color=" + color + ", price=" + price + "]";
	}
	
	
	
}
