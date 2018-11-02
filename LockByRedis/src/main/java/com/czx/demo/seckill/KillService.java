/**
 * 
 */
package com.czx.demo.seckill;

import java.io.IOException;

import com.czx.demo.redislock.DistributedLock;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author -半藏-
 *
 */
public class KillService {

	private static JedisPool pool = null;

	private DistributedLock lock = new DistributedLock(pool);

	static int n = 0;

	static {
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数
		config.setMaxTotal(200);
		// 设置最大空闲数
		config.setMaxIdle(8);
		// 设置最大等待时间
		config.setMaxWaitMillis(1000 * 100);
		// 在borrow一个jedis实例时，是否需要验证，若为true，则所有jedis实例均是可用的
		config.setTestOnBorrow(true);
		pool = new JedisPool(config, "127.0.0.1", 6379, 3000);
	}

	public void seckill() {

		Jedis jedis = null;
		try {
			// 返回锁的value值，供释放锁时候进行判断
			String identifier = lock.lockWithTimeout("resource", 5000, 10);
			if(null == identifier) {
				throw new Exception("网络异常");
			}
			System.out.println(Thread.currentThread().getName() + "获得了锁" + identifier);
			jedis = lock.getJedis();
			int size = Integer.valueOf(jedis.get("goods"));
			if (size > 0 && jedis.decr("goods") >= 0) {
				++n;
				System.out.println("人數：" + n);
				System.out.println(Thread.currentThread().getName() + "抢到了");
			} else {
				System.out.println("货品已售罄！！！！！");
			}
			lock.releaseLock("resource", identifier);
			System.out.println(Thread.currentThread().getName() + "釋放了锁");
		} catch (Exception e) {
			// 异常资源关闭
			if (jedis != null) {
				pool.returnBrokenResource(jedis);
			}
			System.out.println("网络异常！！！！！");
		} finally {
			if (jedis != null) {
				pool.returnResource(jedis);
			}
		}
	}

}
