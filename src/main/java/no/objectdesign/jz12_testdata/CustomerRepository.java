package no.objectdesign.jz12_testdata;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;

public class CustomerRepository {
	

	private SessionFactory sessionFactory;

	/**
	 * Constructor
	 * @param sessionFactory
	 */
	public CustomerRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Customer> findAll() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Customer.class);
		criteria.addOrder(Order.desc("updated"));
		return criteria.list();
	}

	public Customer load(Integer id) {
		return (Customer) sessionFactory.getCurrentSession().load(Customer.class, id);
	}

	public Integer save(Customer customer) {
		return (Integer) sessionFactory.getCurrentSession().save(customer);
	}
	

}
