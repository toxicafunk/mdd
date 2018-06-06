#!/bin/bash

while getopts ":c" opt; do
  case $opt in
    c)
      echo Compiling maven...
	  mvn clean install
	  cd ../es.eci.catalog.jms
	  mvn clean install
	  cd ../es.eci.catalog.kafka
	  mvn clean install
	  cd ../es.eci.catalog.decoder
	  mvn clean install
	  cd ../es.eci.catalog.rest
      ;;
    \?)
      echo No compilation option...
      ;;
  esac
done



echo Stopping current instances...
sudo env "PATH=$PATH" docker-compose down

echo Building images...
sudo env "PATH=$PATH" docker-compose build --no-cache

echo Cleaning...
sudo docker rmi $(sudo docker images | grep "^<none>" | awk '{print $3}')
sudo docker volume rm $(sudo docker volume ls -qf dangling=true)

echo Starting containers...
sudo env "PATH=$PATH" docker-compose up -d --force-recreate
#sudo env "PATH=$PATH" docker-compose logs -f
# docker build -t esecicatalogrest_rest .