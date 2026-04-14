# ---- 构建阶段 ----
FROM node:20-alpine AS builder
WORKDIR /app

# 安装依赖
COPY package.json package-lock.json* ./
RUN npm ci --prefer-offline 2>/dev/null || npm install

# 拷贝源码并构建
COPY . .
RUN npm run build:prod

# ---- 运行阶段 ----
FROM nginx:alpine AS runner

# 拷贝构建产物到 Nginx 目录
COPY --from=builder /app/dist /usr/share/nginx/html

# Nginx SPA 配置（所有路由回退到 index.html）
RUN echo 'server { \
    listen 80; \
    root /usr/share/nginx/html; \
    index index.html; \
    location / { try_files $uri $uri/ /index.html; } \
    location ~* \.(js|css|png|jpg|jpeg|gif|ico|svg|woff|woff2)$ { expires 7d; } \
}' > /etc/nginx/conf.d/default.conf

EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]
