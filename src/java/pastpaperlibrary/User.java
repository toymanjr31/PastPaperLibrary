package pastpaperlibrary;

import java.util.List;
import javax.faces.bean.ManagedBean;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

@ManagedBean
public class User  implements java.io.Serializable {


     private Integer id;
     String firstname;
     String lastname;
     String email;
     String password;
     public static DatabaseOperations dbObj;
     private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(String firstname, String lastname, String email, String password) {
       this.firstname = firstname;
       this.lastname = lastname;
       this.email = email;
       this.password = password;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstname() {
        return this.firstname;
    }
    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void signUp() {
        dbObj = new DatabaseOperations();
        dbObj.addUserInDb(this);
    }
    
    public void deleteUser() {
        dbObj = new DatabaseOperations();
        dbObj.deleteUserInDb(id);
    }
    
    public boolean checkuser(){
        try {
      System.out.println("email "+email);
      System.out.println("passowrd "+password);
      SessionFactory sessionFactory=new Configuration().configure().buildSessionFactory();
      Session session=sessionFactory.openSession();
      session.beginTransaction();
      Query query=session.createQuery("from User where email=:email and password=:password");
      query.setString("email", email);
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


