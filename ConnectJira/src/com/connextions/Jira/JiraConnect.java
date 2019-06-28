package com.connextions.Jira;
import java.net.URI;
import java.net.URISyntaxException;

import com.atlassian.jira.rest.client.JiraRestClient;
import com.atlassian.jira.rest.client.domain.Issue;
import com.atlassian.jira.rest.client.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;


public class JiraConnect {

	public static void main(String[] args) {
		AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
		
		JiraRestClient restClient = null;
		try {
			restClient = factory.createWithBasicHttpAuthentication(new URI("https://itjira.connextions.com:8443"), "ksugandh", "password123");
		} catch (URISyntaxException e) {
			System.out.println(e.getMessage());
		}
		
		/*SearchResult issues = restClient.getSearchClient()
				.searchJql("project=WO").claim();*/
		
 
		final Issue issue = restClient.getIssueClient().getIssue("WO-3461").claim();
		System.out.println("Summary : " + issue.getSummary());
		System.out.println("Key :" + issue.getKey());
		System.out.println("Description : " + issue.getDescription());
		System.out.println("Assignee :" + issue.getAssignee());
		System.out.println("status :" + issue.getStatus());
       /* SearchResult r = jc.getSearchClient().searchJql("type = Epic ORDER BY RANK ASC", null);
         
        Iterator<BasicIssue> it = r.getIssues().iterator();
        while (it.hasNext()) {
             
            Issue issue = jc.getIssueClient().getIssue(((BasicIssue)it.next()).getKey(), null);
             
            System.out.println("Epic: " + issue.getKey() + " " + issue.getSummary());
             
            Iterator<IssueLink> itLink = issue.getIssueLinks().iterator();
            while (itLink.hasNext()) {
                 
                IssueLink issueLink = (IssueLink)itLink.next();
                Issue issueL = jc.getIssueClient().getIssue((issueLink).getTargetIssueKey(), null);
                 
                System.out.println(issueLink.getIssueLinkType().getDescription() + ": " + issueL.getKey() + " " + issueL.getSummary() + " " + issueL.getFieldByName("Story Points").getValue());


	}
        }*/
	}

}
