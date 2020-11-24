package pastpaperlibrary;

import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseOperations {
    private static Transaction transObj;
    private static Session sessionObj = HibernateUtil.getSessionFactory().openSession();
 
    public void addUserInDb(User userObj) {        
        try {
            transObj = sessionObj.beginTransaction();
            sessionObj.save(userObj);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdUserId",  userObj.getId());                        
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            transObj.commit();
        }
    }
    
    public void addPaperInDb(Pastpaper paperObj) {        
        try {
            transObj = sessionObj.beginTransaction();
            sessionObj.save(paperObj);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("createdPaperId",  paperObj.getId());                        
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            transObj.commit();
        }
    }
    
    public void deleteUserInDb(int delUserId) {
        try {
            transObj = sessionObj.beginTransaction();
            User userId = (User)sessionObj.load(User.class, new Integer(delUserId));
            sessionObj.delete(userId);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedUserId",  delUserId);  
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            transObj.commit();
        }
    }
    
    public void deletePaperInDb(int delPaperId) {
        try {
            transObj = sessionObj.beginTransaction();
            Pastpaper paperId = (Pastpaper)sessionObj.load(Pastpaper.class, new Integer(delPaperId));
            sessionObj.delete(paperId);
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("deletedPaperId",  delPaperId);  
        } catch (Exception exceptionObj) {
            exceptionObj.printStackTrace();
        } finally {
            transObj.commit();
        }
    }
    
    public List<User> retrieveUser(){
       
        List userList = new ArrayList();
        User user =new User();
        try{
            transObj = sessionObj.beginTransaction();
            Query queryObj =sessionObj.createQuery("from User");
            userList = queryObj.list();
            userList.add(user.firstname);
            userList.add(user.lastname);
            userList.add(user.email);
            userList.add(user.password);
            transObj.commit(); 
        }
        catch(Exception e)
        {
            
        }
        return userList;
    }
    
    public List<Pastpaper> retrievePaper(){
       
        List paperList = new ArrayList();
        Pastpaper paper =new Pastpaper();
        try{
            transObj = sessionObj.beginTransaction();
            Query queryObj =sessionObj.createQuery("from Pastpaper");
            paperList = queryObj.list();
            paperList.add(paper.subject);
            paperList.add(paper.lecturer);
            paperList.add(paper.year);
            paperList.add(paper.file);
            transObj.commit(); 
        }
        catch(Exception e)
        {
            
        }
        return paperList;
    }
    
    public List<Pastpaper> retrievePaperbySubject(String sub){
        List paperList = new ArrayList();
        Pastpaper paper = new Pastpaper();
        try{
            transObj = sessionObj.beginTransaction();
            Query queryObj =sessionObj.createQuery("from Pastpaper where subject= :subject");
            queryObj.setString("subject", sub);
            paperList = queryObj.list();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findPaperBySubject",  sub);
            transObj.commit();
        }
        catch(Exception e)
        {
            
        }
        return paperList;
    }
    
    public List<Pastpaper> retrievePaperbyLecturer(String lec){
        List paperList = new ArrayList();
        Pastpaper paper = new Pastpaper();
        try{
            transObj = sessionObj.beginTransaction();
            Query queryObj =sessionObj.createQuery("from Pastpaper where lecturer= :lecturer");
            queryObj.setString("lecturer", lec);
            paperList = queryObj.list();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findPaperByLecturer",  lec);
            transObj.commit();
        }
        catch(Exception e)
        {
            
        }
        return paperList;
    }
    
    public List<Pastpaper> retrievePaperbyYear(String year){
        List paperList = new ArrayList();
        Pastpaper paper = new Pastpaper();
        try{
            transObj = sessionObj.beginTransaction();
            Query queryObj =sessionObj.createQuery("from Pastpaper where year= :year");
            queryObj.setString("year", year);
            paperList = queryObj.list();
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("findPaperByYear",  year);
            transObj.commit();
        }
        catch(Exception e)
        {
            
        }
        return paperList;
    }
    
    
}
