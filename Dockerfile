FROM ubuntu:20.04

ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

COPY docker/coffee_DB.backup coffee_DB.backup
COPY docker/coffeeSetup.sh coffeeSetup.sh
ADD /target/coffeemachine-0.0.1-SNAPSHOT.jar coffee.jar

RUN apt-get update && apt install sudo postgresql -y openjdk-11-jdk -y

ENTRYPOINT ["bash", "coffeeSetup.sh"]