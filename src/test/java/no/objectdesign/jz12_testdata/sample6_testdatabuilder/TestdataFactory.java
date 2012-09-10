package no.objectdesign.jz12_testdata.sample6_testdatabuilder;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class TestdataFactory {

	private JdbcTemplate jdbcTemplate;

	public Integer insert(CustomerTestdata customerTestdata) 
	{
		int maxId = jdbcTemplate.queryForInt(
									customerTestdata.sqlSelectMaxId());

		int customerId = maxId + 1;
		jdbcTemplate.execute(customerTestdata.asSqlInsert(customerId));

		for (ShoppingListTestdata shoppingListTestdata : 
								   customerTestdata.getShoppingList()) 
		{
			shoppingListTestdata.setCustomerId(customerId);
			insert(shoppingListTestdata);
		}

		return customerId;
	}

	public TestdataFactory(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(ShoppingListTestdata shoppingListTestdata) {
		int maxId = jdbcTemplate.queryForInt(shoppingListTestdata
			.sqlSelectMaxId());

		int shoppingListId = maxId + 1;
		jdbcTemplate.execute(shoppingListTestdata
			.asSqlInsert(shoppingListId));

		for (ItemTestdata itemTestdata : shoppingListTestdata
			.getItems()) {
			itemTestdata.setShoppingListId(shoppingListId);
			insert(itemTestdata);
		}
	}

	public void insert(ItemTestdata itemTestdata) {
		int maxId = jdbcTemplate.queryForInt(itemTestdata
			.sqlSelectMaxId());

		int itemId = maxId + 1;
		jdbcTemplate.execute(itemTestdata.asSqlInsert(itemId));
	}

}
