package com.ericsson.utils;

import java.util.Objects;

import com.ericsson.model.ShoppingCart;

public class ShoppingCartPrinter {

	public static void printShoppingCart(ShoppingCart sc, boolean beforeTaxes) {
		
		if (Objects.isNull(sc) || Objects.isNull(sc.getItemList()) || sc.getItemList().isEmpty()) {
			throw new RuntimeException(ErrorConstants.EMPTY_OR_NULL_SHOPPING_CART_ERROR_MSG);
		}
		
		StringBuilder sb = new StringBuilder();
		
		if (beforeTaxes)
			sb.append("Shopping Cart:\n");
		else 
			sb.append("Checkout:\n");
		
		sb.append(sc.toString(beforeTaxes));
		
		System.out.println(sb.toString());
	}
}
