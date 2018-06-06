FROM java:8u77-jre-alpine

# File Author / Maintainer
MAINTAINER Eric Rodriguez

ENV VERTICLE_FILE dc-jms-integrations-1.0-SNAPSHOT-fat.jar

# Set the location of the verticles
ENV VERTICLE_HOME /usr/verticles

# Copy your fat jar to the container
COPY target/$VERTICLE_FILE $VERTICLE_HOME/
COPY config-docker.json $VERTICLE_HOME/config.json

# Launch the verticle
WORKDIR $VERTICLE_HOME
ENTRYPOINT ["sh", "-c"]
CMD ["java -jar $VERTICLE_FILE -conf config.json -cluster"]   
