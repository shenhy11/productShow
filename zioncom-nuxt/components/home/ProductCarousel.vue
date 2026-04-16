<template>
  <div class="product-carousel-container" @mouseenter="pause" @mouseleave="resume">
    <div class="carousel-track" ref="trackRef">
      <div 
        class="carousel-slide" 
        v-for="(slide, index) in slides" 
        :key="index"
      >
        <div class="slide-content">
          <div class="slide-text">
            <h2 class="slide-title">{{ locale === 'zh' ? slide.titleZh : slide.titleEn }}</h2>
            <p class="slide-subtitle">{{ locale === 'zh' ? slide.subtitleZh : slide.subtitleEn }}</p>
            <NuxtLink :to="localePath(`/products/${slide.id}`)" class="btn-primary">
              {{ $t('home.learnMore') }}
            </NuxtLink>
          </div>
          <div class="slide-image">
            <img :src="slide.image" :alt="slide.titleEn" />
          </div>
        </div>
      </div>
    </div>
    
    <!-- 分页小圆点 -->
    <div class="carousel-indicators">
      <span 
        v-for="(slide, index) in slides" 
        :key="'dot-'+index"
        class="indicator-dot"
        :class="{ active: currentSlide === index }"
        @click="goToSlide(index)"
      ></span>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'

const { locale } = useI18n()
const localePath = useLocalePath()

const trackRef = ref(null)
const currentSlide = ref(0)
let timer = null

// 根据爬虫提取的核心热门产品数据伪造
const slides = ref([
  {
    id: 201,
    titleEn: 'AX3000 Wi-Fi 6 Router',
    titleZh: 'AX3000 Wi-Fi 6 旗舰路由',
    subtitleEn: 'Unlock the real power of your network with next-gen speed.',
    subtitleZh: '次世代网络速率，释放无限潜能。',
    image: 'https://picsum.photos/800/600?random=ax3000'
  },
  {
    id: 202,
    titleEn: 'AC1200 Dual-Band Gigabit',
    titleZh: 'AC1200 全千兆双频路由',
    subtitleEn: 'Stable and seamless connectivity for your entire home.',
    subtitleZh: '全屋无缝覆盖，稳如磐石的连接体验。',
    image: 'https://picsum.photos/800/600?random=ac1200'
  },
  {
    id: 203,
    titleEn: 'X6100UA Wi-Fi 6 Adapter',
    titleZh: 'X6100UA Wi-Fi 6 无线网卡',
    subtitleEn: 'Upgrade your PC to Wi-Fi 6 instantly.',
    subtitleZh: '一键即插即用，让老电脑秒升 Wi-Fi 6。',
    image: 'https://picsum.photos/800/600?random=x6100ua'
  }
])

const nextSlide = () => {
  currentSlide.value = (currentSlide.value + 1) % slides.value.length
  scrollToCurrent()
}

const goToSlide = (index) => {
  currentSlide.value = index
  scrollToCurrent()
}

const scrollToCurrent = () => {
  if (trackRef.value) {
    const width = trackRef.value.clientWidth
    trackRef.value.scrollTo({
      left: width * currentSlide.value,
      behavior: 'smooth'
    })
  }
}

const handleScroll = () => {
  if (!trackRef.value) return
  const width = trackRef.value.clientWidth
  const scrollLeft = trackRef.value.scrollLeft
  // Only update currentSlide if scrolling was user-driven (snapped to a new slide)
  const newIndex = Math.round(scrollLeft / width)
  if (newIndex !== currentSlide.value) {
    currentSlide.value = newIndex
  }
}

const resume = () => {
  timer = setInterval(nextSlide, 5000)
}

const pause = () => {
  if (timer) clearInterval(timer)
}

onMounted(() => {
  resume()
  if (trackRef.value) {
    trackRef.value.addEventListener('scroll', handleScroll, { passive: true })
  }
})

onBeforeUnmount(() => {
  pause()
  if (trackRef.value) {
    trackRef.value.removeEventListener('scroll', handleScroll)
  }
})
</script>

<style scoped>
.product-carousel-container {
  width: 100%;
  max-width: var(--container-max-width, 1400px);
  margin: 0 auto 60px;
  position: relative;
  border-radius: 16px;
  overflow: hidden;
  box-shadow: 0 20px 40px rgba(0,0,0,0.08); /* 突出层级 */
  background: linear-gradient(135deg, #1e293b, #0f172a);
}

.carousel-track {
  display: flex;
  overflow-x: auto;
  scroll-snap-type: x mandatory;
  scroll-behavior: smooth; /* CSS 内置顺滑滚动 */
  -ms-overflow-style: none; /* IE and Edge */
  scrollbar-width: none; /* Firefox */
}

.carousel-track::-webkit-scrollbar {
  display: none; /* Chrome, Safari */
}

.carousel-slide {
  scroll-snap-align: start;
  flex: 0 0 100%;
  width: 100%;
  min-height: 480px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.slide-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  padding: 40px 80px;
  gap: 40px;
}

.slide-text {
  flex: 1;
  color: #fff;
}

.slide-title {
  font-size: 3rem;
  font-weight: 700;
  margin-bottom: 20px;
  line-height: 1.2;
  background: linear-gradient(to right, #ffffff, #94a3b8);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.slide-subtitle {
  font-size: 1.25rem;
  color: #cbd5e1;
  margin-bottom: 30px;
  line-height: 1.6;
}

.slide-image {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
}

.slide-image img {
  max-width: 100%;
  max-height: 400px;
  object-fit: contain;
  /* 悬浮微动画 */
  animation: floatImg 6s ease-in-out infinite;
  filter: drop-shadow(0 20px 30px rgba(0,0,0,0.4));
}

@keyframes floatImg {
  0% { transform: translateY(0); }
  50% { transform: translateY(-15px); }
  100% { transform: translateY(0); }
}

.carousel-indicators {
  position: absolute;
  bottom: 24px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  gap: 12px;
  z-index: 10;
}

.indicator-dot {
  width: 12px;
  height: 12px;
  border-radius: 50%;
  background: rgba(255, 255, 255, 0.3);
  cursor: pointer;
  transition: all 0.3s ease;
}

.indicator-dot.active {
  background: var(--color-primary);
  width: 24px;
  border-radius: 6px;
}

@media screen and (max-width: 900px) {
  .slide-content {
    flex-direction: column;
    padding: 40px 20px;
    text-align: center;
  }
  .slide-title {
    font-size: 2rem;
  }
  .slide-image img {
    max-height: 250px;
  }
}
</style>
