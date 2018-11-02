/**
 * 
 */
package com.czx.demo.seckill;

import com.czx.demo.seckill.KillService;

/**
 * @author -半藏-
 *
 */
public class People extends Thread{

	private KillService service;

    public People(KillService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}
