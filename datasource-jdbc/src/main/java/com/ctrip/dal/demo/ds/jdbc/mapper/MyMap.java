package com.ctrip.dal.demo.ds.jdbc.mapper;

import com.ctrip.dal.demo.ds.jdbc.entity.Dog;
import com.ctrip.dal.demo.ds.jdbc.entity.Person;

public class MyMap<T, K> implements BaseMap<T, String, K> {

    @Override
    public void func(T t, String s, K k) {
        System.out.println(t.getClass());
        System.out.println(k.getClass());

    }

}
