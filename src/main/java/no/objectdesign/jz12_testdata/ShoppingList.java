package no.objectdesign.jz12_testdata;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.apache.commons.lang.Validate;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity(name = "ShoppingList")
public class ShoppingList {

	@Id
	@GeneratedValue
	@Column(name = "id")
	Integer id;
		
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Column(name = "updated", nullable = true)
	private LocalDateTime updated;
		
	@SuppressWarnings("unused")
	@ManyToOne
	@JoinColumn(name="cid")
	private Customer customer;

	@OneToMany(mappedBy = "shoppingList", cascade = CascadeType.ALL)
	private List<Item> items = new ArrayList<Item>();
	
	//Hibernate constructor
	protected ShoppingList() {
	}
	
	public ShoppingList( Customer customer ) {
		Validate.notNull( customer );
		this.customer = customer;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public List<Item> items() {
		return this.items;
	}

	public List<String> itemsByName() {
		List<String> itemsByName = new ArrayList<String>();
		for (Item item : this.items) {
			itemsByName.add(item.name());
		}
		return itemsByName;
	}

	public Integer getId() {
		return id;
	}

	public void addByName(String ... itemsByName) {
		for (String itemByName : itemsByName) {
			this.items.add( new Item( itemByName ));
		}
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

}
