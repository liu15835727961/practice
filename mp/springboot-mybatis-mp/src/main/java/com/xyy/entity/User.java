package com.xyy.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.xyy.enums.SexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName mybatis-plus用来对应数据库表名的
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
//@TableName("tb_user")
@KeySequence(value = "SQE_USER1")
public class User  extends Model<User> {
    /**
     * mybatis-plus的ID策略默认是none
     */
    //@TableId(type=IdType.AUTO)
    private Long id;
    private String userName;
    //插入数据时进行填充:fill = FieldFill.INSERT
    @TableField(select = false,fill = FieldFill.INSERT)//设置该字段不会被查询:select = false
    private String password;
    private String name;
    private Integer age;
    @TableField(value = "email")//指定数据库的字段名
    private String email;
    @TableField(exist = false)//说明该字段在数据库中不存在
    private String address;
    @Version//乐观锁的版本,数据库也需要添加这个字段
    private Integer version;
    @TableLogic//逻辑删除字段,1删除0未删除
    private Integer deleted;
    private SexEnum sex;//性别,枚举类型
}
