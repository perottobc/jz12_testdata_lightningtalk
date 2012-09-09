package no.objectdesign.jz12_testdata.sample5_objectmother;

import no.objectdesign.jz12_testdata.Customer;

public class CustomerObjectMother {

	Customer customer = new Customer();

	public void addShoppingLists(String[][] shoppingListList) {
		for (String[] shoppingList : shoppingListList) {
			customer.newShoppingList().addByName(shoppingList);
		}
	}

	public Customer getCustomer() {
		return this.customer;
	}
}
