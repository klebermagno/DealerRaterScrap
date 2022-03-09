FROM openjdk:8-jdk
COPY target/scrap-1.0-SNAPSHOT-executable.jar scrap-1.0-SNAPSHOT-executable.jar
CMD ["sleep","30"]
ENTRYPOINT ["java","-Xmx4096m" ,"-jar","/scrap-1.0-SNAPSHOT-executable.jar"]