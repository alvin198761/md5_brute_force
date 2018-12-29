package org.alvin.md5.config;

import com.google.common.collect.Lists;
import org.alvin.code.gen.utils.Page;
import org.alvin.md5.md5_len20.MD5_Len20;
import org.alvin.md5.md5_len20.MD5_Len20Cond;
import org.alvin.md5.md5_len20.MD5_Len20Service;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@SpringBootConfiguration
public class AppConfig implements InitializingBean {

	@Value("${md5.key.min}")
	private int min;
	@Value("${md5.key.max}")
	private int max;

	private List<String> wordList = Lists.newArrayList();
	@Autowired
	private MD5_Len20Service md5_len20Service;

	private int index = -1; //重启的问题

	//	@Autowired
//	private ConcurrentLinkedQueue<List<String>> concurrentLinkedQueue;
	@Autowired
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;

	//
//	@Bean
//	public ConcurrentLinkedQueue<List<String>> concurrentLinkedQueue() {
//		return new ConcurrentLinkedQueue<>();
//	}
//
	@Bean
	public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(5);
		pool.setMaxPoolSize(10);
		return pool;
	}

	public AppConfig() {
		//初始化字符
		for (int i = 33; i <= 127; i++) {
			wordList.add(new String(Character.toChars(i)));
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		new Thread(this::producer).start();
//		new Thread(this::consumer).start();
	}

	private void producer() {
//		//重启问题
//		if (md5_len20Service.queryCount(new MD5_Len20Cond()) > 0) {
//			MD5_Len20 md5_len20 = this.md5_len20Service.queryMax();
//			//
//			this.min = md5_len20.getData_key().length();
//			String suffix = md5_len20.getData_key().substring(md5_len20.getData_key().length() - 1);
//			index = wordList.indexOf(suffix);
//		}

		System.out.println("开始执行滚雪球算法： min = " + min + "  max=" + max);
		//启动滚雪球算法
		for (int i = min; i <= max; i++) {
			System.out.println("当前长度：" + i);
			if (md5_len20Service.queryCount(new MD5_Len20Cond()) == 0) {
				md5_len20Service.saveAll(wordList);
			} else {
				saveByLen(i);
			}
		}
	}

	/**
	 * 根据长度滚雪球
	 *
	 * @param len
	 */
	public void saveByLen(int len) {
		int page = 0;
		Page<MD5_Len20> pageData;
//		List<String> subWordList;
//		if (index != -1) {
//			subWordList = wordList.subList(index + 1, wordList.size() - 1);
//			index = -1;
//		} else {
//			subWordList = wordList;
//		}
		do {
			//更长度查询数据 分页，一次100条
			MD5_Len20Cond md5_len20Cond = new MD5_Len20Cond();
			md5_len20Cond.setLen(len - 1);
			md5_len20Cond.setPage(page);
			md5_len20Cond.setSize(100);
			pageData = this.md5_len20Service.queryPage(md5_len20Cond);
			System.out.println("当前页码：" + (page + 1) + " 当前长度：" + len);
			//分页
			page++;
			//批量执行任务
			pageData.getContent().stream().map(item -> {
				return threadPoolTaskExecutor.submit(() -> {
					md5_len20Service.saveAll(wordList.stream().map(w -> item.getData_key().concat(w)).collect(Collectors.toList()));
				});
			}).forEach(item -> {
				//因为是滚雪球，后面的逻辑依赖前面的逻辑，必须等这一轮执行完毕，才能进行下一轮
				try {
					item.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			});
		} while (page < pageData.getTotalPages());
	}

//	/**
//	 * 队列消费
//	 */
//	public void consumer() {
//		while (true) {
//			//五秒后开始消费
//			try {
//				TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			//消费结束后，进入下一次等待
//			while (!concurrentLinkedQueue.isEmpty()) {
//				threadPoolTaskExecutor.submit(() -> md5_len20Service.saveAll(concurrentLinkedQueue.poll()));
//			}
//		}
//	}

}
