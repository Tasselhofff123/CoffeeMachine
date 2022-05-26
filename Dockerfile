FROM openjdk:11
ADD /target/coffeemachine-0.0.1-SNAPSHOT.jar coffee.jar
ENTRYPOINT ["java", "-jar", "coffee.jar"]