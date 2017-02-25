package libraries;

import java.util.ArrayList;
import java.util.List;
 

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
 

import android.content.Context;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;
 
public class UserFunctions {
     
    private JSONParser jsonParser;
     
    // Testing in localhost using wamp or xampp 
    // use http://10.0.2.2/ to connect to your localhost ie http://localhost/
    
    private static String loginURL = "http://10.0.2.2/ah_login_api/";
    
//    private static String registerURL = "http://susmay4u.5gbfree.com/college_vam/hindex.php";
//    private static String registerURL2 = "http://susmay4u.5gbfree.com/college_vam/hindex2.php";
//    private static String sendmsgURL = "http://susmay4u.5gbfree.com/college_vam/send_push_notification_message.php";
  
  private static String registerURL = "http://sarithag.5gbfree.com/gcm_server_files/hindex.php";
  private static String registerURL2 = "http://sarithag.5gbfree.com/gcm_server_files/hindex2.php";
  private static String sendmsgURL = "http://sarithag.5gbfree.com/gcm_server_files/send_push_notification_message.php";
  
    private static String login_tag = "login";
    private static String register_tag = "registervoter";
    private static String getvcode_tag = "getvcode";
    private static String party_tag = "partynews";
    private static String getnews_tag = "getnews";
    private static String getparty_tag = "party";
    private static String status_tag = "changestatus";
   
    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
     
    /**
     * function make 
     * Login Request
     * @param email
     * @param password
     * */
    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
     
    
    public JSONObject getallusers(String branch){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "getgcmusers"));
        params.add(new BasicNameValuePair("branch",branch));
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
   
    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerVoter(String name,String voterid,String email,String phone){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
       ;
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("email", email));
     
        params.add(new BasicNameValuePair("voterid", voterid));
        params.add(new BasicNameValuePair("phone", phone));
        
       
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        
        // return json
        return json;
    }
    
    public JSONObject senMessage(String regid,String message){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
       
        params.add(new BasicNameValuePair("tag", "send"));
        params.add(new BasicNameValuePair("regId", regid));
        params.add(new BasicNameValuePair("message", message));
     
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        
        // return json
        return json;
    }
    
    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject addcandidates(String name,String state,String district,String area,String party,String details){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
       
        params.add(new BasicNameValuePair("tag","addcandidates"));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("state", state));
        params.add(new BasicNameValuePair("district", district));
        params.add(new BasicNameValuePair("area", area));
        params.add(new BasicNameValuePair("party", party));
        params.add(new BasicNameValuePair("details", details));
        
       
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        
        // return json
        return json;
    }
    /**
     * Function to get messages
     * */  
    public JSONObject getvcode(String email){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", getvcode_tag));
        params.add(new BasicNameValuePair("email", email));
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    
    public JSONObject instruction(){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "instruction"));
       
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    
    public JSONObject changestatus(String email,String status){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", getvcode_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("status", status));
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    
    
    public JSONObject candetails(String name){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "candidatedetails"));
        params.add(new BasicNameValuePair("name", name));
    
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    public JSONObject calvotes(String votes,String party){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "votes"));
        params.add(new BasicNameValuePair("votes",votes));
        params.add(new BasicNameValuePair("party", party));
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    public JSONObject getnews(){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", getnews_tag));
     
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    
    
    public JSONObject getResults(){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", "Results"));
     
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    public JSONObject getVotes(String party){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag","getvotes"));
        params.add(new BasicNameValuePair("party",party));
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    public JSONObject getParty(){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", getparty_tag));
     
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    
    public JSONObject getAreas(String state){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag","districts"));
        params.add(new BasicNameValuePair("state",state));
       
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    
    public JSONObject getCandidates(String district){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag","candidates"));
        params.add(new BasicNameValuePair("district",district));
       
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    
    public JSONObject getCandidatesbyname(String district,String name){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag","candidatedetailsbyname"));
        params.add(new BasicNameValuePair("district",district));
        params.add(new BasicNameValuePair("name",name));
       
      
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
     
        return json;
    }
    public JSONObject addParty(String name,String headline,String file,String image){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
       
        params.add(new BasicNameValuePair("tag", party_tag));
        params.add(new BasicNameValuePair("name", name));
        params.add(new BasicNameValuePair("headline", headline));
     
        params.add(new BasicNameValuePair("news", file));
    
        params.add(new BasicNameValuePair("image",image));
       
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        
        // return json
        return json;
    }
    
    
    public JSONObject query(String mail,String candidate,String query){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
       
        params.add(new BasicNameValuePair("tag","query"));
        params.add(new BasicNameValuePair("mail",mail));
        params.add(new BasicNameValuePair("candidate", candidate));
     
        params.add(new BasicNameValuePair("query",query));
    
      
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        
        // return json
        return json;
    }
    
    
    public JSONObject viewReply(String mail){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
       
        params.add(new BasicNameValuePair("tag","reply"));
        params.add(new BasicNameValuePair("mail",mail));
      
      
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        
        // return json
        return json;
    }
    
    public JSONObject viewquery(String mail){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
       
        params.add(new BasicNameValuePair("tag","viewquery"));
        params.add(new BasicNameValuePair("mail",mail));
      
      
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        
        // return json
        return json;
    }
    
    public JSONObject reply(String query,String answer){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
       
        params.add(new BasicNameValuePair("tag","candidatereply"));
        params.add(new BasicNameValuePair("query",query));
        params.add(new BasicNameValuePair("answer",answer));
      
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        
        // return json
        return json;
    }
}