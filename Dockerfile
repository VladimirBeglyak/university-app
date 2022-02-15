FROM adoptopenjdk:16-jre-hotspot

WORKDIR /app

COPY build/libs .

CMD ["java", "-jar", "university-app-0.0.1-SNAPSHOT-plain.jar"]
