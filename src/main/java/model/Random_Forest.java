package model;

import java.util.ArrayList;

public class Random_Forest {
	
	ArrayList<Double> cur_buffer,pre_buffer;
	private double cur_acc, accX, accY, accZ;
	int index;
	String state,anomaly_state;
	public String getAnomaly_state() {
		return anomaly_state;
	}



	public void setAnomaly_state(String anomaly_state) {
		this.anomaly_state = anomaly_state;
	}



	Identity identity;
	
	
	
	final static double walking = 9;
	final static double staying = 1;
	final static double running = 15;
	final static double un_touch = 0.5;
	final static double buffer_size = 50;
	final static double drop_phone_neg = -5;
	final static double drop_phone_pos = 5;
	public Random_Forest(Identity identity) {
		cur_buffer = new ArrayList<Double>();
		pre_buffer = new ArrayList<Double>();
		this.identity = identity;
	}
	
	

	public double getAccX() {
		return accX;
	}



	public void setAccX(double accX) {
		this.accX = accX;
	}



	public double getAccY() {
		return accY;
	}



	public void setAccY(double accY) {
		this.accY = accY;
	}



	public double getAccZ() {
		return accZ;
	}



	public void setAccZ(double accZ) {
		this.accZ = accZ;
	}



	public ArrayList<Double> getBuffer() {
		return cur_buffer;
	}

	public void setBuffer(ArrayList<Double> buffer) {
		this.cur_buffer = buffer;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public void add(double d, int index) {
		cur_buffer.add(index, d);
		cur_acc = d;
	}
	
	public double getCur_acc() {
		return cur_acc;
	}

	public void setCur_acc(double cur_acc) {
		this.cur_acc = cur_acc;
	}

	public void add(double d) {
		if(this.cur_buffer.size()<=buffer_size) {
			cur_buffer.add(d);
		}
		else {
			ArrayList<Double> copy = new ArrayList<Double>();
			for(int i=1; i<buffer_size;i++) {
				copy.add(cur_buffer.get(i));
			}
			copy.add(d);
			cur_buffer = copy;
		}
		cur_acc = d;
	}
	
	
	
	
	
	
	public double average(ArrayList<Double> buffer) {
		double sum=0;
		for(double b:buffer) {
			sum+=b;
		}
		return sum/buffer.size();
	}
	
	public double min() {
		double min=Double.MAX_VALUE;
		for(double b:this.cur_buffer) {
			if(min>b)
				min=b;
		}
		return min;
	}
	
	public double max() {
		double max=Double.MIN_VALUE;
		for(double b:this.cur_buffer) {
			if(max<b)
				max=b;
		}
		return max;
	}
	
	
	
	public String get_state() {
		state = "normal_running";						
		if(cur_acc<staying)
			return "normal_staying";
		if(cur_acc<walking)
			return "normal_walking";
		if(cur_acc<running)
			return "normal_running";
//		if(accZ<drop_phone_neg || accZ > drop_phone_pos) {
//			identity.setAnomaly(true);
//			anomaly_state = "anomaly: might drop_phone";
//			return anomaly_state;
//		}
			
		if(average(pre_buffer)>staying && average(cur_buffer)<staying)
			return "anomoly_fell";
		if(average(cur_buffer)<un_touch)
			return "device not started or lost";
		
		
		return state;
	}
	
}
