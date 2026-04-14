<template>
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
        <el-button type="primary" plain icon="Plus" @click="handleAdd" v-hasPermi="['support:faqCategory:add']">新增</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="faqCategoryList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="id" />
            <el-table-column label="nameZh" align="center" prop="nameZh" />
      <el-table-column label="nameEn" align="center" prop="nameEn" />
      <el-table-column label="sortOrder" align="center" prop="sortOrder" />
      <el-table-column label="status" align="center" prop="status" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['support:faqCategory:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['support:faqCategory:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" v-model:page="queryParams.pageNum" v-model:limit="queryParams.pageSize" @pagination="getList" />

    <!-- 添加或修改FAQ分类对话框 -->
    <el-dialog :title="title" v-model="open" width="600px" append-to-body>
      <el-form ref="faqCategoryRef" :model="form" :rules="rules" label-width="100px">
                <el-form-item label="nameZh" prop="nameZh">
          <el-input v-model="form.nameZh" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="nameEn" prop="nameEn">
          <el-input v-model="form.nameEn" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="sortOrder" prop="sortOrder">
          <el-input v-model="form.sortOrder" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="status" prop="status">
          <el-input v-model="form.status" placeholder="请输入内容" />
        </el-form-item>

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

<script setup name="FaqCategory">
import { listFaqCategory, getFaqCategory, delFaqCategory, addFaqCategory, updateFaqCategory } from "@/api/support/faqCategory";

const { proxy } = getCurrentInstance();

const faqCategoryList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10
  },
  rules: {}
});

const { queryParams, form, rules } = toRefs(data);

function getList() {
  loading.value = true;
  listFaqCategory(queryParams.value).then(res => {
    faqCategoryList.value = res.rows;
    total.value = res.total;
    loading.value = false;
  });
}

function cancel() {
  open.value = false;
  reset();
}

function reset() {
  form.value = {};
  proxy.resetForm("faqCategoryRef");
}

function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加FAQ分类";
}

function handleUpdate(row) {
  reset();
  const id = row.id;
  getFaqCategory(id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改FAQ分类";
  });
}

function submitForm() {
  proxy.$refs["faqCategoryRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateFaqCategory(form.value).then(res => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addFaqCategory(form.value).then(res => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

function handleDelete(row) {
  const ids = row.id;
  proxy.$modal.confirm('是否确认删除ID为"' + ids + '"的数据项？').then(function() {
    return delFaqCategory(ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

getList();
</script>