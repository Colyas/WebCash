package com.siriusif.managed.bean;

import java.util.ArrayList;
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
import com.siriusif.sampledata.SampleDataProvider;

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
		System.out.println("!!!!! CONSTRUCTOR !!!!");
		if (maybeOrder == null) {
			orderView();
		} else {
			order =(Order)maybeOrder;
		}
		menuView();
	}

	private void orderView() {
		order = SampleDataProvider.getSampleOrder();
	}
	
	private void menuView() {
		groups = new ArrayList<Group>();
		
		Group first = new Group("Салати");
		first.getGoods().add(new Good("Сирний 200г.", 7.50));
		first.getGoods().add(new Good("Домашній 280г.", 7.50));
		first.getGoods().add(new Good("З капусти 200г.", 7.50));
		first.getGoods().add(new Good("Кореєць 200г.", 7.50));
		first.getGoods().add(new Good("Цезар 240г.", 7.50));
		first.getGoods().add(new Good("Олів'є 250г.", 7.50));
		first.getGoods().add(new Good("М'ясний Теріякі 150г.", 7.50));
		first.getGoods().add(new Good("Грецький 220г.", 7.50));
		first.getGoods().add(new Good("Рибний 200г.", 7.50));
		groups.add(first);
		
		Group two = new Group("Суп");
		two.getGoods().add(new Good("Сирний 200г.", 7.50));
		two.getGoods().add(new Good("Домашній 280г.", 7.50));
		two.getGoods().add(new Good("З капусти 200г.", 7.50));
		two.getGoods().add(new Good("Кореєць 200г.", 7.50));
		two.getGoods().add(new Good("Цезар 240г.", 7.50));
		two.getGoods().add(new Good("Олів'є 250г.", 7.50));
		two.getGoods().add(new Good("М'ясний Теріякі 150г.", 7.50));
		two.getGoods().add(new Good("Грецький 220г.", 7.50));
		two.getGoods().add(new Good("Рибний 200г.", 7.50));
		groups.add(two);
	}
	
	public void onClick(ActionEvent evt){
		 System.out.println("SB : onClick");
		 Good good = (Good)evt.getComponent().getAttributes().get("selectedGood");
		 System.out.println("SB : "+good.getName());
		 System.out.println("SB : "+good.getPrice());
		 Sale sale = new Sale();
		 sale.setSalesGood(good);
		 sale.setAmount(5);
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
