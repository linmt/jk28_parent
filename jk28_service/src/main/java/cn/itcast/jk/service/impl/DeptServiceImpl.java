package cn.itcast.jk.service.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.Dept;
import cn.itcast.jk.service.DeptService;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.UtilFuns;

public class DeptServiceImpl implements DeptService {

	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<Dept> find(String hql, Class<Dept> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public Dept get(Class<Dept> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<Dept> findPage(String hql, Page<Dept> page, Class<Dept> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(Dept entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//新增
			entity.setState(1);//1启用  0停用  默认为启用
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<Dept> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<Dept> entityClass, Serializable id) {
		baseDao.deleteById(entityClass, id);
	}

	public void delete(Class<Dept> entityClass, Serializable[] ids) {
		baseDao.delete(entityClass, ids);
	}

}
