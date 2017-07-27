package com.qiaoyu.app.dao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CLIENT")
public class Client implements Serializable {

	private Integer id;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String shippingAddress;
	
	private String shippingAddressee;
	
	private String shippingAttention;
	
	private String shippingAddress1;
	
	private String shippingAddress2;
	
	private String shippingCity;
	
	private String shippingStateProvince;
	
	private String shippingZip;
	
	private String shippingCountry;
	
	private String shippingCountryCode;
	
	private String shippingPhone;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Column(name = "SHIPPING_ADDRESS")
	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	@Column(name = "SHIPPING_ADDRESSEE")
	public String getShippingAddressee() {
		return shippingAddressee;
	}

	public void setShippingAddressee(String shippingAddressee) {
		this.shippingAddressee = shippingAddressee;
	}

	@Column(name = "SHIPPING_ATTENTION")
	public String getShippingAttention() {
		return shippingAttention;
	}

	public void setShippingAttention(String shippingAttention) {
		this.shippingAttention = shippingAttention;
	}

	@Column(name = "SHIPPING_ADDRESS1")
	public String getShippingAddress1() {
		return shippingAddress1;
	}

	public void setShippingAddress1(String shippingAddress1) {
		this.shippingAddress1 = shippingAddress1;
	}

	@Column(name = "SHIPPING_ADDRESS2")
	public String getShippingAddress2() {
		return shippingAddress2;
	}

	public void setShippingAddress2(String shippingAddress2) {
		this.shippingAddress2 = shippingAddress2;
	}

	@Column(name = "SHIPPING_CITY")
	public String getShippingCity() {
		return shippingCity;
	}

	public void setShippingCity(String shippingCity) {
		this.shippingCity = shippingCity;
	}

	@Column(name = "SHIPPING_STATE_PROVINCE")
	public String getShippingStateProvince() {
		return shippingStateProvince;
	}

	public void setShippingStateProvince(String shippingStateProvince) {
		this.shippingStateProvince = shippingStateProvince;
	}

	@Column(name = "SHIPPING_ZIP")
	public String getShippingZip() {
		return shippingZip;
	}

	public void setShippingZip(String shippingZip) {
		this.shippingZip = shippingZip;
	}

	@Column(name = "SHIPPING_COUNTRY")
	public String getShippingCountry() {
		return shippingCountry;
	}

	public void setShippingCountry(String shippingCountry) {
		this.shippingCountry = shippingCountry;
	}

	@Column(name = "SHIPPING_COUNTRY_CODE")
	public String getShippingCountryCode() {
		return shippingCountryCode;
	}

	public void setShippingCountryCode(String shippingCountryCode) {
		this.shippingCountryCode = shippingCountryCode;
	}

	@Column(name = "SHIPPING_PHONE")
	public String getShippingPhone() {
		return shippingPhone;
	}

	public void setShippingPhone(String shippingPhone) {
		this.shippingPhone = shippingPhone;
	}
	
}
