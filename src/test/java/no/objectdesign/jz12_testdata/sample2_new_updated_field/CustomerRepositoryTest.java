package no.objectdesign.jz12_testdata.sample2_new_updated_field;

import static org.fest.assertions.Assertions.assertThat;

import javax.sql.DataSource;

import no.objectdesign.jz12_testdata.Customer;
import no.objectdesign.jz12_testdata.CustomerRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:test_infra.hsqldb.xml",
	"classpath:main_module.xml" })
@TransactionConfiguration
@Transactional
public class CustomerRepositoryTest {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	DataSource dataSource;

	@Before
	public void insertTestdata() {
		sql("insert into Customer (id,nick,updated)         "
			   + "values (1,'Dokie','0001-01-01 01:01:01')   ",
			"insert into ShoppingList (cid,id,updated)       "
				+ "values (1,1,'0001-01-01 01:01:01')         ",
			"insert into Item (sid,id,name,updated)          "
				+ "values (1,1,'bread', '0001-01-01 01:01:01')",
			"insert into Item (sid,id,name,updated)          "
				+ "values (1,2,'butter','0001-01-01 01:01:01')",
			"insert into Item (sid,id,name,updated)          "
				+ "values (1,3,'milk',  '0001-01-01 01:01:01')");
	}

	@Test
	public void shouldFetchCustomerWithShoppingListAndItems() {         
		Customer customer = customerRepository.load(1);
		assertThat(customer.getNick()).isEqualTo("Dokie");
		assertThat(customer.shoppingLists()).hasSize(1);
		assertThat(customer.shoppingList(0).items()).hasSize(3);
	}

	void sql(String... sql) {
		for (String sqlLine : sql) {
			jdbcTemplate.execute(sqlLine);
		}
	}
}
