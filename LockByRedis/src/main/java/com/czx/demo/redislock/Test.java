/**
 * 
 */
package com.czx.demo.redislock;

/**
 * @author -半藏-
 *
 */
public class Test {

	public static void main(String[] args) {
        Service service = new Service();
        for (int i = 0; i < 5; i++) {
            ThreadA threadA = new ThreadA(service);
            threadA.start();
        }
    }
}
