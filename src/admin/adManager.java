package admin;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import teacher.Item;
import teacher.Item1;
import teacher.TeaAll;
import teacher.Teacher;

public class adManager {
	String drivername;
	String url;
	String user;
	String pwd;
	Connection con;
	//�������ݿ�
	public void Manager() throws ClassNotFoundException, SQLException {
		drivername = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		url = "jdbc:sqlserver://localhost:1433;DatabaseName=system";
		user = "sa";
		pwd = "123456";
		
		Class.forName(drivername);
		con = DriverManager.getConnection(url, user, pwd);
	}
	
	//����ѧ��
	public void addStu(Stumssg stumssg) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "insert into 学生(学号,姓名,性别,专业,年级,邮件,联系电话,绩点,密码) values(?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, stumssg.getSid());
		pst.setString(2, stumssg.getSname());
		pst.setString(3, stumssg.getSsex());
		pst.setString(4, stumssg.getSmajor());
		pst.setString(5, stumssg.getSyear());
		pst.setString(6, stumssg.getSemail());
		pst.setString(7, stumssg.getStell());
		pst.setString(8, stumssg.getSgrade());
		pst.setString(9, stumssg.getSpwd());
		pst.executeUpdate();
	}
	
	//������ʦ
	public void addTea(Teacher teacher) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "insert into 老师 (工号,姓名,ְ职称,简介,密码) values(?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, teacher.getTid());
		pst.setString(2, teacher.getTname());
		pst.setString(3, teacher.getWorkname());
		pst.setString(4, teacher.getTshow());
		pst.setString(5, teacher.getTpwd());
		pst.executeUpdate();
	}
	
	//��ѯѧ��
	public Stumsg stuQuery(String sid) throws ClassNotFoundException, SQLException {
		Manager();
		Stumsg msg = null;
		String sql = "select * from 学生";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		List<String> list5 = new ArrayList<String>();
		List<String> list6 = new ArrayList<String>();
		List<String> list7 = new ArrayList<String>();
		List<String> list8 = new ArrayList<String>();
		List<String> list9 = new ArrayList<String>();
		List<String> list10 = new ArrayList<String>();
		while(res.next()) {
			msg = new Stumsg();
			list1.add(res.getString("学号"));
			list2.add(res.getString("姓名"));
			list3.add(res.getString("邮件"));
			list4.add(res.getString("联系电话"));
			list5.add(res.getString("专业"));
			list6.add(res.getString("密码"));
			list7.add(res.getString("性别"));
			list8.add(res.getString("年级"));
			list9.add(res.getString("绩点"));
			list10.add(res.getString("选题题目编号"));
		}
		msg.setSid(list1);
		msg.setSname(list2);
		msg.setSemail(list3);
		msg.setStell(list4);
		msg.setSmajor(list5);
		msg.setSpwd(list6);
		msg.setSsex(list7);
		msg.setSyear(list8);
		msg.setSgrade(list9);
		msg.setIid(list10);
		return msg;
	}
	
	//ɾ��ѧ��
	public void StuDlete(String stuid) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "delete from 学生 where 学号=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, stuid);
		pst.executeUpdate();
	}
	
	//��������
	public void StuReset(Stumssg mssg) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 学生 set 密码=? where 学号=?";
		PreparedStatement pst = con.prepareStatement(sql);
		
		pst.setString(1, mssg.getSpwd());
		pst.setString(2, mssg.getSid());
		pst.executeUpdate();
	}
	
	//��ѯ��ʦ
	public TeaAll teaQueryAll(String tid) throws ClassNotFoundException, SQLException {
		Manager();
		TeaAll all = new TeaAll();
		String sql = "select * from 老师";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();

		while(res.next()) {
			list1.add(res.getString("工号"));
			list2.add(res.getString("姓名"));
			list3.add(res.getString("职称"));
			list4.add(res.getString("简介"));
		}
		all.setTid(list1);
		all.setTname(list2);
		all.setWorkname(list3);
		all.setTshow(list4);
		return all;
	}
	
	//��������
	public void teaReset(Teacher tea) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 老师 set 密码=? where 工号=?";
		PreparedStatement pst = con.prepareStatement(sql);
			
		pst.setString(1, tea.getTpwd());
		pst.setString(2, tea.getTid());
		pst.executeUpdate();
	}
		
	//ɾ��ѧ��
	public void teaDlete(String tid) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "delete from 老师 where 工号=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, tid);
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
	
	//��ѯ��Ŀ��Ϣ
	public Item Iquery(String tid) throws ClassNotFoundException, SQLException {
		Manager();
		Item item = null;
		String sql = "select * from 题目 where 教师工号='"+tid+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		item = new Item();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		List<String> list4 = new ArrayList<String>();
		List<String> list5 = new ArrayList<String>();
		List<String> list6 = new ArrayList<String>();
			
		while(res.next()) {
			list1.add(res.getString("题目名字"));
			list2.add(res.getString("类别"));
			list3.add(res.getString("简介"));
			list4.add(res.getString("开发工具"));
			list5.add(res.getString("题目编号"));
			list6.add(res.getString("教师工号"));
		}
		item.setIid(list5);
		item.setTid(list6);
		item.setIname(list1);
		item.setIkind(list2);
		item.setIshow(list3);
		item.setItool(list4);
		return item;
	}
	
	//��ѯ�꼶רҵ����
	public Stumsg stuCount() throws ClassNotFoundException, SQLException {
		Manager();
		Stumsg msg = new Stumsg();
		String sql = "select 年级,专业,count(专业) as 联系电话 from 学生 group by 年级, 专业 order by 年级";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		while(res.next()) {
			list1.add(res.getString("年级"));
			list2.add(res.getString("专业"));
			list3.add(res.getString("联系电话"));
		}
		
		msg.setSyear(list1);
		msg.setSmajor(list2);
		msg.setStell(list3);
		
		return msg;
	}
	
	//������Ŀ��ѯ
	public Item ITQuery() throws ClassNotFoundException, SQLException {
		Manager();
		Item item = new Item();
		String sql ="select 老师.工号,老师.姓名,题目.题目名字 from 老师,题目 where 老师.工号 = 题目.教师工号";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		while(res.next()) {
			list1.add(res.getString("工号"));
			list2.add(res.getString("姓名"));
			list3.add(res.getString("题目名字"));
		}
		
		item.setTid(list1);
		item.setItool(list2);
		item.setIname(list3);
		
		return item;
	}
	
	//��ѧ���������Ŀ���
	public void ISupdate(Stumssg mssg) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 学生 set 题目编号='"+mssg.getIid()+"' where 学号='"+mssg.getSid()+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.executeUpdate();
	}
	
	//ѡ��ʱ������
	public void updateTime(Admin admin) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 管理员 set 开始时间='"+admin.getAstime()+"',结束时间='"+admin.getAetime()+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.executeUpdate();
	}
	
	//ѡ��ʱ���ѯ
	public Admin adTime() throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "select 开始时间,结束时间 from 管理员";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		Admin admin = new Admin();
		while(res.next()) {
			admin.setAstime(res.getString("开始时间"));
			admin.setAetime(res.getString("结束时间"));
		}
		return admin;
	}
	
	//��ѯ�Լ�����
	public Stumssg Ssgrade(String sid) throws ClassNotFoundException, SQLException {
		Manager();
		Stumssg mssg = new Stumssg();
		String sql = "select 绩点 from 学生 where 学号='"+sid+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		while(res.next()) {
			mssg.setSgrade(res.getString("绩点"));
		}
		return mssg;
	}
}
