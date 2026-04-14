# 第一阶段：构建打包
FROM node:20-alpine AS builder
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build:prod

# 第二阶段：Nginx 部署静态文件
FROM nginx:alpine
COPY --from=builder /app/dist /usr/share/nginx/html
# 使用默认配置或自定义 conf（可以在docker-compose里挂载）
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
