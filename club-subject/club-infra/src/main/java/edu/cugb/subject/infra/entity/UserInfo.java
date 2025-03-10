package edu.cugb.subject.infra.entity;

import lombok.Data;

/**
 * @Author: pengjia
 * @Description: 用户信息实体类
 * 单独创建一个userInfo实体类，只包含rpc调用需要的属性，不受别的服务的影响
 */
@Data
public class UserInfo {

    private String userName;
    private String nickName;

}
