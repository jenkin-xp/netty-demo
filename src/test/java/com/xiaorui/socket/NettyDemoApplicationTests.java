package com.xiaorui.socket;

import com.xiaorui.socket.base.util.RedisUtils;
import com.xiaorui.socket.entity.Goods;
import com.xiaorui.socket.mapper.GoodsMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class NettyDemoApplicationTests extends BaseTest{

    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testMapper() {
        Goods goods = goodsMapper.selectById(11);
        System.out.println(goods);
    }

    @Test
    public void testRedis() {
        System.out.println(redisUtils.hasKey("123"));
    }

}
