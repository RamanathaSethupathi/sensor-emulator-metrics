# sensor-emulator-metrics
This application reads the given metrics data (includes weight and timestamp) and create alerts based on the category ( underweight or overweight)

This App contains below methods.

# 1. create 
method that accepts JSON object as input which contains timestamp and weight. This JSON value is read from sensor-emulator application. The JSON will looks like below:

{
  "timeStamp": "1458062848734", 
  "value": "153"
}

This method can be access by the URL: http://localhost:8080/Sensor/Metrics/create

# 2. readall
That displays all the Metrics data available in the system. Can be access by the below URL: http://localhost:8080/Sensor/Metrics/readall


# 3. readbytimerange 
That displays all the Metrics data between the given timestamps. Can be accessed by the URL : http://localhost:8080/Sensor/Metrics/readbytimerange/{startTimestamp}/{endTimeStamp}

We have two more Rest API to read the alerts.

# 1. readall 
That displays all the Alerts data available in the system. Can be access by the below URL: http://localhost:8080/Sensor/Metrics/readall

# 2. readbytimerange 
That displays all the Alerts data between the given timestamps. Can be accessed by the URL : http://localhost:8080/Sensor/Metrics/readbytimerange/{startTimestamp}/{endTimeStamp}

# How to build it?

mvn clean package

This creates a jar file in target directory 'Sensor-Emulator-App-0.0.1-SNAPSHOT.jar'

# How to run it?

java -jar -DbaseWeight=150 -DserverName=localhost -Dport=27017 Sensor-Emulator-App-0.0.1-SNAPSHOT.jar







