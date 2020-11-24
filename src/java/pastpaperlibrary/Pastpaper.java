package pastpaperlibrary;

import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Pastpaper  implements java.io.Serializable {


     private Integer id;
     String subject;
     String lecturer;
     String year;
     String file;
     List<Pastpaper> paperList;
     public static DatabaseOperations dbObj;
     private static final long serialVersionUID = 1L;

    public Pastpaper() {
    }

    public Pastpaper(String subject, String lecturer, String year, String file) {
       this.subject = subject;
       this.lecturer = lecturer;
       this.year = year;
       this.file = file;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getSubject() {
        return this.subject;
    }
    
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getLecturer() {
        return this.lecturer;
    }
    
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }
    public String getYear() {
        return this.year;
    }
    
    public void setYear(String year) {
        this.year = year;
    }
    public String getFile() {
        return this.file;
    }
    
    public void setFile(String file) {
        this.file = file;
    }
    
    public List<Pastpaper> getPaperList() {
        return paperList;
    }

    public void setPaperList(List<Pastpaper> paperList) {
        this.paperList = paperList;
    }
    
    public void addPastPaper(){
        dbObj = new DatabaseOperations();
        dbObj.addPaperInDb(this);
    }
    
    public void deletePastPaper() {
        dbObj = new DatabaseOperations();
        dbObj.deletePaperInDb(id);
    }
    
    public List<Pastpaper> getbySubject(){
        dbObj = new DatabaseOperations();
        paperList = dbObj.retrievePaperbySubject(subject);
        for(Pastpaper temp : paperList){
            subject = temp.getSubject();
            lecturer = temp.getLecturer();
            year = temp.getYear();
            file = temp.getFile();
        }
        return paperList;
    }
    
    public List<Pastpaper> getbyLecturer(){
        dbObj = new DatabaseOperations();
        paperList = dbObj.retrievePaperbyLecturer(lecturer);
        for(Pastpaper temp : paperList){
            subject = temp.getSubject();
            lecturer = temp.getLecturer();
            year = temp.getYear();
            file = temp.getFile();
        }
        return paperList;
    }
    
    public List<Pastpaper> getbyYear(){
        dbObj = new DatabaseOperations();
        paperList = dbObj.retrievePaperbyYear(year);
        for(Pastpaper temp : paperList){
            subject = temp.getSubject();
            lecturer = temp.getLecturer();
            year = temp.getYear();
            file = temp.getFile();
        }
        return paperList;
    }

}


