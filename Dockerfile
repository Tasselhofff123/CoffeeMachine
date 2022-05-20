FROM ubuntu:22.10
ENV TZ=Europe/Moscow
ADD /target/coffeemachine-0.0.1-SNAPSHOT.jar coffee.jar
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
RUN apt-get update
RUN apt -y install postgresql
RUN apt -y install openjdk-11-jdk
RUN apt install sudo
RUN /etc/init.d/postgresql start
ENTRYPOINT ["bash"]