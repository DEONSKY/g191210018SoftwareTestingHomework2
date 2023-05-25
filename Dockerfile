FROM openjdk
COPY src g191210018selenium
WORKDIR g191210018selenium
RUN mkdir -p lib
COPY lib lib
RUN mkdir -p bin
RUN javac -d build -cp .:lib/SeleniumDriver/selenium-server-4.9.0.jar:lib/SeleniumDriver/selenium-server-4.9.0/*.jar:lib/SeleniumDriver/selenium-server-4.9.0/lib/*.jar:lib/junit/junit-jupiter-api_5.9.2.jar:lib/junit/junit-jupiter-engine_5.9.2.jar:lib/junit/junit-jupiter-migrationsupport_5.9.2.jar:lib/junit/junit-jupiter-params_5.9.2.jar:lib/junit/junit-platform-commons_1.9.2.jar:lib/junit/junit-platform-engine_1.9.2.jar:lib/junit/junit-platform-launcher_1.9.2.jar:lib/junit/junit-platform-runner_1.9.2.jar:lib/junit/junit-platform-suite-api_1.9.2.jar:lib/junit/junit-platform-suite-engine_1.9.2.jar:lib/junit/junit-platform-suite-commons_1.9.2.jar:lib/junit/junit-vintage-engine_5.9.2.jar:lib/junit/org.opentest4j_1.2.0.jar:lib/junit/org.apiguardian.api_1.1.2.jar:lib/junit/org.junit_4.13.2.v20211018-1956.jar:lib/junit/org.hamcrest.core_1.3.0.v20180420-1519.jar -d bin ./g191210018selenium/*.java
COPY Manifest.txt bin
COPY lib bin/lib
WORKDIR bin
RUN jar -cvfm Program.jar Manifest.txt ./g191210018selenium/*.class
CMD ["java","-cp","lib/*:Program.jar","g191210018selenium.Program","remote"]