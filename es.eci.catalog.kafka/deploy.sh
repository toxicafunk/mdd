#!/bin/bash
echo Building image...
sudo docker build -t vertxkafka .
sudo docker run --name vertxkafka_inst -i -t vertxkafka