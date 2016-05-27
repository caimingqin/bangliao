package com.bl.common.mapper;

import java.util.List;
import java.util.Map;

import com.bl.common.entity.BaseEntity;
import com.bl.common.plugin.page.Page;
import com.bl.common.search.Searchable;

/**
 * <p>抽象数据访问层基类 提供一些简便方法<br/>
 * <p/>
 * <p>泛型 ： M 表示实体类型；ID表示主键类型
 * <p/>
 */
public interface BaseMapper<M extends BaseEntity> {
    /*代码自动生成*/
	int deleteByPrimaryKey(String id);
    int insert(M record);
    M selectByPrimaryKey(String id);
    int updateByPrimaryKey(M record);
    
    
    /*需要用到时，mapper.xml自行实现*/
    int delete(M record);//逻辑删除
    long count();
    List<M> findAll();
    Page<M> pageList(Map<String, Object> queryParams);
	List<M> findAllBySearchable(Searchable searchable);
	Page<M> findPageBySearchable(Searchable searchable);
}
