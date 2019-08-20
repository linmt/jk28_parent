package cn.itcast.jk.job;

import java.util.Date;

public class MailJob {
	public void send() throws Exception{
		System.out.println("任务执行完成了："+new Date());
	}
}
