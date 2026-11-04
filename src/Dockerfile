FROM eclipse-temurin:26-jre
WORKDIR /app
COPY DocumentGenerator_new.jar .
COPY autoDocs.xml .
COPY db_positions ./src/db_positions
EXPOSE 63412
CMD ["java", "-jar", "DocumentGenerator_new.jar"]