# java-kafka-stream-example

This is a basic example for stream processing in Kafka. The project demonstrates how to read names from a Kafka topic (`names1`), transform them to uppercase, and save the results to another Kafka topic (`names2`).

## Pre-run

Before running the application, make sure to perform the following steps:

Create the Kafka topic `names2` with the specified schema. Use the following Avro schema:

   ```
   {
     "fields": [
       {
         "name": "name",
         "type": "string"
       }
     ],
     "name": "Customer",
     "type": "record"
   }
   ```
## run the main class
