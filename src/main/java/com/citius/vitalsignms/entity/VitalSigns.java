package com.citius.vitalsignms.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vital_signs")
public class VitalSigns {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String height;
	private String weight;
	private String bloodPressure;
	private String bodyTemperature;
	private String respirationRate;
	private String dateTime;
	private String meetingid;

	private int patientInfoId;

	private int employeeId;

	
	
	
	
	public String getMeetingid() {
		return meetingid;
	}

	public void setMeetingid(String meetingid) {
		this.meetingid = meetingid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getBloodPressure() {
		return bloodPressure;
	}

	public void setBloodPressure(String bloodPressure) {
		this.bloodPressure = bloodPressure;
	}

	public String getBodyTemperature() {
		return bodyTemperature;
	}

	public void setBodyTemperature(String bodyTemperature) {
		this.bodyTemperature = bodyTemperature;
	}

	public String getRespirationRate() {
		return respirationRate;
	}

	public void setRespirationRate(String respirationRate) {
		this.respirationRate = respirationRate;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getPatientInfoId() {
		return patientInfoId;
	}

	public void setPatientInfoId(int patientInfoId) {
		this.patientInfoId = patientInfoId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public VitalSigns() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VitalSigns(int id, String height, String weight, String bloodPressure, String bodyTemperature,
			String respirationRate, String dateTime, String meetingid, int patientInfoId, int employeeId) {
		super();
		this.id = id;
		this.height = height;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.bodyTemperature = bodyTemperature;
		this.respirationRate = respirationRate;
		this.dateTime = dateTime;
		this.meetingid = meetingid;
		this.patientInfoId = patientInfoId;
		this.employeeId = employeeId;
	}
    
	
	
	
}
