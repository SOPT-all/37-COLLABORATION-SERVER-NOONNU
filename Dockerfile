# Java 21로 변경
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Gradle wrapper와 설정 파일 복사
COPY gradlew ./
COPY gradle ./gradle
COPY build.gradle ./
COPY settings.gradle ./

RUN chmod +x gradlew

# 의존성 다운로드 (캐시 레이어)
RUN ./gradlew dependencies --no-daemon

# 소스 코드 복사 및 빌드
COPY src ./src
RUN ./gradlew clean build -x test --no-daemon

# JAR 파일 추출
RUN find /app/build/libs -name "*.jar" ! -name "*plain.jar" -exec cp {} /app/app.jar \;

# 실행 스테이지 (JRE 21)
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/app.jar ./app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]