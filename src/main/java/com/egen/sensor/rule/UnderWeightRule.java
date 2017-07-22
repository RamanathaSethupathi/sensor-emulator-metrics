package com.egen.sensor.rule;

import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;

import com.egen.sensor.SensorEmulatorMetricsApplication;
import com.egen.sensor.alerts.model.Alerts;
import com.egen.sensor.metrics.model.Metrics;
/**
 * 
 * Rule class defined to identify under Weight
 * 
 *
 */
@Rule (name="underWeightRule", description=" Rule to identify Under Weight")
public class UnderWeightRule {

//	public static Integer weight;
	private static Metrics metrics;
	
	public Metrics getMetrics() {
		return metrics;
	}
	private static Integer baseWeight;
	
	public  UnderWeightRule(Metrics metrics) {
	
			this.metrics = metrics;
			baseWeight=SensorEmulatorMetricsApplication.baseWeight;
			System.out.println("UnderWeightRule Base Weight "+baseWeight);
			System.out.println("Metrivs Value "+metrics.getTimeStamp()+"    "+metrics.getValue());
	}
	public UnderWeightRule() {
		// Do nothing
	}
	
	@Condition
	/**
	 * Method that compares the given weight
	 * with base weight and returns true if it falls below 
	 * 10% of base weight 
	 * @return boolean
	 */
	public static boolean checkWeight() {
		System.out.println("Metrivs Value 123"+metrics.getTimeStamp()+"    "+metrics.getValue());
		if(metrics.getValue()<=(baseWeight - (baseWeight*0.1))) {
			return true;
		}
		else {
			return false;
		}
	}
	@Action
	/**
	 * This method will be executed if the above condition method evaluates to true.
	 * If the weight of the person falls below 10% of his base weight then
	 * an alert should be created
	 */
	public void addAlert() {
		Alerts alert = new Alerts();
		alert.setMessage(" Under Weight Detected for given input "+metrics.getValue());
		alert.setTimeStamp(metrics.getTimeStamp());
		SensorEmulatorMetricsApplication.getMongoDataStore().save(alert);
	}

	public void setbaseWeight(Integer baseWeight) {
		this.baseWeight = baseWeight;
	}
	public void setMetrics(Metrics metrics) {
		this.metrics = metrics;
	}
	
}
