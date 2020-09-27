package main.java.com.example.bookstore.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CouponExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CouponExample() {
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

        public Criteria andCouponIdIsNull() {
            addCriterion("coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(Integer value) {
            addCriterion("coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(Integer value) {
            addCriterion("coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(Integer value) {
            addCriterion("coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(Integer value) {
            addCriterion("coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<Integer> values) {
            addCriterion("coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<Integer> values) {
            addCriterion("coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponImgIsNull() {
            addCriterion("coupon_img is null");
            return (Criteria) this;
        }

        public Criteria andCouponImgIsNotNull() {
            addCriterion("coupon_img is not null");
            return (Criteria) this;
        }

        public Criteria andCouponImgEqualTo(String value) {
            addCriterion("coupon_img =", value, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgNotEqualTo(String value) {
            addCriterion("coupon_img <>", value, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgGreaterThan(String value) {
            addCriterion("coupon_img >", value, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_img >=", value, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgLessThan(String value) {
            addCriterion("coupon_img <", value, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgLessThanOrEqualTo(String value) {
            addCriterion("coupon_img <=", value, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgLike(String value) {
            addCriterion("coupon_img like", value, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgNotLike(String value) {
            addCriterion("coupon_img not like", value, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgIn(List<String> values) {
            addCriterion("coupon_img in", values, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgNotIn(List<String> values) {
            addCriterion("coupon_img not in", values, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgBetween(String value1, String value2) {
            addCriterion("coupon_img between", value1, value2, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponImgNotBetween(String value1, String value2) {
            addCriterion("coupon_img not between", value1, value2, "couponImg");
            return (Criteria) this;
        }

        public Criteria andCouponUseIsNull() {
            addCriterion("coupon_use is null");
            return (Criteria) this;
        }

        public Criteria andCouponUseIsNotNull() {
            addCriterion("coupon_use is not null");
            return (Criteria) this;
        }

        public Criteria andCouponUseEqualTo(Integer value) {
            addCriterion("coupon_use =", value, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponUseNotEqualTo(Integer value) {
            addCriterion("coupon_use <>", value, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponUseGreaterThan(Integer value) {
            addCriterion("coupon_use >", value, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponUseGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_use >=", value, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponUseLessThan(Integer value) {
            addCriterion("coupon_use <", value, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponUseLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_use <=", value, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponUseIn(List<Integer> values) {
            addCriterion("coupon_use in", values, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponUseNotIn(List<Integer> values) {
            addCriterion("coupon_use not in", values, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponUseBetween(Integer value1, Integer value2) {
            addCriterion("coupon_use between", value1, value2, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponUseNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_use not between", value1, value2, "couponUse");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyIsNull() {
            addCriterion("coupon_money is null");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyIsNotNull() {
            addCriterion("coupon_money is not null");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyEqualTo(Integer value) {
            addCriterion("coupon_money =", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyNotEqualTo(Integer value) {
            addCriterion("coupon_money <>", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyGreaterThan(Integer value) {
            addCriterion("coupon_money >", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_money >=", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyLessThan(Integer value) {
            addCriterion("coupon_money <", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_money <=", value, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyIn(List<Integer> values) {
            addCriterion("coupon_money in", values, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyNotIn(List<Integer> values) {
            addCriterion("coupon_money not in", values, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyBetween(Integer value1, Integer value2) {
            addCriterion("coupon_money between", value1, value2, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponMoneyNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_money not between", value1, value2, "couponMoney");
            return (Criteria) this;
        }

        public Criteria andCouponVaildIsNull() {
            addCriterion("coupon_vaild is null");
            return (Criteria) this;
        }

        public Criteria andCouponVaildIsNotNull() {
            addCriterion("coupon_vaild is not null");
            return (Criteria) this;
        }

        public Criteria andCouponVaildEqualTo(Date value) {
            addCriterion("coupon_vaild =", value, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponVaildNotEqualTo(Date value) {
            addCriterion("coupon_vaild <>", value, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponVaildGreaterThan(Date value) {
            addCriterion("coupon_vaild >", value, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponVaildGreaterThanOrEqualTo(Date value) {
            addCriterion("coupon_vaild >=", value, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponVaildLessThan(Date value) {
            addCriterion("coupon_vaild <", value, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponVaildLessThanOrEqualTo(Date value) {
            addCriterion("coupon_vaild <=", value, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponVaildIn(List<Date> values) {
            addCriterion("coupon_vaild in", values, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponVaildNotIn(List<Date> values) {
            addCriterion("coupon_vaild not in", values, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponVaildBetween(Date value1, Date value2) {
            addCriterion("coupon_vaild between", value1, value2, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponVaildNotBetween(Date value1, Date value2) {
            addCriterion("coupon_vaild not between", value1, value2, "couponVaild");
            return (Criteria) this;
        }

        public Criteria andCouponTimeIsNull() {
            addCriterion("coupon_time is null");
            return (Criteria) this;
        }

        public Criteria andCouponTimeIsNotNull() {
            addCriterion("coupon_time is not null");
            return (Criteria) this;
        }

        public Criteria andCouponTimeEqualTo(Date value) {
            addCriterion("coupon_time =", value, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponTimeNotEqualTo(Date value) {
            addCriterion("coupon_time <>", value, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponTimeGreaterThan(Date value) {
            addCriterion("coupon_time >", value, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("coupon_time >=", value, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponTimeLessThan(Date value) {
            addCriterion("coupon_time <", value, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponTimeLessThanOrEqualTo(Date value) {
            addCriterion("coupon_time <=", value, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponTimeIn(List<Date> values) {
            addCriterion("coupon_time in", values, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponTimeNotIn(List<Date> values) {
            addCriterion("coupon_time not in", values, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponTimeBetween(Date value1, Date value2) {
            addCriterion("coupon_time between", value1, value2, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponTimeNotBetween(Date value1, Date value2) {
            addCriterion("coupon_time not between", value1, value2, "couponTime");
            return (Criteria) this;
        }

        public Criteria andCouponNumIsNull() {
            addCriterion("coupon_num is null");
            return (Criteria) this;
        }

        public Criteria andCouponNumIsNotNull() {
            addCriterion("coupon_num is not null");
            return (Criteria) this;
        }

        public Criteria andCouponNumEqualTo(Integer value) {
            addCriterion("coupon_num =", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumNotEqualTo(Integer value) {
            addCriterion("coupon_num <>", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumGreaterThan(Integer value) {
            addCriterion("coupon_num >", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_num >=", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumLessThan(Integer value) {
            addCriterion("coupon_num <", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_num <=", value, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumIn(List<Integer> values) {
            addCriterion("coupon_num in", values, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumNotIn(List<Integer> values) {
            addCriterion("coupon_num not in", values, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumBetween(Integer value1, Integer value2) {
            addCriterion("coupon_num between", value1, value2, "couponNum");
            return (Criteria) this;
        }

        public Criteria andCouponNumNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_num not between", value1, value2, "couponNum");
            return (Criteria) this;
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

        public Criteria andCouponGetnumIsNull() {
            addCriterion("coupon_getnum is null");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumIsNotNull() {
            addCriterion("coupon_getnum is not null");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumEqualTo(Integer value) {
            addCriterion("coupon_getnum =", value, "couponGetnum");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumNotEqualTo(Integer value) {
            addCriterion("coupon_getnum <>", value, "couponGetnum");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumGreaterThan(Integer value) {
            addCriterion("coupon_getnum >", value, "couponGetnum");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_getnum >=", value, "couponGetnum");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumLessThan(Integer value) {
            addCriterion("coupon_getnum <", value, "couponGetnum");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_getnum <=", value, "couponGetnum");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumIn(List<Integer> values) {
            addCriterion("coupon_getnum in", values, "couponGetnum");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumNotIn(List<Integer> values) {
            addCriterion("coupon_getnum not in", values, "couponGetnum");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumBetween(Integer value1, Integer value2) {
            addCriterion("coupon_getnum between", value1, value2, "couponGetnum");
            return (Criteria) this;
        }

        public Criteria andCouponGetnumNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_getnum not between", value1, value2, "couponGetnum");
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