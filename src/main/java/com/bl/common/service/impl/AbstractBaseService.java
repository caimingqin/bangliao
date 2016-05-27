package com.bl.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bl.common.entity.BaseEntity;
import com.bl.common.exception.BusinessException;
import com.bl.common.mapper.BaseMapper;
import com.bl.common.plugin.page.Page;
import com.bl.common.plugin.page.PageHelper;
import com.bl.common.plugin.page.PageInfo;
import com.bl.common.search.Searchable;
import com.bl.common.service.BaseService;
import com.bl.common.utils.IdUtil;

/**
 * <p>
 * 抽象service层基类 提供一些简便方法
 * <p/>
 * <p>
 * 泛型 ： M 表示实体类型；ID表示主键类型
 * <p/>
 */
public abstract class AbstractBaseService<M extends BaseEntity> implements BaseService<M> {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	protected BaseMapper<M> baseMapper;

	/**
	 * 
	 * @param BaseMapper
	 */
	@Override
	@Autowired
	public void setBaseMapper(BaseMapper<M> baseMapper) {
		this.baseMapper = baseMapper;
	}

	/**
	 * 
	 * @param m
	 * @return
	 * @see com.bl.common.service.BaseService#save(M)
	 */
	@Override
	public int save(M m) {
		BaseEntity bs = (BaseEntity) m;
		if (bs.getId() == null) {
			this.preSave(m);
			return this.baseMapper.insert(m);
		} else {
			return this.update(m);
		}

	}

	/**
	 * 
	 * @param m
	 * @return
	 * @see com.bl.common.service.BaseService#update(M)
	 */
	private int update(M m) {
		this.preUpdate(m);
		return baseMapper.updateByPrimaryKey(m);
	}

	@Override
	public int delete(String id) {
		M m = this.get(id);
		if (m == null) {
			return 0;
		}
		this.markDeleted(m);
		return deleteByEntity(m);
	}

	private void markDeleted(M m) {
		BaseEntity bs = (BaseEntity) m;
		bs.setUpdateDate(new Date());
		bs.markDeleted();
		
	}

	private void preSave(M m) {
		BaseEntity bs = (BaseEntity) m;
		bs.setCreateDate(new Date());
		bs.setId(IdUtil.getUUId());
		// bs.setCreateBy("caimingqin");
	}

	private void preUpdate(M m) {
		BaseEntity bs = (BaseEntity) m;
		bs.setUpdateDate(new Date());
		// bs.setUpdateBy("updateByCcccc");
	}

	private int deleteByEntity(M m) {
		return baseMapper.delete(m);
	}

	@Override
	public int delete(String[] ids) {
		int deletedCount = 0;
		for (String id : ids) {
			M m = this.get(id);
			if (m == null) {
				throw new BusinessException("找不到可删除的记录ID[" + id + "]");
			}
			int markDeletedNum = this.deleteByEntity(m);
			if (markDeletedNum > 0) {
				deletedCount++;
			}
		}
		if (ids.length != deletedCount) {
			throw new BusinessException("批量更新异常");
		}

		return deletedCount;
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @see com.bl.common.service.BaseService#findOne(String)
	 */
	@Override
	public M findOne(String id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @see com.bl.common.service.BaseService#get(String)
	 */
	@Override
	public M get(String id) {
		return baseMapper.selectByPrimaryKey(id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @see com.bl.common.service.BaseService#exists(String)
	 */
	@Override
	public boolean exists(String id) {
		M m = this.get(id);
		if (m != null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 * @see com.bl.common.service.BaseService#count()
	 */
	@Override
	public long count() {
		return baseMapper.count();
	}

	/**
	 * 
	 * @return
	 * @see com.bl.common.service.BaseService#findAll()
	 */
	@Override
	public List<M> findAll() {
		return baseMapper.findAll();
	}

	@Override
	public PageInfo<M> pageList(Map<String, Object> queryParams, int pageNum) {
		return this.pageList(queryParams, pageNum, 10);
	}

	@Override
	public PageInfo<M> pageList(Map<String, Object> queryParams, int pageNum, int pageSize) {
		if (pageSize == 0) {
			pageSize = 10;
		}
		PageHelper.startPage(pageNum, pageSize);
		Page<M> page = baseMapper.pageList(queryParams);

		return page.toPageInfo();
	}

	@Override
	public PageInfo<M> findPage(Searchable searchable) {
		PageHelper.startPage(searchable.getPageNum(), searchable.getPageSize());
		Page<M> page = baseMapper.findPageBySearchable(searchable);
		return page.toPageInfo();
	}

	@Override
	public List<M> findAll(Searchable searchable) {

		return baseMapper.findAllBySearchable(searchable);
	}

}
