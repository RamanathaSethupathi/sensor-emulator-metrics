package com.egen.sensor.metrics.model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
/**
 *Bean class that represents the Metrics collection in Mongo DB 
 *
 */
@Entity(value="Metrics", noClassnameStored=true)
public class Metrics {

	@Id()
	private String id;
	private String timeStamp;
	private int value;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
    public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "Metrics [id=" + id + ", timeStamp=" + timeStamp + ", value=" + value + "]";
	}

}
