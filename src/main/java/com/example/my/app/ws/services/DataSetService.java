package com.example.my.app.ws.services;

import java.util.List;

import com.example.my.app.ws.shared.dto.DataSetDto;

public interface DataSetService {
	DataSetDto dataSetCreate(DataSetDto dataSetDto);
	DataSetDto getDataSet(String dataSetId);
	void DataSetDelete(String dataSetId);
	DataSetDto getDataSetByDataSetId(String dataSetId);
	DataSetDto DataSetUpdate(String dataSetId,DataSetDto dataSetDto);
	
	//List<DataSetDto> getUsers(int page,int limit,String search,int status);
	DataSetDto updateDataset(String workflowid, String datasetid);
	List<DataSetDto> getAllDataSet();
	List<DataSetDto> getDataSets(String id);
	List<DataSetDto> getDataSetsOut();
}
