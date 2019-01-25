package com.oriaxx77.javaplay.java8features.util.model;

import java.util.ArrayList;
import java.util.List;

public class Order {
	private List<LineItem> lineItems = new ArrayList<>();

	public List<LineItem> getLineItems() {
		return lineItems;
	}
	
	public void addLineItem( LineItem lineItem ){
		lineItems.add(lineItem);
	}
	
	
}
