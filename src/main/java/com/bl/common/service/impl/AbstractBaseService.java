package com.bl.common.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.bl.common.entity.BaseEntity;
import com.bl.common.exception.BusinessException;
import com.bl.common.exception.ErrorCode;
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
	public void save(M m) throws BusinessException {
		try {
			BaseEntity bs = (BaseEntity) m;
			if (bs.getId() == null) {
				this.preSave(m);
				int insert = this.baseMapper.insert(m);
				if (insert == 0) {
					throw new BusinessException(ErrorCode.SERVER_SAVE,"保存失败");
				}
			} else {
				int update = this.update(m);
				if (update == 0) {
					throw new BusinessException(ErrorCode.SERVER_UPDATE,"更新失败");
				}
			}
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_RUNTIME,"系统异常");
		}

	}

	/**
	 * 
	 * @param m
	 * @return
	 * @see com.bl.common.service.BaseService#update(M)
	 */
	private int update(M m) throws BusinessException {
		this.preUpdate(m);
		return baseMapper.updateByPrimaryKey(m);
	}

	@Override
	public void delete(String id) throws BusinessException {
		try {
			M m = this.get(id);
			if (m == null) {
				throw new BusinessException(ErrorCode.SERVER_DELETE,"没有可更新的数据");
			}
			this.markDeleted(m);
			int deleteByEntity = deleteByEntity(m);
			if (deleteByEntity == 0) {
				throw new BusinessException(ErrorCode.SERVER_DELETE,"删除数据失败");
			}
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_RUNTIME,"系统异常");
		}

	}

	private void markDeleted(M m) {
		BaseEntity bs = (BaseEntity) m;
		bs.setUpdateDate(new Date());
		bs.setUpdateBy("sys");// TODO
	}

	private void preSave(M m) {
		BaseEntity bs = (BaseEntity) m;
		bs.setCreateDate(new Date());
		bs.setId(IdUtil.getUUId());
		bs.setCreateBy("createBy"); // TODO
	}

	private void preUpdate(M m) {
		BaseEntity bs = (BaseEntity) m;
		bs.setUpdateDate(new Date());
		bs.setUpdateBy("UpdateBy");// TODO
	}

	private int deleteByEntity(M m) {
		return baseMapper.delete(m);
	}

	@Override
	public void delete(String[] ids) throws BusinessException {
		try {
			int deletedCount = 0;
			for (String id : ids) {
				M m = this.get(id);
				if (m == null) {
					throw new BusinessException(ErrorCode.SERVER_DELETE,"找不到可删除的记录ID[" + id + "]");
				}
				int markDeletedNum = this.deleteByEntity(m);
				if (markDeletedNum > 0) {
					deletedCount++;

				}
				if (ids.length != deletedCount) {
					throw new BusinessException(ErrorCode.SERVER_DELETE,"批量删除异常");
				}
			}
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_RUNTIME,"系统异常");
		}
	}

	@Override
	public void delete(Searchable searchable) throws BusinessException {
		try {
			searchable.setUpdateBy("sys");//TODO
			searchable.setUpdateDate(new Date());
			baseMapper.deleteBySearchable(searchable);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_RUNTIME,"系统异常");
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @see com.bl.common.service.BaseService#findOne(String)
	 */
	@Override
	public M findOne(String id) throws BusinessException {
		try {
			return baseMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_RUNTIME,"系统异常");
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @see com.bl.common.service.BaseService#get(String)
	 */
	@Override
	public M get(String id) throws BusinessException {
		try {
			return baseMapper.selectByPrimaryKey(id);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_RUNTIME,"系统异常");
		}

	}

	/**
	 * 
	 * @param id
	 * @return
	 * @see com.bl.common.service.BaseService#exists(String)
	 */
	@Override
	public boolean exists(String id) throws BusinessException {
		try {
			M m = this.get(id);
			if (m != null) {
				return true;
			}
			return false;
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_RUNTIME,"系统异常");
		}

	}

	/**
	 * 
	 * @return
	 * @see com.bl.common.service.BaseService#count()
	 */
	@Override
	public long count() throws BusinessException {
		try {
			return baseMapper.count();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_RUNTIME,"系统异常");
		}

	}

	/**
	 * 
	 * @return
	 * @see com.bl.common.service.BaseService#findAll()
	 */
	@Override
	public List<M> findAll() throws BusinessException {
		try {
			return baseMapper.findAll();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_QUERY,e.getMessage());
		}

	}

	@Override
	public PageInfo<M> pageList(Map<String, Object> queryParams, int pageNum) throws BusinessException {
		try {
			return this.pageList(queryParams, pageNum, 10);
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_QUERY,e.getMessage());
		}

	}

	@Override
	public PageInfo<M> pageList(Map<String, Object> queryParams, int pageNum, int pageSize) throws BusinessException {
		try {
			if (pageSize == 0) {
				pageSize = 10;
			}
			PageHelper.startPage(pageNum, pageSize);
			Page<M> page = baseMapper.pageList(queryParams);
			return page.toPageInfo();
		} catch (Exception e) {
			throw new BusinessException(ErrorCode.SERVER_QUERY,e.getMessage());
		}

	}

	@Override
	public PageInfo<M> findPage(Searchable searchable) throws BusinessException {
		try {
			PageHelper.startPage(searchable.getPageNum(), searchable.getPageSize());
			Page<M> page = baseMapper.findPageBySearchable(searchable);
			return page.toPageInfo();
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}

	}

	@Override
	public List<M> findAll(Searchable searchable) throws BusinessException {
		try {

			return baseMapper.findAllBySearchable(searchable);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
	}

}
