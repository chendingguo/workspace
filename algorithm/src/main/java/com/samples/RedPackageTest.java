package com.samples;

import java.util.Random;

public class RedPackageTest {

    public static double getRandomMoney(RedPackage _redPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_redPackage.remainSize == 1) {
            _redPackage.remainSize--;
            return (double) Math.round(_redPackage.remainMoney * 100) / 100;
        }
        Random r = new Random();
        double min = 0.01; //
        double max = _redPackage.remainMoney / _redPackage.remainSize * 2;
        double money = r.nextDouble() * max;
        money = money <= min ? 0.01 : money;
        money = Math.floor(money * 100) / 100;
        _redPackage.remainSize--;
        _redPackage.remainMoney -= money;
        return money;
    }




    public static void main(String[] args) {
        RedPackage redPackage=new RedPackage();
        redPackage.remainSize  = 5;
        redPackage.remainMoney = 20;
        while(redPackage.remainSize>0){
            System.out.println(getRandomMoney(redPackage));
        }
    }
}

class RedPackage {
    int remainSize;
    double remainMoney;
}
