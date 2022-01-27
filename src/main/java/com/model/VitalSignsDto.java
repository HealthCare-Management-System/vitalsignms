package com.model;

public class VitalSignsDto {

	private int id;
	private String height;
	private String weight;
	private String bloodPressure;
	private String bodyTemperature;
	private String respirationRate;
	private String dateTime;
	private PatientDetailsDto patientInfoId;
	private UserDto employeeId;
	private String meetingid;

	
	
	
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



	public PatientDetailsDto getPatientInfoId() {
		return patientInfoId;
	}

	public void setPatientInfoId(PatientDetailsDto patientInfoId) {
		this.patientInfoId = patientInfoId;
	}

	public UserDto getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(UserDto employeeId) {
		this.employeeId = employeeId;
	}

	public VitalSignsDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VitalSignsDto(int id, String height, String weight, String bloodPressure, String bodyTemperature,
			String respirationRate, String dateTime, PatientDetailsDto patientInfoId, UserDto employeeId,
			String meetingid) {
		super();
		this.id = id;
		this.height = height;
		this.weight = weight;
		this.bloodPressure = bloodPressure;
		this.bodyTemperature = bodyTemperature;
		this.respirationRate = respirationRate;
		this.dateTime = dateTime;
		this.patientInfoId = patientInfoId;
		this.employeeId = employeeId;
		this.meetingid = meetingid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bloodPressure == null) ? 0 : bloodPressure.hashCode());
		result = prime * result + ((bodyTemperature == null) ? 0 : bodyTemperature.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((employeeId == null) ? 0 : employeeId.hashCode());
		result = prime * result + ((height == null) ? 0 : height.hashCode());
		result = prime * result + id;
		result = prime * result + ((meetingid == null) ? 0 : meetingid.hashCode());
		result = prime * result + ((patientInfoId == null) ? 0 : patientInfoId.hashCode());
		result = prime * result + ((respirationRate == null) ? 0 : respirationRate.hashCode());
		result = prime * result + ((weight == null) ? 0 : weight.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VitalSignsDto other = (VitalSignsDto) obj;
		if (bloodPressure == null) {
			if (other.bloodPressure != null)
				return false;
		} else if (!bloodPressure.equals(other.bloodPressure))
			return false;
		if (bodyTemperature == null) {
			if (other.bodyTemperature != null)
				return false;
		} else if (!bodyTemperature.equals(other.bodyTemperature))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (employeeId == null) {
			if (other.employeeId != null)
				return false;
		} else if (!employeeId.equals(other.employeeId))
			return false;
		if (height == null) {
			if (other.height != null)
				return false;
		} else if (!height.equals(other.height))
			return false;
		if (id != other.id)
			return false;
		if (meetingid == null) {
			if (other.meetingid != null)
				return false;
		} else if (!meetingid.equals(other.meetingid))
			return false;
		if (patientInfoId == null) {
			if (other.patientInfoId != null)
				return false;
		} else if (!patientInfoId.equals(other.patientInfoId))
			return false;
		if (respirationRate == null) {
			if (other.respirationRate != null)
				return false;
		} else if (!respirationRate.equals(other.respirationRate))
			return false;
		if (weight == null) {
			if (other.weight != null)
				return false;
		} else if (!weight.equals(other.weight))
			return false;
		return true;
	}

	

}
