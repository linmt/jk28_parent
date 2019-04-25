package cn.itcast.jk.shiro;

import cn.itcast.jk.domain.Module;
import cn.itcast.jk.domain.Role;
import cn.itcast.jk.domain.User;
import cn.itcast.jk.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by 张洲徽 on 2019/4/25.
 */
public class AuthRealm extends AuthorizingRealm{

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //授权  当jsp页面出现shrio标签时，就会执行授权方法
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection pc) {
        System.out.println("授权");

        //根据reaml的名字去找对应的reaml
        User user=(User)pc.fromRealm(this.getName()).iterator().next();

        //对象导航
        Set<Role> roles=user.getRoles();
        List<String> permissions=new ArrayList<String>();

        //遍历每个角色，得到每个角色下的模块（权限）列表
        for(Role role:roles){
            Set<Module> modules=role.getModules();
            for(Module m:modules){
                permissions.add(m.getName());
            }
        }
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        //添加用户的模块（权限）
        info.addStringPermissions(permissions);
        return info;
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证");

        //向下转型
        UsernamePasswordToken upToken = (UsernamePasswordToken)token;

        //调用业务方法，实现根据用户名查询
        String hql="from User where userName=?";
        List<User> list=userService.find(hql,User.class,new String[]{upToken.getUsername()});
        if(list!=null && list.size()>0){
            User user=list.get(0);
            //这里为什么传user对象而不是username？？？
            AuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
            //此处如果返回，就会立即进入到密码比较器
            return info;
        }
        //就会出现异常
        return null;
    }
}
