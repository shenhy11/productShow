import os

modules = [
    {
        "module": "zioncom-product", "package": "com.ruoyi.product",
        "entities": [
            {"name": "ProductCategory", "table": "zc_product_category", "comment": "产品分类", "fields": [("id", "Long"), ("parentId", "Long"), ("nameZh", "String"), ("nameEn", "String"), ("icon", "String"), ("sortOrder", "Integer"), ("status", "Integer")]},
            {"name": "Product", "table": "zc_product", "comment": "产品", "fields": [("id", "Long"), ("categoryId", "Long"), ("model", "String"), ("nameZh", "String"), ("nameEn", "String"), ("summaryZh", "String"), ("summaryEn", "String"), ("detailZh", "String"), ("detailEn", "String"), ("coverImage", "String"), ("bannerImages", "String"), ("status", "String"), ("seoTitleZh", "String"), ("seoTitleEn", "String"), ("seoDescZh", "String"), ("seoDescEn", "String"), ("sortOrder", "Integer")]},
            {"name": "ProductSpec", "table": "zc_product_spec", "comment": "产品规格", "fields": [("id", "Long"), ("productId", "Long"), ("specGroup", "String"), ("specKey", "String"), ("specValue", "String"), ("sortOrder", "Integer")]}
        ]
    },
    {
        "module": "zioncom-content", "package": "com.ruoyi.content",
        "entities": [
            {"name": "Article", "table": "zc_article", "comment": "文章", "fields": [("id", "Long"), ("type", "String"), ("subType", "String"), ("titleZh", "String"), ("titleEn", "String"), ("coverImage", "String"), ("summaryZh", "String"), ("summaryEn", "String"), ("contentZh", "String"), ("contentEn", "String"), ("publishDate", "Date"), ("status", "String"), ("viewCount", "Integer"), ("seoTitleZh", "String"), ("seoTitleEn", "String"), ("seoDescZh", "String"), ("seoDescEn", "String"), ("author", "String"), ("sortOrder", "Integer")]}
        ]
    },
    {
        "module": "zioncom-support", "package": "com.ruoyi.support",
        "entities": [
            {"name": "Firmware", "table": "zc_firmware", "comment": "固件", "fields": [("id", "Long"), ("productId", "Long"), ("version", "String"), ("fileUrl", "String"), ("fileName", "String"), ("fileSize", "Long"), ("releaseNoteZh", "String"), ("releaseNoteEn", "String"), ("releaseDate", "Date"), ("status", "Integer")]},
            {"name": "Document", "table": "zc_document", "comment": "文档", "fields": [("id", "Long"), ("productId", "Long"), ("docType", "String"), ("titleZh", "String"), ("titleEn", "String"), ("fileUrl", "String"), ("fileName", "String"), ("fileSize", "Long"), ("sortOrder", "Integer"), ("status", "Integer")]},
            {"name": "FaqCategory", "table": "zc_faq_category", "comment": "FAQ分类", "fields": [("id", "Long"), ("nameZh", "String"), ("nameEn", "String"), ("sortOrder", "Integer"), ("status", "Integer")]},
            {"name": "Faq", "table": "zc_faq", "comment": "FAQ", "fields": [("id", "Long"), ("categoryId", "Long"), ("questionZh", "String"), ("questionEn", "String"), ("answerZh", "String"), ("answerEn", "String"), ("sortOrder", "Integer"), ("status", "Integer")]},
            {"name": "TechVideo", "table": "zc_tech_video", "comment": "技术视频", "fields": [("id", "Long"), ("productId", "Long"), ("titleZh", "String"), ("titleEn", "String"), ("descZh", "String"), ("descEn", "String"), ("videoUrl", "String"), ("coverImage", "String"), ("duration", "String"), ("sortOrder", "Integer"), ("status", "Integer")]},
            {"name": "WebEmulator", "table": "zc_web_emulator", "comment": "Web模拟器", "fields": [("id", "Long"), ("productId", "Long"), ("model", "String"), ("titleZh", "String"), ("titleEn", "String"), ("url", "String"), ("sortOrder", "Integer"), ("status", "Integer")]},
            {"name": "SupportRequest", "table": "zc_support_request", "comment": "支持请求", "fields": [("id", "Long"), ("name", "String"), ("email", "String"), ("productModel", "String"), ("subject", "String"), ("message", "String"), ("status", "String"), ("remark", "String")]}
        ]
    },
    {
        "module": "zioncom-brand", "package": "com.ruoyi.brand",
        "entities": [
            {"name": "Milestone", "table": "zc_milestone", "comment": "里程碑", "fields": [("id", "Long"), ("year", "Integer"), ("titleZh", "String"), ("titleEn", "String"), ("descZh", "String"), ("descEn", "String"), ("imageUrl", "String"), ("sortOrder", "Integer"), ("status", "Integer")]},
            {"name": "WorldwidePartner", "table": "zc_worldwide_partner", "comment": "渠道商", "fields": [("id", "Long"), ("region", "String"), ("country", "String"), ("name", "String"), ("website", "String"), ("email", "String"), ("phone", "String"), ("address", "String"), ("longitude", "Double"), ("latitude", "Double"), ("logoUrl", "String"), ("sortOrder", "Integer"), ("status", "Integer")]},
            {"name": "ContactMessage", "table": "zc_contact_message", "comment": "联系消息", "fields": [("id", "Long"), ("name", "String"), ("email", "String"), ("company", "String"), ("phone", "String"), ("subject", "String"), ("message", "String"), ("isRead", "Integer")]}
        ]
    }
]

