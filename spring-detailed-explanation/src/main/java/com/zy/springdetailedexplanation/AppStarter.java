package com.zy.springdetailedexplanation;

import com.zy.springdetailedexplanation.pojo.User;
import com.zy.springdetailedexplanation.service.BeanInjectionDemo;
import com.zy.springdetailedexplanation.service.MessageService;
import com.zy.springdetailedexplanation.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppStarter {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        System.out.println("spring上下文启动成功！！");
        //反射
        MessageService messageService = applicationContext.getBean(MessageService.class);
        String s = messageService.sayHello();
        System.out.println(s);

        BeanInjectionDemo beanInjectionDemo = (BeanInjectionDemo) applicationContext.getBean("beanInjectionDemo");
        System.out.println(beanInjectionDemo.sayHello());

        UserService userService =(UserService) applicationContext.getBean("userService");
        List<User> users = userService.getUsers();
        for (User user : users) {
            System.out.println(user.getName());
        }
    }

}
