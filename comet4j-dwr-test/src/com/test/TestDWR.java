package com.test;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.comet4j.core.CometContext;
import org.comet4j.core.CometEngine;

import com.test.TestComet.HelloAppModule;

public class TestDWR implements ServletContextListener {

	private int count;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		Thread helloAppModule = new Thread(new HelloAppModule(), "Sender DWR Module");
		// 是否启动
		helloAppModule.setDaemon(true);
		// 启动线程
		helloAppModule.start();
	}

	class HelloAppModule implements Runnable {
		public void run() {
			while (true) {
				try {
					// 睡眠时间
					Thread.sleep(2000);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				// 获取消息内容
				 SendMsg.sendMsg("-----"+count);
				 count++;
				// 开始发送
			}
		}
	}

	public long getFreeMemory() {
		return Runtime.getRuntime().freeMemory() / 1024;
	}
}
