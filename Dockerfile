FROM openjdk:11
ENV POSTGRES_DB="coffee_machine", POSTGRES_USER="postgres", POSTGRES_PASSWORD="cyfqgthjr202020"
EXPOSE 5432
ADD /target/coffeemachine-0.0.1-SNAPSHOT.jar coffee.jar
ENTRYPOINT ["java","-jar","coffee.jar"]