package com.example.javastudy;

import com.example.javastudy.designMode.Response;
import com.example.javastudy.designMode.chainMode.User;
import com.example.javastudy.designMode.policyMode.Order;
import com.example.javastudy.designMode.policyMode.Payment;
import com.example.javastudy.designMode.policyMode.PaymentEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.math.BigDecimal;
import java.util.*;

@SpringBootTest
class JavaStudyApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        Order order = new Order();
        order.setPayType("Wechat");
        order.setAmount(new BigDecimal("100"));
        Payment payment = applicationContext.getBean(order.getPayType(), Payment.class);
        Response pay = payment.pay(order);

        System.out.println(pay);

    }

    @Test
    void testForeach(){
        Map<String, List<User>> map = new HashMap<>();
        List<User> u1 = new ArrayList<>();
        User user1 = new User("a","a",1);
        User user2 = new User("b","a",2);
        u1.add(user1);
        u1.add(user2);
        map.put("user1",u1);

        List<User> u2 = new ArrayList<>();
        User user3 = new User("c","q",1);
        User user4 = new User("d","q",2);
        User user5 = new User("d","q",2);
        u2.add(user3);
        u2.add(user4);
        u2.add(user5);
        map.put("user2",u2);
//        map.forEach((string,list)->{
//            System.out.println(string);
//            System.out.println(list.size());
//        });

        Map<String,String> map1 = new HashMap<>();
        for(Map.Entry<String, List<User>> entry:map.entrySet()){
            System.out.println(entry.getKey()+"--->"+entry.getValue());
        }
    }

    @Test
    public void testDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        System.out.println(calendar.getTime());
    }

    @Test
    public void test(){
        int[] x = {2,3,4,51,1,9,18};
        int n = x.length;
        int target = 4;
        int res = binarySearch(x, n, target);
        System.out.println(res);
    }

    int binarySearch(int x[], int n, int target) {
        int left = 0;
        int right = n - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (x[mid] == target) {
                return mid;
            }
            else if (x[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        // 没找到
        return -1;
    }

    @Test
    public void campare(){
        Float f = 1f;
        int b = 10;
        if (f<b){
            System.out.println("f大于b");
        }
    }

}
