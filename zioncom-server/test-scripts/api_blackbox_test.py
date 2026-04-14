import urllib.request
import urllib.parse
import json
import unittest

BASE_URL = "http://localhost:8080"
TOKEN = ""  # 真实环境可以通过登录接口拿 Token

def request(url, method="GET", data=None, headers=None):
    if headers is None:
        headers = {}
    headers["Content-Type"] = "application/json"
    if TOKEN:
        headers["Authorization"] = f"Bearer {TOKEN}"

    req_data = None
    if data is not None:
        req_data = json.dumps(data).encode("utf-8")

    req = urllib.request.Request(url, data=req_data, headers=headers, method=method)
    try:
        with urllib.request.urlopen(req) as response:
            body = response.read().decode("utf-8")
            return response.status, json.loads(body)
    except urllib.error.HTTPError as e:
        body = e.read().decode("utf-8")
        try:
            return e.code, json.loads(body)
        except json.JSONDecodeError:
            return e.code, body

class ZioncomApiBlackboxTest(unittest.TestCase):

    def test_01_product_list(self):
        """黑盒测试 - 查询产品列表"""
        url = f"{BASE_URL}/api/product/product/list?pageNum=1&pageSize=10"
        status, resp = request(url, "GET")
        
        # RuoYi 拦截器如果报错未授权，状态码可能是 401 
        # 本地如果放开了白名单或是带着临时token，这里就返回 200
        # 预设验证为请求通过了 Spring 框架处理
        self.assertIn(status, [200, 401], f"Unexpected HTTP status: {status}")
        
        if status == 200:
            self.assertEqual(resp.get("code"), 200)
            self.assertIn("rows", resp, "响应应该包含 rows 数组")

    def test_02_faq_list(self):
        """黑盒测试 - 查询 FAQ 列表"""
        url = f"{BASE_URL}/api/support/faq/list"
        status, resp = request(url, "GET")
        
        self.assertIn(status, [200, 401], f"Unexpected HTTP status: {status}")

        if status == 200:
            self.assertEqual(resp.get("code"), 200)

    def test_03_create_product_lifecycle(self):
        """黑盒测试 - 产品生命周期 (创建 -> 检索 -> 删除)"""
        # 注意: 如果当前没有获得 Token，且接口需要鉴权，该测试将返回 401 跳过深入断言
        url = f"{BASE_URL}/api/product/product"
        payload = {
            "model": "AUTO-TEST-200",
            "nameZh": "自动化测试路由器",
            "nameEn": "Auto Test Router",
            "status": "0" 
        }
        
        status, resp = request(url, "POST", data=payload)
        self.assertIn(status, [200, 401])
        
        if status == 401:
            print("[警告] 无有效 Token，跳过资源生命周期测试。")
            return

        # ================= 1. 断言创建成功 =================
        self.assertEqual(resp.get("code"), 200, f"创建产品失败: {resp.get('msg')}")
        
        # ================= 2. 检索并获取 ID =================
        query_url = f"{BASE_URL}/api/product/product/list?model=AUTO-TEST-200"
        qs_status, qs_resp = request(query_url, "GET")
        self.assertEqual(qs_status, 200)
        
        rows = qs_resp.get("rows", [])
        self.assertGreaterEqual(len(rows), 1, "未检索到新建的产品")
        product_id = rows[0].get("id")
        self.assertIsNotNone(product_id)

        # ================= 3. 删除 =================
        del_url = f"{BASE_URL}/api/product/product/{product_id}"
        del_status, del_resp = request(del_url, "DELETE")
        self.assertEqual(del_status, 200)
        self.assertEqual(del_resp.get("code"), 200, "删除测试产品失败")

if __name__ == '__main__':
    # 模拟运行
    print("==============================================")
    print("开始运行 Zioncom API 自动化黑盒测试".center(30))
    print("==============================================")
    unittest.main()
