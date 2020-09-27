package main.java.com.example.bookstore.pojo;

import java.util.ArrayList;
import java.util.List;

public class BusinessExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public BusinessExample() {
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

        public Criteria andBusinessIdIsNull() {
            addCriterion("business_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNotNull() {
            addCriterion("business_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdEqualTo(String value) {
            addCriterion("business_id =", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotEqualTo(String value) {
            addCriterion("business_id <>", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThan(String value) {
            addCriterion("business_id >", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_id >=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThan(String value) {
            addCriterion("business_id <", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThanOrEqualTo(String value) {
            addCriterion("business_id <=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLike(String value) {
            addCriterion("business_id like", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotLike(String value) {
            addCriterion("business_id not like", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIn(List<String> values) {
            addCriterion("business_id in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotIn(List<String> values) {
            addCriterion("business_id not in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdBetween(String value1, String value2) {
            addCriterion("business_id between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotBetween(String value1, String value2) {
            addCriterion("business_id not between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessPassIsNull() {
            addCriterion("business_pass is null");
            return (Criteria) this;
        }

        public Criteria andBusinessPassIsNotNull() {
            addCriterion("business_pass is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessPassEqualTo(String value) {
            addCriterion("business_pass =", value, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassNotEqualTo(String value) {
            addCriterion("business_pass <>", value, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassGreaterThan(String value) {
            addCriterion("business_pass >", value, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassGreaterThanOrEqualTo(String value) {
            addCriterion("business_pass >=", value, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassLessThan(String value) {
            addCriterion("business_pass <", value, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassLessThanOrEqualTo(String value) {
            addCriterion("business_pass <=", value, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassLike(String value) {
            addCriterion("business_pass like", value, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassNotLike(String value) {
            addCriterion("business_pass not like", value, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassIn(List<String> values) {
            addCriterion("business_pass in", values, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassNotIn(List<String> values) {
            addCriterion("business_pass not in", values, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassBetween(String value1, String value2) {
            addCriterion("business_pass between", value1, value2, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessPassNotBetween(String value1, String value2) {
            addCriterion("business_pass not between", value1, value2, "businessPass");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNull() {
            addCriterion("business_name is null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIsNotNull() {
            addCriterion("business_name is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessNameEqualTo(String value) {
            addCriterion("business_name =", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotEqualTo(String value) {
            addCriterion("business_name <>", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThan(String value) {
            addCriterion("business_name >", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameGreaterThanOrEqualTo(String value) {
            addCriterion("business_name >=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThan(String value) {
            addCriterion("business_name <", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLessThanOrEqualTo(String value) {
            addCriterion("business_name <=", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameLike(String value) {
            addCriterion("business_name like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotLike(String value) {
            addCriterion("business_name not like", value, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameIn(List<String> values) {
            addCriterion("business_name in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotIn(List<String> values) {
            addCriterion("business_name not in", values, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameBetween(String value1, String value2) {
            addCriterion("business_name between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessNameNotBetween(String value1, String value2) {
            addCriterion("business_name not between", value1, value2, "businessName");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneIsNull() {
            addCriterion("business_phone is null");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneIsNotNull() {
            addCriterion("business_phone is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneEqualTo(String value) {
            addCriterion("business_phone =", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneNotEqualTo(String value) {
            addCriterion("business_phone <>", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneGreaterThan(String value) {
            addCriterion("business_phone >", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("business_phone >=", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneLessThan(String value) {
            addCriterion("business_phone <", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneLessThanOrEqualTo(String value) {
            addCriterion("business_phone <=", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneLike(String value) {
            addCriterion("business_phone like", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneNotLike(String value) {
            addCriterion("business_phone not like", value, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneIn(List<String> values) {
            addCriterion("business_phone in", values, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneNotIn(List<String> values) {
            addCriterion("business_phone not in", values, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneBetween(String value1, String value2) {
            addCriterion("business_phone between", value1, value2, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessPhoneNotBetween(String value1, String value2) {
            addCriterion("business_phone not between", value1, value2, "businessPhone");
            return (Criteria) this;
        }

        public Criteria andBusinessImgIsNull() {
            addCriterion("business_img is null");
            return (Criteria) this;
        }

        public Criteria andBusinessImgIsNotNull() {
            addCriterion("business_img is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessImgEqualTo(String value) {
            addCriterion("business_img =", value, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgNotEqualTo(String value) {
            addCriterion("business_img <>", value, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgGreaterThan(String value) {
            addCriterion("business_img >", value, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgGreaterThanOrEqualTo(String value) {
            addCriterion("business_img >=", value, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgLessThan(String value) {
            addCriterion("business_img <", value, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgLessThanOrEqualTo(String value) {
            addCriterion("business_img <=", value, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgLike(String value) {
            addCriterion("business_img like", value, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgNotLike(String value) {
            addCriterion("business_img not like", value, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgIn(List<String> values) {
            addCriterion("business_img in", values, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgNotIn(List<String> values) {
            addCriterion("business_img not in", values, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgBetween(String value1, String value2) {
            addCriterion("business_img between", value1, value2, "businessImg");
            return (Criteria) this;
        }

        public Criteria andBusinessImgNotBetween(String value1, String value2) {
            addCriterion("business_img not between", value1, value2, "businessImg");
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