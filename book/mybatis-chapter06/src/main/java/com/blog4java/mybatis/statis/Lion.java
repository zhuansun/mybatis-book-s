package com.blog4java.mybatis.statis;

import lombok.Data;

/**
 * 委托类狮子，实现代理接口的具体行为
 *
 * @author : zhuansun
 * @date : 2021-02-03 18:46
 **/
@Data
public class Lion implements Cat {

    private String name;

    @Override
    public String eatFood(String foodName) {
        String eat = "一个名叫 " + this.name + " 的狮子吃食物：" + foodName;
        System.out.println(eat);
        return eat;
    }

}
