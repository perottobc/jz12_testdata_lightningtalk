package no.objectdesign.jz12_testdata.sample6_testdatabuilder;

import java.util.ArrayList;
import java.util.List;

public class ShoppingListTestdata {

	private int customerId;
	
	private List<ItemTestdata> items = new ArrayList<ItemTestdata>();

	public ShoppingListTestdata(String[] shoppingListItemsByName) {
		for (String itemByName : shoppingListItemsByName) {
			items.add( new ItemTestdata( itemByName));
		}
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String sqlSelectMaxId() {
		return "select MAX(id) from ShoppingList";
	}
	
	public String asSqlInsert(Integer id) {
		return "insert into ShoppingList (cid,id) values ("+customerId+","+id+")";
	}

	public List<ItemTestdata> getItems() {
		return items;
	}
	
}
