package com.connextions.daoImpl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.connextions.dao.JiraDao;

public class JiraDaoImpl implements JiraDao{
	
	private JiraRestClient restClient;
	
	
	private void getJiraConnection(){
		AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
		
		try {
			restClient = factory.createWithBasicHttpAuthentication(new URI("https://hub.connextions.com:8443"), "ksugandh", "kgs78691");
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@Override
	public List<SearchResult> fetchIssuesByAssignee(String assignee) {
		
		if(restClient == null){
			this.getJiraConnection();
		}
		
		SearchResult issues = restClient.getSearchClient()
				.searchJql("assignee=" + assignee).claim();
		
		return null;
	}

}
