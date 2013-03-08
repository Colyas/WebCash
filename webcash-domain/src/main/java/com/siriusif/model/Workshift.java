package com.siriusif.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Workshift Entity 
 * CREATE TABLE "Workshifts" (
 *  "id" INT NOT NULL, 
 *  "dailyId" INT NULL,
 *  "openedAt" DATETIME NOT NULL,
 *   "closedAt" DATETIME NULL, 
 *   "DaySum" MONEY NULL, );
 */

@Entity
@Table(name = "workshifts")
public class Workshift {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;

	@Column(name = "dailyId", nullable = true)
	private int dailyId;

	/**
	 * time of work shift opening
	 */
	@Column(name = "openedAt", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
	@Temporal(TemporalType.DATE)
	private Date openedAt;

	/**
	 * time of work shift closing
	 */
	@Column(name = "closedAt", nullable = true, columnDefinition = "TIMESTAMP", insertable = false)
	@Temporal(TemporalType.DATE)
	private Date closedAt;
	
	/**
	 * sum per work shift
	 */
	@Column(name="daySum", nullable = true, precision=16, scale=2)
	private BigDecimal daySum;

	/* autogenerated stuff */


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getDailyId() {
		return dailyId;
	}

	public void setDailyId(int dailyId) {
		this.dailyId = dailyId;
	}

	public Date getOpenedAt() {
		return openedAt;
	}

	public void setOpenedAt(Date openedAt) {
		this.openedAt = openedAt;
	}
	public Date getClosedAt() {
		return closedAt;
	}
	
	public void setClosedAt(Date closedAt) {
		this.closedAt = closedAt;
	}
	
	public BigDecimal getDaySum() {
		return daySum;
	}
	
	public void setDaySum(BigDecimal daySum) {
		this.daySum = daySum;
	}
}
