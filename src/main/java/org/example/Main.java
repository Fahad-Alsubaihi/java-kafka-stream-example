package org.example;



public class Main {
    public static void main(String[] args) {
        Producer prod=new Producer("names1");
        prod.start();
        Stream str=new Stream("names1","names2");
        str.start();



    }
}