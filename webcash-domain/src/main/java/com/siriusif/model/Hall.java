package com.siriusif.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Hall")
public class Hall {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(name="name", nullable=false, length=100)
	private String name;
	
	@OneToMany
	private List<TablesHall> tables;
	
	public Hall(){
		tables = new ArrayList<TablesHall>();
	}
	
	public void addTables(TablesHall table) {
		table.setHall(this);
		tables.add(table);
	}

	/* autogenerated stuff */
	
	
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
	public List<TablesHall> getTables() {
		return tables;
	}
	public void setTables(List<TablesHall> tables) {
		this.tables = tables;
	}

}
