package org.alvin.md5.md5_len20;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @类说明 [长度20以内的md5全集]实体类
 * @author 高振中
 * @date 2018-12-26 18:17:07
 **/
@Setter
@Getter
//@Accessors(chain = true)
@Builder	
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MD5_Len20", description = "长度20以内的md5全集实体")
public class MD5_Len20 {
    // 数据库中的字段
	@ApiModelProperty(value = "主键", dataType = "Long")
	private Long id; // 主键
	@ApiModelProperty(value = "键", dataType = "String")
	private String data_key; // 键
	@ApiModelProperty(value = "md5-16", dataType = "String")
	private String md5_16; // md5-16
	@ApiModelProperty(value = "md5-32", dataType = "String")
	private String md5_32; // md5-32
    // 此处可添加查询显示辅助字段
}