#!/bin/bash


yum update

sudo amazon-linux-extras install docker

sudo service docker start

sudo usermod -a -G docker ec2-user

docker info

docker build -t docktom .

docker images --filter reference=docktom

docker run -t -i -p 8080:8080 docktom

