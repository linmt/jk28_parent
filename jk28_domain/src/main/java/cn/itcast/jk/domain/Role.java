package cn.itcast.jk.domain;

import java.util.HashSet;
import java.util.Set;

//完成
public class Role extends BaseEntity{
	private String id;
	private Set<User> users = new HashSet<User>(0);//角色与用户    多对多
	private String name;//角色名
	private String remark;//备注
	private String orderNo;//排序号
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Set<User> getUsers() {
		return users;
	}
	public void setUsers(Set<User> users) {
		this.users = users;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


}
