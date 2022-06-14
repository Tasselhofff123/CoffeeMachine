FROM openjdk:11
ADD target/coffeemachine-0.0.1-SNAPSHOT.jar coffee.jar
ADD docker/docker-compose/app/wait-for-it.sh wait-for-it.sh
RUN chmod +x wait-for-it.sh
ENTRYPOINT ["bash", "./wait-for-it.sh", "postgres:5432", "--timeout=300", "--", "java", "-jar", "coffee.jar"]