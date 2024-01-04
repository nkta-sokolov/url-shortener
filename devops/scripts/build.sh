#!/bin/bash

function build() {
  JAR_FILE=$1
  SERVICE_NAME=$2

  docker build -f ./devops/docker/Dockerfile \
    --build-arg JAR_FILE="${JAR_FILE}" \
    --build-arg SERVICE_NAME="${SERVICE_NAME}" \
    -t "${SERVICE_NAME}":latest .
}

cd ../..

echo "Building JAR files"
mvn clean package -DskipTests

echo "Building Docker images"
build ./shorten-service/target/shorten-service.jar shorten-service
build ./redirection-service/target/redirection-service.jar redirection-service
build ./analytics-service/target/analytics-service.jar analytics-service
build ./admin/target/admin.jar admin