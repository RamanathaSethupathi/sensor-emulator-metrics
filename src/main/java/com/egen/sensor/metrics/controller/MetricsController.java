package com.egen.sensor.metrics.controller;

import java.util.List;

import org.easyrules.api.RulesEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.morphia.query.Query;
import com.egen.sensor.SensorEmulatorMetricsApplication;
import com.egen.sensor.metrics.model.Metrics;
import com.egen.sensor.rule.OverWeightRule;
import com.egen.sensor.rule.UnderWeightRule;

@RestController
@RequestMapping(value="/Sensor/Metrics")
public class MetricsController {

	 /**
	  * Method that accepts the given Metrics data ( timestamp and weight)
	  * and stores it in DB. Also triggers the rule to identify the weight is 
	  * falls under overweight/underweight
	  * @param metrics
	  * @return
	  */
	 @RequestMapping(value="/create", method = RequestMethod.POST)
	 public Metrics create(@RequestBody Metrics metrics) {
	     
		SensorEmulatorMetricsApplication.getMongoDataStore().save(metrics);
		ApplicationContext context
	       = new ClassPathXmlApplicationContext("applicationContext.xml");
	    RulesEngine rulesEngine
	       = (RulesEngine) context.getBean("rulesEngine");

	    UnderWeightRule underWt = new UnderWeightRule(metrics);
	    OverWeightRule overWt = new OverWeightRule(metrics);
	    rulesEngine.fireRules();
	     return metrics;
	 }	 
	 
	 /**
	  * Method that returns all the Metrics data
	  * available in the Database
	  * @return
	  */
	 @RequestMapping(value="/readall", method = RequestMethod.GET)
	 public List<Metrics> readAll() {
	     Query<Metrics> querys = SensorEmulatorMetricsApplication.getMongoDataStore().createQuery(Metrics.class);
	     return querys.asList();
	 }
	 
	/**
	 * Method that returns the list of Metrics data ( timestamp and weight)
	 * for the given time period
	 * @param starttimeStamp
	 * @param endtimeStamp
	 * @return
	 */
	 @RequestMapping(value="/readbytimerange/{starttimeStamp}/{endtimeStamp}", method = RequestMethod.GET)
	 public List<Metrics> readByTimeRange(@PathVariable("starttimeStamp") String starttimeStamp, @PathVariable("endtimeStamp") String endtimeStamp) {
	    Query<Metrics> query = SensorEmulatorMetricsApplication.getMongoDataStore().createQuery(Metrics.class).filter("timeStamp >=", starttimeStamp)
	    		                                                     .filter("timeStamp <=", endtimeStamp);
	    return query.asList();
	 }
	 
	 
}