def to_snake(name):
    import re
    s1 = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', name)
    return re.sub('([a-z0-9])([A-Z])', r'\1_\2', s1).lower()

base_dir = "d:/code/antigravity_project/productShow/zioncom-server"

for m in modules:
    mod_dir = os.path.join(base_dir, m['module'])
    src_dir = os.path.join(mod_dir, "src/main/java", m['package'].replace(".", "/"))
    xml_dir = os.path.join(mod_dir, "src/main/resources/mapper", m['module'].replace("zioncom-", ""))
    
    os.makedirs(src_dir + "/domain", exist_ok=True)
    os.makedirs(src_dir + "/mapper", exist_ok=True)
    os.makedirs(src_dir + "/service/impl", exist_ok=True)
    os.makedirs(src_dir + "/controller", exist_ok=True)
    os.makedirs(xml_dir, exist_ok=True)
    
    # 1. pom.xml
    pom = f"""<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent><groupId>com.ruoyi</groupId><artifactId>ruoyi</artifactId><version>3.9.2</version></parent>
    <artifactId>{m['module']}</artifactId>
    <dependencies><dependency><groupId>com.ruoyi</groupId><artifactId>ruoyi-common</artifactId></dependency></dependencies>
</project>"""
    with open(os.path.join(mod_dir, "pom.xml"), "w", encoding="utf-8") as f: f.write(pom)
    
    for e in m['entities']:
        ename = e['name']
        ename_low = ename[0].lower() + ename[1:]
        pkg = m['package']
        
        # Domain
        domain = f"package {pkg}.domain;\nimport com.ruoyi.common.core.domain.BaseEntity;\nimport java.util.Date;\npublic class {ename} extends BaseEntity {{\n"
        for fn, ft in e['fields']:
            domain += f"    private {ft} {fn};\n"
            domain += f"    public {ft} get{fn[0].upper()+fn[1:]}() {{ return {fn}; }}\n"
            domain += f"    public void set{fn[0].upper()+fn[1:]}({ft} {fn}) {{ this.{fn} = {fn}; }}\n"
        domain += "}\n"
        with open(os.path.join(src_dir, f"domain/{ename}.java"), "w", encoding="utf-8") as f: f.write(domain)
        
        # Mapper
        mapper = f"package {pkg}.mapper;\nimport java.util.List;\nimport {pkg}.domain.{ename};\npublic interface {ename}Mapper {{\n    List<{ename}> selectList({ename} param);\n    {ename} selectById(Long id);\n    int insert({ename} param);\n    int update({ename} param);\n    int deleteById(Long id);\n}}\n"
        with open(os.path.join(src_dir, f"mapper/{ename}Mapper.java"), "w", encoding="utf-8") as f: f.write(mapper)
        
        # Service
        srv = f"package {pkg}.service;\nimport java.util.List;\nimport {pkg}.domain.{ename};\npublic interface I{ename}Service {{\n    List<{ename}> selectList({ename} param);\n    {ename} selectById(Long id);\n    int insert({ename} param);\n    int update({ename} param);\n    int deleteById(Long id);\n}}\n"
        with open(os.path.join(src_dir, f"service/I{ename}Service.java"), "w", encoding="utf-8") as f: f.write(srv)
        
        # ServiceImpl
        srv_impl = f"package {pkg}.service.impl;\nimport java.util.List;\nimport org.springframework.stereotype.Service;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport {pkg}.domain.{ename};\nimport {pkg}.mapper.{ename}Mapper;\nimport {pkg}.service.I{ename}Service;\n@Service\npublic class {ename}ServiceImpl implements I{ename}Service {{\n    @Autowired private {ename}Mapper mapper;\n    public List<{ename}> selectList({ename} param) {{ return mapper.selectList(param); }}\n    public {ename} selectById(Long id) {{ return mapper.selectById(id); }}\n    public int insert({ename} param) {{ return mapper.insert(param); }}\n    public int update({ename} param) {{ return mapper.update(param); }}\n    public int deleteById(Long id) {{ return mapper.deleteById(id); }}\n}}\n"
        with open(os.path.join(src_dir, f"service/impl/{ename}ServiceImpl.java"), "w", encoding="utf-8") as f: f.write(srv_impl)
        
        # Controller
        api_path = m['module'].replace("zioncom-", "") + "/" + to_snake(ename).replace("_", "/")
        ctrl = f"package {pkg}.controller;\nimport java.util.List;\nimport org.springframework.beans.factory.annotation.Autowired;\nimport org.springframework.web.bind.annotation.*;\nimport com.ruoyi.common.core.controller.BaseController;\nimport com.ruoyi.common.core.domain.AjaxResult;\nimport com.ruoyi.common.core.page.TableDataInfo;\nimport {pkg}.domain.{ename};\nimport {pkg}.service.I{ename}Service;\n@RestController\n@RequestMapping(\"/api/{api_path}\")\npublic class {ename}Controller extends BaseController {{\n    @Autowired private I{ename}Service service;\n    @GetMapping(\"/list\")\n    public TableDataInfo list({ename} param) {{ startPage(); List<{ename}> list = service.selectList(param); return getDataTable(list); }}\n    @GetMapping(\"/{{id}}\")\n    public AjaxResult getInfo(@PathVariable(\"id\") Long id) {{ return AjaxResult.success(service.selectById(id)); }}\n    @PostMapping\n    public AjaxResult add(@RequestBody {ename} param) {{ return toAjax(service.insert(param)); }}\n    @PutMapping\n    public AjaxResult edit(@RequestBody {ename} param) {{ return toAjax(service.update(param)); }}\n    @DeleteMapping(\"/{{id}}\")\n    public AjaxResult remove(@PathVariable(\"id\") Long id) {{ return toAjax(service.deleteById(id)); }}\n}}\n"
        with open(os.path.join(src_dir, f"controller/{ename}Controller.java"), "w", encoding="utf-8") as f: f.write(ctrl)
        
        # XML
        xml = f"<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\" \"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">\n<mapper namespace=\"{pkg}.mapper.{ename}Mapper\">\n"
        xml += f"  <select id=\"selectList\" resultType=\"{pkg}.domain.{ename}\">SELECT * FROM {e['table']}</select>\n"
        xml += f"  <select id=\"selectById\" resultType=\"{pkg}.domain.{ename}\">SELECT * FROM {e['table']} WHERE id = #{{id}}</select>\n"
        
        # Insert
        xml += f"  <insert id=\"insert\" useGeneratedKeys=\"true\" keyProperty=\"id\">INSERT INTO {e['table']} ("
        fields = [f for f in e['fields'] if f[0] != 'id']
        cols = [to_snake(f[0]) for f in fields]
        xml += ",".join(cols) + ") VALUES ("
        vars = [f"#{{{f[0]}}}" for f in fields]
        xml += ",".join(vars) + ")</insert>\n"
        
        # Update
        xml += f"  <update id=\"update\">UPDATE {e['table']} SET "
        sets = [f"{to_snake(f[0])}=#{{{f[0]}}}" for f in fields]
        xml += ",".join(sets) + " WHERE id = #{id}</update>\n"
        
        # Delete
        xml += f"  <delete id=\"deleteById\">DELETE FROM {e['table']} WHERE id = #{{id}}</delete>\n"
        xml += "</mapper>"
        
        with open(os.path.join(xml_dir, f"{ename}Mapper.xml"), "w", encoding="utf-8") as f: f.write(xml)

print("Scaffolding complete!")
