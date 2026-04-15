"""
Zioncom 前台导航跳转测试脚本
测试首页各导航链接、新闻卡片点击、产品卡片点击
"""
from playwright.sync_api import sync_playwright

BASE_URL = "http://8.166.113.25:3000"
RESULTS = []

def log(step, url, success, note=""):
    entry = {"step": step, "url": url, "success": success, "note": note}
    RESULTS.append(entry)
    status = "✅" if success else "❌"
    print(f"{status} [{step}] url={url}  {note}")

def safe_goto(page, url, timeout=15000):
    try:
        page.goto(url, wait_until="domcontentloaded", timeout=timeout)
        page.wait_for_timeout(1500)
        return True
    except Exception as e:
        print(f"    goto failed: {e}")
        return False

def safe_click(page, selector, timeout=8000):
    try:
        el = page.locator(selector).first
        el.wait_for(state="visible", timeout=timeout)
        el.click()
        page.wait_for_timeout(2000)
        return True
    except Exception as e:
        print(f"    click failed ({selector}): {e}")
        return False

with sync_playwright() as p:
    browser = p.chromium.launch(headless=True)
    page = browser.new_page(viewport={"width": 1440, "height": 900})

    # ── Step 1: 首页 ──
    ok = safe_goto(page, BASE_URL)
    page.screenshot(path="zc_1_home.png", full_page=False)
    log("首页加载", page.url, ok)
    print(f"  页面 title: {page.title()}")

    # ── Step 2: 点击导航 Products ──
    ok = safe_click(page, "nav a:has-text('Products')")
    page.screenshot(path="zc_2_products.png", full_page=False)
    log("点击 Products 导航", page.url, "/products" in page.url)

    # ── Step 3: 点击导航 Support ──
    safe_goto(page, BASE_URL)
    ok = safe_click(page, "nav a:has-text('Support')")
    page.screenshot(path="zc_3_support.png", full_page=False)
    log("点击 Support 导航", page.url, "/support" in page.url)

    # ── Step 4: 点击导航 News ──
    safe_goto(page, BASE_URL)
    ok = safe_click(page, "nav a:has-text('News')")
    page.screenshot(path="zc_4_news.png", full_page=False)
    log("点击 News 导航", page.url, "/news" in page.url)

    # ── Step 5: 点击导航 About ──
    safe_goto(page, BASE_URL)
    ok = safe_click(page, "nav a:has-text('About')")
    page.screenshot(path="zc_5_about.png", full_page=False)
    log("点击 About 导航", page.url, "/about" in page.url)

    # ── Step 6: 首页新闻卡片点击 ──
    safe_goto(page, BASE_URL)
    # 先等新闻区域出现
    try:
        page.wait_for_selector(".news-grid, .latest-news", timeout=5000)
    except:
        pass
    ok = safe_click(page, "a.news-card")
    if not ok:
        ok = safe_click(page, ".news-grid a")
    page.screenshot(path="zc_6_news_detail.png", full_page=False)
    log("首页新闻卡片点击", page.url, "/news/" in page.url)

    # ── Step 7: 产品列表卡片点击 ──
    safe_goto(page, BASE_URL + "/products")
    try:
        page.wait_for_selector(".product-grid, .product-card", timeout=5000)
    except:
        pass
    ok = safe_click(page, "a.product-card")
    page.screenshot(path="zc_7_product_detail.png", full_page=False)
    log("产品列表卡片点击", page.url, "/products/" in page.url)

    # ── Step 8: Support 子页面跳转 ──
    safe_goto(page, BASE_URL + "/support")
    ok = safe_click(page, "a.support-card")
    page.screenshot(path="zc_8_support_sub.png", full_page=False)
    log("Support 子页面卡片点击", page.url, "/support/" in page.url)

    # ── Step 9: About Milestones 跳转 ──
    safe_goto(page, BASE_URL + "/about")
    ok = safe_click(page, "a:has-text('Milestones')")
    page.screenshot(path="zc_9_milestones.png", full_page=False)
    log("About → Milestones 跳转", page.url, "milestones" in page.url)

    browser.close()

print("\n" + "="*60)
print("📊 测试汇总")
print("="*60)
passed = sum(1 for r in RESULTS if r["success"])
total = len(RESULTS)
for r in RESULTS:
    status = "✅" if r["success"] else "❌"
    print(f"  {status} {r['step']:28s} → {r['url']}")
print(f"\n总计：{passed}/{total} 通过")
print("截图已保存在当前目录: zc_*.png")
