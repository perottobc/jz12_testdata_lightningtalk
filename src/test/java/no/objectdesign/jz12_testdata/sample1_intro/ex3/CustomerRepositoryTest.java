package no.objectdesign.jz12_testdata.sample1_intro.ex3;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

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
		sql("insert into Customer (id,nick) values (1,'Dokie')   ",
			 "insert into ShoppingList (cid,id) values (1,1)      ",
			 "insert into Item (sid,id,name) values (1,1,'bread') ",
			 "insert into Item (sid,id,name) values (1,2,'butter')",
			 "insert into Item (sid,id,name) values (1,3,'milk')  ");
	}

	@Test
	public void shouldFetchCustomerWithShoppingListAndItems() {
		Customer customer = customerRepository.load(1);
		List<String> items = customer.shoppingList(0).itemsByName();

		assertThat(customer.getNick()).isEqualTo("Dokie");
		assertThat(customer.shoppingList()).hasSize(1);		
		assertThat(items).contains("bread", "butter", "milk");	
	}
                                                                          
	void sql(String... sql) {
		for (String sqlLine : sql) {
			jdbcTemplate.execute(sqlLine);
		}
	}
}
