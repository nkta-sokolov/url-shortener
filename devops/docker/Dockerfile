FROM amazoncorretto:17

ARG SERVICE_NAME
ARG JAR_FILE
RUN mkdir -p /opt/"$SERVICE_NAME"
WORKDIR /opt/"$SERVICE_NAME"

COPY "$JAR_FILE" ./"$SERVICE_NAME".jar

CMD exec java "${JAVA_OPTS}" -jar ./"${SERVICE_NAME}".jar
