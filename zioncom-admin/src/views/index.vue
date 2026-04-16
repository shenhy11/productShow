<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <h2>欢迎使用 Zioncom 企业管理系统后台</h2>
        <p>在此管理面板中，您可以管理公司的多语言业务数据：包括产品目录、新闻中心、售后支持、下载专区等内容。</p>
        <p>请在左侧菜单浏览所需的运营功能模块并进行常规维护。</p>
      </el-col>
    </el-row>
    <el-divider />
  </div>
</template>

<script setup name="Index">
import { ref } from 'vue';
const version = ref('1.0.0');
</script>

<style scoped lang="scss">
.home {
  padding: 40px;
  h2 {
    font-weight: 500;
  }
  p {
    font-size: 15px;
    color: #666;
    line-height: 1.8;
  }
}
</style>
