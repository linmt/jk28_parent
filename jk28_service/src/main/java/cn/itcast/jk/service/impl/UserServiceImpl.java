package cn.itcast.jk.service.impl;

import cn.itcast.jk.dao.BaseDao;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.UserService;
import cn.itcast.jk.utils.Encrypt;
import cn.itcast.jk.utils.Page;
import cn.itcast.jk.utils.SysConstant;
import cn.itcast.jk.utils.UtilFuns;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

public class UserServiceImpl implements UserService {
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public List<User> find(String hql, Class<User> entityClass, Object[] params) {
		return baseDao.find(hql, entityClass, params);
	}

	public User get(Class<User> entityClass, Serializable id) {
		return baseDao.get(entityClass, id);
	}

	public Page<User> findPage(String hql, Page<User> page, Class<User> entityClass, Object[] params) {
		return baseDao.findPage(hql, page, entityClass, params);
	}

	public void saveOrUpdate(User entity) {
		if(UtilFuns.isEmpty(entity.getId())){
			//新增
			String id = UUID.randomUUID().toString();
			entity.setId(id);
			entity.getUserinfo().setId(id);

			//补充Shiro添加后的bug
			entity.setPassword(Encrypt.md5(SysConstant.DEFAULT_PASS, entity.getUserName()));
		}
		baseDao.saveOrUpdate(entity);
	}

	public void saveOrUpdateAll(Collection<User> entitys) {
		baseDao.saveOrUpdateAll(entitys);
	}

	public void deleteById(Class<User> entityClass, Serializable id) {
		
		baseDao.deleteById(entityClass, id);//这里不用递归
	}

	public void delete(Class<User> entityClass, Serializable[] ids) {
		for(Serializable id :ids){
			this.deleteById(User.class,id);
		}
	}
}
