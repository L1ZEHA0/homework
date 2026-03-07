package com.itheima.varible;

public class VaribleDemo1 {
    public static void main(String[] args) {
       /* 微信余额：0元
        支付宝余额：10元
        银行卡余额：20元
        问题一：请问现在一共有多少钱？
        问题二：微信收了10元红包。又发了两元红包，余额多少？*/


        //1.定义一个变量，用来记录微信的余额
        double a = 0;

        //2.定义一个变量，用来记录支付宝的余额
        double b = 10;

        //3.定义一个变量，用来记录银行卡的余额
        double c = 20;

        //4.打印现在总共有多少钱
        System.out.println("现在总共有多少钱：" + (a + b + c));

        //5.微信收了10元红包
       a = a + 10;

       a = a - 2;
        System.out.println(a);
    }
}
