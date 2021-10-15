package com.ericsson.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.ericsson.utils.ErrorConstants;

public class ShoppingCart {

	private List<Item> itemList = new ArrayList<Item>();
	private double salesTax = 0.0;
	private double totalAmount = 0.0;

	public ShoppingCart(List<Item> itemList) {
		setItemList(itemList);
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String toString(boolean beforeTaxes) {
		StringBuilder sb = new StringBuilder();

		for (Item it : getItemList()) {
			if (Objects.isNull(it)) {
				throw new RuntimeException(ErrorConstants.NULL_ITEM_ERROR_MSG);
			}
			sb.append(it.toString(beforeTaxes)).append("\n");
		}
		if (!beforeTaxes) {
			sb.append("Sales Taxes: ").append(getSalesTax()).append("\n");
			sb.append("Total: ").append(getTotalAmount());
		}
		return sb.toString();
	}
}
