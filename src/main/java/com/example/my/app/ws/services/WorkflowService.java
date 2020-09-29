package com.example.my.app.ws.services;

import java.util.List;

import com.example.my.app.ws.shared.dto.WorkflowDto;

public interface WorkflowService {
	WorkflowDto createWorkflow(WorkflowDto workflowDto);

	WorkflowDto getWorkflowByWorkflowId(String workflowId);

	void deleteWorkflow(String workflowId);

	List<WorkflowDto> getAllworkflows(String email);

	WorkflowDto updateWorkflow(String workflowid, String userid);

	WorkflowDto updateWorkflowRemove(String workflowid, String userid);
}
