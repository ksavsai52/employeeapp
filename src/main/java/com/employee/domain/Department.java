package com.employee.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity(name = "department")
public class Department implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8063877916608986266L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(name = "salary_min_range")
	private double minSalaryRange;

	@Column(name = "salary_max_range")
	private double maxSalaryRange;

	@OneToMany(mappedBy = "department", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Employee> employees;

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

	public double getMinSalaryRange() {
		return minSalaryRange;
	}

	public void setMinSalaryRange(double minSalaryRange) {
		this.minSalaryRange = minSalaryRange;
	}

	public double getMaxSalaryRange() {
		return maxSalaryRange;
	}

	public void setMaxSalaryRange(double maxSalaryRange) {
		this.maxSalaryRange = maxSalaryRange;
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

}
