package com.zyz.empSys.domain;

import java.io.Serializable;
import java.sql.Date;
/**
 * Ա��ʵ����
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class Emp implements Serializable{

	//Ա��ID
	private Integer id;
	//Ա������
	private String name;
	//Ա������
	private String password;
	//Ա���Ա�
	private String gender;
	//Ա������
	private Integer age;
	//Ա����ְ����
	private Date hiredate;
	//Ա��н��
	private Double salary;
	//Ա���绰
	private String phone;
	//Ա������
	private String email;
	
	/**
	 * �ղι���
	 */
	public Emp() {

	}
	
	public Emp(Integer id) {
		this.id = id;
	}
	
	/**
	 * �вι���
	 */
	public Emp(Integer id, String name, String password, String gender, Integer age, Date hiredate, Double salary,
			String phone, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.hiredate = hiredate;
		this.salary = salary;
		this.phone = phone;
		this.email = email;
	}
	
	/**
	 * �вι���
	 */
	public Emp(Integer id, String name, String password, String gender, Integer age, Date hiredate,
			String phone, String email) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.hiredate = hiredate;
		this.phone = phone;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "Emp [id=" + id + ", name=" + name + ", password=" + password + ", gender=" + gender + ", age=" + age
				+ ", hiredate=" + hiredate + ", salary=" + salary + ", phone=" + phone + ", email=" + email + "]";
	}
	@Override
	public int hashCode() {
		
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {

		return super.equals(obj);
	}
	
	
}
