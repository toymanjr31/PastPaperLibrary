package pastpaperlibrary;

import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@ManagedBean
public class Admin  implements java.io.Serializable {


     private Integer id;
     private String username;
     private String password;
     public static DatabaseOperations dbObj;
     private static final long serialVersionUID = 1L;

    public Admin() {
    }

    public Admin(String username, String password) {
       this.username = username;
       this.password = password;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<User> getalluserrecords(){
    dbObj = new DatabaseOperations();
    List<User> userList=dbObj.retrieveUser();
    return userList;
    }
    
    public List<Pastpaper> getallpaperrecords(){
    dbObj = new DatabaseOperations();
    List<Pastpaper> paperList=dbObj.retrievePaper();
    return paperList;
    }

    public boolean checkadmin(){
        try {
      System.out.println("username "+username);
      System.out.println("password "+password);
      SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
      Session session=sessionFactory.openSession();
      session.beginTransaction();
      Query query=session.createQuery("from Admin where username=:username and password=:password");
      query.setString("username", username);
      query.setString("password", password);
      List list=query.list();
      System.out.println("list size "+list.size());
            return list.size()==1;
    } catch (Exception e) {
        System.out.println(e);
    }
    return false;
   }




}


