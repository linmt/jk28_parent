package cn.itcast.jk.action.stat;

import cn.itcast.jk.action.BaseAction;
import cn.itcast.jk.dao.springdao.SqlDao;
import cn.itcast.jk.utils.file.FileUtil;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class StatChartAction extends BaseAction {
	
	//最好要提供StatChartService接口及实现类
	private SqlDao sqlDao;//为了省事
	public void setSqlDao(SqlDao sqlDao) {
		this.sqlDao = sqlDao;
	}

	/**
	 * 厂家的销量排名(旧版amchart实现)
	 */
	public String factorysale() throws Exception {
	    String sql="select factory_name,sum(amount) samount from contract_product_c group by factory_name order by samount desc";

		//1.执行sql语句，得到统计结果
		List<String> list = sqlDao.executeSQL(sql);

	    //2.组织符合要求的xml数据
		StringBuilder sb=new StringBuilder();
		sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		sb.append("<pie>");

		for(int i=0;i<list.size();i++){
			sb.append("<slice title=\""+list.get(i)+"\" pull_out=\"true\">"+list.get(++i)+"</slice>");
		}

		sb.append("</pie>");
	    
	    //3.将拼接好的字符串写入data.xml文件中
		FileUtil fileUtil = new FileUtil();
		String sPath = ServletActionContext.getServletContext().getRealPath("/");
		fileUtil.createTxt(sPath,"stat\\chart\\factorysale\\data.xml",sb.toString(),"UTF-8");

		return "factorysale";
	}
}
