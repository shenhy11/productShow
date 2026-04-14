# ---- 构建阶段 ----
FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /build

# 先拷贝 pom.xml 利用 Docker 缓存 Maven 依赖
COPY pom.xml .
COPY ruoyi-admin/pom.xml ruoyi-admin/
COPY ruoyi-common/pom.xml ruoyi-common/
COPY ruoyi-framework/pom.xml ruoyi-framework/
COPY ruoyi-generator/pom.xml ruoyi-generator/
COPY ruoyi-quartz/pom.xml ruoyi-quartz/
COPY ruoyi-system/pom.xml ruoyi-system/

# 先下载依赖（利用分层缓存）
RUN mvn dependency:go-offline -B 2>/dev/null || true

# 拷贝源码并构建
COPY . .
RUN mvn clean package -DskipTests -pl ruoyi-admin -am -B

# ---- 运行阶段 ----
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# 创建非 root 用户
RUN addgroup -S zioncom && adduser -S zioncom -G zioncom

# 拷贝构建产物
COPY --from=builder /build/ruoyi-admin/target/ruoyi-admin-*.jar app.jar

# 创建文件上传临时目录
RUN mkdir -p /tmp/zioncom/uploadPath && chown -R zioncom:zioncom /tmp/zioncom

USER zioncom
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-XX:MaxRAMPercentage=75.0", "-jar", "app.jar"]
