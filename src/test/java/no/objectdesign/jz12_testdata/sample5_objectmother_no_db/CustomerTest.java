package no.objectdesign.jz12_testdata.sample5_objectmother_no_db;

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import no.objectdesign.jz12_testdata.Customer;
import no.objectdesign.jz12_testdata.CustomerRepository;
import no.objectdesign.jz12_testdata.sample5_objectmother.CustomerObjectMother;

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

public class CustomerTest {

	private Integer custId;

	@Test
	public void toppTipsIsBasedOnItemCountInShoppingHistory() {
		
		CustomerObjectMother objMother = new CustomerObjectMother()
		.addShoppingLists(new String[][] {
			{ "bread","butter","milk" }, 
			{ "egg","bacon","juice" },
			{ "egg","bacon","juice" }, 
			{ "egg","bacon" }, 
			{ "egg" } });
		
		Customer customer = objMother.getCustomer();

		List<String> tips = customer.shoppingTips();

		assertThat(tips).startsWith("egg", "bacon", "juice");
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	DataSource dataSource;

	// updated gir presedens ved tips
	// alt fungerer, inntil lastUpdated blir endret til å følge updateDato
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
