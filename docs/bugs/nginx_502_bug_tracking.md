# Nginx 502 Bug 追踪记录

> 记录时间：2026-04-15 23:53

---

## 📌 问题现象

- `http://8.166.113.25`（端口 80，经 Nginx）→ HTTP 502
- `http://8.166.113.25:3000`（端口 3000，直连 Nuxt）→ 之前也 502，但后来变成 200
- `curl http://localhost:3000/` 从服务器内部测试 → **HTTP 200 OK**（Nuxt 完全正常）
- `docker compose ps` → 所有容器均 `Up` 状态

**结论：Nuxt 本身正常运行，问题出在 Nginx → nuxt-app:3000 的容器内部网络通信**

---

## ✅ 已确认事项

| 服务 | 状态 |
|---|---|
| zioncom-nginx | Up，port 80 |
| zioncom-web (nuxt-app) | Up，port 3000，HTTP 200 |
| zioncom-api (spring-api) | Up，已显示"若依启动成功" |
| zioncom-mysql | Up |
| zioncom-redis | Up |
| zioncom-admin | Up，port 8081 |

---

## 🔍 已排查/已修复的 Bug（共 6 个）

| # | Bug | 修复 | Commit |
|---|---|---|---|
| 1 | `products/news` 页路由点击无响应（await阻塞SSR） | 全部改 `lazy: true` | `d51aa2b` |
| 2 | `useHttp` JSON.stringify ComputedRef循环引用500 | 改用字符串拼接key | `217958e` |
| 3 | Spring Boot Dockerfile `--spring.profiles.active=prod` 找不到配置 | 改为 `druid` | `b151731` |
| 4 | MySQL 8.0 `Public Key Retrieval is not allowed` | JDBC URL 加 `allowPublicKeyRetrieval=true` | `63afad1` |
| 5 | `nuxt.config.ts` proxy 用 `process.env`（build-time陷阱）→ 指向错误localhost | 改回硬编码 `spring-api:8080` | `ccde952` |
| 6 | Nginx DNS 缓存：容器重建后 IP 变化，Nginx 仍用旧 IP → 502 | 添加 `resolver 127.0.0.11`，改为变量 `proxy_pass` | `81f0454` |

---

## ❌ 当前未解决问题

**Nginx 仍然 502，即使 Nginx 配置已加动态 DNS resolver 并 restart**

---

## 🧪 明天继续排查步骤（按优先级）

### Step 1：确认 Nginx 加载了新配置
```bash
docker compose exec nginx nginx -T 2>&1 | grep -A5 "resolver\|nuxt\|proxy_pass"
```
预期看到 `resolver 127.0.0.11` 和 `set $nuxt_upstream`，如果没有说明配置没加载进去。

### Step 2：从 Nginx 容器内部直接 wget 测试
```bash
# 进入 nginx 容器测试是否能到达 nuxt-app
docker compose exec nginx sh -c "wget -qO- http://nuxt-app:3000/ | head -5"
```
- 如果成功 → Nginx 配置有问题
- 如果失败 → Docker 网络隔离问题（nginx 和 nuxt-app 不在同一网络）

### Step 3：确认两个容器在同一 Docker 网络
```bash
docker network ls
docker network inspect productshow_zioncom-net
```
预期看到 `zioncom-nginx` 和 `zioncom-web` 都在 `productshow_zioncom-net` 网络里。

### Step 4：查看 Nginx 错误日志
```bash
docker compose exec nginx cat /var/log/nginx/error.log | tail -20
# 或者
docker compose logs nginx --tail=30
```

### Step 5：如果网络隔离 → 全量重启
```bash
docker compose down
docker compose up -d
```

---

## 📋 关键代码状态（最新已修复版本）

### nginx/conf.d/default.conf（已修改，commit 81f0454）
```nginx
server {
    listen 80;
    server_name _;
    resolver 127.0.0.11 valid=10s ipv6=off;

    location / {
        set $nuxt_upstream nuxt-app:3000;
        proxy_pass http://$nuxt_upstream;
        ...
    }
    location /api/ {
        set $api_upstream spring-api:8080;
        proxy_pass http://$api_upstream/;
        ...
    }
}
```

### zioncom-nuxt/nuxt.config.ts（已修改，commit ccde952）
- 移除了 ISR 规则（避免 ISR cache poison）
- proxy 改为硬编码 `http://spring-api:8080/`
- 产品/新闻/支持页改为 `ssr: false`

---

## 💡 可能的根本原因假设（待验证）

1. **Nginx 配置没有被加载**：volume mount 路径问题，nginx 读了旧配置
2. **Docker 网络隔离**：`docker compose up -d --build nuxt-app` 后 nuxt-app 网络连接丢失
3. **Nginx resolver 行为**：变量 proxy_pass 在某些 Nginx alpine 版本行为不同
4. **nuxt-app 端口仅监听 Docker 内部，端口映射有问题**

---

## 🔧 明天快速恢复操作

```bash
# 1. 拉取最新代码（所有修复都在 feature-login 分支）
cd ~/productShow
git pull origin feature-login

# 2. 全量重建（最保险）
docker compose down
docker compose up -d --build

# 3. 等 5-10 分钟所有服务启动完成后测试
curl http://localhost:3000/
curl http://localhost:80/
```
