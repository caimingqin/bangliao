package com.bl.common.entity;

import java.io.Serializable;
import java.util.Date;

import com.bl.common.plugin.entity.LogicDeleteable;

/**
 * 
 * 抽象实体基类，提供统一的ID，和相关的基本功能方法 
 * 提供基础的id, createBy, createDate, updateBy, updateDate, version, isDeleted等基础数据库字典的ORM
 * 提供审计字段createBy, createDate, updateBy, updateDate的自动记录功能，业务模块不需要对这些字段进行处理。
 * 提供逻辑删除的基础实现
 * @param <ID>
 */

public abstract class BaseEntity  implements LogicDeleteable,Serializable{

    /** 
	 * @Fields serialVersionUID
	 */ 

	/**
	 * 
	 */
	private static final long serialVersionUID = -9196583770731157014L;

	public static final String f_id="id";
	public static final String f_createBy="createBy";
	public static final String f_createDate="createDate";
	public static final String f_updateBy="updateBy";
	public static final String f_updateDate="updateDate";
	public static final String f_version="version";
	/**
	 * UUID
	 */
    private String id;
    
	/**
     * 记录创建人标识，记录用户的UUID
     */
	private String createBy;
    
    /**
     * 记录创建日期
     */
	private Date createDate;
	
	/**
	 * 记录最后更新人标识，记录用户的UUID
	 */
	private String updateBy;
	
	/**
	 * 记录最后更新日期
	 */
	private Date updateDate;
	/**
	 * 版本号
	 */
	private int version;
	
	/**
	 * 逻辑删除标志
	 */
	private String isDeleted = "0";
	
	/** 
	 * @return createBy 
	 */
	public String getCreateBy() {
		return createBy;
	}

	/** 
	 * @param createBy 要设置的 createBy 
	 */
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	/** 
	 * @return createDate 
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/** 
	 * @param createDate 要设置的 createDate 
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/** 
	 * @return updateBy 
	 */
	public String getUpdateBy() {
		return updateBy;
	}

	/** 
	 * @param updateBy 要设置的 updateBy 
	 */
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	/** 
	 * @return updateDate 
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/** 
	 * @param updateDate 要设置的 updateDate 
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/** 
	 * @return version 
	 */
	public int getVersion() {
		return version;
	}

	/** 
	 * @param version 要设置的 version 
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/** 
	 * @return isDeleted 
	 */
	@Override
	public String getIsDeleted() {
		return isDeleted;
	}

	/** 
	 * @param isDeleted 要设置的 isDeleted 
	 */
	@Override
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	/**
	 * 
	 * 标记为逻辑删除  
	 * @see com.bl.common.plugin.entity.LogicDeleteable#markDeleted()
	 */
	@Override
	public void markDeleted() {
		this.isDeleted = "1";
	}


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    /**
     *  
	 * @return 
	 * @see java.lang.Object#toString() 
	 */
	@Override
	public String toString() {
		return "BaseEntity [id=" + id + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", updateBy=" + updateBy
				+ ", updateDate=" + updateDate + ", version=" + version
				+ ", isDeleted=" + isDeleted + "]";
	}

	/**
	 *  
	 * @return 
	 * @see java.lang.Object#hashCode() 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 *  
	 * @param obj
	 * @return 
	 * @see java.lang.Object#equals(java.lang.Object) 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

//	public void incrementVersion() {
//		this.version++;
//	}
}
