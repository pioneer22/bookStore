package main.java.com.example.bookstore.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TopicExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TopicExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTopicIdIsNull() {
            addCriterion("topic_id is null");
            return (Criteria) this;
        }

        public Criteria andTopicIdIsNotNull() {
            addCriterion("topic_id is not null");
            return (Criteria) this;
        }

        public Criteria andTopicIdEqualTo(Integer value) {
            addCriterion("topic_id =", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotEqualTo(Integer value) {
            addCriterion("topic_id <>", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThan(Integer value) {
            addCriterion("topic_id >", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("topic_id >=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThan(Integer value) {
            addCriterion("topic_id <", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdLessThanOrEqualTo(Integer value) {
            addCriterion("topic_id <=", value, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdIn(List<Integer> values) {
            addCriterion("topic_id in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotIn(List<Integer> values) {
            addCriterion("topic_id not in", values, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdBetween(Integer value1, Integer value2) {
            addCriterion("topic_id between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicIdNotBetween(Integer value1, Integer value2) {
            addCriterion("topic_id not between", value1, value2, "topicId");
            return (Criteria) this;
        }

        public Criteria andTopicImgIsNull() {
            addCriterion("topic_img is null");
            return (Criteria) this;
        }

        public Criteria andTopicImgIsNotNull() {
            addCriterion("topic_img is not null");
            return (Criteria) this;
        }

        public Criteria andTopicImgEqualTo(String value) {
            addCriterion("topic_img =", value, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgNotEqualTo(String value) {
            addCriterion("topic_img <>", value, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgGreaterThan(String value) {
            addCriterion("topic_img >", value, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgGreaterThanOrEqualTo(String value) {
            addCriterion("topic_img >=", value, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgLessThan(String value) {
            addCriterion("topic_img <", value, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgLessThanOrEqualTo(String value) {
            addCriterion("topic_img <=", value, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgLike(String value) {
            addCriterion("topic_img like", value, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgNotLike(String value) {
            addCriterion("topic_img not like", value, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgIn(List<String> values) {
            addCriterion("topic_img in", values, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgNotIn(List<String> values) {
            addCriterion("topic_img not in", values, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgBetween(String value1, String value2) {
            addCriterion("topic_img between", value1, value2, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicImgNotBetween(String value1, String value2) {
            addCriterion("topic_img not between", value1, value2, "topicImg");
            return (Criteria) this;
        }

        public Criteria andTopicTimeIsNull() {
            addCriterion("topic_time is null");
            return (Criteria) this;
        }

        public Criteria andTopicTimeIsNotNull() {
            addCriterion("topic_time is not null");
            return (Criteria) this;
        }

        public Criteria andTopicTimeEqualTo(Date value) {
            addCriterion("topic_time =", value, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicTimeNotEqualTo(Date value) {
            addCriterion("topic_time <>", value, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicTimeGreaterThan(Date value) {
            addCriterion("topic_time >", value, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("topic_time >=", value, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicTimeLessThan(Date value) {
            addCriterion("topic_time <", value, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicTimeLessThanOrEqualTo(Date value) {
            addCriterion("topic_time <=", value, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicTimeIn(List<Date> values) {
            addCriterion("topic_time in", values, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicTimeNotIn(List<Date> values) {
            addCriterion("topic_time not in", values, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicTimeBetween(Date value1, Date value2) {
            addCriterion("topic_time between", value1, value2, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicTimeNotBetween(Date value1, Date value2) {
            addCriterion("topic_time not between", value1, value2, "topicTime");
            return (Criteria) this;
        }

        public Criteria andTopicNameIsNull() {
            addCriterion("topic_name is null");
            return (Criteria) this;
        }

        public Criteria andTopicNameIsNotNull() {
            addCriterion("topic_name is not null");
            return (Criteria) this;
        }

        public Criteria andTopicNameEqualTo(String value) {
            addCriterion("topic_name =", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotEqualTo(String value) {
            addCriterion("topic_name <>", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameGreaterThan(String value) {
            addCriterion("topic_name >", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameGreaterThanOrEqualTo(String value) {
            addCriterion("topic_name >=", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameLessThan(String value) {
            addCriterion("topic_name <", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameLessThanOrEqualTo(String value) {
            addCriterion("topic_name <=", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameLike(String value) {
            addCriterion("topic_name like", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotLike(String value) {
            addCriterion("topic_name not like", value, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameIn(List<String> values) {
            addCriterion("topic_name in", values, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotIn(List<String> values) {
            addCriterion("topic_name not in", values, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameBetween(String value1, String value2) {
            addCriterion("topic_name between", value1, value2, "topicName");
            return (Criteria) this;
        }

        public Criteria andTopicNameNotBetween(String value1, String value2) {
            addCriterion("topic_name not between", value1, value2, "topicName");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}