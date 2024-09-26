package com.fdmgroup.apiPack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Address {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ADDRESS_ID_GEN")
    @SequenceGenerator(name="ADDRESS_ID_GEN", sequenceName = "ADDRESS_ID_SEQ")
	@Column(name = "ADDRESS_ID")
	private long addressId;
	@NotBlank(message = "Street Number must not be blank")
    @Column(name = "STREET_NUMBER", nullable = false)
	private String streetNumber;
	@NotBlank(message = "City must not be blank")
	@Column(name = "CITY", nullable = false)
	private String city;
	@NotBlank(message = "Province must not be blank")
	@Column(name = "PROVINCE", nullable = false)
	private String province;
	@NotBlank(message = "Postal Code must not be blank")
	@Column(name = "POSTAL_CODE", nullable = false)
	private String postalCode;
	
	
	
	public Address(@NotBlank(message = "Street Number must not be blank") String streetNumber,
			@NotBlank(message = "City must not be blank") String city,
			@NotBlank(message = "Province must not be blank") String province,
			@NotBlank(message = "Postal Code must not be blank") String postalCode) {
		super();
		this.streetNumber = streetNumber;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
	}
	public Address(long addressId, @NotBlank(message = "Street Number must not be blank") String streetNumber,
			@NotBlank(message = "City must not be blank") String city,
			@NotBlank(message = "Province must not be blank") String province,
			@NotBlank(message = "Postal Code must not be blank") String postalCode) {
		super();
		this.addressId = addressId;
		this.streetNumber = streetNumber;
		this.city = city;
		this.province = province;
		this.postalCode = postalCode;
	}
	public Address() {
		// TODO Auto-generated constructor stub
	}
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	
}
