package no.objectdesign.jz12_testdata.sample5_objectmother;

import no.objectdesign.jz12_testdata.Customer;

public class CustomerObjectMother {

	Customer customer = new Customer();

	public CustomerObjectMother addShoppingLists(
		String[][] shoppingListList) 
	{
		for (String[] shoppingList : shoppingListList) {
			customer.newShoppingList().addByName(shoppingList);
		}
		return this;
	}

	public Customer getCustomer() {
		return this.customer;
	}
}
