package com.siriusif.service.model;

import static com.siriusif.model.helpers.SaleBuiledr.money;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.siriusif.helper.AbstractSpringTest;
import com.siriusif.helper.Helper;
import com.siriusif.model.Good;
import com.siriusif.model.Group;

public class GroupDaoImplTest extends AbstractSpringTest {
	
	@Autowired
	private GroupDao groupDao;

	@Test
	public void testAdd() {
		int size = groupDao.list().size();
		
		Group group = new Group();
		group.setgName("Супи");
		groupDao.add(group);
		
		assertTrue(size < groupDao.list().size());
	}
	
	@Test
	public void testOneToManyGroupGoods(){
		int size = groupDao.list().size();
		Group group = new Group("Овочі");
		
		Good good = new Good();
		good.setName("огірки");
		good.setPrice(money(10.00));
		good.setShortName("Огірочки");
		group.addGood(good);
		
		Good good1 = new Good();
		good1.setName("Цибуля");
		good1.setPrice(money(11.00));
		good1.setShortName("Цибулька");
		group.addGood(good1);
		
		Good good2 = new Good();
		good2.setName("Картопля");
		good2.setPrice(money(12.00));
		good2.setShortName("Бульба");
		group.addGood(good2);
		
		groupDao.add(group);
		
		assertTrue(size < groupDao.list().size());
		Group groupFromBd = groupDao.find(group.getgId());
		assertEquals("Овочі", groupFromBd.getgName());
		assertEquals(3, groupFromBd.getGoods().size());
	}
	
	@Test
	public void testOneToManyGroupSubgroup(){
		int size = groupDao.list().size();
		Group group = new Group("Напої");
		
		Group subGroup = new Group("Гарячі напої");
		group.addSubGroup(subGroup);
		
		Group subGroup1 = new Group("Холдні напої");
		group.addSubGroup(subGroup1);
		
		Group subGroup2 = new Group("Соки");
		group.addSubGroup(subGroup2);
		
		Group subGroup3 = new Group("Вода");
		group.addSubGroup(subGroup3);
		
		groupDao.add(group);
		
		assertTrue(size < groupDao.list().size());
		Group groupFromBd = groupDao.find(group.getgId());
		assertEquals("Напої", groupFromBd.getgName());
		assertEquals(4, groupFromBd.getSubGroups().size());
		Group subgroupFromBd = groupDao.find(subGroup.getgId());
		assertEquals("Гарячі напої", subgroupFromBd.getgName());
	}
	
	@Test
	public void testGroupGoodsFromDb()throws IOException{
		Group group = Helper.fromJson("/group_goods.json", Group.class);
		groupDao.add(group);
		
		Group groupFromBd = groupDao.find(group.getgId());
		assertEquals("Напої", groupFromBd.getgName());
//		assertEquals(2, groupFromBd.getGoods().size());
//		assertEquals(2, groupFromBd.getSubGroups().size());
	}

}
