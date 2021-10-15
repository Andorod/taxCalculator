package com.ericsson.model.enums;

public enum ItemType {

	BOOK("BOOK", true),
	MEDICAL_PRODUCT("MEDICAL_PRODUCT", true),
	FOOD("FOOD", true),
	OTHER("OTHER", false);

	private String type;
	private boolean salesTaxExempt = false;

	private ItemType(String type, boolean salesTaxExempt) {
		this.type = type;
		this.salesTaxExempt = salesTaxExempt;
	}

	public String getType() {
		return type;
	}

	public Boolean getSalesTaxExempt() {
		return salesTaxExempt;
	}
}
