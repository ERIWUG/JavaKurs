import java.sql.*;
/**
 * @author E.V. Belyi
 * @version 1.0
 *
 * Interface for manipulate with DB
 */
public class DBHandler extends Config{
    private Connection con;
    /*
    GetConnection to DB
     */
    public Connection GetConnection() throws ClassNotFoundException,SQLException{
        String connectionString = "jdbc:mysql://"+host+":"+port+"/"+name;
        //jdbc:mysql://localhost;database=mywebsitedb;user=sa;password=thatstrue;"
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(connectionString,user,pass);
        return con;
    }
    /*
    *Find last Id in table
    * @param String q - table name
     */
    public int GetLastId(String q) throws ClassNotFoundException,SQLException{
        String s1 = "Select id from "+q+" order by id desc limit 1";
        PreparedStatement ps=GetConnection().prepareStatement(s1);
        ResultSet rs = ps.executeQuery();
        int n=0;
        while(rs.next()){
            n = rs.getInt(1);
        }
        return n;
    }
    /*
     *Get all staff names
     */
    public String[] GetNames()throws ClassNotFoundException,SQLException {
        String s = "Select firstName,lastName from bd.staff order by lastName ";
        int n = GetLastId("staff");
        PreparedStatement ps=GetConnection().prepareStatement(s);
        ResultSet rs = ps.executeQuery();
        String[] res = new String[n];
        int i=0;
        while(rs.next()){
            res[i] = rs.getString(2)+" "+rs.getString(1);
            i++;
        }
        return res;
    }
    /*
     *Get id of current user
     * @param String n - user name and last name
     */
    public int GetId(String n)throws  ClassNotFoundException,SQLException{
        String[] sb = n.split(" ");
        String s = "Select id from bd.staff where staff.firstName = '"+sb[1]+"' and staff.lastName ='"+sb[0]+"'";
        PreparedStatement ps=GetConnection().prepareStatement(s);
        ResultSet rs = ps.executeQuery();
        int ans=0;
        while(rs.next()){
            ans = rs.getInt("id");
        }
        return ans;
    }
    /*
     *Get vacation days of user in current month
     * @param String n - user name and last name
     * @param int n - month number
     */
    public int GetDay(String name,int n)  throws ClassNotFoundException,SQLException{
        int id = GetId(name);

        String s = "Select * from vacationcart where id = "+id;
        PreparedStatement ps=GetConnection().prepareStatement(s);
        ResultSet rs = ps.executeQuery();
        int res = 0;
        while(rs.next()){
            res = rs.getInt(n);
        }
        return res;
    }
    /*
     *Get vacation days of user
     * @param String n - user name and last name
     */
    public int[] GetDays(String n) throws ClassNotFoundException,SQLException{
        int id = GetId(n);
        int[] ans = new int[12];
        String s = "Select * from vacationcart where id = "+id;
        PreparedStatement ps=GetConnection().prepareStatement(s);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            for(int i=2;i<14;i++){
                ans[i-2] = rs.getInt(i);
            }
        }
        return ans;
    }
    /*
     *Update vacation days of user in current month
     * @param String n - user name and last name
     * @param int[] n - new data
     */
    public void UpdateDays(String n,int[] newd) throws ClassNotFoundException,SQLException{
        int id = GetId(n);
        String s = "UPDATE `bd`.`vacationcart` SET `January` = '"+newd[0]+"', `February` = '"+newd[1]+"', `March` = '"+newd[2]+"', `April` = '"+newd[3]+"', `May` = '"+newd[4]+"', `June` = '"+newd[5]+"', `July` = '"+newd[6]+"', `August` = '"+newd[7]+"', `September` = '"+newd[8]+"', `October` = '"+newd[9]+"', `November` = '"+newd[10]+"', `December` = '"+newd[11]+"' WHERE (`id` = '"+id+"');";
        PreparedStatement ps = GetConnection().prepareStatement(s);
        ps.executeUpdate();
    }
    /*
     *Delete user
     * @param String n - user name and last name
     */
    public void DeleteName(String n) throws ClassNotFoundException,SQLException{
        int id = GetId(n);
        String s = "DELETE FROM `bd`.`staff` WHERE (`id` = '"+id+"');";
        PreparedStatement ps = GetConnection().prepareStatement(s);
        ps.executeUpdate();
        s = "DELETE FROM `bd`.`vacationcart` WHERE (`id` = '"+id+"');";
        ps = GetConnection().prepareStatement(s);
        ps.executeUpdate();

    }
    /*
     *Update user name and last name
     * @param String n - user name
     * @param String n1 - user last name
     */
    public void UpdateName(String n,String n1) throws ClassNotFoundException,SQLException{
        int id = GetId(n+" "+n1);
        String s = "UPDATE `bd`.`staff` SET `firstName` = '"+n1+"', `lastName` = '"+n+"' WHERE (`id` = '"+id+"');";
        PreparedStatement ps = GetConnection().prepareStatement(s);
        ps.executeUpdate();
    }
    /*
     *make new user
     * @param String name - user name
     * @param String lastName - user last name
     * @param int[] days - vacation Days
     */
    public void InsertName(String name,String lastName,int[] days)throws ClassNotFoundException,SQLException{
        int id = GetLastId("staff")+1;
        String s = "INSERT INTO `bd`.`staff` (`id`, `firstName`, `lastName`, `qualification`) VALUES ('"+id+"', '"+name+"', '"+lastName+"', '"+"q1"+"');";
        PreparedStatement ps = GetConnection().prepareStatement(s);
        ps.executeUpdate();
        s ="INSERT INTO `bd`.`vacationcart` (`id`, `January`, `February`, `March`, `April`, `May`, `June`, `July`, `August`, `September`, `October`, `November`, `December`) VALUES ('"+id+"', '"+days[0]+"', '"+days[1]+"', '"+days[2]+"', '"+days[3]+"', '"+days[4]+"', '"+days[5]+"', '"+days[6]+"', '"+days[7]+"', '"+days[8]+"', '"+days[9]+"', '"+days[10]+"', '"+days[11]+"');";
        ps = GetConnection().prepareStatement(s);
        ps.executeUpdate();
    }
}
