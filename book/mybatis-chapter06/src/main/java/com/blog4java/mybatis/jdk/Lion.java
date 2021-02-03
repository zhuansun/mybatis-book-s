package com.blog4java.mybatis.jdk;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author : zhuansun
 * @date : 2021-02-03 19:09
 **/
@AllArgsConstructor
@Data
public class Lion implements Cat{

    private String name;

    @Override
    public String eatFood(String foodName) {
        String eat = "一个名叫 " + this.name + " 的狮子吃食物：" + foodName;
        System.out.println(eat);
        return eat;
    }

}
