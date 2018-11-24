/**
 * 
 */
package com.stg.insurance.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author abhinavkumar.gupta
 *
 */
@Entity
@Table(name = "DRIVERS_INFORMATION")
public class DriversInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "DriversName")
	private String driversName;

	@Column(name = "dateOfBirth_dt6")
	private String dateOfBirthDt6;

	@Column(name = "driversLicenseNumber")
	private String driversLicenseNumber;

	@Column(name = "licenseState")
	private String licenseState;

	@Column(name = "dateOfBirthDt8")
	private String dateOfBirthDt8;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the driversName
	 */
	public String getDriversName() {
		return driversName;
	}

	/**
	 * @param driversName
	 *            the driversName to set
	 */
	public void setDriversName(String driversName) {
		this.driversName = driversName;
	}

	/**
	 * @return the dateOfBirthDt6
	 */
	public String getDateOfBirthDt6() {
		return dateOfBirthDt6;
	}

	/**
	 * @param dateOfBirthDt6
	 *            the dateOfBirthDt6 to set
	 */
	public void setDateOfBirthDt6(String dateOfBirthDt6) {
		this.dateOfBirthDt6 = dateOfBirthDt6;
	}

	/**
	 * @return the driversLicenseNumber
	 */
	public String getDriversLicenseNumber() {
		return driversLicenseNumber;
	}

	/**
	 * @param driversLicenseNumber
	 *            the driversLicenseNumber to set
	 */
	public void setDriversLicenseNumber(String driversLicenseNumber) {
		this.driversLicenseNumber = driversLicenseNumber;
	}

	/**
	 * @return the licenseState
	 */
	public String getLicenseState() {
		return licenseState;
	}

	/**
	 * @param licenseState
	 *            the licenseState to set
	 */
	public void setLicenseState(String licenseState) {
		this.licenseState = licenseState;
	}

	/**
	 * @return the dateOfBirthDt8
	 */
	public String getDateOfBirthDt8() {
		return dateOfBirthDt8;
	}

	/**
	 * @param dateOfBirthDt8
	 *            the dateOfBirthDt8 to set
	 */
	public void setDateOfBirthDt8(String dateOfBirthDt8) {
		this.dateOfBirthDt8 = dateOfBirthDt8;
	}

}
