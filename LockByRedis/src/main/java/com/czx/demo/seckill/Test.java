/**
 * 
 */
package com.czx.demo.seckill;

import com.czx.demo.seckill.KillService;

/**
 * @author -半藏-
 *
 */
public class Test {

	public static void main(String[] args) {
        
		KillService service = new KillService();
        for (int i = 0; i < 1000; i++) {
        	People threadA = new People(service);
            threadA.start();
        }
	}
}
