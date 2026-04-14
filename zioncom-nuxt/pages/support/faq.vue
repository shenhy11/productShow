<template>
  <div class="faq-page container">
    <div class="page-header">
      <h1>Frequently Asked Questions</h1>
      <p>Find answers to common questions about our products and services.</p>
    </div>
    
    <div v-if="pending" class="loading">Loading FAQs...</div>
    <div v-else-if="groupedFaqs.length === 0" class="no-data">No FAQs available.</div>
    <div v-else class="faq-layout">
      <!-- 侧边栏分类 -->
      <aside class="faq-sidebar">
        <ul>
          <li 
            v-for="group in groupedFaqs" 
            :key="'nav-'+group.categoryId"
            :class="{ active: activeCategory === group.categoryId }"
            @click="activeCategory = group.categoryId"
          >
            {{ locale === 'zh' ? group.categoryNameZh : group.categoryNameEn }}
          </li>
        </ul>
      </aside>
      
      <!-- FAQ 内容区 -->
      <div class="faq-content">
        <div v-for="group in groupedFaqs" :key="'content-'+group.categoryId" v-show="activeCategory === group.categoryId">
          <h2>{{ locale === 'zh' ? group.categoryNameZh : group.categoryNameEn }}</h2>
          <div class="faq-items">
            <div class="faq-item" v-for="faq in group.items" :key="'faq-'+faq.id">
              <div class="faq-question" @click="toggleFaq(faq.id)">
                <span>{{ locale === 'zh' ? faq.questionZh : faq.questionEn }}</span>
                <i class="icon-toggle" :class="{ open: openId === faq.id }">+</i>
              </div>
              <div class="faq-answer" v-show="openId === faq.id">
                <div v-html="locale === 'zh' ? faq.answerZh : faq.answerEn"></div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
const { locale } = useI18n()
const { request } = useHttp()

const activeCategory = ref(null)
const openId = ref(null)

// 并发请求分类和FAQ记录
const [{ data: catRes }, { data: faqRes, pending }] = await Promise.all([
  request('/api/support/faqCategory/list'),
  request('/api/support/faq/list')
])

const groupedFaqs = computed(() => {
  const categories = catRes.value?.data || [
    { id: 1, nameZh: '路由设置', nameEn: 'Router Setup' },
    { id: 2, nameZh: '固件升级', nameEn: 'Firmware Upgrade' }
  ]
  const faqs = faqRes.value?.rows || [
    { id: 101, categoryId: 1, questionZh: '如何登录路由器后台？', questionEn: 'How to login to router?', answerZh: '<p>浏览器输入 192.168.0.1</p>', answerEn: '<p>Enter 192.168.0.1</p>' },
    { id: 102, categoryId: 2, questionZh: '升级失败怎么办？', questionEn: 'Upgrade failed?', answerZh: '<p>重启并重试</p>', answerEn: '<p>Reboot and try again</p>' }
  ]
  
  const map = {}
  categories.forEach(c => {
    map[c.id] = { categoryId: c.id, categoryNameZh: c.nameZh, categoryNameEn: c.nameEn, items: [] }
  })
  
  faqs.forEach(f => {
    if (map[f.categoryId]) {
      map[f.categoryId].items.push(f)
    }
  })
  
  // 只返回有问答的分类
  const result = Object.values(map).filter(g => g.items.length > 0)
  
  if (result.length > 0 && !activeCategory.value) {
    activeCategory.value = result[0].categoryId
  }
  
  return result
})

function toggleFaq(id) {
  openId.value = openId.value === id ? null : id
}

useHead({ title: 'FAQ' })
</script>

<style scoped>
.faq-page {
  padding: 40px 15px;
}
.page-header {
  text-align: center;
  margin-bottom: 50px;
}
.page-header h1 {
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 15px;
}
.faq-layout {
  display: flex;
  gap: 40px;
  align-items: flex-start;
}
.faq-sidebar {
  width: 250px;
  flex-shrink: 0;
  background: #f9f9f9;
  border-radius: 8px;
  padding: 20px 0;
}
.faq-sidebar ul {
  list-style: none;
  padding: 0;
  margin: 0;
}
.faq-sidebar li {
  padding: 12px 20px;
  cursor: pointer;
  color: #555;
  border-left: 3px solid transparent;
  transition: all 0.3s;
}
.faq-sidebar li:hover {
  background: #eaeaea;
}
.faq-sidebar li.active {
  background: #fff;
  border-left-color: var(--color-primary);
  color: var(--color-primary);
  font-weight: bold;
  box-shadow: 0 2px 5px rgba(0,0,0,0.02);
}
.faq-content {
  flex: 1;
}
.faq-content h2 {
  margin-top: 0;
  color: var(--color-primary);
  margin-bottom: 30px;
  border-bottom: 2px solid #eaeaea;
  padding-bottom: 10px;
}
.faq-item {
  border: 1px solid #eaeaea;
  margin-bottom: 15px;
  border-radius: 4px;
}
.faq-question {
  padding: 18px 20px;
  background: #fdfdfd;
  cursor: pointer;
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: bold;
  font-size: 1.1rem;
  color: #333;
}
.faq-question:hover {
  background: #f5f5f5;
}
.icon-toggle {
  font-style: normal;
  font-size: 1.5rem;
  color: var(--color-primary);
  transition: transform 0.3s;
}
.icon-toggle.open {
  transform: rotate(45deg);
}
.faq-answer {
  padding: 20px;
  border-top: 1px solid #eaeaea;
  color: #555;
  line-height: 1.6;
}

@media screen and (max-width: 767px) {
  .faq-layout {
    flex-direction: column;
  }
  .faq-sidebar {
    width: 100%;
  }
}
</style>
