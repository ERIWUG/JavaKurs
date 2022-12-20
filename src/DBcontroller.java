import java.sql.SQLException;
/**
 * @author E.V. Belyi
 * @version 1.0
 *
 * Interface for process database data
 */
public interface DBcontroller
{
    /*
     *Get vacationdays of user in current month
     * @param String n -user name and last name ;
     * @param int n1 - month number
     */
    static int DBGetDay(String n,int n1){
        DBHandler db = new DBHandler();
        int res = 0;
        try {
            res = db.GetDay(n,n1);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    /*
     *Get all member names
     */
    static String[] DBGetNames(){
        DBHandler db = new DBHandler();
        String[] res;
        try {
            res = db.GetNames();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    /*
     *Get vacationdays of user
     * @param String n -user name and last name ;
     */
    static int[] DBGetDays(String n){
        DBHandler db = new DBHandler();
        int[] res;
        try {
            res = db.GetDays(n);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return res;
    }
    /*
     *update vacationdays of user
     * @param String n -user name and last name ;
     * @param int[] newd - all vacation days
     */
    static void DBUpdateDays(String n, int[] newd){
        DBHandler db = new DBHandler();
        try {
           db.UpdateDays(n,newd);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    /*
     *Delete current user
     * @param String n - username and last name ;
     */
    static void DBDeleteName(String n){
        DBHandler db = new DBHandler();
        try {
            db.DeleteName(n);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     *update user
     * @param String n -username ;
     * @param String n1 - user lastName
     */
    static void DBUpdateName(String n,String n1){
        DBHandler db = new DBHandler();

        try {
            db.UpdateName(n,n1);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /*
     *make new user
     * @param String name -user name ;
     * @param String lastName - user lastName
     * @param int[] days - vacationDays
     */
    static void DBInsertName(String name,String lastName,int[] days){
        DBHandler db = new DBHandler();
        try {
            db.InsertName(name,lastName,days);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
