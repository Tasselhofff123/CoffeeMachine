FROM ubuntu:20.04
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY coffee_DB.backup coffee_DB.backup
COPY coffeeSetup.sh coffeeSetup.sh
RUN apt-get update
RUN apt install sudo
RUN apt -y install postgresql
RUN apt -y install openjdk-11-jdk
ADD /target/coffeemachine-0.0.1-SNAPSHOT.jar coffee.jar
ENTRYPOINT ["bash", "coffeeSetup.sh"]