package week.day0121;

import java.sql.*;

public class Operator {
    Connection conn;        // ���ݿ�����
    Statement stmt;            // ���ݿ����
    // ����MySQL�����ݿ���������
    private String DBDRIVER;
    // ����MySQL���ݿ�����ӵ�ַ
    private String DBURL;
    // MySQL���ݿ�������û���
    private String DBUSER;
    // MySQL���ݿ����������
    private String DBPASS;

    public boolean Init(String strHost, String strDB, String strUserName, String strPasswd) {
        boolean isFind = false;
        try {
            // ����MySQL���ݿ�����ӵ�ַ
            this.DBURL = "jdbc:mysql://" + strHost + ":3306/" + strDB;
            // MySQL���ݿ�������û���
            this.DBUSER = strUserName;
            // MySQL���ݿ����������
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
        // ����MySQL�����ݿ���������
        this.DBDRIVER = "org.gjt.mm.mysql.Driver";
        // ����MySQL���ݿ�����ӵ�ַ
        this.DBURL = "jdbc:mysql://localhost:3306/yjsj";
        // MySQL���ݿ�������û���
        this.DBUSER = "root";
        // MySQL���ݿ����������
        this.DBPASS = "root";
        this.conn = null;        // ���ݿ�����
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
            this.stmt = this.conn.createStatement();    // ʵ����Statement����
            this.stmt.executeUpdate(sql);        // ִ�����ݿ���²���
            this.stmt.close();                    // �رղ���
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
            this.stmt = this.conn.createStatement();    // ʵ����Statement����
            this.stmt.executeUpdate(sql);        // ִ�����ݿ���²���
            this.stmt.close();                    // �رղ���
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

            this.stmt = this.conn.createStatement();    // ʵ����Statement����
            this.stmt.executeUpdate(sql);        // ִ�����ݿ���²���
            this.stmt.close();                    // �رղ���
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
