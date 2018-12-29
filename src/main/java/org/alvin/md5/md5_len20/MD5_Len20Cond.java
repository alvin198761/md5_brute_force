package org.alvin.md5.md5_len20;

import com.google.common.base.Strings;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
//import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.alvin.code.gen.beans.BaseCondition;

/**
 * @类说明 [长度20以内的md5全集]查询条件实体
 * @author: 高振中
 * @date : 2018-12-26 18:17:07
 **/
@Setter
@Getter
//@Accessors(chain = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "MD5_Len20Cond", description = "长度20以内的md5全集查询条件实体")
public class MD5_Len20Cond extends BaseCondition {

	/**
	 * @方法说明: 拼加自定义条件
	 **/
	@Override
	public void addCondition() {
		add(id, "AND t.id = ?");
		add(data_key, "AND t.data_key LIKE ?", 3);
		add(md5_16, "AND t.md5_16 LIKE ?", 3);
		add(md5_32, "AND t.md5_32 LIKE ?", 3);
		add(len, "AND LENGTH(t.data_key) = ?");
		// add(ids, "AND t.id IN ");
	}

	// 查询条件
	@ApiModelProperty(hidden = true)
	private Long id; // 主键
	@ApiModelProperty(hidden = true)
	private String data_key; // 键
	@ApiModelProperty(hidden = true)
	private String md5_16; // md5-16
	@ApiModelProperty(hidden = true)
	private String md5_32; // md5-32
	// private List<Long> ids;// 主键列表
	@ApiModelProperty(hidden = true)
	private Integer len; //长度
	@ApiModelProperty(hidden = true)
	private String secret;
}