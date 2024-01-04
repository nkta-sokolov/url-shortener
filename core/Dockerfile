FROM amazoncorretto:17

ENV SERVICE_NAME=url-shortener
RUN mkdir -p /opt/"$SERVICE_NAME"
WORKDIR /opt/"$SERVICE_NAME"

COPY "$SERVICE_NAME"*jar ./"$SERVICE_NAME".jar

CMD exec java "${JAVA_OPTS}" -jar ./"$SERVICE_NAME".jar