package no.objectdesign.jz12_testdata.sample6_testdatabuilder;

import java.util.ArrayList;
import java.util.List;

public class CustomerTestdata {

	private String name = "Jon";

	private List<ShoppingListTestdata> shoppingList = new ArrayList<ShoppingListTestdata>();

	public CustomerTestdata withShoppingLists(String[][] shoppingLists) {
		for (String[] shoppingListItemsByName : shoppingLists) {
			shoppingList.add( new ShoppingListTestdata(shoppingListItemsByName) );
		}
		return this;
	}

	public String asSqlInsert(Integer id) {
		return "insert into Customer (id,nick) values (" + id + ",'"
			+ name + "')";
	}

	public String sqlSelectMaxId() {
		return "select MAX(id) from ShoppingList";
	}

	public List<ShoppingListTestdata> getShoppingList() {
		return shoppingList;
	}
	
}
