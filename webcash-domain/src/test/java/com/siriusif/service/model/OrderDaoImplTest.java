package com.siriusif.service.model;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.Order;
import com.siriusif.model.Suborder;
import com.siriusif.model.User;
import com.siriusif.model.Workshift;

public class OrderDaoImplTest extends AbstractSpringTest{
	
	private static User stubUser;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private WorkshiftDao workshiftDao;

	private static Workshift stubWorkshift;

	@Before
	public void setUp(){
		stubUser = new User();
		stubUser.setName("user");
		stubUser.setLogin("login");
		stubUser.setPassword("****");
		userDao.add(stubUser);
		
		stubWorkshift = new Workshift();
		stubWorkshift.setOpenedAt(Helper.stringToDate("01/01/2012"));
		stubWorkshift.setDailyId(42);
		workshiftDao.add(stubWorkshift);
		
	}
	
	
	@Test
	public void testAdd() {
		int size = orderDao.list().size();
		
		Order order = new Order();
		order.setAuthor(stubUser);
		order.setCard(true);
		order.setDailyId(size);
		order.setDiscount(5);
		order.setNomerok(2);
		order.setPayed(BigDecimal.valueOf(13.51));
		order.setReadOnly(true);
		order.setSum(BigDecimal.valueOf(14,56));
		order.setTableNum(5);
		order.setType(false);
		order.setWorkshift(stubWorkshift);
		orderDao.add(order);
		
		assertTrue (size < orderDao.list().size());
	}
	
	@Test
	public void testAddWorkshiftDate() {
		Order order = new Order();
		order.setAuthor(stubUser);
		order.setWorkshift(stubWorkshift);
		orderDao.add(order);
		Date workingDate = Helper.stringToDate("22/01/2013");
		stubWorkshift.setOpenedAt((workingDate));
		//the date shouldn't change
		workshiftDao.update(stubWorkshift);
		orderDao.update(order);
		
		Order orFromDB = orderDao.find(order.getId());
		assertNotSame(workingDate, orFromDB.getWorkingDate());
		//strange behavior of this object. Ajust it.
		assertEquals(stubWorkshift.getOpenedAt(), orFromDB.getWorkingDate());
	}
	
	@Test
	public void testSetSumma(){
		int size = orderDao.list().size();
		
		Order order = new Order();
		order.setSum(BigDecimal.valueOf(15.25));
		order.setAuthor(stubUser);
		order.setPayed(BigDecimal.valueOf(13.51));
		order.setWorkshift(stubWorkshift);
		order.setDailyId(size);
		orderDao.add(order);
		Order orFromDB = orderDao.find(order.getId());
		
		assertEquals(BigDecimal.valueOf(1525, 2), orFromDB.getSum());
		assertTrue (size < orderDao.list().size());
	}
	
	//Test
	public void testOneToManyOrderSuborders(){
		int size = orderDao.list().size();
		Order order = new Order();
		
		order.addSuborder(new Suborder(1));
		order.addSuborder(new Suborder(2));
		order.addSuborder(new Suborder(3));
		order.addSuborder(new Suborder(5));
		order.setTableNum(8);
		order.setSum(BigDecimal.valueOf(15.25));
		order.setAuthor(stubUser);
		order.setPayed(BigDecimal.valueOf(13.51));
		order.setWorkshift(stubWorkshift);
		order.setDailyId(size);
		orderDao.add(order);
		
		assertTrue (size < orderDao.list().size());
		Order orFromDB = orderDao.find(order.getId());
		assertEquals(8, orFromDB.getTableNum());
		assertEquals(4, orFromDB.getSuborders().size());
	}
	
	@Test
	public void testRemoveOrder(){
		Order order = new Order();
		order.setAuthor(stubUser);
		order.setWorkshift(stubWorkshift);
		orderDao.add(order);
		//after we removed order
		orderDao.remove(order);
		//user should remain in database
		assertNotNull(userDao.find(stubUser.getId()));
	}
}
