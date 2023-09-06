package com.ctrip.demo.util;

import com.ctrip.demo.entity.GsonReflectEntity;
import org.junit.Test;

import static org.junit.Assert.*;

public class GsonUtilsTest {

    @Test
    public void serializeTest() throws IllegalAccessException, InstantiationException {
        GsonReflectEntity entity = new GsonReflectEntity();
        System.out.println(entity);

        Class<GsonReflectEntity> clazz = GsonReflectEntity.class;
        GsonReflectEntity entity1 = clazz.newInstance();
        System.out.println(entity1);

        String ss = "{\"id\":4}";
        GsonReflectEntity entity2 = GsonUtils.json2T(ss, GsonReflectEntity.class);
        System.out.println(entity2);

    }

}