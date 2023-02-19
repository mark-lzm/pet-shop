package com.litchi.petshop.foster;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Author Mark
 * 2023/2/15 22:48
 */
public class Test {
    public static void main(String[] args) {
        Date createTime = new Date();
        Date endTime = new Date(123,1,16,22,52,11);
        long diff = endTime.getTime() - createTime.getTime();
        System.out.println(diff/1000/60/60);
        TimeUnit time = TimeUnit.DAYS;
        long diffrence = time.convert(diff, TimeUnit.MILLISECONDS);
        System.out.println(diffrence);

    }
}
