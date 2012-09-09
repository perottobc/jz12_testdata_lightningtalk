package no.objectdesign.jz12_testdata.sample6_testdatabuilder;


public class ItemTestdata {

	private String name;
	private int shoppingListId;

	public ItemTestdata(String name) {
		this.name = name;
	}

	public void setShoppingListId(int shoppingListId) {
		this.shoppingListId = shoppingListId;
	}

	public String sqlSelectMaxId() {
		return "select MAX(id) from Item";
	}

	public String asSqlInsert(int id) {
		return "insert into Item (sid,id,name) values ("
			+ shoppingListId + "," + id + ",'" + name + "')";
	}
}
