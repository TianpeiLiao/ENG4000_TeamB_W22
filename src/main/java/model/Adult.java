package model;

import java.util.Objects;

public class Adult extends Identity {
	int fault_tolerance;
	@Override
	public String toString() {
		return "Adult [fault_tolerance=" + fault_tolerance + ", adult_behavior=" + adult_behavior + ", gender=" + gender
				+ ", phone_num=" + phone_num + ", emergency_contact=" + emergency_contact + ", age=" + age
				+ ", getFault_tolerance()=" + getFault_tolerance() + ", getAdult_behavior()=" + getAdult_behavior()
				+ ", hashCode()=" + hashCode() + ", getGender()=" + getGender() + ", getPhone_num()=" + getPhone_num()
				+ ", getEmergency_contact()=" + getEmergency_contact() + ", getAge()=" + getAge() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}
	String adult_behavior;
	public int getFault_tolerance() {
		return fault_tolerance;
	}
	public void setFault_tolerance(int fault_tolerance) {
		this.fault_tolerance = fault_tolerance;
	}
	public String getAdult_behavior() {
		return adult_behavior;
	}
	public void setAdult_behavior(String adult_behavior) {
		this.adult_behavior = adult_behavior;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(adult_behavior, fault_tolerance);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Adult other = (Adult) obj;
		return Objects.equals(adult_behavior, other.adult_behavior) && fault_tolerance == other.fault_tolerance;
	}
	public Adult(int fault_tolerance, String adult_behavior) {
		super();
		this.fault_tolerance = fault_tolerance;
		this.adult_behavior = adult_behavior;
	}
}
