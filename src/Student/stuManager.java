package Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import admin.Stumssg;
import teacher.Item1;

public class stuManager {
	String drivername;
	String url;
	String user;
	String pwd;
	Connection con;
	
	//����һ���������ݿ�ķ���
	public void Manager() throws ClassNotFoundException, SQLException {
		drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		url = "jdbc:sqlserver://localhost:1433;DatabaseName=system";
		user = "sa";
		pwd = "123456";
		
		Class.forName(drivername);
		con = DriverManager.getConnection(url, user, pwd);
	}
	
	//�޸�����
	public void SupdatePwd(Stumssg mssg) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 学生 set 密码=? where 学号=?";
		PreparedStatement pst = con.prepareStatement(sql);
		
		pst.setString(1, mssg.getSpwd());
		pst.setString(2, mssg.getSid());
		pst.executeUpdate();
	}
	
	//����ѧ�Ų�ѯѧ����Ϣ
	public Stumssg stuQbyid(String sid) throws ClassNotFoundException, SQLException {
		Manager();
		Stumssg mssg = new Stumssg();
		String sql = "select * from 学生 where 学号='"+sid+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		while(res.next()) {
			mssg.setSid(res.getString("学号"));
			mssg.setSpwd(res.getString("密码"));
			mssg.setSname(res.getString("姓名"));
			mssg.setSsex(res.getString("性别"));
			mssg.setSmajor(res.getString("专业"));
			mssg.setSyear(res.getString("年级"));
			mssg.setSemail(res.getString("邮件"));
			mssg.setStell(res.getString("联系电话"));
			mssg.setSgrade(res.getString("绩点"));
			mssg.setIid(res.getString("选题题目编号"));
		}
		return mssg;
	}
	
	//�޸�ѧ���ĵ绰������
	public void StuInfo(Stumssg mssg) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 学生 set 邮件=?,联系电话=? where 学号=?";
		PreparedStatement pst = con.prepareStatement(sql);
		
		pst.setString(1, mssg.getSemail());
		pst.setString(2, mssg.getStell());
		pst.setString(3, mssg.getSid());
		pst.executeUpdate();
	}
	
	//������Ŀ��Ų�ѯ��Ŀ��Ϣ
	public Item1 IQbyid(String iid) throws ClassNotFoundException, SQLException {
		Manager();
		Item1 item1 = new Item1();
		String sql = "select * from 题目 where 题目编号='"+iid+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		
		while(res.next()) {
			item1.setIid(res.getString("题目编号"));
			item1.setIname(res.getString("题目名字"));
			item1.setTid(res.getString("教师工号"));
			item1.setIkind(res.getString("类别"));
			item1.setIshow(res.getString("简介"));
			item1.setItool(res.getString("开发工具"));
		}
		return item1;
		
	}
	
	//Ϊѡ�α����ȷ����Ϣ
	public void SCinsert(SC sc) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "insert into 选题 (学生确认,学号,教师工号,题目编号) values(?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		sc.setStrue("true");
		pst.setString(1, sc.getStrue());
		pst.setString(2, sc.getSid());
		pst.setString(3, sc.getTid());
		pst.setString(4, sc.getIid());
		pst.executeUpdate();
	}
	
	
	
	//��ʦȷ��
	public void TeaSCadd(String sid) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 选题 set 老师确认='true' where 学号='"+sid+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.executeUpdate();
	}
	
	//����ѧ����ʾѡ����Ϣ
	public SC SCquery(String sid) throws ClassNotFoundException, SQLException {
		Manager();
		SC sc = new SC();
		String sql = "select 选题.题目编号,题目.题目名字,选题.教师工号,选题.老师确认,选题.学生确认 from 选题,题目 where 题目.题目编号=选题.题目编号 and 选题.学号='"+sid+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		while(res.next()) {
			sc.setIid(res.getString("题目编号"));
			sc.setTtrue(res.getString("老师确认"));
			sc.setTid(res.getString("教师工号"));
			sc.setSid(res.getString("题目名字"));
			sc.setStrue(res.getString("学生确认"));
		}
		return sc;
	}
	
	//���ݹ�����ʾѡ����Ϣ
	public SCC SCTquery(String tid) throws ClassNotFoundException, SQLException {
		Manager();
		SCC scc = new SCC();
		String sql = "select 选题.题目名字,题目.题目编号,选题.教师工号,学生.学号,选题.老师确认,选题.学生确认 from 选题,题目,学生 where 题目.题目名字=选题.题目名字 and 选题.学号=学生.学号 and 选题.教师工号='"+tid+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		List<String> list5 = new ArrayList<String>();
		List<String> list6 = new ArrayList<String>();
		while(res.next()) {
			list1.add(res.getString("题目编号"));
			list2.add(res.getString("题目名字"));
			list3.add(res.getString("教师工号"));
			list4.add(res.getString("学号"));
			list5.add(res.getString("学生确认"));
			list6.add(res.getString("老师确认"));
		}
		scc.setIid(list1);
		scc.setTtrue(list2);
		scc.setTid(list3);
		scc.setSid(list4);
		scc.setStrue(list5);
		scc.setAtrue(list6);
		return scc;
	}
	//��ѯѡ������
	public SCC SCcount(String sid) throws ClassNotFoundException, SQLException {
		Manager();
		SCC scc = new SCC();
		String sql = "select count(*) as 题目编号 from 选题 where 老师确认='true' and 学生确认='true' and 教师工号=(select 教师工号 from 选题 where 题目编号=(select 题目编号 from 选题 where 学号='"+sid+"'))";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		List<String> list1 = new ArrayList<String>();
		while(res.next()) {
			list1.add(res.getString("题目编号"));
		}
		scc.setIid(list1);
		return scc;
	}
	//ɾ��ѡ����Ϣ
	public void SCdelete(String sid) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "delete from 选题 where 学号=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, sid);
		pst.executeUpdate();
	}
	
	//��ѯ�����Ӧ����
	public SCC gradeC() throws ClassNotFoundException, SQLException {
		Manager();
		SCC scc = new SCC();
		String sql = "select count(*) as 绩点 from 学生 where 绩点<=2.5 union select count(*) from 学生 where 绩点>=3.5";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		List<String> list1 = new ArrayList<String>();
		while(res.next()) {
			list1.add(res.getString("绩点"));
		}
		scc.setSid(list1);
		return scc;
	}
	
}
