/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.Project;

import Business.Employee.Employee;
import java.util.ArrayList;

/**
 *
 * @author vinod
 */
public class Project {
   
    private String name;
    private int id;
    private int budget;
    private ArrayList<Employee> volunteerList;
    private Employee manager;
    private static int count = 0;

    public Project() {
        count = count + 1;
        id = count;       
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public ArrayList<Employee> getVolunteerList() {
        return volunteerList;
    }

    public void setVolunteerList(ArrayList<Employee> volunteerList) {
        this.volunteerList = volunteerList;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }
    
    @Override
    public String toString() {
        return name;
    }
    
    
}
