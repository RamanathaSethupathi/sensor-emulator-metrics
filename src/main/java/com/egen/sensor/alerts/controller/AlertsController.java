package com.egen.sensor.alerts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.egen.sensor.SensorEmulatorMetricsApplication;
import com.egen.sensor.alerts.model.Alerts;
import com.google.code.morphia.query.Query;

@RestController
@RequestMapping(value="/Sensor/Alerts")
public class AlertsController {
	/**
	 * Method that returns all the alerts available in DB
	 * @return
	 */
	 @RequestMapping(value="/readall", method = RequestMethod.GET)
	 public List<Alerts> readAll() {
	     Query<Alerts> querys = SensorEmulatorMetricsApplication.getMongoDataStore().createQuery(Alerts.class);
	     return querys.asList();
	 }
	 
	 /**
	  * Method that returns the list of alerts that falls 
	  * between the given timestamps
	  * @param starttimeStamp
	  * @param endtimeStamp
	  * @return
	  */
	 @RequestMapping(value="/readbytimerange/{starttimeStamp}/{endtimeStamp}", method = RequestMethod.GET)
	 public List<Alerts> readByTimeRange(@PathVariable("starttimeStamp") String starttimeStamp, @PathVariable("endtimeStamp") String endtimeStamp) {
	    Query<Alerts> query = SensorEmulatorMetricsApplication.getMongoDataStore().createQuery(Alerts.class).filter("timeStamp >=", starttimeStamp)
	    		                                                     .filter("timeStamp <=", endtimeStamp);
	    return query.asList();
	 }
	 
}
