package com.test;

import java.util.Collection;

import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;
import org.directwebremoting.WebContextFactory;
import org.directwebremoting.proxy.dwr.Util;
import org.directwebremoting.Browser;

public class SendMsg {

	// @SuppressWarnings("deprecation")
	// public static void sendMsg(String msg) {
	// // 得到上下文
	// WebContext contex = (WebContext) WebContextFactory.get();
	//
	// // 得到要推送到 的页面 dwr3为项目名称 ， 一定要加上。
	// Collection<ScriptSession> sessions =
	// contex.getScriptSessionsByPage("/showMsg.jsp");
	//
	// // 不知道该怎么解释这个 ，
	// Util util = new Util(sessions);
	//
	// // 下面是创建一个javascript脚本 ， 相当于在页面脚本中添加了一句 show(msg);
	// ScriptBuffer sb = new ScriptBuffer();
	// sb.appendScript("show(");
	// sb.appendData(msg);
	// sb.appendScript(")");
	//
	// // 推送
	// util.addScript(sb);
	// }
	@SuppressWarnings("deprecation")
	public static void sendMsg(final String msg) {
		Runnable run = new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();

			public void run() {
				// 设置要调用的 js及参数
				script.appendCall("show", msg);
				// 得到所有ScriptSession
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				// 遍历每一个ScriptSession
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		};
		// 执行推送
		Browser.withAllSessions(run);
	}
}