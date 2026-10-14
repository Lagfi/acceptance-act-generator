FROM eclipse-temurin:26-jre
WORKDIR /app
COPY DocumentGenerator.jar .
COPY autoDocs.fodt .
EXPOSE 63412
CMD ["java", "-jar", "DocumentGenerator.jar"]