FROM eclipse-temurin:26-jre
WORKDIR /app
COPY FioDoc.jar .
COPY autoDocs.fodt .
EXPOSE 63412
CMD ["java", "-jar", "FioDoc.jar"]