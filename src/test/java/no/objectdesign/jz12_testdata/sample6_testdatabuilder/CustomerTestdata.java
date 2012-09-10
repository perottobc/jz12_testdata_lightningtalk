package no.objectdesign.jz12_testdata.sample6_testdatabuilder;

import java.util.ArrayList;
import java.util.List;
@SuppressWarnings({ "unchecked","rawtypes" })

public class CustomerTestdata {

	private String name = "John";		
	private List<ShoppingListTestdata> 
	      shoppingListTestdata = new ArrayList();

	public CustomerTestdata withShoppingLists(
		String[][] shoppingListList) 
	{
		for (String[] shoppingList : shoppingListList) 
		{
			shoppingListTestdata.add(
				new ShoppingListTestdata(shoppingList));
		}
		return this;
	}

	
	public String sqlSelectMaxId() {
		return "select MAX(id) from Customer";
	}
	
	public String asSqlInsert(Integer id) {
		return "insert into Customer (id,nick) " + 
				 "values (" + id + ",'" + name + "')";
	}
	
	public List<ShoppingListTestdata> getShoppingList() {
		return shoppingListTestdata;
	}

	public String getName() {
		return name;
	}
	
	

}
