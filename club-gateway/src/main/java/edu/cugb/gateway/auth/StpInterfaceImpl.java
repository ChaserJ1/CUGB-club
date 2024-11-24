package edu.cugb.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表
        //方案1，直接和数据库交互，不太合适
        //方案2，redis缓存，redis和数据库的数据一致性问题
        //方案3，redis拿不到就去调微服务获取
        List<String> permissionList = new ArrayList<>();
        permissionList.add("user:add");
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表

        List<String> roleList = new ArrayList<>();
        roleList.add("admin");
        return roleList;
    }

}
