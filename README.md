## Small Review

It is about rating system of a product by starts and calculation of average. 

Things that I use here are Java and Kafka Fundamentals

- Dependencies that are mainly used: 
  - Spring for Apache Kafka
  - Spring Boot DevTools
  - Spring Web

- Version used:
  - Java 17 
  - Spring Boot 3.2.5
  - Based on Gradle - Groovy
  - Packaging with jar
# Instructions on how to run : 

- Run RatingProjectOrderApplication.java & RatingProjectSampleApplication.java 
- Well you should have kafka installed on your system
- To check messages in console open two tab and place this commands
- kafka-console-consumer.sh --bootstrap-server localhost:9092 --property print.key=true --topic t-product-order
- kafka-console-consumer.sh --bootstrap-server localhost:9092 --property print.key=true --topic t-product-order-rating
- If you want to post messages manually use postman 
- OtherWise uncomment in RatingProjectOrderApplication.java code and run it.



# Updates(Extensions) may come up on this project.