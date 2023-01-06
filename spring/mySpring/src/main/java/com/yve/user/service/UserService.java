package com.yve.user.service;

import com.yve.springframe.Component;
import com.yve.springframe.Scope;

/**
 * @author 伟大的Yve菌
 * 用户的service类, 通过spring获取到Bean对象后调用test()方法测试
 */
@Component("userService")
@Scope("prototype")
public class UserService {
    public void test() {
        System.out.println("userService");
    }
}
