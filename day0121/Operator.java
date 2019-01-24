package week.day0121;

import java.sql.*;

public class Operator {
    Connection conn;        // 数据库连接
    Statement stmt;            // 数据库操作
    // 定义MySQL的数据库驱动程序
    private String DBDRIVER;
    // 定义MySQL数据库的连接地址
    private String DBURL;
    // MySQL数据库的连接用户名
    private String DBUSER;
    // MySQL数据库的连接密码
    private String DBPASS;

    public boolean Init(String strHost, String strDB, String strUserName, String strPasswd) {
        boolean isFind = false;
        try {
            // 定义MySQL数据库的连接地址
            this.DBURL = "jdbc:mysql://" + strHost + ":3306/" + strDB;
            // MySQL数据库的连接用户名
            this.DBUSER = strUserName;
            // MySQL数据库的连接密码
            this.DBPASS = strPasswd;

            //Class.forName(this.DBDRIVER);
            this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
            isFind = true;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.toString());
        }
        return isFind;
    }

    public Operator() {
        // 定义MySQL的数据库驱动程序
        this.DBDRIVER = "org.gjt.mm.mysql.Driver";
        // 定义MySQL数据库的连接地址
        this.DBURL = "jdbc:mysql://localhost:3306/yjsj";
        // MySQL数据库的连接用户名
        this.DBUSER = "root";
        // MySQL数据库的连接密码
        this.DBPASS = "root";
        this.conn = null;        // 数据库连接
        try{
            Class.forName(this.DBDRIVER);
            this.conn = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
        }
        catch (Exception ex){
            System.out.println("Error:" + ex.toString());
        }
    }

    public boolean Add(Person person) throws Exception{
        boolean isFind = false;
        int nResult = this.FindCount(person.GetName());
        if (nResult < 0){
           return isFind;
        }else if (nResult == 1) {
            System.out.println("Find Record:"+person.GetName());
            return isFind;
        }
        try {
            System.out.println("Operator Adder:");
            String sql = "INSERT INTO user_info(name,age) VALUES ('"
                    + person.GetName() + "'," + person.GetAge() + ");";
            this.stmt = this.conn.createStatement();    // 实例化Statement对象
            this.stmt.executeUpdate(sql);        // 执行数据库更新操作
            this.stmt.close();                    // 关闭操作
            isFind = true;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.toString());
            isFind = false;
        }
        return isFind;
    }

    public boolean Del(String strName) throws  Exception{
        boolean isFind = false;
        int nResult = this.FindCount(strName);
        if (nResult < 0){
           return isFind;
        }else if (nResult == 0) {
            System.out.println("No Find Record:"+strName);
            return isFind;
        }
        System.out.println("Operator Del.");
        try {
            String sql = "delete from user_info where name = '" +
                    strName + "';";
            isFind = true;
            this.stmt = this.conn.createStatement();    // 实例化Statement对象
            this.stmt.executeUpdate(sql);        // 执行数据库更新操作
            this.stmt.close();                    // 关闭操作
            isFind = true;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.toString());
            isFind = false;
        }
        return isFind;
    }

    public boolean Modi(Person person) throws Exception {
        boolean isFind = false;
        System.out.println("Operator Modi.");
        int nResult = this.FindCount(person.GetName());
        if (nResult < 0){
           return isFind;
        }else if (nResult == 0) {
            System.out.println("No Find Record:"+person.GetName());
            return isFind;
        }
        try {
            String sql = "update user_info set age =" +
                    person.GetAge() + " where name = '" +
                    person.GetName() + "'";

            this.stmt = this.conn.createStatement();    // 实例化Statement对象
            this.stmt.executeUpdate(sql);        // 执行数据库更新操作
            this.stmt.close();                    // 关闭操作
            isFind = true;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.toString());
            isFind = false;
        }
        return isFind;
    }

    public boolean DisplayAll() throws Exception {
        System.out.println("Operator Dispaly ALL.");
        boolean isFind = false;
        try {
            String sql = "select * from user_info";
            PreparedStatement pstmt;
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("* ");
            }
            pstmt.close();
            System.out.println("**********************\n");

            isFind = true;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.toString());
            isFind = false;
        }
        return isFind;
    }
    public int FindCount(String strName) throws Exception {
        System.out.println("Operator Find.");
        int nCount = 0;
        try {
            String sql = "select count(id) from user_info where name = '" +
                    strName + "'";
            Statement stmt = this.conn.createStatement();
            ResultSet rs = null;
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                nCount = rs.getInt(1);
                break;
            }
            stmt.close();

        } catch (Exception ex) {
            System.out.println("Error:" + ex.toString());
            nCount = -1;
        }
        return nCount;
    }

    public boolean Find(String strName) throws Exception {
        System.out.println("Operator Find.");
        boolean isFind = false;
        try {
            String sql = "select * from user_info where name = '" +
                    strName + "'";
            PreparedStatement pstmt;
            pstmt = (PreparedStatement)conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            System.out.println("Display All ***************************\n");
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= col; i++) {
                    System.out.print(rs.getString(i) + "\t");
                    if ((i == 2) && (rs.getString(i).length() < 8)) {
                        System.out.print("\t");
                    }
                }
                System.out.println("Dispaly All ***************************\n");
            }
            pstmt.close();
            isFind = true;
        } catch (Exception ex) {
            System.out.println("Error:" + ex.toString());
            isFind = false;
        }
        return isFind;
    }
}
