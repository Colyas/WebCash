package com.siriusif.model;

import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sale")
public class Sale {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	
	@Column(name = "amount", nullable = false)
	private double amount;

	/** () total sum of sale **/
	private Currency sum;
	
	@Column(name="allowsum", nullable = true, precision=16, scale=2)
	private BigDecimal allowSum;
	
	@Column(name="fp", columnDefinition="boolean default false") 
	private boolean fp;
	
	@Column(name="printed", columnDefinition="boolean default false") 
	private boolean printed;
	
	@Transient
	private Good salesGood;
	
	@ManyToOne
	@JoinColumn(name = "suborder_id")
	private Suborder suborder;

	//
	// public void addGoodSales(Sale goodSales) {
	// goodSales.setSalesGood(salesGood);
	// }

	public BigDecimal getCalculatedSum() {
		// TODO change from double to Currency
		BigDecimal sum = null;
		BigDecimal p = salesGood.getPrice();
		double am = getAmount();
		sum = p * am;
		return sum;
	}

	/* autogenerated stuff */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Currency getSum() {
		return sum;
	}

	public BigDecimal getAllowSum() {
		return allowSum;
	}

	public void setAllowSum(BigDecimal allowSum) {
		this.allowSum = allowSum;
	}

	public boolean isFp() {
		return fp;
	}

	public void setFp(boolean fp) {
		this.fp = fp;
	}

	public boolean isPrinted() {
		return printed;
	}

	public void setPrinted(boolean printed) {
		this.printed = printed;
	}

	public Good getSalesGood() {
		return salesGood;
	}

	public void setSalesGood(Good salesGood) {
		this.salesGood = salesGood;
	}

	public Suborder getSuborder() {
		return suborder;
	}

	public void setSuborder(Suborder suborder) {
		this.suborder = suborder;
	}

}
