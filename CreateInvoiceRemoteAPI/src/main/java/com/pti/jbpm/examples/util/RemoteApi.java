package com.pti.jbpm.examples.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.jbpm.process.audit.AuditLogService;
import org.kie.api.runtime.KieSession;
import org.kie.api.task.TaskService;
import org.kie.services.client.api.RemoteRestRuntimeFactory;
import org.kie.services.client.api.command.RemoteRuntimeEngine;

public class RemoteApi {
	public static final String PROCESS_ID = "com.pti.bpm.CreateInvoice";
	private static String deploymentId = "com.pti.demo:CreateInvoice:1.0";
	private static String baseUrl = "http://localhost:8080/jbpm-console";
	private static String user = "krisv";
	private static String pwd = "krisv";
	private static RemoteRuntimeEngine runtimeEngine;

	static {
		try {
			RemoteRestRuntimeFactory restSessionFactory = new RemoteRestRuntimeFactory(
					deploymentId, new URL(baseUrl), user, pwd);
			System.out.println("===RemoteApi: got RemoteRestRuntimeFactory");
			// Create KieSession and TaskService instances and use them
			runtimeEngine = restSessionFactory.newRuntimeEngine();
		} catch (MalformedURLException e) {
			System.out
					.println("===RemoteApi: creating RemoteRuntimeEngine error");
			e.printStackTrace();
		}
	}

	public static KieSession getKieSession() {
		System.out.println("===RemoteApi: got KieSession");
		return runtimeEngine.getKieSession();
	}

	public static AuditLogService getAuditLogService() {
		System.out.println("===RemoteApi: got AuditLogService");
		return runtimeEngine.getAuditLogService();
	}

	public static TaskService getTaskService() {
		System.out.println("===RemoteApi: got TaskService");
		return runtimeEngine.getTaskService();
	}
}
