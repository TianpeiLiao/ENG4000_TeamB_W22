package model;

import java.util.Objects;

public abstract class Identity {
	Random_Forest random_forest;
	String phone_num, emergency_contact, age;
	int dataset_id;
	boolean anomaly;
	public int getDataset_id() {
		return dataset_id;
	}
	
	public boolean isAnomaly() {
		return anomaly;
	}

	public void setAnomaly(boolean anomaly) {
		this.anomaly = anomaly;
	}


	public void setDataset_id(int dataset_id) {
		this.dataset_id = dataset_id;
	}


	public Random_Forest getRandom_forest() {
		return random_forest;
	}
	
	
	public void add_acc(double[] acc) {
		random_forest.add(acc[3]);
		random_forest.setAccX(acc[0]);
		random_forest.setAccY(acc[1]);
		random_forest.setAccZ(acc[2]);
	}
	public void setRandom_forest(Random_Forest random_forest) {
		this.random_forest = random_forest;
	}
	String gender,f_name,l_name,url;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getF_name() {
		return f_name;
	}
	public void setF_name(String f_name) {
		this.f_name = f_name;
	}
	public String getL_name() {
		return l_name;
	}
	public void setL_name(String l_name) {
		this.l_name = l_name;
	}
	
	
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone_num() {
		return phone_num;
	}
	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	public String getEmergency_contact() {
		return emergency_contact;
	}
	public void setEmergency_contact(String emergency_contact) {
		this.emergency_contact = emergency_contact;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(age, emergency_contact, gender, phone_num);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Identity other = (Identity) obj;
		return age == other.age && emergency_contact == other.emergency_contact && Objects.equals(gender, other.gender)
				&& phone_num == other.phone_num;
	}
	@Override
	public String toString() {
		return "Identity [gender=" + gender + ", phone_num=" + phone_num + ", emergency_contact=" + emergency_contact
				+ ", age=" + age + "]";
	}
	
	
	
}
