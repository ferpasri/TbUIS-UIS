FROM maven:3.9.6-eclipse-temurin-11 AS builder

WORKDIR /app
COPY . /app

# Run Maven to package the WAR file
RUN mvn clean install -DskipTests

# Second stage: Deploy WAR to Tomcat
FROM tomcat:9.0.98-jdk11-corretto
WORKDIR /usr/local/tomcat/webapps

# Copy WAR file from Maven build
COPY --from=builder /app/target/*.war ./uis-web.war

# Expose Tomcat port
EXPOSE 8080

CMD ["catalina.sh", "run"]
