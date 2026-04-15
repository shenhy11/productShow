# 第一阶段：构建前端资源和打包依赖
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY pom.xml .
COPY ruoyi-admin/pom.xml ruoyi-admin/
COPY ruoyi-common/pom.xml ruoyi-common/
COPY ruoyi-framework/pom.xml ruoyi-framework/
COPY ruoyi-system/pom.xml ruoyi-system/
COPY ruoyi-generator/pom.xml ruoyi-generator/
COPY ruoyi-quartz/pom.xml ruoyi-quartz/
COPY zioncom-oss/pom.xml zioncom-oss/
COPY zioncom-product/pom.xml zioncom-product/
COPY zioncom-content/pom.xml zioncom-content/
COPY zioncom-support/pom.xml zioncom-support/
COPY zioncom-brand/pom.xml zioncom-brand/
# 拷贝配置文件
COPY maven-settings.xml .
# 拷贝所有源码
COPY . .
# 打包所有模块，使用阿里云加速源
RUN mvn -s maven-settings.xml clean package -Dmaven.test.skip=true

# 第二阶段：运行阶段
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=build /app/ruoyi-admin/target/ruoyi-admin.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.profiles.active=druid"]
