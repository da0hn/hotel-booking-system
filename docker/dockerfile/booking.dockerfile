FROM maven:3.9.4-amazoncorretto-21 as builder

WORKDIR /application

COPY ./ /application

RUN ls -la
RUN mvn clean package

FROM amazoncorretto:21

WORKDIR /service

COPY --from=builder /application/booking-service/target/*.jar /service/api.jar
ENV TZ=America/Cuiaba

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -Duser.region=BR -Duser.language=pt ${JAVA_OPTS} -jar /service/api.jar" ]
