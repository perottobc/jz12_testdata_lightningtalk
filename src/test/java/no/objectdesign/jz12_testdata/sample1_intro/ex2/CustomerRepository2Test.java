package no.objectdesign.jz12_testdata.sample1_intro.ex2;

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
public class CustomerRepository2Test {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	DataSource dataSource;

	@Before
	public void insertTestdata() {
		sql("insert into Customer (id,nick) values (1,'Dokie')",
			 "insert into ShoppingList (cid,id) values (1,1)   ");
	}

	@Test
	public void shouldFetchCustomerWithShoppingList() {
		Customer customer = customerRepository.load(1);
		assertThat(customer.getNick()).isEqualTo("Dokie");
		assertThat(customer.shoppingList()).hasSize(1);
	}

	void sql(String... sql) {
		for (String sqlLine : sql) {
			jdbcTemplate.execute(sqlLine);
		}
	}
}
