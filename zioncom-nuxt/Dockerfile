# ---- 构建阶段 ----
FROM node:20-alpine AS builder
WORKDIR /app

# 安装依赖（利用 Docker 层缓存）
COPY package.json package-lock.json* ./
RUN npm ci --prefer-offline

# 拷贝源码并构建
COPY . .
RUN npm run build

# ---- 运行阶段 ----
FROM node:20-alpine AS runner
WORKDIR /app

# 创建非 root 用户
RUN addgroup -S nuxt && adduser -S nuxt -G nuxt

# 拷贝构建产物（Nuxt output）
COPY --from=builder /app/.output .output
COPY --from=builder /app/public public

USER nuxt
EXPOSE 3000

ENV NODE_ENV=production
ENV NUXT_HOST=0.0.0.0
ENV NUXT_PORT=3000

CMD ["node", ".output/server/index.mjs"]
