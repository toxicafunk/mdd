#!/bin/bash
echo Building image...
sudo docker build -t vertxdecoder .
sudo docker run --name vertxdecoder_inst -i -t vertxdecoder