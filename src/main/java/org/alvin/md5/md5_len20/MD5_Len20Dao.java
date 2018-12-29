package org.alvin.md5.md5_len20;

import org.alvin.code.gen.beans.BaseDao;
import org.alvin.code.gen.utils.Page;
import org.alvin.code.gen.utils.SqlUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 高振中
 * @类说明 [长度20以内的md5全集]数据访问层
 * @date 2018-12-26 18:17:07
 **/
@Repository
public class MD5_Len20Dao extends BaseDao {

	private StringBuilder insert = new StringBuilder();
	private StringBuilder select = new StringBuilder();

	/**
	 * @方法说明 构造方法-拼加SQL
	 */
	public MD5_Len20Dao() {
		select.append("SELECT t.id,t.data_key,t.md5_16,t.md5_32");
		select.append(" FROM md5_len20 t WHERE 1=1");

		insert.append("INSERT INTO md5_len20 (data_key,md5_16,md5_32) ");
		insert.append(" VALUES (:data_key,:md5_16,:md5_32)");
	}

	/**
	 * @方法说明 新增长度20以内的md5全集记录
	 */
	public int save(MD5_Len20 vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("REPLACE INTO md5_len20 (id,data_key,md5_16,md5_32)");
		sql.append(" VALUES (?,?,?,?)");
		Object[] params = {vo.getId(), vo.getData_key(), vo.getMd5_16(), vo.getMd5_32()};
		//logger.info(SqlUtil.showSql(sql.toString(), params));//显示SQL语句
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 新增长度20以内的md5全集记录并返回自增涨主键值
	 */
	public long saveReturnPK(MD5_Len20 vo) {
		return saveKey(vo, insert.toString(), "id");
	}

	/**
	 * @方法说明 批量插入长度20以内的md5全集记录
	 */
	public int[] insertBatch(List<MD5_Len20> list) {
		return batchOperate(list, insert.toString());
	}

	/**
	 * @方法说明 物理删除长度20以内的md5全集记录(多条)
	 */
	public int delete(Long ids[]) {
		String sql = "DELETE FROM md5_len20 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * @方法说明 更新长度20以内的md5全集记录
	 */
	public int update(MD5_Len20 vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE md5_len20 SET data_key=?,md5_16=?,md5_32=? ");
		sql.append(" WHERE id=? ");
		Object[] params = {vo.getData_key(), vo.getMd5_16(), vo.getMd5_32(), vo.getId()};
		return jdbcTemplate.update(sql.toString(), params);
	}

	/**
	 * @方法说明 按条件查询分页长度20以内的md5全集列表
	 */
	public Page<MD5_Len20> queryPage(MD5_Len20Cond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(cond.getOrderSql());//增加排序子句;
		//logger.info(SqlUtil.showSql(sb.toString(),cond.getArray()));//显示SQL语句
		return queryPage(sb.toString(), cond, MD5_Len20.class);
	}

	/**
	 * @方法说明 按条件查询不分页长度20以内的md5全集列表
	 */
	public List<MD5_Len20> queryList(MD5_Len20Cond cond) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(cond.getCondition());
		//sb.append(" ORDER BY operate_time DESC");
		return jdbcTemplate.query(sb.toString(), cond.getArray(), new BeanPropertyRowMapper<>(MD5_Len20.class));
	}

	/**
	 * @方法说明 按ID查找单个长度20以内的md5全集实体
	 */
	public MD5_Len20 findById(Long id) {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" AND t.id=?");
		return jdbcTemplate.queryForObject(sb.toString(), new Object[]{id}, new BeanPropertyRowMapper<>(MD5_Len20.class));
	}

	/**
	 * @方法说明 按条件查询长度20以内的md5全集记录个数
	 */
	public long queryCount(MD5_Len20Cond cond) {
		String countSql = "SELECT COUNT(1) FROM md5_len20 t WHERE 1=1" + cond.getCondition();
		return jdbcTemplate.queryForObject(countSql, cond.getArray(), Long.class);
	}

	/**
	 * @方法说明 按条件查询长度20以内的md5全集记录个数
	 */
	public int deleteLogic(Long ids[]) {
		String sql = "UPDATE md5_len20 SET delete_remark=1 WHERE id" + SqlUtil.ArrayToIn(ids);
		return jdbcTemplate.update(sql);
	}

	/**
	 * 获取最大数据
	 *
	 * @return
	 */
	public MD5_Len20 findMax() {
		StringBuilder sb = new StringBuilder(select);
		sb.append(" ORDER BY id desc LIMIT 0,1");
		return jdbcTemplate.queryForObject(sb.toString(), new BeanPropertyRowMapper<>(MD5_Len20.class));
	}

}