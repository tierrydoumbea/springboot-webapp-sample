FROM anapsix/alpine-java 

# Maintainer 
EXPOSE 8080
LABEL maintainer="yannickparkerS@gmail.com"
COPY build/libs/*.jar /gradle/arthur.jar
CMD ["java", "-jar","/gradle/arthur.jar"]
