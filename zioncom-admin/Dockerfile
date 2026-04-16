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
# 覆盖 nginx 默认配置，支持 History 模式并转发后台 /prod-api 请求
COPY zioncom-admin/nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
