#!/bin/bash

sbt clean compile
docker-compose up -d db && docker ps
sbt docker
java -jar animal-shelter-api/target/scala-2.13/animal-shelter-api.jar