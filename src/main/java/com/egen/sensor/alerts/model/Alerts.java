package com.egen.sensor.alerts.model;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
/**
 *Bean class that represents the Alerts collection in Mongo DB 
 *
 */
@Entity(value="Alert", noClassnameStored=true)
public class Alerts {

	@Id()
	private String id;
	private String message;
	private String timeStamp;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}
	@Override
	public String toString() {
		return "Alerts [id=" + id + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}

}
