FROM eclipse-temurin
RUN mkdir /opt/app
COPY target/calculatorapp-0.0.1-SNAPSHOT.jar /opt/app/calculatorapp.jar
CMD ["java", "-jar", "/opt/app/calculatorapp.jar"]