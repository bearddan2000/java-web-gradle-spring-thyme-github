FROM gradle:jdk11

WORKDIR /app

ADD --chown=gradle:gradle bin .

ENTRYPOINT ["gradle"]

CMD ["build", "bootRun"]

# CMD ["build", "test", "cucumber", "bootRun"]
