<template>
  <div class="contact-page container">
    <div class="page-header">
      <h1>Contact Us</h1>
      <p>We'd love to hear from you. Please fill out this form or use the contact information below.</p>
    </div>
    
    <div class="contact-layout">
      <div class="contact-info">
        <h2>Headquarters</h2>
        <p><strong>Address:</strong><br>Building A, Zioncom Technology Park,<br>Shenzhen, Guangdong, China</p>
        <p><strong>Phone:</strong><br>+86 755 1234 5678</p>
        <p><strong>Email:</strong><br>info@zioncom.net</p>
      </div>
      
      <div class="contact-form-container">
        <h2>Send us a Message</h2>
        <form @submit.prevent="submitForm" class="contact-form">
          <div class="form-group">
            <label>Name <span class="required">*</span></label>
            <input type="text" v-model="form.name" required placeholder="Your Name" />
          </div>
          
          <div class="form-group">
            <label>Email <span class="required">*</span></label>
            <input type="email" v-model="form.email" required placeholder="Your Email address" />
          </div>
          
          <div class="form-group">
            <label>Subject</label>
            <input type="text" v-model="form.subject" placeholder="Subject of your inquiry" />
          </div>
          
          <div class="form-group">
            <label>Message <span class="required">*</span></label>
            <textarea v-model="form.message" required rows="5" placeholder="How can we help you?"></textarea>
          </div>
          
          <button type="submit" class="submit-btn" :disabled="submitting">
            {{ submitting ? 'Sending...' : 'Send Message' }}
          </button>
          
          <div v-if="successMsg" class="success-msg">{{ successMsg }}</div>
        </form>
      </div>
    </div>
  </div>
</template>

<script setup>
const { request } = useHttp()

const form = reactive({
  name: '',
  email: '',
  subject: '',
  message: ''
})

const submitting = ref(false)
const successMsg = ref('')

async function submitForm() {
  submitting.value = true
  successMsg.value = ''
  
  try {
    // 实际提交到后端 brand 模块的接口
    const { data } = await request('/api/brand/contact', {
      method: 'POST',
      body: form
    })
    
    successMsg.value = 'Your message has been sent successfully. We will get back to you soon.'
    form.name = ''
    form.email = ''
    form.subject = ''
    form.message = ''
  } catch (error) {
    alert('Failed to send message. Please try again later.')
  } finally {
    submitting.value = false
  }
}

useHead({ title: 'Contact Us' })
</script>

<style scoped>
.contact-page {
  padding: 40px 15px;
  max-width: 1000px;
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
.page-header p {
  color: #666;
  font-size: 1.1rem;
}
.contact-layout {
  display: flex;
  gap: 60px;
}
.contact-info {
  flex: 1;
  background: #f9f9f9;
  padding: 40px;
  border-radius: 8px;
}
.contact-info h2 {
  margin-top: 0;
  color: var(--color-primary);
  margin-bottom: 30px;
}
.contact-info p {
  margin-bottom: 25px;
  line-height: 1.6;
  color: #444;
}
.contact-form-container {
  flex: 2;
}
.contact-form-container h2 {
  margin-top: 0;
  margin-bottom: 30px;
  color: #333;
}
.form-group {
  margin-bottom: 20px;
}
.form-group label {
  display: block;
  margin-bottom: 8px;
  font-weight: 500;
  color: #333;
}
.required {
  color: red;
}
.form-group input,
.form-group textarea {
  width: 100%;
  padding: 12px 15px;
  border: 1px solid #ddd;
  border-radius: 4px;
  font-family: inherit;
  font-size: 1rem;
  box-sizing: border-box;
}
.form-group input:focus,
.form-group textarea:focus {
  border-color: var(--color-primary);
  outline: none;
}
.submit-btn {
  padding: 12px 30px;
  background: var(--color-primary);
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 1.1rem;
  cursor: pointer;
  transition: background 0.3s;
}
.submit-btn:hover:not(:disabled) {
  background: var(--color-secondary);
}
.submit-btn:disabled {
  background: #ccc;
  cursor: not-allowed;
}
.success-msg {
  margin-top: 20px;
  padding: 15px;
  background: #d4edda;
  color: #155724;
  border-radius: 4px;
}

@media screen and (max-width: 767px) {
  .contact-layout {
    flex-direction: column;
  }
}
</style>
