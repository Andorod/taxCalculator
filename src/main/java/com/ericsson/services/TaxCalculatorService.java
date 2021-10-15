package com.ericsson.services;

import com.ericsson.model.ShoppingCart;

public interface TaxCalculatorService {
	public void calculateShoppingCartTaxes(ShoppingCart sc) throws RuntimeException;
}
