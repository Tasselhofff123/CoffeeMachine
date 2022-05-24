FROM ubuntu:20.04
ENV TZ=Europe/Moscow
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone
COPY coffee_DB.backup coffee_DB.backup
RUN apt-get update
RUN apt install sudo
RUN apt -y install postgresql postgresql-contrib
RUN service postgresql restart
RUN sudo -u postgres createdb coffee_machine && sudo -u postgres pg_restore -d coffee_machine /coffee_DB.backup && sudo -u postgres psql -c "ALTER USER postgres PASSWORD 'cyfqgthjr202020';"
ADD /target/coffeemachine-0.0.1-SNAPSHOT.jar coffee.jar
RUN apt -y install openjdk-11-jdk
ENTRYPOINT ["bash"]