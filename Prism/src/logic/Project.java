package logic;

import javax.swing.*;
import java.util.ArrayList;

public class Project extends JFrame {
    
    private static String filedir = "Prism/data/jsons/projects.json";
    protected static ArrayList<Project> projects = new ArrayList<Project>();

    public String name;
    public String description;
    public int id;
    public String startDate;
    public String endDate;
    public ArrayList<String> users = new ArrayList<String>();

    public Project(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static void loadProjects() {
        
    }

}
