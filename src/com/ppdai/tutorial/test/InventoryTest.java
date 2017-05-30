package com.ppdai.tutorial.test;

import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.ppdai.tutorial.Inventory;
import com.ppdai.tutorial.Item;

public class InventoryTest {

	@Test
	public void testInventory() {
		Inventory inventory = new Inventory();
		Item[] items = inventory.getItems();

		Assert.assertNotNull(items);
		// test the internal item array length is 1
		Assert.assertEquals(1, items.length);
		// test current item count is 0
		Assert.assertEquals(0, inventory.getSize());
	}

	@Test
	public void testAddItem() {
		Inventory inventory = new Inventory();
		Item item1 = new Item();

		// add one item, and test whether the item1 has been added
		inventory.addItem(item1);
		Assert.assertEquals(item1, inventory.getItems()[0]);

		// add second item, and test whether the item2 has been added
		Item item2 = new Item();
		inventory.addItem(item2);
		Assert.assertEquals(item2, inventory.getItems()[1]);

	}

	@Test
	public void testGetTotalPrice() {
		Inventory inventory = new Inventory();
		Assert.assertEquals(0, inventory.getTotalPrice());

		Item item1 = new Item("item1", 1.1, 1);
		inventory.addItem(item1);
		Assert.assertEquals(1, inventory.getTotalPrice());

		Item item2 = new Item("item2", 2.2, 2);
		inventory.addItem(item2);
		Assert.assertEquals(3, inventory.getTotalPrice());

	}

	@Test
	public void testGetTotalWeight() {
		Inventory inventory = new Inventory();
		Assert.assertEquals(0, inventory.getTotalPrice());

		Item item1 = new Item("item1", 1.1);
		inventory.addItem(item1);
		Assert.assertEquals(1.1, inventory.getTotalWeight(), 0.01);

		Item item2 = new Item("item2", 2.2);
		inventory.addItem(item2);
		Assert.assertEquals(3.3, inventory.getTotalWeight(), 0.01);
	}

	@Test
	public void testGetSize() {
		Inventory inventory = new Inventory();
		Assert.assertEquals(0, inventory.getSize());

		Item item1 = new Item();
		inventory.addItem(item1);
		Assert.assertEquals(1, inventory.getSize());

		Item item2 = new Item();
		inventory.addItem(item2);
		Assert.assertEquals(2, inventory.getSize());

		// add 10
		for (int i = 0; i < 10; i++) {
			Item t = new Item();
			inventory.addItem(t);
		}

		Assert.assertEquals(12, inventory.getSize());

	}

	@Test
	public void testWeighItemsByName() {
		Inventory inventory = new Inventory();
		Item item1 = new Item("a", 1.1);
		Item item2 = new Item("a", 1.2);
		Item item3 = new Item("b", 1.2);

		inventory.addItem(item1);
		inventory.addItem(item2);
		inventory.addItem(item3);

		Assert.assertEquals(2.3, inventory.weighItemsByName("a"), 0.01);
	}

	@Test
	public void testPriceItemsByName() {
		Inventory inventory = new Inventory();
		Item item1 = new Item("a", 1.1, 1);
		Item item2 = new Item("a", 1.2, 2);
		Item item3 = new Item("b", 1.2, 3);
		Item item4 = new Item("A", 1.2, 4);

		inventory.addItem(item1);
		inventory.addItem(item2);
		inventory.addItem(item3);
		inventory.addItem(item4);

		Assert.assertEquals(7, inventory.priceItemsByName("a"));
	}

	@Test
	public void testGetItems() {
		Inventory inventory = new Inventory();
		Item[] items1 = inventory.getItems();
		Assert.assertEquals(1, items1.length);
		// items is empty
		Assert.assertNull(items1[0]);

		// add a item
		Item item1 = new Item();
		inventory.addItem(item1);

		Item[] items2 = inventory.getItems();
		Assert.assertEquals(item1, items2[0]);

		// add second item
		Item item2 = new Item();
		inventory.addItem(item2);

		Item[] items3 = inventory.getItems();
		Assert.assertEquals(item1, items3[0]);
		Assert.assertEquals(item2, items3[1]);

		// add 12 item consistently
		Item[] items = new Item[12];
		for (int i = 0; i < 12; i++) {
			items[i] = new Item();
			inventory.addItem(items[i]);
		}

		// add more than 11 item to test the extendArray Method
		for (int i = 0; i < 12; i++) {
			Assert.assertEquals(items[i], inventory.getItems()[i + 2]);
		}

	}

	@Test
	public void testToString() {
		Inventory inventory = new Inventory();
		Item item1 = new Item("a", 1.1, 1);
		Item item2 = new Item("b", 1.2, 3);

		inventory.addItem(item1);
		inventory.addItem(item2);

		String result = "You currently have the following items " + "in your inventory: \n"
				+ "a, 1.1 Fantasy Units, 1 Gold\n" +"b, 1.2 Fantasy Units, 3 Gold\n";
		Assert.assertEquals(result, inventory.toString());
	}

}
