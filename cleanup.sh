#/bin/bash
docker container prune
docker image prune
gradle clean build docker
