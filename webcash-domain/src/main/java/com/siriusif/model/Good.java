package com.siriusif.model;


import java.util.Currency;



public class Good {
	private int id;
	private int art;
	private String name;
	private double price;//TODO change from double to Currency
	private boolean kitchen;
	private boolean bar;
	private boolean pdv;
	private boolean weight;
	private int group;
	private String shortName;
	private boolean piece;
	private Currency minPrice;
	private boolean billiard;
	private int kitchenPrinterId;
	private Group parentGroup;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getArt() {
		return art;
	}
	public void setArt(int art) {
		this.art = art;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public boolean isKitchen() {
		return kitchen;
	}
	public void setKitchen(boolean kitchen) {
		this.kitchen = kitchen;
	}
	public boolean isBar() {
		return bar;
	}
	public void setBar(boolean bar) {
		this.bar = bar;
	}
	public boolean isPdv() {
		return pdv;
	}
	public void setPdv(boolean pdv) {
		this.pdv = pdv;
	}
	public boolean isWeight() {
		return weight;
	}
	public void setWeight(boolean weight) {
		this.weight = weight;
	}
	public int getGroup() {
		return group;
	}
	public void setGroup(int group) {
		this.group = group;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public boolean isPiece() {
		return piece;
	}
	public void setPiece(boolean piece) {
		this.piece = piece;
	}
	public Currency getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Currency minPrice) {
		this.minPrice = minPrice;
	}
	public boolean isBilliard() {
		return billiard;
	}
	public void setBilliard(boolean billiard) {
		this.billiard = billiard;
	}
	public int getKitchenPrinterId() {
		return kitchenPrinterId;
	}
	public void setKitchenPrinterId(int kitchenPrinterId) {
		this.kitchenPrinterId = kitchenPrinterId;
	}
	public Group getParentGroup() {
		return parentGroup;
	}
	public void setParentGroup(Group parentGroup) {
		this.parentGroup = parentGroup;
	}
	

}