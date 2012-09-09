package no.objectdesign.jz12_testdata.sample3_testdata_file;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import no.objectdesign.jz12_testdata.Customer;
import no.objectdesign.jz12_testdata.CustomerRepository;

import org.apache.commons.io.FileUtils;
import org.hibernate.exception.SQLGrammarException;
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

	@Before
	public void insertTestdata() {
		sql(new File("./src/test/resources/testdata.sql"));
	}

	@Test
	public void shouldFetchCustomerWithShoppingListAndItems() {
		Customer customer = customerRepository.load(1);
		List<String> items = customer.shoppingList(0).itemsByName();

		assertThat(customer.getNick()).isEqualTo("Dokie");
		assertThat(customer.shoppingList()).hasSize(1);		
		assertThat(items).contains("bread", "butter", "milk");	
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	DataSource dataSource;

	void sql(File sql) {
		List<String> sqlInserts = null;
		try {
			sqlInserts = FileUtils.readLines(sql);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		for (String insert : sqlInserts) {
			if (!insert.trim().isEmpty()) {
				try {
					jdbcTemplate.execute(insert);
				} catch (SQLGrammarException e) {
					throw new RuntimeException(
						"Feil på linje: " + insert, e);
				}
			}
		}
	}

}
