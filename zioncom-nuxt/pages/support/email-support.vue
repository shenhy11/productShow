<template>
  <div class="email-support-page container">
    <div class="page-header">
      <h1>Email Support</h1>
      <p>Submit a ticket and our technical team will assist you within 24 hours.</p>
    </div>
    
    <div class="form-wrapper">
      <form @submit.prevent="submitForm">
        <div class="form-row">
          <div class="form-group">
            <label>Name <span class="required">*</span></label>
            <input type="text" v-model="form.name" required placeholder="Your Name">
          </div>
          <div class="form-group">
            <label>Email <span class="required">*</span></label>
            <input type="email" v-model="form.email" required placeholder="Your Email Address">
          </div>
        </div>
        
        <div class="form-row">
          <div class="form-group">
            <label>Product Model <span class="required">*</span></label>
            <input type="text" v-model="form.productModel" required placeholder="e.g. WR1200, AX3000">
          </div>
        </div>
        
        <div class="form-group">
          <label>Description of Issue <span class="required">*</span></label>
          <textarea v-model="form.message" required rows="6" placeholder="Please describe the issue in detail..."></textarea>
        </div>
        
        <button type="submit" class="submit-btn" :disabled="submitting">
          {{ submitting ? 'Submitting...' : 'Submit Request' }}
        </button>
        
        <div v-if="errorMsg" class="error-msg mt-4">{{ errorMsg }}</div>
        <div v-if="successMsg" class="success-msg mt-4">{{ successMsg }}</div>
      </form>
    </div>
  </div>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'

// @ts-ignore
const { request } = useHttp()

const form = reactive({
  name: '',
  email: '',
  productModel: '',
  message: ''
})

const submitting = ref(false)
const successMsg = ref('')
const errorMsg = ref('')

const validateForm = (): boolean => {
  errorMsg.value = ''
  
  if (!form.name.trim()) {
    errorMsg.value = 'Name cannot be empty.'
    return false
  }
  
  const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/
  if (!form.email.trim() || !emailRegex.test(form.email)) {
    errorMsg.value = 'Please enter a valid email address.'
    return false
  }
  
  if (!form.productModel.trim()) {
    errorMsg.value = 'Product Model is required.'
    return false
  }
  
  if (!form.message.trim() || form.message.length < 10) {
    errorMsg.value = 'Message must be at least 10 characters long.'
    return false
  }
  
  return true
}

async function submitForm() {
  if (!validateForm()) return
  
  submitting.value = true
  successMsg.value = ''
  
  try {
    await request('/api/support/supportRequest', {
      method: 'POST',
      body: form
    })
    
    successMsg.value = 'Your support request has been submitted successfully! We will email you shortly.'
    form.name = ''
    form.email = ''
    form.productModel = ''
    form.message = ''
  } catch (error) {
    errorMsg.value = 'Failed to submit the server request. Please try again later.'
  } finally {
    submitting.value = false
  }
}

// @ts-ignore
useHead({ title: 'Email Support' })
</script>

<style scoped>
.email-support-page {
  padding: 40px 15px;
  max-width: 800px;
  margin: 0 auto;
}
.page-header {
  text-align: center;
  margin-bottom: 40px;
}
.page-header h1 {
  font-size: 2.5rem;
  color: #333;
  margin-bottom: 15px;
}
.form-wrapper {
  background: #fdfdfd;
  padding: 40px;
  border-radius: 8px;
  border: 1px solid #eaeaea;
  box-shadow: 0 5px 15px rgba(0,0,0,0.02);
}
.form-row {
  display: flex;
  gap: 20px;
}
.form-group {
  flex: 1;
  margin-bottom: 25px;
}
.form-group label {
  display: block;
  font-weight: 500;
  margin-bottom: 8px;
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
  width: 100%;
}
.submit-btn:hover:not(:disabled) {
  background: var(--color-secondary);
}
.submit-btn:disabled {
  background: #ccc;
  cursor: not-allowed;
}
.success-msg {
  padding: 15px;
  background: #d4edda;
  color: #155724;
  border-radius: 4px;
  text-align: center;
}
.error-msg {
  padding: 15px;
  background: #f8dbdf;
  color: #721c24;
  border: 1px solid #f5c6cb;
  border-radius: 4px;
  text-align: center;
}

@media screen and (max-width: 767px) {
  .form-row {
    flex-direction: column;
    gap: 0;
  }
}
</style>
