package teacher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class teaManager {
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
	
	//���ݹ����޸���ʦ���
	public void updata(Teacher teacher) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 老师 set 简介=? where 工号=?";
		PreparedStatement pst = con.prepareStatement(sql);
		
		pst.setString(1,teacher.getTshow());
		pst.setString(2,teacher.getTid());
		pst.executeUpdate();
	}
	
	//�޸�����
	public void updatePwd(Teacher teacher) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 老师 set 密码=? where 工号=?";
		PreparedStatement pst = con.prepareStatement(sql);
		
		pst.setString(1,teacher.getTpwd());
		pst.setString(2,teacher.getTid());
		pst.executeUpdate();
	}
	
	
	//��ѯ��ʦ��Ϣ
	public Teacher query(String tid) throws ClassNotFoundException, SQLException {
		Manager();
		Teacher teacher = null;
		String sql = "select * from 老师 where 工号='"+tid+"'";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		while(res.next()) {
			teacher = new Teacher();
			teacher.setTid(tid);
			teacher.setTname(res.getString("姓名"));
			teacher.setTpwd(res.getString("密码"));
			teacher.setWorkname(res.getString("ְ职称"));
			teacher.setTshow(res.getString("简介"));
		}
		
		return teacher;
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
	
	//������ѯ
	public Item  Kquery() throws ClassNotFoundException, SQLException {
		Manager();
		Item item = new Item();
		String sql = "select 类别,count(题目编号) as 数量  from 题目 group by 类别";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		while(res.next()) {
			list1.add(res.getString("类别"));
			list2.add(res.getString("数量"));
		}
		item.setIkind(list1);
		item.setIid(list2);
		return item;
	}
	public Item  Tquery() throws ClassNotFoundException, SQLException {
		Manager();
		Item item = new Item();
		String sql = "select 老师.工号,老师.姓名,count(学号) as 数量 from 学生,老师,题目 where 学生.选题题目编号=题目.题目编号 and 老师.工号=题目.教师工号 group by 数量,老师.工号 ";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();
		List<String> list3 = new ArrayList<String>();
		while(res.next()) {
			list1.add(res.getString("工号"));
			list2.add(res.getString("姓名"));
			list3.add(res.getString("数量"));
		}
		item.setTid(list1);
		item.setIname(list2);
		item.setIid(list3);
		return item;
	}
	//��ѯ������Ŀ
	public Item IqueryAll(String tid) throws ClassNotFoundException, SQLException {
		Manager();
		Item item = null;
		String sql = "select * from 题目";
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
	
	//������Ŀ
	public void addItem(Item1 item1) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "insert into 题目 (题目编号,题目名字,教师工号,类别,简介,开发工具) values(?,?,?,?,?,?)";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, item1.getIid());
		pst.setString(2, item1.getIname());
		pst.setString(3, item1.getTid());
		pst.setString(4, item1.getIkind());
		pst.setString(5, item1.getIshow());
		pst.setString(6, item1.getItool());
		pst.executeUpdate();
	}
	
	//ɾ����Ŀ
	public void Delete(String Iid) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "delete from 题目 where 题目编号=?";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1,Iid);
		pst.executeUpdate();
	}
	
	//����Ա������Ŀ
	public void adminSend() throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "update 题目 set �发布状态״̬='true'";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.executeUpdate();
	}
	
	//��ѯ��Ŀ�Ƿ񷢲�
	public Item1 Adminselect(Item1 item1) throws ClassNotFoundException, SQLException {
		Manager();
		String sql = "select �发布状态״̬ from 题目";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet res = pst.executeQuery();
		while(res.next()) {
			item1.setIsend(res.getString("发布状态"));
		}
		return item1;
	}
}
