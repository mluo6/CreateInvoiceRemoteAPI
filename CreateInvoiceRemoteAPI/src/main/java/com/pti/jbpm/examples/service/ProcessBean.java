package com.pti.jbpm.examples.service;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.ProcessInstance;

import com.pti.jbpm.examples.util.RemoteApi;

public class ProcessBean {
    public long startProcess(String recipient) throws Exception {
        long processInstanceId = -1;

        try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("userCode", recipient);
			params.put("siteCode", "PTBANK");
			params.put("companyCode", "SELLER01");
			ProcessInstance processInstance = RemoteApi.getKieSession().startProcess(
					RemoteApi.PROCESS_ID, params);
			processInstanceId = processInstance.getId();
			System.out.println("=== Process started ... : processInstanceId = "
					+ processInstanceId);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return processInstanceId;
    }

	public void stopProcessInstance(String processInstanceId) throws Exception {
		System.out.println("ProcessBean stopProcessInstance...");
		try {
	        RemoteApi.getKieSession().abortProcessInstance(Long.valueOf(processInstanceId));
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
