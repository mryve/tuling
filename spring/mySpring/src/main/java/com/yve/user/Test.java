package com.yve.user;

import com.yve.springframe.MyApplicationContext;
import com.yve.user.service.UserService;

/**
 * @author 伟大的Yve菌
 * 通过Test类启动主方法, 内部声明一个spring的ApplicationContext对象调用bean的方法
 */
public class Test {
    public static void main(String[] args) {
        MyApplicationContext context = new MyApplicationContext(AppConfig.class);
//        System.out.println(context.getBean("userService"));
//        System.out.println(context.getBean("userService"));
//        System.out.println(context.getBean("userService"));
        UserService userService = (UserService) context.getBean("com.yve.user.service.UserService");
        //userService.test();
    }

}
