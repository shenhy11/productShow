package com.ruoyi.support.domain;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;
public class Faq extends BaseEntity {
    private Long id;
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    private Long categoryId;
    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }
    private String questionZh;
    public String getQuestionZh() { return questionZh; }
    public void setQuestionZh(String questionZh) { this.questionZh = questionZh; }
    private String questionEn;
    public String getQuestionEn() { return questionEn; }
    public void setQuestionEn(String questionEn) { this.questionEn = questionEn; }
    private String answerZh;
    public String getAnswerZh() { return answerZh; }
    public void setAnswerZh(String answerZh) { this.answerZh = answerZh; }
    private String answerEn;
    public String getAnswerEn() { return answerEn; }
    public void setAnswerEn(String answerEn) { this.answerEn = answerEn; }
    private Integer sortOrder;
    public Integer getSortOrder() { return sortOrder; }
    public void setSortOrder(Integer sortOrder) { this.sortOrder = sortOrder; }
    private Integer status;
    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }
}
