package org.alvin.md5.md5_len20;

import java.util.List;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.hash.Hashing;
import org.alvin.code.gen.utils.Page;
import org.alvin.md5.utils.Md5Utils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 高振中
 * @类说明 [长度20以内的md5全集]业务逻辑层
 * @date 2018-12-26 18:17:07
 **/
@Service
public class MD5_Len20Service {

	@SuppressWarnings("unused")
	private Log logger = LogFactory.getLog(getClass());
	@Autowired
	private MD5_Len20Dao dao; //注入长度20以内的md5全集数据访问层

	/**
	 * @方法说明 新增[长度20以内的md5全集]记录
	 */
	@Transactional
	public int save(MD5_Len20 mD5_Len20) {
		return dao.save(mD5_Len20);
	}

	/**
	 * @方法说明 删除长度20以内的md5全集记录(多条)
	 */
	public int delete(Long ids[]) {
		//return dao.deleteLogic(ids);//逻辑删除
		return dao.delete(ids);//物理删除
	}

	/**
	 * @方法说明 更新长度20以内的md5全集记录
	 */
	@Transactional
	public int update(MD5_Len20 mD5_Len20) {
		return dao.update(mD5_Len20);
	}

	/**
	 * @方法说明 按条件查询分页长度20以内的md5全集列表
	 */
	public Page<MD5_Len20> queryPage(MD5_Len20Cond cond) {
		return dao.queryPage(cond);
	}

	/**
	 * @方法说明 按条件查询不分页长度20以内的md5全集列表
	 */
	public List<MD5_Len20> queryList(MD5_Len20Cond cond) {
		return dao.queryList(cond);
	}

	/**
	 * @方法说明 按主键查找单个长度20以内的md5全集记录
	 */
	public MD5_Len20 findById(Long id) {
		return dao.findById(id);
	}

	/**
	 * @方法说明 按条件查询长度20以内的md5全集记录个数
	 */
	public long queryCount(MD5_Len20Cond cond) {
		return dao.queryCount(cond);
	}

	/**
	 * 批量保存
	 *
	 * @param wordList
	 */
	@Transactional
	public void saveAll(List<String> wordList) {
		System.out.println("批量保存数量：" + wordList.size());
		List<MD5_Len20> list = wordList.stream().map(item -> {
			MD5_Len20 md5_len20 = new MD5_Len20();
			md5_len20.setData_key(item);
			md5_len20.setMd5_16(Md5Utils.encrypt16(item).toUpperCase());
			md5_len20.setMd5_32(Md5Utils.encrypt32(item).toUpperCase());
			return md5_len20;
		}).collect(Collectors.toList());
		this.dao.insertBatch(list);
	}

	public MD5_Len20 queryMax() {
		return this.dao.findMax();
	}
}