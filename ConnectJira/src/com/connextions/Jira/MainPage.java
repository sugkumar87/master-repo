package com.connextions.Jira;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="mainPage")
@RequestScoped
public class MainPage {
	
	public String assignJiraIssues(){
		System.out.println("Assign Jira Issues....");
		return "assignJiraIssues";
	}
	
	public String generateStatusReport(){
		System.out.println("Generate Status Report....");
		return "generateStatusReport";
	}

}
