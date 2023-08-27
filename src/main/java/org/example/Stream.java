package org.example;


import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;



public class Stream implements Runnable {

    private Thread thread;
    String inputTopic;
    String outputTopic;

    public Stream(String inputTopic, String outputTopic) {
        this.inputTopic = inputTopic;
        this.outputTopic = outputTopic;
    }

    public void run() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "UpperCase-test");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        // Create a StreamsBuilder instance
        StreamsBuilder builder = new StreamsBuilder();

        // Define the processing logic
        KStream<String, String> inputStream = builder.stream(inputTopic);
        KStream<String, String> outputStream = inputStream.mapValues(value -> value.toUpperCase());
        outputStream.to(outputTopic);

        // Build the Kafka Streams application
        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        // Start the Kafka Streams application
        streams.start();

        // Add shutdown hook to gracefully close the streams application
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }


    public void start () {
        System.out.println("Starting Stream");
        if (thread == null) {
            thread = new Thread (this);
            thread.start ();
        }
    }


}
