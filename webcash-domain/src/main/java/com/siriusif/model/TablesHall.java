package com.siriusif.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DiningTable")
public class TablesHall {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(name="name", nullable=false, length=100)
	private String name;
	
	@Column(name="description", nullable=true, length=100)
	private String description;
	
	@Column(name = "ctop", nullable = true)
	private int top;
	
	// left is reserved word in SQL
	@Column(name = "cleft", nullable = true)
	private int left;
	
	@Column(name = "height", nullable = true)
	private int height;
	
	@Column(name = "width", nullable = true)
	private int width;
	
	@ManyToOne
	private Hall hall;
	
	public TablesHall(){
		
	}
	
	public TablesHall(String name, String description, int left, int top) {
		this();
		this.name = name;
		this.description = description;
		this.left = left;
		this.top = top;
	}

	
	/* autogenerated stuff */
	
	
	public Hall getHall() {
		return hall;
	}
	public void setHall(Hall hall) {
		this.hall = hall;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getLeft() {
		return left;
	}
	public void setLeft(int left) {
		this.left = left;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	

}
