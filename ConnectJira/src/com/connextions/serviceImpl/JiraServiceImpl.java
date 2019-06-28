package com.connextions.serviceImpl;

import java.util.List;

import org.springframework.context.annotation.Scope;

import com.atlassian.jira.rest.client.domain.SearchResult;
import com.connextions.dao.JiraDao;
import com.connextions.service.JiraService;

public class JiraServiceImpl implements JiraService{

	private JiraDao jiraDao;
	
	@Override
	public List<SearchResult> fetchIssuesByAssignee(String assignee) {
		return jiraDao.fetchIssuesByAssignee(assignee);
	}

}
