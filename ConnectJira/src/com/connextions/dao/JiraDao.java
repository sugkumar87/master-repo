package com.connextions.dao;

import java.util.List;

import com.atlassian.jira.rest.client.domain.SearchResult;

public interface JiraDao {

	/**
	 * This method retrieve issues to an assigned user.
	 * @param assignee
	 */
	List<SearchResult> fetchIssuesByAssignee(String assignee);
}
