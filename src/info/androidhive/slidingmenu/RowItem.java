package info.androidhive.slidingmenu;

public class RowItem {
   
    private String username,mailid,regid;
    boolean selected = false;
    
 
    public RowItem(String username,String mailid,String regid) {
    	
        this.mailid=mailid;
        this.username = username;
        this.regid =regid;
        
       
    }
   
   
    public void setusername(String username) {
    	 this.username = username;
    }
    public String getname() {
        return username;
    }
    
    public void setmailid(String mailid) {
    	 this.mailid=mailid;
    }
    public String getmailid() {
        return mailid;
    }
    
    
    public void setregid(String regid) {
    	 this.regid =regid;
    }
    public String getregid() {
    	return regid;
    }
    @Override
    public String toString() {
        return regid;
    }
    public boolean isSelected() {
    	  return selected;
    	 }
    	 public void setSelected(boolean selected) {
    	  this.selected = selected;
    	 }
    	  
}