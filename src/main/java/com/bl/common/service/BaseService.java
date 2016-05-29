package com.bl.common.service;

import java.util.List;
import java.util.Map;

import com.bl.common.entity.BaseEntity;
import com.bl.common.exception.BusinessException;
import com.bl.common.mapper.BaseMapper;
import com.bl.common.plugin.page.PageInfo;
import com.bl.common.search.Searchable;

public interface BaseService<M extends BaseEntity> {

	void setBaseMapper(BaseMapper<M> BaseMapper);

	/**
	 * 保存单个实体
	 *
	 * @param m
	 *            实体
	 * @return 返回保存的实体
	 */
	void save(M m) throws BusinessException;

	/**
	 * 根据主键删除相应实体
	 *
	 * @param id
	 *            主键
	 */
	void delete(String id) throws BusinessException;
	void delete(Searchable searchable) throws BusinessException;

	/**
	 * 根据主键删除相应实体
	 *
	 * @param ids
	 *            实体id
	 */
	void delete(String[] ids) throws BusinessException;

	
	/**
	 * 按照主键查询
	 *
	 * @param id
	 *            主键
	 * @return 返回id对应的实体
	 */
	M findOne(String id) throws BusinessException;

	/**
	 * 按照主键查询
	 *
	 * @param id
	 *            主键
	 * @return 返回id对应的实体
	 */
	M get(String id) throws BusinessException;

	/**
	 * 实体是否存在
	 *
	 * @param id
	 *            主键
	 * @return 存在 返回true，否则false
	 */
	boolean exists(String id) throws BusinessException;

	/**
	 * 统计实体总数
	 *
	 * @return 实体总数
	 */
	long count() throws BusinessException;

	/**
	 * 查询所有实体
	 *
	 * @return
	 */
	List<M> findAll() throws BusinessException;

	/**
	 * 
	 * @param queryParams
	 *            查询条件
	 * @param pageNum
	 *            页码
	 * @return
	 */

	PageInfo<M> pageList(Map<String, Object> queryParams, int pageNum) throws BusinessException;

	/**
	 * 分页查询
	 * 
	 * @param queryParams
	 *            查询条件
	 * @param pageNum
	 *            页码
	 * @param pageSize
	 *            分页大小 默认10条
	 * @return
	 */
	PageInfo<M> pageList(Map<String, Object> queryParams, int pageNum, int pageSize) throws BusinessException;

	PageInfo<M> findPage(Searchable searchable) throws BusinessException;

	List<M> findAll(Searchable searchable) throws BusinessException;

}