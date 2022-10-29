FROM openjdk:11-jdk

RUN mkdir -p /home/to-do-list-app

COPY . /home/to-do-list-app

WORKDIR /home/to-do-list-app

EXPOSE 8400

CMD ["./gradlew", "clean", "bootRun"]