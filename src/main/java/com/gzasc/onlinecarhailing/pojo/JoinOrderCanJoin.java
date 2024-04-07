package com.gzasc.onlinecarhailing.pojo;

// 判断乘客发出的拼单订单是否可以拼单的类。主要作用是通过属性名让我知道哪个变量是用来判断可以的，要不就0和1，可能会弄混了。
public class JoinOrderCanJoin {
////    控制是否拼单订单的属性，不知道要不要。
//    非拼单订单：-1
    public static final Integer NON_JOIN_ORDER = -1;
//    可以拼单的订单：1
    public static final Integer CAN_JOIN_ORDER = 1;
//    不可以拼单的订单：0
    public static final Integer NO_CAN_JOIN_ORDER = 0;
}
