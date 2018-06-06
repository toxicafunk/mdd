#!/bin/bash
echo Building image...
sudo docker build -t vertxgraphql .
sudo docker run --name vertxgraphql_inst -i -t vertxgraphql
