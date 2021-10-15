package com.ericsson.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.ericsson.model.Item;
import com.ericsson.model.ShoppingCart;
import com.ericsson.model.enums.ItemType;
import com.ericsson.utils.ErrorConstants;

class TaxCalculatorServiceImplTest {

	@InjectMocks
	private TaxCalculatorServiceImpl service;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCalculateShoppingCartTaxes_OK_baseCase() {

		ShoppingCart sc = new ShoppingCart(
				Arrays.asList(
						new Item(1, ItemType.FOOD, true, "box of chocolates", 10.0),
						new Item(1, ItemType.OTHER, true, "bottle of perfume", 47.5)));

		assertEquals(0.0, sc.getSalesTax());
		assertEquals(0.0, sc.getTotalAmount());

		// Under Test
		service.calculateShoppingCartTaxes(sc);

		// First item -> food (no sales tax), imported
		Item it = sc.getItemList().get(0);
		assertEquals(0.0, it.getSalesTax());
		assertEquals(it.getPriceBeforetaxes() * 0.05, it.getImportTax());

		// Second item -> other (sales tax), imported
		it = sc.getItemList().get(1);
		assertTrue(it.getSalesTax() >= it.getPriceBeforetaxes() * 0.1);
		assertTrue(it.getImportTax() >= it.getPriceBeforetaxes() * 0.05);

		assertEquals(7.65, sc.getSalesTax());
		assertEquals(65.15, sc.getTotalAmount());
	}
	
	@Test
	void testCalculateShoppingCartTaxes_OK() {

		ShoppingCart sc = new ShoppingCart(
				Arrays.asList(
						new Item(2, ItemType.OTHER, false, "flowers", 15.0),
						new Item(1, ItemType.OTHER, true, "bottle of perfume", 47.5)));

		assertEquals(0.0, sc.getSalesTax());
		assertEquals(0.0, sc.getTotalAmount());

		// Under Test
		service.calculateShoppingCartTaxes(sc);

		// First item -> other (sales tax), not imported
		Item it = sc.getItemList().get(0);
		assertNotEquals(0.0, it.getSalesTax());
		assertEquals(0.0, it.getImportTax());

		// Second item -> other (sales tax), imported
		it = sc.getItemList().get(1);
		assertTrue(it.getSalesTax() >= it.getPriceBeforetaxes() * 0.1);
		assertTrue(it.getImportTax() >= it.getPriceBeforetaxes() * 0.05);

		assertNotEquals(0.0, sc.getSalesTax());
		assertNotEquals(0.0, sc.getTotalAmount());
	}

	@Test
	void testCalculateShoppingCartTaxes_KO_nullShoppingCart() {
		try {
			// Under Test
			service.calculateShoppingCartTaxes(null);
			Assertions.fail();
		} catch (RuntimeException e) {
			assertEquals(ErrorConstants.EMPTY_OR_NULL_SHOPPING_CART_ERROR_MSG, e.getMessage());
			return;
		}
		Assertions.fail();
	}
	
	@Test
	void testCalculateShoppingCartTaxes_KO_nullItemList() {
		
		ShoppingCart sc = new ShoppingCart(null);
		
		try {
			// Under Test
			service.calculateShoppingCartTaxes(sc);
			Assertions.fail();
		} catch (RuntimeException e) {
			assertEquals(ErrorConstants.EMPTY_OR_NULL_SHOPPING_CART_ERROR_MSG, e.getMessage());
			return;
		}
		Assertions.fail();
	}
	
	@Test
	void testCalculateShoppingCartTaxes_KO_emptyItemList() {
		
		ShoppingCart sc = new ShoppingCart(new ArrayList<Item>());
		
		try {
			// Under Test
			service.calculateShoppingCartTaxes(sc);
			Assertions.fail();
		} catch (RuntimeException e) {
			assertEquals(ErrorConstants.EMPTY_OR_NULL_SHOPPING_CART_ERROR_MSG, e.getMessage());
			return;
		}
		Assertions.fail();
	}
	
	@Test
	void testCalculateShoppingCartTaxes_KO_nullItem() {
		
		List<Item> itemList = new ArrayList<Item>();
		itemList.add(null);
		ShoppingCart sc = new ShoppingCart(itemList);
		
		try {
			// Under Test
			service.calculateShoppingCartTaxes(sc);
			Assertions.fail();
		} catch (RuntimeException e) {
			assertEquals(ErrorConstants.NULL_ITEM_ERROR_MSG, e.getMessage());
			return;
		}
		Assertions.fail();
	}
	
}
