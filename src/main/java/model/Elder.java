package model;

import java.util.Objects;

public class Elder extends Identity{
	int fault_tolerance;
	String elder_behavior;
	public int getFault_tolerance() {
		return fault_tolerance;
	}
	public void setFault_tolerance(int fault_tolerance) {
		this.fault_tolerance = fault_tolerance;
	}
	public String getElder_behavior() {
		return elder_behavior;
	}
	public void setElder_behavior(String elder_behavior) {
		this.elder_behavior = elder_behavior;
	}
	@Override
	public String toString() {
		return "Elder [fault_tolerance=" + fault_tolerance + ", elder_behavior=" + elder_behavior + ", gender=" + gender
				+ ", phone_num=" + phone_num + ", emergency_contact=" + emergency_contact + ", age=" + age
				+ ", getFault_tolerance()=" + getFault_tolerance() + ", getElder_behavior()=" + getElder_behavior()
				+ ", getGender()=" + getGender() + ", getPhone_num()=" + getPhone_num() + ", getEmergency_contact()="
				+ getEmergency_contact() + ", getAge()=" + getAge() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + ", getClass()=" + getClass() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(elder_behavior, fault_tolerance);
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
		Elder other = (Elder) obj;
		return Objects.equals(elder_behavior, other.elder_behavior) && fault_tolerance == other.fault_tolerance;
	}
	
	
	
}
