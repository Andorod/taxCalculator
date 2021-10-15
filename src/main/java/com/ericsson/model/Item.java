package com.ericsson.model;

import com.ericsson.model.enums.ItemType;

public class Item {
	private int amount = 0;
	private ItemType type = ItemType.OTHER;
	private boolean imported = false;
	private String description;
	private double unitPriceBeforeTaxes = 0.0;
	private double salesTax = 0.0;
	private double importTax = 0.0;

	public Item(int amount, ItemType type, boolean imported, String description, double unitPriceBeforeTaxes) {
		super();
		setAmount(amount);
		setType(type);
		setImported(imported);
		setDescription(description);
		setUnitPriceBeforeTaxes(unitPriceBeforeTaxes);
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public ItemType getType() {
		return type;
	}

	public void setType(ItemType type) {
		this.type = type;
	}

	public boolean isImported() {
		return imported;
	}

	public void setImported(boolean imported) {
		this.imported = imported;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPriceBeforeTaxes() {
		return unitPriceBeforeTaxes;
	}

	public void setUnitPriceBeforeTaxes(double unitPriceBeforeTaxes) {
		this.unitPriceBeforeTaxes = unitPriceBeforeTaxes;
	}

	public double getSalesTax() {
		return salesTax;
	}

	public void setSalesTax(double baseTax) {
		this.salesTax = baseTax;
	}

	public double getImportTax() {
		return importTax;
	}

	public void setImportTax(double importTax) {
		this.importTax = importTax;
	}

	public double getPriceAfterTaxes() {
		return getPriceBeforetaxes() + salesTax + importTax;
	}

	public double getPriceBeforetaxes() {
		return amount * unitPriceBeforeTaxes;
	}

}
