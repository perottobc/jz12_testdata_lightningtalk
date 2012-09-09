package no.objectdesign.jz12_testdata;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDateTime;

@Entity(name = "item")
public class Item {

	@Id
	@GeneratedValue
	@Column(name = "id")
	Long id;

	@SuppressWarnings("unused")
	@ManyToOne
	@JoinColumn(name="sid")
	private ShoppingList shoppingList;

	@Column(name = "name")
	private String name;
	
	@Type(type = "org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Column(name = "updated", nullable = true)
	private LocalDateTime updated;	

	// Constructor for hibernate
	protected Item() {
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *           of item
	 */
	public Item(String name) {
		this.name = name;
	}

	public String name() {
		return name;
	}

	public LocalDateTime getUpdated() {
		return updated;
	}

	
}
