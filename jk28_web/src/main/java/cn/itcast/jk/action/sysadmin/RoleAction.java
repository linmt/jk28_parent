package cn.itcast.jk.action.sysadmin;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.service.RoleService;
import cn.itcast.jk.utils.Page;
import com.opensymphony.xwork2.ModelDriven;
/**
 * 部门管理的Action
 * @author Administrator
 *
 */
public class RoleAction extends BaseAction implements ModelDriven<Role> {
	private Role model = new Role();
	public Role getModel() {
		return model;
	}
	
	//分页查询
	private Page page  = new Page();
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
	//注入RoleService
	private RoleService roleService;
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	
	/**
	 * 分页查询
	 */
	public String list() throws Exception {
		roleService.findPage("from Role", page, Role.class, null);
		//设置分页的url地址
		page.setUrl("roleAction_list");
		//将page对象压入栈顶
		super.push(page);
		return "list";
	}
	
	/**
	 * 查看
	 *     id=rterytrytrytr
	 * model对象
	 *      id属性：rterytrytrytr
	 */
	public String toview() throws Exception {
		//1.调用业务方法，根据id,得到对象
		Role dept = roleService.get(Role.class, model.getId());
		//放入栈顶
		super.push(dept);
		//3.跳页面
		return "toview";
	}
	
	/**
	 * 进入新增页面
	 */
	public String tocreate() throws Exception {
		//调用业务方法
	
		//跳页面
		return "tocreate";
	}
	
	/**
	 * 保存
	 *     <s:select name="parent.id"
	 *     <input type="text" name="deptName" value=""/>
	 * model对象能接收
	 *      parent 
	 *           id
	 *      deptName
	 */
	public String insert() throws Exception {
		//1.调用业务方法，实现保存
		roleService.saveOrUpdate(model);
		//跳页面
		return "alist";
	}
	
	
	/**
	 * 进入修改页面
	 */
	public String toupdate() throws Exception {
		//1.根据id,得到一个对象
		Role obj = roleService.get(Role.class, model.getId());
		//2.将对象放入值栈中
		super.push(obj);
		//5.跳页面
		return "toupdate";
	}
	
	/**
	 * 修改
	 */
	public String update() throws Exception {
		//调用业务
		Role obj = roleService.get(Role.class, model.getId());//根据id,得到一个数据库中保存的对象
		
		//2.设置修改的属性
		obj.setName(model.getName());
		obj.setRemark(model.getRemark());
		
		roleService.saveOrUpdate(obj);
		return "alist";
	}
	
	/**
	 * 删除
	 * <input type="checkbox" name="id" value="100"/>
	 * <input type="checkbox" name="id" value="3d00290a-1af0-4c28-853e-29fbf96a2722"/>
	 * .....
	 * model
	 *    id:String类型
	 *       具有同名框的一组值如何封装数据？
	 *       如何服务端是String类型：
	 *                       100, 3d00290a-1af0-4c28-853e-29fbf96a2722, 3d00290a-1af0-4c28-853e-29fbf96a2722
	 *                       
	 *    id:Integer,Float,Double.Date类型                  id=100               id=200        id=300  
	 *    id=300
	 *    Integer []id;  {100,200,300}
	 */
	public String delete() throws Exception {
		String ids[] = model.getId().split(", ");
		//调用业务方法，实现批量删除
		roleService.delete(Role.class, ids);
		return "alist";
	}
}
