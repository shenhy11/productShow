import os

# Config defining the front-end structure to generate
modules = [
    {
        "module": "product",
        "entities": [
            {"name": "category", "className": "ProductCategory", "comment": "产品分类", "fields": ["nameZh", "nameEn", "sortOrder", "status"]},
            {"name": "product", "className": "Product", "comment": "产品", "fields": ["model", "nameZh", "nameEn", "status", "sortOrder"]},
            {"name": "spec", "className": "ProductSpec", "comment": "产品规格", "fields": ["specGroup", "specKey", "specValue", "sortOrder"]}
        ]
    },
    {
        "module": "content",
        "entities": [
            {"name": "article", "className": "Article", "comment": "文章", "fields": ["type", "titleZh", "titleEn", "publishDate", "status", "viewCount"]}
        ]
    },
    {
        "module": "support",
        "entities": [
            {"name": "firmware", "className": "Firmware", "comment": "固件", "fields": ["version", "fileName", "releaseDate", "status"]},
            {"name": "document", "className": "Document", "comment": "文档", "fields": ["docType", "titleZh", "titleEn", "status"]},
            {"name": "faqCategory", "className": "FaqCategory", "comment": "FAQ分类", "fields": ["nameZh", "nameEn", "sortOrder", "status"]},
            {"name": "faq", "className": "Faq", "comment": "FAQ", "fields": ["questionZh", "questionEn", "sortOrder", "status"]},
            {"name": "techVideo", "className": "TechVideo", "comment": "技术视频", "fields": ["titleZh", "titleEn", "duration", "status"]},
            {"name": "webEmulator", "className": "WebEmulator", "comment": "Web模拟器", "fields": ["model", "titleZh", "titleEn", "status"]},
            {"name": "supportRequest", "className": "SupportRequest", "comment": "支持请求", "fields": ["name", "email", "productModel", "status"]}
        ]
    },
    {
        "module": "brand",
        "entities": [
            {"name": "milestone", "className": "Milestone", "comment": "里程碑", "fields": ["year", "titleZh", "titleEn", "status"]},
            {"name": "partner", "className": "WorldwidePartner", "comment": "渠道商", "fields": ["region", "name", "country", "status"]},
            {"name": "contact", "className": "ContactMessage", "comment": "联系消息", "fields": ["name", "email", "subject", "isRead"]}
        ]
    },
    {
        "module": "oss",
        "entities": [
            {"name": "file", "className": "OssFile", "comment": "文件管理", "fields": ["fileName", "category", "fileSize", "createTime"]}
        ]
    }
]

def to_snake_url(name):
    import re
    s1 = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', name)
    return re.sub('([a-z0-9])([A-Z])', r'\1_\2', s1).replace('_', '/').lower()

api_template = """import request from '@/utils/request'

// 查询{comment}列表
export function list{className}(query) {{
  return request({{
    url: '/api/{module}/{name}/list',
    method: 'get',
    params: query
  }})
}}

// 查询{comment}详细
export function get{className}(id) {{
  return request({{
    url: '/api/{module}/{name}/' + id,
    method: 'get'
  }})
}}

// 新增{comment}
export function add{className}(data) {{
  return request({{
    url: '/api/{module}/{name}',
    method: 'post',
    data: data
  }})
}}

// 修改{comment}
export function update{className}(data) {{
  return request({{
    url: '/api/{module}/{name}',
    method: 'put',
    data: data
  }})
}}

// 删除{comment}
export function del{className}(id) {{
  return request({{
    url: '/api/{module}/{name}/' + id,
    method: 'delete'
  }})
}}"""

vue_template = """<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch">
      <el-form-item label="关键字" prop="keyword">
        <el-input v-model="queryParams.keyword" placeholder="请输入关键字" clearable @keyup.enter="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['{module}:{name}:add']">新增</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="{name}List" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
      {columns}
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['{module}:{name}:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['{module}:{name}:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改{comment}对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="{name}Ref" :model="form" :rules="rules" label-width="100px">
        {form_items}
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="{className}">
import {{ list{className}, get{className}, del{className}, add{className}, update{className} }} from "@/api/{module}/{name}";

const {{ proxy }} = getCurrentInstance();

const {name}List = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({{
  form: {{}},
  queryParams: {{
    pageNum: 1,
    pageSize: 10
  }},
  rules: {{}}
}});

const {{ queryParams, form, rules }} = toRefs(data);

function getList() {{
  loading.value = true;
  list{className}(queryParams.value).then(res => {{
    {name}List.value = res.rows;
    total.value = res.total;
    loading.value = false;
  }});
}}

function cancel() {{
  open.value = false;
  reset();
}}

function reset() {{
  form.value = {{}};
  proxy.resetForm("{name}Ref");
}}

function handleQuery() {{
  queryParams.value.pageNum = 1;
  getList();
}}

function resetQuery() {{
  proxy.resetForm("queryRef");
  handleQuery();
}}

function handleAdd() {{
  reset();
  open.value = true;
  title.value = "添加{comment}";
}}

function handleUpdate(row) {{
  reset();
  const id = row.id;
  get{className}(id).then(response => {{
    form.value = response.data;
    open.value = true;
    title.value = "修改{comment}";
  }});
}}

function submitForm() {{
  proxy.$refs["{name}Ref"].validate(valid => {{
    if (valid) {{
      if (form.value.id != null) {{
        update{className}(form.value).then(res => {{
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        }});
      }} else {{
        add{className}(form.value).then(res => {{
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        }});
      }}
    }}
  }});
}}

function handleDelete(row) {{
  const ids = row.id;
  proxy.$modal.confirm('是否确认删除ID为"' + ids + '"的数据项？').then(function() {{
    return del{className}(ids);
  }}).then(() => {{
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }}).catch(() => {{}});
}}

getList();
</script>"""

base_dir = "d:/code/antigravity_project/productShow/zioncom-admin"

for m in modules:
    mod_name = m['module']
    api_dir = os.path.join(base_dir, "src/api", mod_name)
    views_dir = os.path.join(base_dir, "src/views", mod_name)
    
    os.makedirs(api_dir, exist_ok=True)
    os.makedirs(views_dir, exist_ok=True)
    
    for e in m['entities']:
        ename = e['name']
        cls = e['className']
        cmt = e['comment']
        
        # 写入 API.js
        api_content = api_template.format(module=mod_name, name=to_snake_url(cls), className=cls, comment=cmt)
        with open(os.path.join(api_dir, f"{ename}.js"), "w", encoding="utf-8") as f:
            f.write(api_content)
            
        # 生成 View .vue
        columns = ""
        form_items = ""
        for field in e['fields']:
            columns += f'      <el-table-column label="{field}" align="center" prop="{field}" />\n'
            form_items += f'        <el-form-item label="{field}" prop="{field}">\n          <el-input v-model="form.{field}" placeholder="请输入内容" />\n        </el-form-item>\n'
            
        vue_content = vue_template.format(
            module=mod_name, name=ename, className=cls, comment=cmt,
            columns=columns, form_items=form_items
        )
        
        e_dir = os.path.join(views_dir, ename)
        os.makedirs(e_dir, exist_ok=True)
        with open(os.path.join(e_dir, "index.vue"), "w", encoding="utf-8") as f:
            f.write(vue_content)

print("Vue3 frontend scaffolding complete!")
