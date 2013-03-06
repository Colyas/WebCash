package com.siriusif.managed.bean;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import com.siriusif.model.Good;
import com.siriusif.model.Group;
import com.siriusif.model.Order;
import com.siriusif.model.Sale;
import com.siriusif.model.Suborder;
import static com.siriusif.model.helpers.SaleBuiledr.*;

@ManagedBean(name="orderBean")
public class OrderBean {
	private Order order;
	private List<Group> groups;
	


	public OrderBean(){
		//TODO SB : Remove this when we will have DB connection
		// begin
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		Object maybeOrder = session.getAttribute("order");
		// end
		if (maybeOrder == null) {
			orderView();
		} else {
			order =(Order)maybeOrder;
		}
		menuView();
	}

	private void orderView() {
		order = new Order();
		
		Suborder first = new Suborder(1);
		
		first.addSale(buildSaleOld("���� ������", 0.280, 10.50));
		first.addSale(buildSaleOld("����� �������", 0.280, 11.00));
		first.addSale(buildSaleOld("�'��� �� ����������", 0.200, 20.00));
		first.addSale(buildSaleOld("�������� ������ � �������", 0.200, 8.00));
		order.addSuborder(first);

		Suborder second = new Suborder(2);
		second.addSale(buildSaleOld("�������� ������ � �������", 0.100, 8.00));
		second.addSale(buildSaleOld("�������", 1, 4.00));
		order.addSuborder(second);

		Suborder third = new Suborder(3);
		third.addSale(buildSaleOld("����", 0.200, 16.00));
		third.addSale(buildSaleOld("���� ��������", 0.040, 9.00));
		third.addSale(buildSaleOld("��������", 0.150, 14.00));
		order.addSuborder(third);
	}
	
	private void menuView() {
		groups = new LinkedList<Group>();
		
		Group first = new Group("������");
		first.getGoods().add(new Good("������ 200�.", money(7.50)));
		first.getGoods().add(new Good("������� 280�.", money(7.50)));
		first.getGoods().add(new Good("� ������� 200�.", money(7.50)));
		first.getGoods().add(new Good("������ 200�.", money(7.50)));
		first.getGoods().add(new Good("����� 240�.", money(7.50)));
		first.getGoods().add(new Good("���'� 250�.", money(7.50)));
		first.getGoods().add(new Good("�'����� ����� 150�.", money(7.50)));
		first.getGoods().add(new Good("�������� 220�.", money(7.50)));
		first.getGoods().add(new Good("������ 200�.", money(7.50)));
		groups.add(first);
		
		Group two = new Group("����� ������");
		two.getGoods().add(new Good("������ 200�.", money(7.50)));
		two.getGoods().add(new Good("������� 280�.", money(7.50)));
		two.getGoods().add(new Good("� ������� 200�.", money(7.50)));
		two.getGoods().add(new Good("������ 200�.", money(7.50)));
		two.getGoods().add(new Good("����� 240�.", money(7.50)));
		two.getGoods().add(new Good("���'� 250�.", money(7.50)));
		two.getGoods().add(new Good("�'����� ����� 150�.", money(7.50)));
		two.getGoods().add(new Good("�������� 220�.", money(7.50)));
		two.getGoods().add(new Good("������ 200�.", money(7.50)));
		groups.add(two);
		groups.add(new Group("���� ������"));
		groups.add(new Group("�������"));
	}
	
	public void onClick(ActionEvent evt){
		 Good good = (Good)evt.getComponent().getAttributes().get("selectedGood");
		 Sale sale = new Sale();
		 sale.setSalesGood(good);
		 sale.setAmount(new BigDecimal(0.505).setScale(3, RoundingMode.HALF_UP));
		 order.getSuborders().get(0).addSale(sale);
		//TODO SB : Remove this when we will have DB connection
		// begin
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("order",order);
		//end
		
//		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome "  + "!"));  
		
	  }

	public Order getOrder() {
		return order;
	}
	public List<Group> getGroups() {
		return groups;
	}

}
