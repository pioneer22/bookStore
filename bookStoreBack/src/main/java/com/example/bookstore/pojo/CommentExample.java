package main.java.com.example.bookstore.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CommentExample() {
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

        public Criteria andComIdIsNull() {
            addCriterion("com_id is null");
            return (Criteria) this;
        }

        public Criteria andComIdIsNotNull() {
            addCriterion("com_id is not null");
            return (Criteria) this;
        }

        public Criteria andComIdEqualTo(Integer value) {
            addCriterion("com_id =", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotEqualTo(Integer value) {
            addCriterion("com_id <>", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdGreaterThan(Integer value) {
            addCriterion("com_id >", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("com_id >=", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdLessThan(Integer value) {
            addCriterion("com_id <", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdLessThanOrEqualTo(Integer value) {
            addCriterion("com_id <=", value, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdIn(List<Integer> values) {
            addCriterion("com_id in", values, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotIn(List<Integer> values) {
            addCriterion("com_id not in", values, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdBetween(Integer value1, Integer value2) {
            addCriterion("com_id between", value1, value2, "comId");
            return (Criteria) this;
        }

        public Criteria andComIdNotBetween(Integer value1, Integer value2) {
            addCriterion("com_id not between", value1, value2, "comId");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Integer value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Integer value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Integer value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Integer value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Integer value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Integer> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Integer> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Integer value1, Integer value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andComMessIsNull() {
            addCriterion("com_mess is null");
            return (Criteria) this;
        }

        public Criteria andComMessIsNotNull() {
            addCriterion("com_mess is not null");
            return (Criteria) this;
        }

        public Criteria andComMessEqualTo(String value) {
            addCriterion("com_mess =", value, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessNotEqualTo(String value) {
            addCriterion("com_mess <>", value, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessGreaterThan(String value) {
            addCriterion("com_mess >", value, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessGreaterThanOrEqualTo(String value) {
            addCriterion("com_mess >=", value, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessLessThan(String value) {
            addCriterion("com_mess <", value, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessLessThanOrEqualTo(String value) {
            addCriterion("com_mess <=", value, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessLike(String value) {
            addCriterion("com_mess like", value, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessNotLike(String value) {
            addCriterion("com_mess not like", value, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessIn(List<String> values) {
            addCriterion("com_mess in", values, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessNotIn(List<String> values) {
            addCriterion("com_mess not in", values, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessBetween(String value1, String value2) {
            addCriterion("com_mess between", value1, value2, "comMess");
            return (Criteria) this;
        }

        public Criteria andComMessNotBetween(String value1, String value2) {
            addCriterion("com_mess not between", value1, value2, "comMess");
            return (Criteria) this;
        }

        public Criteria andComTimeIsNull() {
            addCriterion("com_time is null");
            return (Criteria) this;
        }

        public Criteria andComTimeIsNotNull() {
            addCriterion("com_time is not null");
            return (Criteria) this;
        }

        public Criteria andComTimeEqualTo(Date value) {
            addCriterion("com_time =", value, "comTime");
            return (Criteria) this;
        }

        public Criteria andComTimeNotEqualTo(Date value) {
            addCriterion("com_time <>", value, "comTime");
            return (Criteria) this;
        }

        public Criteria andComTimeGreaterThan(Date value) {
            addCriterion("com_time >", value, "comTime");
            return (Criteria) this;
        }

        public Criteria andComTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("com_time >=", value, "comTime");
            return (Criteria) this;
        }

        public Criteria andComTimeLessThan(Date value) {
            addCriterion("com_time <", value, "comTime");
            return (Criteria) this;
        }

        public Criteria andComTimeLessThanOrEqualTo(Date value) {
            addCriterion("com_time <=", value, "comTime");
            return (Criteria) this;
        }

        public Criteria andComTimeIn(List<Date> values) {
            addCriterion("com_time in", values, "comTime");
            return (Criteria) this;
        }

        public Criteria andComTimeNotIn(List<Date> values) {
            addCriterion("com_time not in", values, "comTime");
            return (Criteria) this;
        }

        public Criteria andComTimeBetween(Date value1, Date value2) {
            addCriterion("com_time between", value1, value2, "comTime");
            return (Criteria) this;
        }

        public Criteria andComTimeNotBetween(Date value1, Date value2) {
            addCriterion("com_time not between", value1, value2, "comTime");
            return (Criteria) this;
        }

        public Criteria andComStatusIsNull() {
            addCriterion("com_status is null");
            return (Criteria) this;
        }

        public Criteria andComStatusIsNotNull() {
            addCriterion("com_status is not null");
            return (Criteria) this;
        }

        public Criteria andComStatusEqualTo(Integer value) {
            addCriterion("com_status =", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusNotEqualTo(Integer value) {
            addCriterion("com_status <>", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusGreaterThan(Integer value) {
            addCriterion("com_status >", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("com_status >=", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusLessThan(Integer value) {
            addCriterion("com_status <", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusLessThanOrEqualTo(Integer value) {
            addCriterion("com_status <=", value, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusIn(List<Integer> values) {
            addCriterion("com_status in", values, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusNotIn(List<Integer> values) {
            addCriterion("com_status not in", values, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusBetween(Integer value1, Integer value2) {
            addCriterion("com_status between", value1, value2, "comStatus");
            return (Criteria) this;
        }

        public Criteria andComStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("com_status not between", value1, value2, "comStatus");
            return (Criteria) this;
        }

        public Criteria andPicAddressIsNull() {
            addCriterion("pic_address is null");
            return (Criteria) this;
        }

        public Criteria andPicAddressIsNotNull() {
            addCriterion("pic_address is not null");
            return (Criteria) this;
        }

        public Criteria andPicAddressEqualTo(String value) {
            addCriterion("pic_address =", value, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressNotEqualTo(String value) {
            addCriterion("pic_address <>", value, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressGreaterThan(String value) {
            addCriterion("pic_address >", value, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressGreaterThanOrEqualTo(String value) {
            addCriterion("pic_address >=", value, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressLessThan(String value) {
            addCriterion("pic_address <", value, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressLessThanOrEqualTo(String value) {
            addCriterion("pic_address <=", value, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressLike(String value) {
            addCriterion("pic_address like", value, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressNotLike(String value) {
            addCriterion("pic_address not like", value, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressIn(List<String> values) {
            addCriterion("pic_address in", values, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressNotIn(List<String> values) {
            addCriterion("pic_address not in", values, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressBetween(String value1, String value2) {
            addCriterion("pic_address between", value1, value2, "picAddress");
            return (Criteria) this;
        }

        public Criteria andPicAddressNotBetween(String value1, String value2) {
            addCriterion("pic_address not between", value1, value2, "picAddress");
            return (Criteria) this;
        }

        public Criteria andComReplyIsNull() {
            addCriterion("com_reply is null");
            return (Criteria) this;
        }

        public Criteria andComReplyIsNotNull() {
            addCriterion("com_reply is not null");
            return (Criteria) this;
        }

        public Criteria andComReplyEqualTo(String value) {
            addCriterion("com_reply =", value, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyNotEqualTo(String value) {
            addCriterion("com_reply <>", value, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyGreaterThan(String value) {
            addCriterion("com_reply >", value, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyGreaterThanOrEqualTo(String value) {
            addCriterion("com_reply >=", value, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyLessThan(String value) {
            addCriterion("com_reply <", value, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyLessThanOrEqualTo(String value) {
            addCriterion("com_reply <=", value, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyLike(String value) {
            addCriterion("com_reply like", value, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyNotLike(String value) {
            addCriterion("com_reply not like", value, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyIn(List<String> values) {
            addCriterion("com_reply in", values, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyNotIn(List<String> values) {
            addCriterion("com_reply not in", values, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyBetween(String value1, String value2) {
            addCriterion("com_reply between", value1, value2, "comReply");
            return (Criteria) this;
        }

        public Criteria andComReplyNotBetween(String value1, String value2) {
            addCriterion("com_reply not between", value1, value2, "comReply");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNull() {
            addCriterion("reply_time is null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIsNotNull() {
            addCriterion("reply_time is not null");
            return (Criteria) this;
        }

        public Criteria andReplyTimeEqualTo(Date value) {
            addCriterion("reply_time =", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotEqualTo(Date value) {
            addCriterion("reply_time <>", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThan(Date value) {
            addCriterion("reply_time >", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("reply_time >=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThan(Date value) {
            addCriterion("reply_time <", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeLessThanOrEqualTo(Date value) {
            addCriterion("reply_time <=", value, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeIn(List<Date> values) {
            addCriterion("reply_time in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotIn(List<Date> values) {
            addCriterion("reply_time not in", values, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeBetween(Date value1, Date value2) {
            addCriterion("reply_time between", value1, value2, "replyTime");
            return (Criteria) this;
        }

        public Criteria andReplyTimeNotBetween(Date value1, Date value2) {
            addCriterion("reply_time not between", value1, value2, "replyTime");
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