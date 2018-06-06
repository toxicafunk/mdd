#!/bin/bash
echo Building image...
sudo docker build -t vertxjms .
# sudo docker run --name vertxjms_inst --add-host=master.blaster:10.0.131.201 -i -t vertxjms
sudo docker run --name vertxjms_inst -i -t vertxjms