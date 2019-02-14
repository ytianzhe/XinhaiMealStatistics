package com.xinhai.org.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;

import com.xinhai.org.entity.Eat_day_log;
import com.xinhai.org.entity.Eat_day_log_Statistical;
import com.xinhai.org.entity.Eat_food;
import com.xinhai.org.entity.Eat_result_day;
import com.xinhai.org.entity.Eat_user;
import com.xinhai.org.until.ConnectionFactory;

/**
 * @author Tony
 * @version 创建时间：2018年7月14日 上午9:04:11
 * @ClassName 类名称
 * @Description 类描述
 */
public class SqlMethods {

	// 搜索后台管理人员账号密码是否存在
	public static int SearchUsername(String username, String password) throws SQLException {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from  eat_backuser b where b.username='" + username + "' and b.password='"
					+ password + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 搜索所有注册的状态正常的人
	public static List<Eat_user> SearchAlltel() throws SQLException {
		List<Eat_user> lessonList = new ArrayList<Eat_user>();
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT eu.id,eu.mobile,eu.`name` FROM `eat_user` eu where  eu.`status`=1";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Eat_user au = new Eat_user();
				au.setId(rs.getInt(1));
				au.setMobile(rs.getString(2));
				au.setName(rs.getString(3));
				lessonList.add(au);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}

	// 删除/恢复用户

	public static void UpdateEat_userStatus(int id, String status) throws SQLException {
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		// ResultSet rs= null;
		// int count=0;
		try {
			// String sql="SELECT npmls.*,e.name FROM
			// eight_factory.`new_precision_machining_log _statistics` npmls
			// left join employee e on npmls.workerid=e.employeeNumber where
			// npmls.id="+id;
			String sql2 = "UPDATE `eat_user` SET  status = '" + status + "' where id=" + id;
			// System.out.println(sql2);
			stmt.executeUpdate(sql2);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 搜索该手机号是否已经注册
	public static int SearchEatUserTel(String telNumber) throws SQLException {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from  eat_user eu where eu.mobile='" + telNumber + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 插入用户数据
	public static void insertEat_User(Timestamp now, String name, String mobile) throws SQLException {

		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		// ResultSet rs= null;
		// int count=0;
		try {
			String sql = "INSERT INTO `eat_user` (firstAddTime,name,mobile,status)" + "VALUES ('" + now + "','" + name
					+ "','" + mobile + "',1)";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 查询今天的吃饭情况是否已经记录
	public static int SearchEatResultDay() throws SQLException {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from  eat_result_day erd  where to_days(erd.firstAddTime) = to_days(now());";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 插入吃饭结果数据
	// 插入用户数据
	public static void insertResultDay(Timestamp now, int trueEatNumber, int trueRiceNumber) throws SQLException {

		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		// ResultSet rs= null;
		// int count=0;
		try {
			String sql = "INSERT INTO `eat_result_day` (firstAddTime,trueEatNumber,trueRiceNumber,status)" + "VALUES ('"
					+ now + "','" + trueEatNumber + "','" + trueRiceNumber + "',1)";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 查询菜是否被注册
	
	public static int SearchEatFood(String foodName) throws SQLException {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from  eat_food ef where ef.foodName='" + foodName + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 搜索所有正常的食物

	public static List<Eat_food> SearchEatfood() throws SQLException {
		List<Eat_food> lessonList = new ArrayList<Eat_food>();
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT ef.*,eft.typeName as foodtypeName  FROM `eat_food` ef left join eat_food_type eft on eft.id=ef.foodType  where ef.`status`=1 ";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Eat_food af = new Eat_food();
				af.setId(rs.getInt(1));
				af.setFirstAddTime(rs.getTimestamp(2));
				af.setFoodName(rs.getString(3));
				af.setFoodType(rs.getInt(4));
				af.setFirstAddPerson(rs.getInt(5));
				af.setStatus(rs.getInt(6));
				af.setIsLast(rs.getInt(7));
				af.setFoodtypeName(rs.getString(8));
				lessonList.add(af);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}

	// 插入菜的名称
	public static void insertEat_food(Timestamp now, String foodName, int foodType, int firstAddPerson)
			throws SQLException {

		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		// ResultSet rs= null;
		// int count=0;
		try {
			String sql = "INSERT INTO `eat_food` (firstAddTime,foodName,foodType,firstAddPerson,status,isLast)"
					+ "VALUES ('" + now + "','" + foodName + "','" + foodType + "','" + firstAddPerson + "',1,0)";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 搜索该手机号是否已经注册
	public static int SearchUserid(String username) throws SQLException {
		int id = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select eb.id from  eat_backuser eb where eb.username='" + username + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}

	// 插入菜的类型是否被注册

	public static int SearchEatFoodType(String foodType) throws SQLException {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from  eat_food_type eft where eft.typeName='" + foodType + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 查询菜的id

	public static int SearchEatFoodTypeId(String foodType) throws SQLException {
		int id = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select eft.id from  eat_food_type eft where eft.typeName='" + foodType + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}

	// 插入菜的类型名称
	public static void inserteat_food_type(Timestamp now, String foodType) throws SQLException {

		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		// ResultSet rs= null;
		// int count=0;
		try {
			String sql = "INSERT INTO `eat_food_type` (firstAddTime,typeName,status)" + "VALUES ('" + now + "','"
					+ foodType + "',1)";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 查询openid 是否存在
	public static int SearchOpenid(String openid) throws Exception {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from `eat_user` eu where eu.openid='" + openid + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	// 查询手机号是否被注册
	public static int SearchTelNumber(String telNumber) throws Exception {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from `eat_user` eu where eu.mobile='" + telNumber + "' and eu.status=1";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	// 查询名字是否被注册
	public static int SearchEatName(String eatName) throws Exception {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) from `eat_user` eu where eu.name='" + eatName + "' and eu.status=1";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	// 查询openid 对应的状态
	public static int SearchStatusByOpenid(String openid) throws Exception {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select eu.status from `eat_user` eu where eu.openid='" + openid + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	// 注册手机号和名字
	public static int insertEatUserInfo(String openid, Timestamp now, String telNumber, String eatUserName)
			throws Exception {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO `eat_user` (openid,firstAddTime,name,mobile,status)" + "VALUES ('" + openid
					+ "','" + now + "','" + eatUserName + "','" + telNumber + "',1)";
			// System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return count;
	}

	// 查询用户信息by openid

	public static List<Eat_user> SearchEatUserInfoByOpenid(String openid) throws Exception {
		List<Eat_user> lessonList = new ArrayList<Eat_user>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select eu.id,eu.mobile,eu.name,eu.status from `eat_user` eu where eu.openid='" + openid + "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Eat_user eu = new Eat_user();
				eu.setId(rs.getInt(1));
				eu.setMobile(rs.getString(2));
				eu.setName(rs.getString(3));
				eu.setStatus(rs.getInt(4));
				lessonList.add(eu);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lessonList;
	}

	// 查询用户信息byUserid
	public static List<Eat_user> SearchEatUserInfoByUserid(int UserId) throws Exception {
		List<Eat_user> lessonList = new ArrayList<Eat_user>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select eu.id,eu.mobile,eu.name,eu.status,eu.openid from `eat_user` eu where eu.id='" + UserId
					+ "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Eat_user eu = new Eat_user();
				eu.setId(rs.getInt(1));
				eu.setMobile(rs.getString(2));
				eu.setName(rs.getString(3));
				eu.setStatus(rs.getInt(4));
				eu.setOpenid(rs.getString(5));
				lessonList.add(eu);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return lessonList;
	}

	// 增加明日的吃饭统计

	public static void inserteat_day_log(Timestamp now, int userId, int isEat, double riceNumber,
			String AdditionalInformation) throws SQLException {

		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();

		// ResultSet rs= null;
		// int count=0;
		try {
			String sql = "INSERT INTO `eat_day_log` (firstAddTime,userId,isEat,riceNumber,information,status)"
					+ "VALUES ('" + now + "'," + userId + "," + isEat + "," + riceNumber + ",'" + AdditionalInformation
					+ "',1)";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 查询今天插入的明日吃饭数据
	public static List<Eat_day_log> SelectEat_day_logbyOpenid(String openid) throws SQLException {
		List<Eat_day_log> lessonList = new ArrayList<Eat_day_log>();
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			// String sql="select edl.*,eu.openid as userOpenId FROM eat_day_log
			// edl left join eat_user eu on eu.id=edl.userId where
			// to_days(edl.firstAddTime) = to_days(now()) and
			// openid='"+openid+"'";
			String sql2 = "select edl.*,eu.openid as userOpenId ,ed.`name`  as dictionaryName,eu.name FROM eat_day_log edl left join eat_user eu on  eu.id=edl.userId  left join eat_dictionary ed on ed.value=edl.isEat where  to_days(edl.firstAddTime) = to_days(now())  and ed.type='isEat' and eu.openid='"
					+ openid + "'";
			System.out.println(sql2);
			rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				Eat_day_log edl = new Eat_day_log();
				// edl.setId(rs.getInt(1));
				// edl.setIsEat(rs.getInt(4));
				edl.setRiceNumber(rs.getDouble(5));
				edl.setFirstAddTime(rs.getTimestamp(2));
				edl.setDictionaryName(rs.getString(8));
				edl.setEatName(rs.getString(9));
				lessonList.add(edl);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lessonList;
	}

	// 查询明天的预约吃饭记录的数量
	public static int SearchCount(int userId) throws SQLException {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select count(*) FROM eat_day_log edl left join eat_user eu on  eu.id=edl.userId  where  to_days(edl.firstAddTime) = to_days(now())  and edl.userId='"
					+ userId + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	public static List<Eat_day_log> SelectEat_day_logbyUserId(int userId) throws SQLException {
		// int count=0;
		List<Eat_day_log> lessonList = new ArrayList<Eat_day_log>();

		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select edl.* FROM eat_day_log edl left join eat_user eu on  eu.id=edl.userId  where  to_days(edl.firstAddTime) = to_days(now())  and edl.userId='"
					+ userId + "'";
			String sql2 = "select edl.*,eu.openid as userOpenId ,ed.`name`  as dictionaryName,eu.name  FROM eat_day_log edl left join eat_user eu on  eu.id=edl.userId  left join eat_dictionary ed on ed.value=edl.isEat where  to_days(edl.firstAddTime) = to_days(now())  and ed.type='isEat' and edl.userId='"
					+ userId + "'";

			System.out.println(sql2);
			rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				Eat_day_log edl = new Eat_day_log();
				edl.setRiceNumber(rs.getDouble(5));
				edl.setFirstAddTime(rs.getTimestamp(2));
				edl.setDictionaryName(rs.getString(9));
				edl.setEatName(rs.getString(10));
				edl.setInformation(rs.getString(6));
				lessonList.add(edl);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}

	// 查询明天吃饭的人数
	public static int SearchEat_day_logTomorrowStatisticsCount() throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM `eat_day_log` edl where  to_days(edl.firstAddTime) = to_days(now()) ";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 查询明天吃饭的人 饭的数量
	public static double SearchEat_day_logTomorrowStatisticsRiceNumberSum() throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		double count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			// String sql = "select sum(edl.isEat) as isEatNum
			// ,sum(edl.riceNumber) as riceNum from eat_day_log edl where
			// to_days(edl.firstAddTime) = to_days(now()) and edl.`status`=1;";
			String sql = "select sum(edl.riceNumber) as riceNum from  eat_day_log edl  where to_days(edl.firstAddTime) = to_days(now()) and edl.`status`=1;";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getDouble(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 查询今天吃饭的所有的人名字
	public static List<Eat_day_log> SearchEat_day_logTodayAllUserName() throws SQLException {
		// int count=0;
		List<Eat_day_log> lessonList = new ArrayList<Eat_day_log>();

		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT edl.*,eu.`name` as userName FROM `eat_day_log` edl  left join eat_user eu on eu.id=edl.userId where to_days(edl.firstAddTime) = to_days(now()) and edl.status='1'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Eat_day_log edl = new Eat_day_log();
				edl.setId(rs.getInt(1));
				edl.setUserName(rs.getString(7));
				lessonList.add(edl);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}

	// 累计费用：元|本月费用： 元 一个人15
	public static int SearchCumulativeCost() throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM `eat_day_log` edl   where  edl.status='1' ";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count * 15;
	}

	// 查询吃饭数据
	public static List<Eat_day_log> SearchEat_day_logTodaybyConditions(String conditions) throws SQLException {
		// int count=0;
		List<Eat_day_log> lessonList = new ArrayList<Eat_day_log>();

		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			// conditions=" and to_days(eat_day_log.firstAddTime) =
			// to_days(now())";
			String sql = "SELECT edl.*,eu.`name` as userName FROM eat_day_log edl  left join eat_user eu on eu.id=edl.userId where  edl.status='1' "
					+ conditions + "";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Eat_day_log edl = new Eat_day_log();
				// edl.setId(rs.getInt(1));
				// edl.setUserName(rs.getString(7));
				lessonList.add(edl);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}

	// 查userId对应的手机号
	public static String SearchTelCount(int userId) throws SQLException {
		String telNumber = null;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT eu.mobile FROM `eat_user` eu   where  eu.id=" + userId;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				telNumber = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return telNumber;
	}

	// 对一个userid 插入一个手机号
	public static void UpdateTelNumber(String telNumber, int userId) throws SQLException {

		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();

		// ResultSet rs= null;
		// int count=0;
		try {
			String sql = "update eat_user eu SET mobile ='" + telNumber + "' where eu.id=" + userId;
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 查询今天 吃饭统计
	public static List<Eat_result_day> SearchEat_result_dayToday() throws SQLException {
		// int count=0;
		List<Eat_result_day> lessonList = new ArrayList<Eat_result_day>();

		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			// conditions=" and to_days(eat_day_log.firstAddTime) =
			// to_days(now())";
			String sql = "SELECT * FROM xh_ytz.eat_result_day erd where to_days(erd.firstAddTime) = to_days(now()) and erd.status='1'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Eat_result_day erd = new Eat_result_day();
				erd.setTrueEatNumber(rs.getInt(4));
				erd.setTrueRiceNumber(rs.getDouble(6));
				lessonList.add(erd);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}

	// 修改一条预约吃饭数据的状态
	// 对一个userid 插入一个手机号
	public static void UpdateEat_day_logStatusById(int id) throws SQLException {

		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		// ResultSet rs= null;
		// int count=0;
		try {
			String sql = "update eat_day_log edl SET status ='0' where edl.id=" + id;
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// 查询手机号是否重复
	public static int RepeatTel(String telnumber) throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) from eat_user eu  where eu.mobile='" + telnumber + "'";
			// System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 统计 sql
	public static int SearchEatDayLogbyConditions(String conditions) throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM eat_day_log  edl " + conditions;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 统计 sql 米饭数量
	public static double SearchEatDayLogRiceNumberByConditions(String conditions) throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		double riceNumber = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT sum(edl.riceNumber) as riceNum FROM eat_day_log  edl " + conditions;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				riceNumber = rs.getDouble(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return riceNumber;
	}

	// 统计 sql 实际吃饭人数
	public static int SearchEatResultDaybyConditionsTrue(String conditions) throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT edl.trueEatNumber FROM eat_result_day  edl " + conditions;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 统计 sql 实际吃饭数量
	public static double SearchEatDayLogRiceNumberByConditionsTrue(String conditions) throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		double riceNumber = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT edl.trueRiceNumber FROM eat_result_day  edl " + conditions;
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				riceNumber = rs.getDouble(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return riceNumber;
	}

	// 获取 折线 明天吃饭人数 今天的吃饭人数 本周每日吃饭人数 上周 每日的平均吃饭人数 本月的平均吃饭人数的数据
	public static List<Eat_day_log_Statistical> SearchEat_result_dayStatistical() throws SQLException {
		// int count=0;
		List<Eat_day_log_Statistical> lessonList = new ArrayList<Eat_day_log_Statistical>();

		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			// conditions=" and to_days(eat_day_log.firstAddTime) =
			// to_days(now())";
			// String sql = "select * from ((SELECT count(*) as todayCount FROM
			// eat_day_log edl where to_days(edl.firstAddTime) = to_days(now()))
			// as a,(SELECT count(*) as lastCount FROM eat_day_log edl WHERE
			// TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS(
			// NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0) as b,(SELECT
			// count(*)/(WEEKDAY(now())+1) as thisWeekAveCount FROM eat_day_log
			// edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1))
			// as c,(SELECT count(*)/6 as lastWeekAveCount FROM eat_day_log edl
			// WHERE YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1) as d,(SELECT count(*)/DAYOFMONTH(NOW()+1) as
			// thisMonthAveCount FROM eat_day_log edl WHERE DATE_FORMAT(
			// edl.firstAddTime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ))
			// as e,(SELECT sum(edl.riceNumber) as todayRiceNum FROM eat_day_log
			// edl where to_days(edl.firstAddTime) = to_days(now())) as
			// f,(SELECT sum(edl.riceNumber) as lastdayRiceNum FROM eat_day_log
			// edl WHERE TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and
			// TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0) as g,(select
			// sum(riceNumber)/(WEEKDAY(now())+1) as thisWeekAveRiceNum from
			// eat_day_log edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1))
			// as h, (SELECT sum(edl.riceNumber)/6 as lastWeekAveRiceNum FROM
			// eat_day_log edl WHERE
			// YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1) as j,(select
			// sum(riceNumber)/DAYOFMONTH(NOW()+1) as thisMonthAveRiceNum from
			// eat_day_log where firstAddTime > DATE_ADD(curdate(),interval
			// -day(curdate())+1 day)) as l)";
			String sql2 = "select * from ((SELECT count(*) as todayCount FROM eat_day_log  edl where to_days(edl.firstAddTime) = to_days(now()) and edl.isEat='1') as a,(SELECT count(*) as lastCount FROM eat_day_log  edl WHERE TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0  and edl.isEat='1') as b,(SELECT count(*)/(WEEKDAY(now())+1) as thisWeekAveCount FROM eat_day_log  edl where edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1) and edl.isEat='1') as c,(SELECT count(*)/6 as lastWeekAveCount FROM eat_day_log  edl WHERE YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) = YEARWEEK(now())-1 and edl.isEat='1') as d,(SELECT count(*)/DAYOFMONTH(NOW()+1) as thisMonthAveCount FROM eat_day_log  edl  WHERE DATE_FORMAT( edl.firstAddTime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) and edl.isEat='1') as e,(SELECT sum(edl.riceNumber) as todayRiceNum FROM eat_day_log  edl  where to_days(edl.firstAddTime) = to_days(now()) and edl.isEat='1' ) as f,(SELECT sum(edl.riceNumber) as lastdayRiceNum FROM eat_day_log  edl  WHERE TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0  and edl.isEat='1') as g,(select sum(riceNumber)/(WEEKDAY(now())+1) as thisWeekAveRiceNum from eat_day_log edl where edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1)  and edl.isEat='1') as h,(SELECT sum(edl.riceNumber)/6 as lastWeekAveRiceNum FROM eat_day_log  edl WHERE YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) = YEARWEEK(now() )-1   and edl.isEat='1' ) as j,(select sum(riceNumber)/DAYOFMONTH(NOW()+1) as thisMonthAveRiceNum from eat_day_log  edl where firstAddTime > DATE_ADD(curdate(),interval -day(curdate())+1 day) and edl.isEat='1' ) as l)";
			System.out.println(sql2);
			rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				Eat_day_log_Statistical erds = new Eat_day_log_Statistical();
				erds.setTodayCount(rs.getInt(1));
				erds.setLastdayCount(rs.getInt(2));
				erds.setThisWeekAveCount(rs.getDouble(3));
				erds.setLastWeekAveCount(rs.getDouble(4));
				erds.setThisMonthAveCount(rs.getDouble(5));
				erds.setTodayRiceNum(rs.getDouble(6));
				erds.setLastdayRiceNum(rs.getDouble(7));
				erds.setThisWeekAveRiceNumber(rs.getDouble(8));
				erds.setLastWeekAveRiceNumbe(rs.getDouble(9));
				erds.setThisMonthAveRiceNumber(rs.getDouble(10));
				// erds.setInformation(rs.getString(11));
				lessonList.add(erds);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}

	//
	// 实际获取 折线 明天吃饭人数 今天的吃饭人数 本周每日吃饭人数 上周 每日的平均吃饭人数 本月的平均吃饭人数的数据
	public static int SearchEat_Result_DayTrueStatistical(String conditions) throws SQLException {
		String TimeConditions = null;
		String EatNumber = null;
		switch (conditions) {
		case "today":
			EatNumber = " erd.trueEatNumber ";
			TimeConditions = " where to_days(erd.firstAddTime) = to_days(now())";
			break;

		case "lastday":
			EatNumber = " erd.trueEatNumber ";
			TimeConditions = " WHERE TO_DAYS( NOW( ) ) - TO_DAYS(erd.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) - TO_DAYS(erd.firstAddTime) > 0";
			break;

		case "thisWeek":
			EatNumber = " erd.trueEatNumber/(WEEKDAY(now())+1) ";
			TimeConditions = " where erd.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1) ";
			break;

		case "lastWeek":
			EatNumber = " erd.trueEatNumber/6 ";
			TimeConditions = "  WHERE YEARWEEK(date_format(erd.firstAddTime,'%Y-%m-%d')) = YEARWEEK(now())-1 ";
			break;

		case "thisMonth":
			EatNumber = " erd.trueEatNumber/DAYOFMONTH(NOW()+1) ";
			TimeConditions = " WHERE DATE_FORMAT( erd.firstAddTime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )";
			break;
		default:
			EatNumber = " erd.trueEatNumber ";
			break;

		}
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			// conditions=" and to_days(eat_day_log.firstAddTime) =
			// to_days(now())";
			// String sql = "select * from ((SELECT count(*) as todayCount FROM
			// eat_day_log edl where to_days(edl.firstAddTime) = to_days(now()))
			// as a,(SELECT count(*) as lastCount FROM eat_day_log edl WHERE
			// TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS(
			// NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0) as b,(SELECT
			// count(*)/(WEEKDAY(now())+1) as thisWeekAveCount FROM eat_day_log
			// edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1))
			// as c,(SELECT count(*)/6 as lastWeekAveCount FROM eat_day_log edl
			// WHERE YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1) as d,(SELECT count(*)/DAYOFMONTH(NOW()+1) as
			// thisMonthAveCount FROM eat_day_log edl WHERE DATE_FORMAT(
			// edl.firstAddTime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ))
			// as e,(SELECT sum(edl.riceNumber) as todayRiceNum FROM eat_day_log
			// edl where to_days(edl.firstAddTime) = to_days(now())) as
			// f,(SELECT sum(edl.riceNumber) as lastdayRiceNum FROM eat_day_log
			// edl WHERE TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and
			// TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0) as g,(select
			// sum(riceNumber)/(WEEKDAY(now())+1) as thisWeekAveRiceNum from
			// eat_day_log edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1))
			// as h, (SELECT sum(edl.riceNumber)/6 as lastWeekAveRiceNum FROM
			// eat_day_log edl WHERE
			// YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1) as j,(select
			// sum(riceNumber)/DAYOFMONTH(NOW()+1) as thisMonthAveRiceNum from
			// eat_day_log where firstAddTime > DATE_ADD(curdate(),interval
			// -day(curdate())+1 day)) as l)";
			// String sql2="select * from ((SELECT count(*) as todayCount FROM
			// eat_day_log edl where to_days(edl.firstAddTime) = to_days(now())
			// and edl.isEat='1') as a,(SELECT count(*) as lastCount FROM
			// eat_day_log edl WHERE TO_DAYS( NOW( ) ) -
			// TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) -
			// TO_DAYS(edl.firstAddTime) > 0 and edl.isEat='1') as b,(SELECT
			// count(*)/(WEEKDAY(now())+1) as thisWeekAveCount FROM eat_day_log
			// edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1)
			// and edl.isEat='1') as c,(SELECT count(*)/6 as lastWeekAveCount
			// FROM eat_day_log edl WHERE
			// YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1 and edl.isEat='1') as d,(SELECT
			// count(*)/DAYOFMONTH(NOW()+1) as thisMonthAveCount FROM
			// eat_day_log edl WHERE DATE_FORMAT( edl.firstAddTime, '%Y%m' ) =
			// DATE_FORMAT( CURDATE( ) , '%Y%m' ) and edl.isEat='1') as
			// e,(SELECT sum(edl.riceNumber) as todayRiceNum FROM eat_day_log
			// edl where to_days(edl.firstAddTime) = to_days(now()) and
			// edl.isEat='1' ) as f,(SELECT sum(edl.riceNumber) as
			// lastdayRiceNum FROM eat_day_log edl WHERE TO_DAYS( NOW( ) ) -
			// TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) -
			// TO_DAYS(edl.firstAddTime) > 0 and edl.isEat='1') as g,(select
			// sum(riceNumber)/(WEEKDAY(now())+1) as thisWeekAveRiceNum from
			// eat_day_log edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1)
			// and edl.isEat='1') as h,(SELECT sum(edl.riceNumber)/6 as
			// lastWeekAveRiceNum FROM eat_day_log edl WHERE
			// YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now() )-1 and edl.isEat='1' ) as j,(select
			// sum(riceNumber)/DAYOFMONTH(NOW()+1) as thisMonthAveRiceNum from
			// eat_day_log edl where firstAddTime > DATE_ADD(curdate(),interval
			// -day(curdate())+1 day) and edl.isEat='1' ) as l)";
			String sql = "SELECT " + EatNumber + " FROM eat_result_day   erd " + TimeConditions;

			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 实际的米饭数量

	public static double SearchEat_Result_DayTrueStatisticalRiceNum(String conditions) throws SQLException {
		String TimeConditions = null;
		String EatNumber = null;
		switch (conditions) {
		case "today":
			EatNumber = " erd.trueRiceNumber ";
			TimeConditions = " where to_days(erd.firstAddTime) = to_days(now())";
			break;

		case "lastday":
			EatNumber = " erd.trueRiceNumber ";
			TimeConditions = " WHERE TO_DAYS( NOW( ) ) - TO_DAYS(erd.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) - TO_DAYS(erd.firstAddTime) > 0";
			break;

		case "thisWeek":
			EatNumber = " erd.trueRiceNumber/(WEEKDAY(now())+1) ";
			TimeConditions = " where erd.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1) ";
			break;

		case "lastWeek":
			EatNumber = " erd.trueRiceNumber/6 ";
			TimeConditions = "  WHERE YEARWEEK(date_format(erd.firstAddTime,'%Y-%m-%d')) = YEARWEEK(now())-1 ";
			break;

		case "thisMonth":
			EatNumber = " erd.trueRiceNumber/DAYOFMONTH(NOW()+1) ";
			TimeConditions = " WHERE DATE_FORMAT( erd.firstAddTime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )";
			break;

		default:

			EatNumber = " erd.trueRiceNumber ";

			break;
		}
		double count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			// conditions=" and to_days(eat_day_log.firstAddTime) =
			// to_days(now())";
			// String sql = "select * from ((SELECT count(*) as todayCount FROM
			// eat_day_log edl where to_days(edl.firstAddTime) = to_days(now()))
			// as a,(SELECT count(*) as lastCount FROM eat_day_log edl WHERE
			// TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS(
			// NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0) as b,(SELECT
			// count(*)/(WEEKDAY(now())+1) as thisWeekAveCount FROM eat_day_log
			// edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1))
			// as c,(SELECT count(*)/6 as lastWeekAveCount FROM eat_day_log edl
			// WHERE YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1) as d,(SELECT count(*)/DAYOFMONTH(NOW()+1) as
			// thisMonthAveCount FROM eat_day_log edl WHERE DATE_FORMAT(
			// edl.firstAddTime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ))
			// as e,(SELECT sum(edl.riceNumber) as todayRiceNum FROM eat_day_log
			// edl where to_days(edl.firstAddTime) = to_days(now())) as
			// f,(SELECT sum(edl.riceNumber) as lastdayRiceNum FROM eat_day_log
			// edl WHERE TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and
			// TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0) as g,(select
			// sum(riceNumber)/(WEEKDAY(now())+1) as thisWeekAveRiceNum from
			// eat_day_log edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1))
			// as h, (SELECT sum(edl.riceNumber)/6 as lastWeekAveRiceNum FROM
			// eat_day_log edl WHERE
			// YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1) as j,(select
			// sum(riceNumber)/DAYOFMONTH(NOW()+1) as thisMonthAveRiceNum from
			// eat_day_log where firstAddTime > DATE_ADD(curdate(),interval
			// -day(curdate())+1 day)) as l)";
			// String sql2="select * from ((SELECT count(*) as todayCount FROM
			// eat_day_log edl where to_days(edl.firstAddTime) = to_days(now())
			// and edl.isEat='1') as a,(SELECT count(*) as lastCount FROM
			// eat_day_log edl WHERE TO_DAYS( NOW( ) ) -
			// TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) -
			// TO_DAYS(edl.firstAddTime) > 0 and edl.isEat='1') as b,(SELECT
			// count(*)/(WEEKDAY(now())+1) as thisWeekAveCount FROM eat_day_log
			// edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1)
			// and edl.isEat='1') as c,(SELECT count(*)/6 as lastWeekAveCount
			// FROM eat_day_log edl WHERE
			// YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1 and edl.isEat='1') as d,(SELECT
			// count(*)/DAYOFMONTH(NOW()+1) as thisMonthAveCount FROM
			// eat_day_log edl WHERE DATE_FORMAT( edl.firstAddTime, '%Y%m' ) =
			// DATE_FORMAT( CURDATE( ) , '%Y%m' ) and edl.isEat='1') as
			// e,(SELECT sum(edl.riceNumber) as todayRiceNum FROM eat_day_log
			// edl where to_days(edl.firstAddTime) = to_days(now()) and
			// edl.isEat='1' ) as f,(SELECT sum(edl.riceNumber) as
			// lastdayRiceNum FROM eat_day_log edl WHERE TO_DAYS( NOW( ) ) -
			// TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) -
			// TO_DAYS(edl.firstAddTime) > 0 and edl.isEat='1') as g,(select
			// sum(riceNumber)/(WEEKDAY(now())+1) as thisWeekAveRiceNum from
			// eat_day_log edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1)
			// and edl.isEat='1') as h,(SELECT sum(edl.riceNumber)/6 as
			// lastWeekAveRiceNum FROM eat_day_log edl WHERE
			// YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now() )-1 and edl.isEat='1' ) as j,(select
			// sum(riceNumber)/DAYOFMONTH(NOW()+1) as thisMonthAveRiceNum from
			// eat_day_log edl where firstAddTime > DATE_ADD(curdate(),interval
			// -day(curdate())+1 day) and edl.isEat='1' ) as l)";
			String sql = "SELECT " + EatNumber + " FROM eat_result_day   erd " + TimeConditions;

			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getDouble(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	
	// 获取外表中是否有这个手机号
		public static int SearchCountByTelNumber(String telNumber) throws SQLException {
			// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
			int count  = 0;
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql = "SELECT count(*) FROM eat_employeeuser  eu where eu.employeeTel='" + telNumber + "'";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					count = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return count;
		}
	
	
	
	
	

	// 获取外表中的员工的名字
	public static String SearchEatUserNameByTelNumber(String telNumber) throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		String Name = null;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT eu.employeeName FROM eat_employeeuser  eu where eu.employeeTel='" + telNumber + "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Name = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return Name;
	}

	// 手机号注册员工
	public static void insertEatUserInfoByTelNumber(String openid, Timestamp now, String telNumber, String eatUserName)
			throws Exception {
		// int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO `eat_user` (openid,firstAddTime,name,mobile,status)" + "VALUES ('" + openid
					+ "','" + now + "','" + eatUserName + "','" + telNumber + "',1)";
			// System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// 根据手机号获取useId
	public static int SearchUserIdByTelNumber(String telNumber) throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		// String Name=null;
		int userId = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT eu.id FROM eat_user  eu where eu.mobile='" + telNumber + "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userId;
	}

	// 获取员名字是否注册
	public static int SearchUserNameCountByUserName(String UserName) throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		// String Name=null;
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM eat_user  eu where eu.name='" + UserName + "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 通过名字进行获取userId
	public static int SearchUserUserIdCountByUserName(String UserName) throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		// String Name=null;
		// int count=0;
		int userId = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT eu.id FROM eat_user  eu where eu.name='" + UserName + "'";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				userId = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return userId;
	}

	// 统计今天的吃饭人数 和米饭的申报量 插入表单

	public static void insertEat_result_today_before_midnight(Timestamp now, int eatNumber, double eatRiceNumber)
			throws Exception {
		// int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "INSERT INTO `eat_result_today_before_midnight` (firstAddTime,eatNumber,eatRiceNumber,status)"
					+ "VALUES ('" + now + "','" + eatNumber + "','" + eatRiceNumber + "',1)";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// 统计今天的吃饭人数 和米饭的申报量 更新表单
	public static void upDateEat_result_today_before_midnight(int eatNumber, double eatRiceNumber, Timestamp now)
			throws Exception {
		// int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "UPDATE eat_result_today_before_midnight ertbm SET ertbm.eatNumber=" + eatNumber
					+ ", ertbm.eatRiceNumber=" + eatRiceNumber + " ,ertbm.updateTime='" + now
					+ "' where to_days(ertbm.firstAddTime) = to_days(now())";
			System.out.println(sql);
			stmt.execute(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	// 获取进入填写的明日预约的人数
	public static int SearchEatNumberTodayWrite() throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		// String Name=null;
		// int count=0;
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM eat_day_log  edl  where to_days(edl.firstAddTime) = to_days(now())";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 获取进入填写的明日预约的米饭数量
	public static double SearchEatRiceNumberrTodayWrite() throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		// String Name=null;
		// int count=0;
		double RiceCount = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT sum(edl.riceNumber) FROM eat_day_log  edl   where to_days(edl.firstAddTime) = to_days(now())";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				RiceCount = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return RiceCount;
	}

	// 查询今日的统计是否已经插入
	public static int SearchCountEatRiceNumberrTodayWrite() throws SQLException {
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		// String Name=null;
		// int count=0;
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "SELECT count(*) FROM eat_result_today_before_midnight  ertbm   where to_days(ertbm.firstAddTime) = to_days(now())";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}
	// 新的统计方式

	// 实际获取 折线 明天吃饭人数 今天的吃饭人数 本周每日吃饭人数 上周 每日的平均吃饭人数 本月的平均吃饭人数的数据
	public static int SearchEat_Result_DayTrueStatisticalNeW(Timestamp now) throws SQLException {
		int count = 0;
		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			String sql = "select * from eat_result_today_before_midnight ertbm where ertbm.firstAddTime<='" + now
					+ "' order by ertbm.firstAddTime desc limit 1";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return count;
	}

	// 获取用户的信息用手机获取
	public static List<Eat_user> SearchEat_userInformationByTelNumber(String eatTelNumber) throws SQLException {
		// int count=0;
		List<Eat_user> lessonList = new ArrayList<Eat_user>();

		Connection conn = ConnectionFactory.makeConnection();
		// System.err.println(conn);
		Statement stmt = conn.createStatement();
		ResultSet rs = null;
		try {
			// conditions=" and to_days(eat_day_log.firstAddTime) =
			// to_days(now())";
			// String sql = "select * from ((SELECT count(*) as todayCount FROM
			// eat_day_log edl where to_days(edl.firstAddTime) = to_days(now()))
			// as a,(SELECT count(*) as lastCount FROM eat_day_log edl WHERE
			// TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS(
			// NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0) as b,(SELECT
			// count(*)/(WEEKDAY(now())+1) as thisWeekAveCount FROM eat_day_log
			// edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1))
			// as c,(SELECT count(*)/6 as lastWeekAveCount FROM eat_day_log edl
			// WHERE YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1) as d,(SELECT count(*)/DAYOFMONTH(NOW()+1) as
			// thisMonthAveCount FROM eat_day_log edl WHERE DATE_FORMAT(
			// edl.firstAddTime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ))
			// as e,(SELECT sum(edl.riceNumber) as todayRiceNum FROM eat_day_log
			// edl where to_days(edl.firstAddTime) = to_days(now())) as
			// f,(SELECT sum(edl.riceNumber) as lastdayRiceNum FROM eat_day_log
			// edl WHERE TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) <= 1 and
			// TO_DAYS( NOW( ) ) - TO_DAYS(edl.firstAddTime) > 0) as g,(select
			// sum(riceNumber)/(WEEKDAY(now())+1) as thisWeekAveRiceNum from
			// eat_day_log edl where
			// edl.firstAddTime>subdate(curdate(),date_format(curdate(),'%w')-1))
			// as h, (SELECT sum(edl.riceNumber)/6 as lastWeekAveRiceNum FROM
			// eat_day_log edl WHERE
			// YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
			// YEARWEEK(now())-1) as j,(select
			// sum(riceNumber)/DAYOFMONTH(NOW()+1) as thisMonthAveRiceNum from
			// eat_day_log where firstAddTime > DATE_ADD(curdate(),interval
			// -day(curdate())+1 day)) as l)";
			String sql2 = "SELECT eu.id,eu.name FROM eat_user eu where  eu.mobile='" + eatTelNumber + "'";
			System.out.println(sql2);
			rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				Eat_user eu = new Eat_user();
				eu.setId(rs.getInt(1));
				eu.setName(rs.getString(2));
				lessonList.add(eu);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return lessonList;
	}

	//清空昨天的吃饭记录
	// 统计今天的吃饭人数 和米饭的申报量 更新表单
		public static void UpdateEatFoodisLastempty()
				throws Exception {
			// int count = 0;
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql = "UPDATE eat_food  SET isLast =0 ";
				System.out.println(sql);
				stmt.execute(sql);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	
	
	// 更新食物属性----黄官易
	public static void UpdateEatFoodisLast(String[] lastfood) {
		if (null != lastfood && lastfood.length > 0) {
			String sql = "update eat_food  ef SET isLast =1 where ef.id=?";

			Connection conn = ConnectionFactory.makeConnection();
			PreparedStatement ps = null;
			try {
				conn.setAutoCommit(false);
				ps = conn.prepareStatement(sql);
				System.err.println(lastfood.length);
				for (int i = 0; i < lastfood.length; i++) {
					ps.setInt(1, Integer.parseInt(lastfood[i]));
					int uptNum = ps.executeUpdate();
					System.err.println(uptNum);
				}
				conn.commit();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					if (null != ps) {
						ps.close();
					}
					if (null != conn) {
						conn.close();
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
			/*
			 * for(int i=0;i<lastfood.length;i++){ if(i==0){
			 * extrasql=" where ef.id= '"+lastfood[i].toString()+"'"; } else{
			 * extrasql=extrasql+" and ef.id='"+lastfood[i].toString()+"'"; } }
			 * Connection conn = ConnectionFactory.makeConnection(); Statement
			 * stmt = conn.createStatement(); // ResultSet rs= null; // int
			 * count=0; try { String truesql = sql+extrasql;
			 * System.out.println(truesql); int executeUpdate =
			 * stmt.executeUpdate(truesql); System.out.println(executeUpdate); }
			 * catch (Exception e) { e.printStackTrace(); } }
			 */
		}
	}
//获取被选中的昨天的菜肴
	
	// 搜索所有正常的食物

		public static List<Eat_food> SearchEatfoodisLast() throws SQLException {
			List<Eat_food> lessonList = new ArrayList<Eat_food>();
			Connection conn = ConnectionFactory.makeConnection();
			// System.err.println(conn);
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql = "SELECT ef.*,eft.typeName as foodtypeName  FROM `eat_food` ef left join eat_food_type eft on eft.id=ef.foodType  where ef.`status`=1  and ef.isLast=1";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Eat_food af = new Eat_food();
					af.setId(rs.getInt(1));
					af.setFirstAddTime(rs.getTimestamp(2));
					af.setFoodName(rs.getString(3));
					af.setFoodType(rs.getInt(4));
					af.setFirstAddPerson(rs.getInt(5));
					af.setStatus(rs.getInt(6));
					af.setIsLast(rs.getInt(7));
					af.setFoodtypeName(rs.getString(8));
					lessonList.add(af);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return lessonList;
		}
	//插入用户喜欢的id
		
		public static void insertEatUserLoveFood(String[] lastlovefood, String userId) throws SQLException {

			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			Timestamp now = new Timestamp(System.currentTimeMillis());
			// ResultSet rs= null;
			// int count=0;
			String sql="";
			for(int i=0;i<lastlovefood.length;i++){
				sql=sql+"INSERT INTO `eat_love_food` (firstAddTime,foodId,userId,status)" + "VALUES ('" + now + "','"+lastlovefood[i]+"','"+userId+"',1);";
			}
			//System.out.println(sql);
			try {
				
			//	System.out.println(sql);
				stmt.executeUpdate(sql);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	
		//更新一个用户的名字 用id和新名字
		public static void UpdateEat_userName(int useId, String NewUserName) throws SQLException {
			Connection conn = ConnectionFactory.makeConnection();
			Statement stmt = conn.createStatement();
			// ResultSet rs= null;
			// int count=0;
			try {
				// String sql="SELECT npmls.*,e.name FROM
				// eight_factory.`new_precision_machining_log _statistics` npmls
				// left join employee e on npmls.workerid=e.employeeNumber where
				// npmls.id="+id;
				String sql2 = "UPDATE `eat_user` SET  name = '" + NewUserName + "' where id=" + useId;
				// System.out.println(sql2);
				stmt.executeUpdate(sql2);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		
	//获取一个用户喜爱的昨日食物列表
		// 获取用户的信息用手机获取
		public static List<Eat_food> SearchEat_userLastLoveFoodByUserId(String UserId) throws SQLException {
			// int count=0;
			List<Eat_food> lessonList = new ArrayList<Eat_food>();

			Connection conn = ConnectionFactory.makeConnection();
			// System.err.println(conn);
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				String sql2 = "SELECT ef.foodName FROM eat_love_food elf   left join eat_food ef   on  elf.foodId=ef.id where  elf.userId='"+UserId+"'  and to_days(elf.firstAddTime) = to_days(now()) ";
				System.out.println(sql2);
				rs = stmt.executeQuery(sql2);
				while (rs.next()) {
					Eat_food ef = new Eat_food();
					ef.setFoodName(rs.getString(1));
					lessonList.add(ef);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return lessonList;
		}
		//查询昨天向数据库里面添加的一个eat-result-day的 统计值
		
		public static int lastEatTrueCountSearchEat_result_day() throws SQLException {
			 int count=0;
			//List<Eat_result_day> lessonList = new ArrayList<Eat_result_day>();

			Connection conn = ConnectionFactory.makeConnection();
			// System.err.println(conn);
			Statement stmt = conn.createStatement();
			ResultSet rs = null;
			try {
				// conditions=" and to_days(eat_day_log.firstAddTime) =
				// to_days(now())";
				String sql = "SELECT erd.trueEatNumber FROM eat_result_day erd  WHERE TO_DAYS( NOW( ) ) - TO_DAYS(erd.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) - TO_DAYS(erd.firstAddTime) > 0 ";
				System.out.println(sql);
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					count=rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return count;
		}

		
	public static void main(String[] args) throws Exception {
		// int count =SqlMethods.SearchUsername("admin", "admin");

		// System.out.println(count);
		// SqlMethods.UpdateEat_userStatus(1,"0");
		// int count=SqlMethods.SearchEatResultDay();
		// Timestamp now = new Timestamp(System.currentTimeMillis());
		// SqlMethods.insertResultDay(now, 23, 3);
		// System.out.println(count);
		// SqlMethods.inserteat_day_log(now, 10, 1, 0.5);
		// List<Eat_day_log> lessonList= new ArrayList<Eat_day_log>();
		// 昨天的条件 本周 上周 本月
		// String conditions= " and to_days(edl.firstAddTime) = to_days(now())";
		// String lastdayconditions=" and TO_DAYS( NOW( ) ) -
		// TO_DAYS(eat_day_log.firstAddTime) <= 1";
		// String monthConditions="and
		// YEARWEEK(date_format(eat_day_log.firstAddTime,'%Y-%m-%d')) =
		// YEARWEEK(now())-1";
		// String lastConditions=" and
		// YEARWEEK(date_format(eat_day_log.firstAddTime,'%Y-%m-%d')) =
		// YEARWEEK(now())-1";
		// String thisMonthConditions="and DATE_FORMAT(eat_day_log.firstAddTime,
		// '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )";
		// //String sql = "SELECT edl.*,eu.`name` as userName FROM `eat_day_log`
		// edl left join eat_user eu on eu.id=edl.userId where
		// eat_day_log.status='1' '"+conditions+"'";
		// // System.out.println(sql);
		// lessonList=SearchEat_day_logTodaybyConditions(conditions);
		// for(int i=0;i<lessonList.size();i++){
		// System.out.println(lessonList.get(i).getId());
		//

		// }
		// System.out.println(SearchTelCount(10));
		// List<Eat_user> lessonList = new ArrayList<Eat_user>();
		// lessonList=SqlMethods.SearchEatUserInfoByUserid(10);
		// System.out.println(lessonList.get(0).getName());

		// 明天的要吃的人和吃饭的量
		// int count =SqlMethods.SearchEat_day_logTomorrowStatisticsCount();
		// System.out.println(count);
		// double riceNumber
		// =SqlMethods.SearchEat_day_logTomorrowStatisticsRiceNumberSum();
		// System.out.println(riceNumber);
		// String all="";
		// String todayconditions= "where to_days(edl.firstAddTime) =
		// to_days(now())";
		// String lastdayconditions="WHERE TO_DAYS( NOW( ) ) -
		// TO_DAYS(edl.firstAddTime) <= 1 and TO_DAYS( NOW( ) ) -
		// TO_DAYS(edl.firstAddTime) > 0";
		// String monthConditions="where DATE_FORMAT( edl.firstAddTime, '%Y%m' )
		// = DATE_FORMAT( CURDATE( ) , '%Y%m' )";
		// String lastWeekConditions="where
		// YEARWEEK(date_format(edl.firstAddTime,'%Y-%m-%d')) =
		// YEARWEEK(now())-1";
		// String thisMonthConditions="where DATE_FORMAT(edl.firstAddTime,'%Y%m'
		// ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )";
		//
		// int count =SearchEatDayLogbyConditions(monthConditions);
		// System.out.println(count*15); //本月累计的费用
		// int count1 =SearchEatDayLogbyConditions(all);
		// System.out.println(count1); //累计的费用
		// //今天吃饭的人数
		// int todayEatCount =SearchEatDayLogbyConditions(lastdayconditions);
		// System.out.println("todayEatCount:"+todayEatCount);
		// //今天的米饭数量
		// double
		// todayriceNumber=SearchEatDayLogRiceNumberByConditions(lastdayconditions);
		// System.out.println("todayriceNumber: "+todayriceNumber);
		// //今天吃饭的人数实际
		// int
		// todayEatCountTrue=SearchEatResultDaybyConditionsTrue(lastdayconditions);
		// System.out.println("todayEatCountTrue: "+todayEatCountTrue);
		// ////今天的米饭数量实际
		// double
		// todayriceNumberTrue=SearchEatDayLogRiceNumberByConditionsTrue(lastdayconditions);
		// System.out.println("todayriceNumberTrue: "+todayriceNumberTrue);

		// int count = SearchEat_Result_DayTrueStatistical("thisWeek");
		// System.out.println(count);
		// double count1= SearchEat_Result_DayTrueStatisticalRiceNum("today");
		// System.out.println(count1);
		// int count=SqlMethods.SearchEatNumberTodayWrite();
		// System.out.println(count);
		Timestamp now = new Timestamp(System.currentTimeMillis());
		// upDateEat_result_today_before_midnight(2, 6.5, now);
		int count = SearchEat_Result_DayTrueStatisticalNeW(now);
		System.out.println(count);
	}
}
