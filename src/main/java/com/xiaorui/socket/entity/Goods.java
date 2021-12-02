package com.xiaorui.socket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("goods")
public class Goods extends Model<Goods> {

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    private String name;

    @TableField("goods_type")
    private String goodsType;

    private int hp;

    private int damage;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
