package com.bl.common.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.bl.common.exception.SearchException;

public class Searchable {
	private String orderByClause;
	private int pageNum;
	private int pageSize;
	private boolean distinct;

	private StringBuilder orderByClauseBuilder;
	private List<Criteria> oredCriteria;

	public Searchable() {
		oredCriteria = new ArrayList<Criteria>();
	}

	public Searchable appendOrderByClause(String searchKey, String value) {
		if (orderByClauseBuilder == null) {
			orderByClauseBuilder = new StringBuilder();
		}
		orderByClauseBuilder.append(searchKey).append(" ").append(value).append(",");
		return this;
	}

	public String getOrderByClause() {
		orderByClause = orderByClauseBuilder.subSequence(0, orderByClauseBuilder.lastIndexOf(",")).toString();
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

	// 添加搜索实现

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * 创建一个新的查询
	 *
	 * @return
	 */
	public static Searchable newSearchable() throws SearchException {
		Searchable searchable = new Searchable();
		return searchable;
	}

	/**
	 * 添加查询条件
	 *
	 * @param key
	 *            如 name_like
	 * @param value
	 *            如果是in查询 多个值之间","分隔
	 * @return
	 */
	public Searchable and(final String searchKey, final Object value) throws SearchException {
		Criteria criteria = this.createAddCriteria();
		addSearchParam(searchKey, value, criteria);
		return this;
	}

	public Searchable or(final String searchKey, final Object value) throws SearchException {
		Criteria criteria = this.or();
		addSearchParam(searchKey, value, criteria);
		return this;
	}

	/**
	 * 添加一组查询参数
	 *
	 * @param searchParams
	 * @return
	 */
	public void and(final Map<String, Object> searchParams) throws SearchException {
		Set<Entry<String, Object>> entrySet = searchParams.entrySet();

		for (Entry<String, Object> e : entrySet) {
			this.and(e.getKey(), e.getValue());
		}
	}

	/**
	 * 添加一组查询参数
	 *
	 * @param searchParams
	 * @return
	 */
	public void or(final Map<String, Object> searchParams) throws SearchException {
		Set<Entry<String, Object>> entrySet = searchParams.entrySet();

		for (Entry<String, Object> e : entrySet) {
			this.and(e.getKey(), e.getValue());
		}
	}

	/**
	 * eq 等于 ne 不等于 gt 大于 gte 大于等于 lt 小于 lte 小于等于 prefixLike 前缀模糊匹配 preNotLike
	 * 前缀模糊不匹配 sufLike 后缀模糊匹配 sufNotLike 后缀模糊不匹配 like 模糊匹配 notLike 不匹配 isNull 空
	 * isNotNull 非空 in 包含 notIn 不包含
	 */
	private void addSearchParam(final String searchKey, final Object value, Criteria criteria) {
		if (searchKey == null || !searchKey.contains("_")) {
			throw new SearchException("searchKey is valid");
		}
		String[] propAndTag = searchKey.split("_");
		if (propAndTag.length != 2) {
			throw new SearchException("searchKey is valid");
		}
		String prop = propAndTag[0];
		String tag = propAndTag[1];
		if (tag.endsWith("eq")) {
			criteria.andEqualTo(prop, value);
		} else if (tag.endsWith("ne")) {
			criteria.andNotEqualTo(prop, value);
		} else if (tag.endsWith("gt")) {
			criteria.andGreaterThan(prop, value);
		} else if (tag.endsWith("gte")) {
			criteria.andGreaterThanOrEqualTo(prop, value);
		} else if (tag.endsWith("lt")) {
			criteria.andLessThan(prop, value);
		} else if (tag.endsWith("lte")) {
			criteria.andLessThanOrEqualTo(prop, value);
		} else if (tag.endsWith("preLike")) {
			criteria.andPreLike(prop, value);
		} else if (tag.endsWith("preNotLike")) {
			criteria.andNotPreLike(prop, value);
		} else if (tag.endsWith("sufLike")) {
			criteria.andSufLike(prop, value);
		} else if (tag.endsWith("sufNotLike")) {
			criteria.andNotSufLike(prop, value);
		} else if (tag.endsWith("like")) {
			criteria.andLike(prop, value);
		} else if (tag.endsWith("notLike")) {
			criteria.andNotLike(prop, value);
		} else if (tag.endsWith("bt")) {
			String[] values = validbetweenPropValueAndGet(prop, value);
			criteria.andBetween(prop, values[0], values[1]);
		} else if (tag.endsWith("nbt")) {
			String[] values = validbetweenPropValueAndGet(prop, value);
			criteria.andNotBetween(prop, values[0], values[1]);
		} else if (tag.endsWith("isNull")) {
			criteria.andIsNull(prop);
		} else if (tag.endsWith("isNotNull")) {
			criteria.andIsNotNull(prop);
		} else if (tag.endsWith("in")) {
			String[] array = validInPropValueAndGet(prop, value);
			List<String> values = toList(array);
			criteria.andIn(prop, values);
		} else if (tag.endsWith("notIn")) {
			String[] array = validInPropValueAndGet(prop, value);
			List<String> values = toList(array);
			criteria.andNotIn(prop, values);
		} else {
			throw new SearchException("searchKey is invalid");
		}
	}

	private List<String> toList(String[] array) {
		List<String> results = new ArrayList<String>(array.length);
		for (String s : array) {
			results.add(s);
		}
		return results;
	}

	private String[] validInPropValueAndGet(String prop, Object value) throws SearchException {
		if (value == null) {
			throw new SearchException(prop + " value is null");
		}
		if (value instanceof String[]) {
			String[] values = (String[]) value;
			if (values.length == 0) {
				throw new SearchException(prop + " value is empty");
			}
			for (Object v : values) {
				if (!(v instanceof String)) {
					throw new SearchException(prop + " value is class type is not java.lang.String");
				}
			}
			return (String[]) value;
		} else {
			throw new SearchException(prop + "  is not list");
		}

	}

	private String[] validbetweenPropValueAndGet(String prop, Object value) throws SearchException {
		if (value == null) {
			throw new SearchException(prop + " value is null");
		}
		if (value instanceof String[]) {
			String[] values = (String[]) value;
			if (values.length == 0) {
				throw new SearchException(prop + " value is empty");
			}

			if (values.length != 2) {
				throw new SearchException(prop + " value size is more than 2");
			}
			for (Object v : values) {
				if (!(v instanceof String)) {
					throw new SearchException(prop + " value is class type is not java.lang.String");
				}
			}
			return (String[]) value;
		} else {
			throw new SearchException(prop + "  is not list");
		}

	}

	/**
	 * 是否包含查询键 如 name_like 包括 or 和 and的
	 *
	 * @param key
	 * @return
	 */
	public boolean containsSearchKey(final String key) {
		return false;
	}

	private Criteria or() {
		Criteria criteria = createOrCriteria();
		oredCriteria.add(criteria);
		return criteria;
	}

	private Criteria createAddCriteria() {
		Criteria criteria = createOrCriteria();
		criteria.setAnd(true);
		if (oredCriteria.size() == 0) {
			oredCriteria.add(criteria);
		} else {
			for (Criteria c : this.oredCriteria) {
				if (c.isAnd()) {
					return c;
				}
			}
		}
		return criteria;
	}

	private Criteria createOrCriteria() {
		Criteria criteria = new Criteria();
		criteria.setOr(true);
		return criteria;
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

		protected void addLikeCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value, true, false, false));
		}

		protected void addSuffixLikeCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value, false, true, false));
		}

		protected void addPrefixLikeCriterion(String condition, Object value, String property) {
			if (value == null) {
				throw new RuntimeException("Value for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value, false, false, true));
		}

		protected void addCriterion(String condition, Object value1, Object value2, String property) {
			if (value1 == null || value2 == null) {
				throw new RuntimeException("Between values for " + property + " cannot be null");
			}
			criteria.add(new Criterion(condition, value1, value2));
		}

		public Criteria andIsNull(String prop) {
			addCriterion(prop + " is null");
			return (Criteria) this;
		}

		public Criteria andIsNotNull(String prop) {
			addCriterion(prop + " is not null");
			return (Criteria) this;
		}

		public Criteria andEqualTo(String prop, Object value) {
			addCriterion(prop + " =", value, prop);
			return (Criteria) this;
		}

		public Criteria andNotEqualTo(String prop, Object value) {
			addCriterion(prop + " <>", value, prop);
			return (Criteria) this;
		}

		public Criteria andGreaterThan(String prop, Object value) {
			addCriterion(prop + " >", value, prop);
			return (Criteria) this;
		}

		public Criteria andGreaterThanOrEqualTo(String prop, Object value) {
			addCriterion(prop + " >=", value, prop);
			return (Criteria) this;
		}

		public Criteria andLessThan(String prop, Object value) {
			addCriterion(prop + " <", value, prop);
			return (Criteria) this;
		}

		public Criteria andLessThanOrEqualTo(String prop, Object value) {
			addCriterion(prop + " <=", value, prop);
			return (Criteria) this;
		}

		public Criteria andLike(String prop, Object value) {
			addLikeCriterion(prop + " like ", value, prop);
			return (Criteria) this;
		}

		public Criteria andNotLike(String prop, Object value) {
			addLikeCriterion(prop + " not like ", value, prop);
			return (Criteria) this;
		}

		public Criteria andPreLike(String prop, Object value) {
			addPrefixLikeCriterion(prop + " like ", value, prop);
			return (Criteria) this;
		}

		public Criteria andNotPreLike(String prop, Object value) {
			addPrefixLikeCriterion(prop + " not like ", value, prop);
			return (Criteria) this;
		}

		public Criteria andSufLike(String prop, Object value) {
			addSuffixLikeCriterion(prop + " like ", value, prop);
			return (Criteria) this;
		}

		public Criteria andNotSufLike(String prop, Object value) {
			addSuffixLikeCriterion(prop + " not like ", value, prop);
			return (Criteria) this;
		}

		public Criteria andIn(String prop, List<String> values) {
			addCriterion(prop + " in", values, prop);
			return (Criteria) this;
		}

		public Criteria andNotIn(String prop, List<String> values) {
			addCriterion(prop + " not in", values, prop);
			return (Criteria) this;
		}

		public Criteria andBetween(String prop, Object value1, Object value2) {
			addCriterion(prop + " between", value1, value2, prop);
			return (Criteria) this;
		}

		public Criteria andNotBetween(String prop, Object value1, Object value2) {
			addCriterion(prop + " not between", value1, value2, prop);
			return (Criteria) this;
		}

	}

	public static class Criteria extends GeneratedCriteria {
		/**
		 * 添加这两个属性用区分 and 和 or
		 * 的查询条件 @see#com.bl.common.search.Searchable.createAddCriteria()
		 */
		private boolean and;
		private boolean or;

		protected Criteria() {
			super();
		}

		public boolean isAnd() {
			return and;
		}

		public void setAnd(boolean and) {
			this.and = and;
		}

		public boolean isOr() {
			return or;
		}

		public void setOr(boolean or) {
			this.or = or;
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

		private boolean likeValue;
		private boolean suffixLikeValue;
		private boolean prefixLikeValue;

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

		public boolean isLikeValue() {
			return likeValue;
		}

		public void setLikeValue(boolean likeValue) {
			this.likeValue = likeValue;
		}

		public boolean isSuffixLikeValue() {
			return suffixLikeValue;
		}

		public void setSuffixLikeValue(boolean suffixLikeValue) {
			this.suffixLikeValue = suffixLikeValue;
		}

		public boolean isPrefixLikeValue() {
			return prefixLikeValue;
		}

		public void setPrefixLikeValue(boolean prefixLikeValue) {
			this.prefixLikeValue = prefixLikeValue;
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

		protected Criterion(String condition, Object value, boolean likeValue, boolean suffixLikeValue,
				boolean prefixLikeValue) {
			this.condition = condition;
			this.value = value;
			this.singleValue = true;
			this.likeValue = likeValue;
			this.suffixLikeValue = suffixLikeValue;
			this.prefixLikeValue = prefixLikeValue;
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
