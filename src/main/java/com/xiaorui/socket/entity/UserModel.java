package com.xiaorui.socket.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class UserModel extends Model<UserModel> {

    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    private String roleId;

    @TableField("device_id")
    private String deviceId;

    @TableField("register_date")
    private Date registerDate;

    @TableField("update_time")
    private Date updateTime;

    @TableField("is_delete")
    private Boolean isDelete;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
