package logic;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.JSONException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

public class User {

    private static HashMap<String,User> users = new HashMap<String,User>();
    private static String filedir = "data/jsons/accounts.json";
    public static User user;

    private String username;
    private String email;
    protected ArrayList<Integer> projects = new ArrayList<Integer>();

    public User(String username, String email) {
        User found = getUser(username);
        if (found != null && found.email.equals(email)) {
            this.username = found.username;
            this.email = found.email;
            this.projects = found.projects;
        } else {
            this.username = username;
            this.email = email;
            this.projects = new ArrayList<Integer>();
            users.put(username, this);
        }
        user = this;
    }

    public static User getUser(String username) {return users.get(username);}

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;users.put(this.username, this);}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;users.put(this.username, this);}
    public void addProject(int id){projects.add(id);users.put(this.username, this);}
    public void removeProject(int id){projects.remove(id);users.put(this.username, this);}

    public static void removeUser(String username) {users.remove(username);}

    public static void loadUsers() {
        try {
            JSONParser parser = new JSONParser();
            FileReader reader = new FileReader(filedir);
            Object obj = parser.parse(reader);
            JSONObject jsonObject = (JSONObject) obj;
            JSONArray jsonArray = (JSONArray) jsonObject.get("users");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject2 = (JSONObject) jsonArray.get(i);
                String username = (String) jsonObject2.get("username");
                String email = (String) jsonObject2.get("email");
                JSONArray jsonArray2 = (JSONArray) jsonObject2.get("projects");
                ArrayList<Integer> projects = new ArrayList<Integer>();
                for (int j = 0; j < jsonArray2.size(); j++) {
                    projects.add(Integer.parseInt((String.valueOf(jsonArray2.get(j)))));
                }
                new User(username, email);}
            } catch (Exception e) { e.printStackTrace(); }
    }

    public static void saveUsers() {
        JSONObject json = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        try {
            for (String username : users.keySet()) {
                User user = users.get(username);
                JSONObject userjson = new JSONObject();
                userjson.put("username", user.username);
                userjson.put("email", user.email);
                userjson.put("projects", List.of(user.projects));
                jsonArray.add(userjson);
            }
            json.put("users", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            PrintWriter writer = new PrintWriter(filedir, "UTF-8");
            writer.println(json.toString());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
