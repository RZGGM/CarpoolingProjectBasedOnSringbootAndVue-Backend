package com.gzasc.onlinecarhailing.aop;

import com.gzasc.onlinecarhailing.pojo.Passenger;
import com.gzasc.onlinecarhailing.pojo.Result;
import com.gzasc.onlinecarhailing.utils.RedisUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class CacheAspect {

//    当缓存的数据在数据库中变化时，要更改或是直接移除，可以定义一个类来装缓存的key，或是定义一个属性来装如下。
    private List<String> ticketKey = new  ArrayList<>();
    private List<String> orderKye = new ArrayList<>();

//    由于使用注解的形式实现缓存不利于使用可视化管理工具进行查看和管理缓存，
//    所以使用了 RedisTemPlate；
//    并且
//    由于要实现缓存的类及类里面的方法太多，一定又一个的进行硬编码进类里面会太麻烦，
//    所以使用了AOP技术。

    @Autowired
    RedisUtils redisUtils;



    //    车票缓存，乘客看到的。
    @Around("execution(* com.gzasc.onlinecarhailing.service.impl.TicketServiceImpl.searchAvailableTicket(..))")
    public Object searchTickets(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        方法名
        String name = proceedingJoinPoint.getSignature().getName();
//        目标对象的类名
        String className = proceedingJoinPoint.getTarget().getClass().getName();
        //        方法的参数
        Object[] args = proceedingJoinPoint.getArgs();

//        System.out.println("方法名：" + name + "   类名：" + className + "  参数：" + Arrays.toString(Arrays.stream(args).toArray()));

//        缓存的key，key的名字应该是固定的，因为在移除缓存时还要用到。
//        名字就用类名+方法名+函数的形参的值，这样应该几乎没有重复的了。
        String key = className + name + Arrays.toString(Arrays.stream(args).toArray());
//        从缓存中查看数据
        Object object = redisUtils.get(key);
//        判断缓存中是否有数据
        if (object != null) {
            return object;
        } else {
            object = proceedingJoinPoint.proceed();

//            将Key装入容器中，当缓存要变化时，可以使用。
            ticketKey.add(key);

            redisUtils.set(key, object);
        }

        return object;

    }

    //    在车票变化时移除车票缓存
    @Around("execution(* com.gzasc.onlinecarhailing.service.impl.TicketServiceImpl.add*(..))" +
            "||" +
            "execution(* com.gzasc.onlinecarhailing.service.impl.TicketServiceImpl.remove*(..))" +
            "||" +
            "execution(* com.gzasc.onlinecarhailing.service.impl.TicketServiceImpl.mod*(..))")
    public Object removeTickets(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

//        当调用方法时，就说明要删除缓存了。
        if (!ticketKey.isEmpty()){

           redisUtils.delete(ticketKey);

           return proceedingJoinPoint.proceed();

        }else {
            System.out.println("缓存的key为空。");
            return proceedingJoinPoint.proceed();
        }


    }

//    为共用的拼车订单开启缓存
    @Around("execution(* com.gzasc.onlinecarhailing.service.OrderService.searchOrdersByOrderState(Integer))")
    public Object searchCanJoinOrder(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        缓存的key.
        String key = null;
//        方法名
        String className = proceedingJoinPoint.getSignature().getName();
// 参数
       String args = Arrays.toString(Arrays.stream(proceedingJoinPoint.getArgs()).toArray());

       key = className + args;

        //        从缓存中查看数据
        Object object = redisUtils.get(key);

//        判断缓存中是否有数据
        if (object != null) {
            return object;
        } else {
            object = proceedingJoinPoint.proceed();

//            将Key装入容器中，当缓存要变化时，可以使用。
            orderKye.add(key);

            redisUtils.set(key, object);
        }

        return object;

    }

    //    在拼车订单变化时移除拼车订单缓存
    @Around("execution(* com.gzasc.onlinecarhailing.service.impl.OrderServiceImpl.add*(..))" +
            "||" +
            "execution(* com.gzasc.onlinecarhailing.service.impl.OrderServiceImpl.remove*(..))" +
            "||" +
            "execution(* com.gzasc.onlinecarhailing.service.impl.OrderServiceImpl.mod*(..))")
    public Object removeCanJoinOrder(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

//        当调用方法时，就说明要删除缓存了。
        if (!orderKye.isEmpty()){

//            缓存不为空。

            redisUtils.delete(ticketKey);

            return proceedingJoinPoint.proceed();

        }else {

            return proceedingJoinPoint.proceed();
        }


    }

}
