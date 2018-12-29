package org.alvin.md5.md5_len20;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 高振中
 * @类说明 [长度20以内的md5全集]控制器
 * @date 2018-12-26 18:17:07
 **/
@RestController
@RequestMapping("mD5_Len20")
public class MD5_Len20Controller {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private MD5_Len20Service service; //注入长度20以内的md5全集数据逻辑层

	/**
	 * @方法说明 新增[长度20以内的md5全集]记录
	 */
	@PostMapping("save")
	public int save(@RequestBody MD5_Len20 mD5_Len20) {
		return service.save(mD5_Len20);
	}

//
//	@GetMapping
//	public MD5_Len20 findBySecret(@RequestParam("secret") String secret) {
//		MD5_Len20Cond md5_len20Cond = new MD5_Len20Cond();
//		md5_len20Cond.setSecret(secret);
//	}


//    /**
//     * @方法说明 删除长度20以内的md5全集记录(多条)
//     */
//	@PostMapping("delete")
//	public int delete(@RequestParam("ids[]") Long ids[]) {
//		return service.delete(ids);
//	}
//
//    /**
//     * @方法说明 修改长度20以内的md5全集记录
//     */
//	@PostMapping("update")
//	public int update(@RequestBody MD5_Len20 mD5_Len20) {
//		return service.update(mD5_Len20);
//	}
//
//    /**
//     * @方法说明 按条件查询分页长度20以内的md5全集列表
//     */
//	@PostMapping("queryPage")
//	public Page<MD5_Len20> queryPage(@RequestBody MD5_Len20Cond cond ){
//		return service.queryPage(cond);
//	}
//
//    /**
//     * @方法说明 按条件查询不分页长度20以内的md5全集列表
//     */
//	@PostMapping("queryList")
//	public List<MD5_Len20> queryList(@RequestBody MD5_Len20Cond cond ){
//		return service.queryList(cond);
//	}
//
//    /**
//     * @方法说明 按主键查单个长度20以内的md5全集记录
//     */
//	@PostMapping("findById")
//	public MD5_Len20 findById(@RequestParam("id") Long id) {
//		return service.findById(id);
//	}
//
//    /**
//     * @方法说明 按条件查询长度20以内的md5全集记录个数
//     */
//	@PostMapping("queryCount")
//	public long queryCount(@RequestBody MD5_Len20Cond cond ){
//		return service.queryCount(cond);
//	}
}