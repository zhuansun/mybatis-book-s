package com.blog4java.mybatis.statis;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 代理类，饲养员
 *
 * @author : zhuansun
 * @date : 2021-02-03 18:49
 **/
@Data
@AllArgsConstructor
public class FeederProxy implements Cat{


    private Cat cat;


    @Override
    public String eatFood(String foodName) {
        return cat.eatFood(foodName);
    }

}
