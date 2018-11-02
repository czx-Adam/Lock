/**
 * 
 */
package com.czx.demo.redislock;

/**
 * @author -半藏-
 *
 */
public class ThreadA extends Thread {
    private Service service;

    public ThreadA(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.seckill();
    }
}

