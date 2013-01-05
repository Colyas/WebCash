package com.siriusif.managed.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import com.siriusif.model.Good;
import com.siriusif.model.Group;

@ManagedBean(name="menuBean")
public class MenuBean {
	private String name;
	private String price;
	private List<Good> goods;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	
	public MenuBean(){
		menuView();
	}

	private void menuView() {
		goods = new ArrayList<Good>();
		
		
		goods.add(new Good("������ 200�.", 7.50));
		goods.add(new Good("������� 280�.", 7.50));
		goods.add(new Good("� ������� 200�.", 7.50));
		goods.add(new Good("������ 200�.", 7.50));
		goods.add(new Good("����� 240�.", 7.50));
		goods.add(new Good("���'� 250�.", 7.50));
		goods.add(new Good("�'����� ����� 150�.", 7.50));
		goods.add(new Good("�������� 220�.", 7.50));
		goods.add(new Good("������ 200�.", 7.50));
		
		
		
		goods.add(new Good("������ 200�.", 7.50));
		goods.add(new Good("������� 280�.", 7.50));
		goods.add(new Good("� ������� 200�.", 7.50));
		goods.add(new Good("������ 200�.", 7.50));
		goods.add(new Good("����� 240�.", 7.50));
		goods.add(new Good("���'� 250�.", 7.50));
		goods.add(new Good("�'����� ����� 150�.", 7.50));
		goods.add(new Good("�������� 220�.", 7.50));
		goods.add(new Good("������ 200�.", 7.50));
		
	}
	
	 public void onClick(ActionEvent evt){
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Welcome " + name + " " + price + "!"));  
		
	  }

	public List<Good> getGoods() {
		return goods;
	}

	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
}
