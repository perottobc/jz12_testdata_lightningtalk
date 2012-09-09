package no.objectdesign.jz12_testdata;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity(name = "Customer")
public class Customer {

	@Id
	@GeneratedValue
	@Column(name = "id")
	Integer id;

	@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	@javax.persistence.OrderBy("updated DESC")
	private List<ShoppingList> shoppingLists = new ArrayList<ShoppingList>();

	@Column(name = "nick")
	private String nick;

	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Column(name = "updated", nullable = true)
	private LocalDateTime updated;

	public String getNick() {
		return nick;
	}

	/**
	 * Returns a new shoppingList
	 */
	public ShoppingList newShoppingList() {
		ShoppingList newShoppingList = new ShoppingList(this);
		this.shoppingLists.add(newShoppingList);
		return newShoppingList;
	}

	public List<ShoppingList> shoppingLists() {
		return this.shoppingLists;
	}

	public List<String> shoppingTips() {
		final Map<String, Integer> productCount = new HashMap<String, Integer>();
		for (ShoppingList shoppingList : shoppingLists()) {
			List<Item> items = shoppingList.items();
			for (Item item : items) {
				Integer count = productCount.get(item.name());
				if (null == count) {
					count = 1;
				} else {
					count++;
				}
				productCount.put(item.name(), count);
			}
		}
		List<String> products = new ArrayList<String>(
			productCount.keySet());
		Collections.sort(products, new Comparator<String>() {
			public int compare(String product1, String product2) {
				return productCount.get(product2).compareTo(
					productCount.get(product1));
			}
		});
		return products;
	}

	public List<ShoppingList> shoppingList() {
		return this.shoppingLists;
	}

	public ShoppingList shoppingList(int i) {
		return this.shoppingLists.get(i);
	}

	public LocalDateTime getUpdated() {
		return updated;
	}
	
	

}
