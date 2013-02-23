package com.siriusif.sampledata;

import static com.siriusif.model.helpers.SaleBuiledr.buildSale;

import java.util.ArrayList;
import java.util.List;

import com.siriusif.model.Order;
import com.siriusif.model.Suborder;

public class SampleDataProvider {
	
	public static List<Order> getSampleOrders(){
		ArrayList<Order> orders = new ArrayList<Order>();
		orders.add(getSampleOrder());
		return orders;	
	}
	
	public static Order getSampleOrder(){
		Order order = new Order();
		
		Suborder first = new Suborder(1);
		
		first.addSale(buildSale("Юшка грибна", 0.280, 12.50));
		first.addSale(buildSale("Салат домашній", 0.280, 12.00));
		first.addSale(buildSale("М'ясо по французьки", 0.200, 20.00));
		first.addSale(buildSale("Картопля молода з зеленню", 0.200, 8.00));
		order.addSuborder(first);

		Suborder second = new Suborder(2);
		second.addSale(buildSale("Смалець", 0.100, 8.00));
		second.addSale(buildSale("Сметана", 1, 4.00));
		second.addSale(buildSale("Фреш", 0.200, 16.00));
		order.addSuborder(second);

		Suborder third = new Suborder(3);
		third.addSale(buildSale("Хліб", 10, 0.50));
		third.addSale(buildSale("Кава Еспрессо", 0.040, 9.00));
		third.addSale(buildSale("Штрудель", 0.150, 14.00));
		order.addSuborder(third);
		return order;		
	}

}
