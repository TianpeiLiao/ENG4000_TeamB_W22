package model;

import java.util.Objects;

public class Teenager extends Identity{
	int fault_tolerance;
	@Override
	public String toString() {
		return "Teenager [fault_tolerance=" + fault_tolerance + ", teen_behavior=" + teen_behavior + ", gender="
				+ gender + ", phone_num=" + phone_num + ", emergency_contact=" + emergency_contact + ", age=" + age
				+ ", hashCode()=" + hashCode() + ", getFault_tolerance()=" + getFault_tolerance()
				+ ", getTeen_behavior()=" + getTeen_behavior() + ", getGender()=" + getGender() + ", getPhone_num()="
				+ getPhone_num() + ", getEmergency_contact()=" + getEmergency_contact() + ", getAge()=" + getAge()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + "]";
	}
	String teen_behavior;
	public Teenager(int fault_tolerance, String teen_behavior) {
		super();
		this.fault_tolerance = fault_tolerance;
		this.teen_behavior = teen_behavior;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(fault_tolerance, teen_behavior);
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
		Teenager other = (Teenager) obj;
		return fault_tolerance == other.fault_tolerance && Objects.equals(teen_behavior, other.teen_behavior);
	}
	public int getFault_tolerance() {
		return fault_tolerance;
	}
	public void setFault_tolerance(int fault_tolerance) {
		this.fault_tolerance = fault_tolerance;
	}
	public String getTeen_behavior() {
		return teen_behavior;
	}
	public void setTeen_behavior(String teen_behavior) {
		this.teen_behavior = teen_behavior;
	}
}
