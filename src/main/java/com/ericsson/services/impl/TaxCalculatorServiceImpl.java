package com.ericsson.services.impl;

import java.util.Objects;

import org.springframework.stereotype.Service;

import com.ericsson.model.Item;
import com.ericsson.model.ShoppingCart;
import com.ericsson.services.TaxCalculatorService;
import com.ericsson.utils.ErrorConstants;
import com.ericsson.utils.ShoppingCartPrinter;

@Service
public class TaxCalculatorServiceImpl implements TaxCalculatorService {
	public void calculateShoppingCartTaxes(ShoppingCart sc) throws RuntimeException {

		if (Objects.isNull(sc) || Objects.isNull(sc.getItemList()) || sc.getItemList().isEmpty()) {
			throw new RuntimeException(ErrorConstants.EMPTY_OR_NULL_SHOPPING_CART_ERROR_MSG);
		}
		
		ShoppingCartPrinter.printShoppingCart(sc, true);
		
		double salesTax = 0.0;
		double totalAmount = 0.0;
		
		for (Item it : sc.getItemList()) {
			calculateItemTaxes(it);
			
			salesTax += it.getSalesTax() + it.getImportTax();
			totalAmount += it.getPriceAfterTaxes();
		}
		
		sc.setSalesTax(salesTax);
		sc.setTotalAmount(totalAmount);
		
		ShoppingCartPrinter.printShoppingCart(sc, false);
	}
	
	private void calculateItemTaxes(Item it) {
		
		if (Objects.isNull(it)) {
			throw new RuntimeException(ErrorConstants.NULL_ITEM_ERROR_MSG);
		}
		
		if (!it.getType().getSalesTaxExempt()) {
			it.setSalesTax(calculateRoundedTax(it.getPriceBeforetaxes(), 10));
		}
		
		if (it.isImported()) {
			it.setImportTax(calculateRoundedTax(it.getPriceBeforetaxes(), 5));
		}
	}

	private double calculateRoundedTax(double priceBeforetaxes, int taxPercentage) {
		double baseValue = priceBeforetaxes * taxPercentage;
		
		if (baseValue % 1 != 0) {
			baseValue = Math.ceil(baseValue/5)*5;
		}
		
		return baseValue / 100;
	}
}
