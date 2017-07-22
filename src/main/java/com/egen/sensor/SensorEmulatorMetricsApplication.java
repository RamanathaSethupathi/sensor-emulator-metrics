package com.egen.sensor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * Spring Boot Application that testes the REST API calls for Metrics and Alerts
 *
 */
@SpringBootApplication
public class SensorEmulatorMetricsApplication {

	public static Integer baseWeight;
	private static String serverName;
	private static Integer port;

	public static void main(String[] args) {
		baseWeight = Integer.parseInt(System.getProperty("baseWeight"));
		serverName = System.getProperty("serverName");
		port = Integer.parseInt(System.getProperty("port"));
		System.out.println("Given Base Weight " + baseWeight);
		SpringApplication.run(SensorEmulatorMetricsApplication.class, args);
	}

	/**
	 * Method that creates the datastore for Mongo DB and return the same
	 * instance
	 * 
	 * @return
	 */
	public static Datastore getMongoDataStore() {
		MongoClient mongoClient = new MongoClient(new ServerAddress(serverName, port));
		MongoDatabase db = mongoClient.getDatabase("SensorDB");
		Datastore datastore = new Morphia().createDatastore(mongoClient, db.getName());
		return datastore;
	}
}
