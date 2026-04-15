"""
深度爬取 zioncom.net 官网：首页 + 导航结构 + 产品 + 支持 + 关于
"""
from playwright.sync_api import sync_playwright
import os, json, time

OUT_DIR = "d:/code/antigravity_project/productShow/crawl_output"
os.makedirs(OUT_DIR, exist_ok=True)

def crawl_page(page, url, name, full_page=True):
    """访问页面并截图、提取内容"""
    try:
        page.goto(url, wait_until="networkidle", timeout=30000)
        page.wait_for_timeout(2000)
    except Exception as e:
        print(f"  ⚠ goto {url} 超时或失败: {e}")
        try:
            page.wait_for_timeout(3000)
        except:
            pass

    # 截图
    ss_path = os.path.join(OUT_DIR, f"{name}.png")
    try:
        page.screenshot(path=ss_path, full_page=full_page, timeout=15000)
        print(f"  📸 截图: {ss_path}")
    except:
        print(f"  ⚠ 截图失败: {name}")

    # 提取内容
    title = page.title()
    cur_url = page.url

    # 提取所有 <a> 链接
    links = page.evaluate("""() => {
        return Array.from(document.querySelectorAll('a[href]')).map(a => ({
            text: a.innerText.trim().substring(0, 100),
            href: a.href
        })).filter(l => l.text.length > 0)
    }""")

    # 提取导航菜单
    nav_items = page.evaluate("""() => {
        const navs = document.querySelectorAll('nav a, .nav a, .menu a, header a, .header a, .navbar a');
        return Array.from(navs).map(a => ({
            text: a.innerText.trim().substring(0, 80),
            href: a.href
        })).filter(l => l.text.length > 0)
    }""")

    # 提取页面主要文字内容
    text_content = page.evaluate("""() => {
        const main = document.querySelector('main') || document.querySelector('.main') || document.body;
        return main.innerText.substring(0, 5000);
    }""")

    return {
        "name": name,
        "url": cur_url,
        "title": title,
        "nav_items": nav_items[:30],
        "links_count": len(links),
        "top_links": links[:50],
        "text_preview": text_content[:3000]
    }

with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    context = browser.new_context(
        viewport={"width": 1440, "height": 900},
        user_agent="Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
    )
    page = context.new_page()

    results = []

    # 1. 首页
    print("🌐 抓取首页...")
    r = crawl_page(page, "https://www.zioncom.net/", "01_home")
    results.append(r)
    print(f"  Title: {r['title']}, Links: {r['links_count']}")

    # 从首页导航提取所有可访问的子页链接
    nav_links = r["nav_items"]
    print(f"  导航链接数: {len(nav_links)}")
    for n in nav_links:
        print(f"    - {n['text']}: {n['href']}")

    # 2. 逐个抓取导航链接
    visited = set()
    visited.add("https://www.zioncom.net/")
    
    # 收集所有导航文本和链接（去重），优先处理
    nav_queue = []
    for item in nav_links:
        href = item["href"]
        if href and href.startswith("http") and "zioncom.net" in href and href not in visited:
            nav_queue.append(item)
            visited.add(href)

    for i, item in enumerate(nav_queue[:20]):  # 最多抓 20 个页面
        slug = item["text"].replace(" ", "_").replace("/", "-")[:30] or f"page_{i}"
        print(f"\n🌐 抓取 [{slug}] → {item['href']}")
        r = crawl_page(page, item["href"], f"{i+2:02d}_{slug}")
        results.append(r)
        print(f"  Title: {r['title']}, Links: {r['links_count']}")

    # 3. 尝试直接访问已知常见路径
    common_paths = [
        "/products", "/product", "/about", "/about-us",
        "/support", "/contact", "/news", "/faq",
        "/download", "/partners", "/worldwide"
    ]
    for path in common_paths:
        full_url = f"https://www.zioncom.net{path}"
        if full_url not in visited:
            visited.add(full_url)
            slug = path.replace("/", "").replace("-", "_")
            print(f"\n🌐 探测 [{slug}] → {full_url}")
            r = crawl_page(page, full_url, f"try_{slug}")
            results.append(r)
            print(f"  Title: {r['title']}, URL地址: {r['url']}")

    browser.close()

    # 输出 JSON 汇总
    output_file = os.path.join(OUT_DIR, "crawl_results.json")
    with open(output_file, "w", encoding="utf-8") as f:
        json.dump(results, f, ensure_ascii=False, indent=2)

    print(f"\n✅ 爬取完成！结果保存至: {output_file}")
    print(f"共抓取 {len(results)} 个页面")
