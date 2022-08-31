From openjdk:8-jdk-alpine  
ARG JAR_FILE=./AccountService-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENV DB_URL="jdbc:postgresql://13.232.12.61:5432/postgres"\
    BOOTSTRAP_SERVER=13.232.12.61:9092
ENTRYPOINT exec java -jar /app.jar