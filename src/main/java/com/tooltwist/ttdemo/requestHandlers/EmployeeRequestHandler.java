package com.tooltwist.ttdemo.requestHandlers;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.dinaa.ui.UiModuleException;
import com.dinaa.ui.UimHelper;
import com.dinaa.util.DinaaUtilUrls;
import com.dinaa.xpc.XDS;
import com.dinaa.xpc.XpcException;
import com.dinaa.xpc.XpcLogin;
import com.dinaa.xpc.XpcSecurity;
import com.tooltwist.xdata.XD;
import com.tooltwist.xdata.XDException;

import tooltwist.cloudmall.exceptions.CloudmallException;
import tooltwist.cloudmall.utils.StringUtil;
import tooltwist.cloudmall.utils.WebUtils;
import tooltwist.misc.TtConfig;
import tooltwist.wbd.WbdRequestHandler;

public class EmployeeRequestHandler extends WbdRequestHandler {

	@Override
	public boolean handler(UimHelper uh, String widgetId, String method) throws UiModuleException, IOException, ServletException {
		HttpServletRequest request = uh.getRequest();
		String op = request.getParameter("subop");
		long requestTime = System.currentTimeMillis();

		try {
			if (!op.equals("") || op != null) {
				Class<?>[] params = new Class[] { UimHelper.class };
				Method method1 = this.getClass().getMethod(op, params);
				method1.invoke(this, uh);
			}
		} catch (Exception ex) {
			logger.error(StringUtil.getRequestHandlerLogError(requestTime, this.getClass().getName(), uh.getRequest().getQueryString(), ex.getMessage()));
		}

		return true;
	}
	
	public void deleteEmployee(UimHelper uh) throws XpcException, XDException, IOException, JSONException, CloudmallException {
		String id = uh.getRequestValue("id");
		XpcSecurity xpcSecurity = XpcLogin.login(DinaaUtilUrls.USER_TYPE, TtConfig.getInternalLoginName(), TtConfig.getInternalLoginPassword());
		// initiate new JSONObject
		JSONObject inputJson = new JSONObject();
		inputJson.put("id", id);
		// put JSONObject into XD
		XD input = new XD(inputJson.toString());
		// call getEmployeeList web API
		XD output = XDS.call(xpcSecurity, "deleteEmployee", "delete", input);
		System.out.println(output.getString());
		
		boolean isSuccess = false;
		if (output.getString("//affectedRows").equalsIgnoreCase("1")) {
			isSuccess = true;
		}
		JSONObject resp = new JSONObject();
		resp.put("isSuccess", isSuccess);
		WebUtils.sendResponseObject(uh.getResponse(), resp);
	}
	
	public void saveEmployee(UimHelper uh) throws XpcException, XDException, IOException, JSONException, CloudmallException {
		String name = uh.getRequestValue("name");
		String address = uh.getRequestValue("address");
		String phone = uh.getRequestValue("phone");
		String position = uh.getRequestValue("position");
		String department = uh.getRequestValue("department");
		
				
		XpcSecurity xpcSecurity = XpcLogin.login(DinaaUtilUrls.USER_TYPE, TtConfig.getInternalLoginName(), TtConfig.getInternalLoginPassword());
		// initiate new JSONObject
		JSONObject inputJson = new JSONObject();
		inputJson.put("name", name);
		inputJson.put("address", address);
		inputJson.put("phoneNumber", phone);
		inputJson.put("position", position);
		inputJson.put("department", department);
		// put JSONObject into XD
		XD input = new XD(inputJson.toString());
		// call getEmployeeList web API
		XD output = XDS.call(xpcSecurity, "addEmployee", "add", input);
		System.out.println(output.getString());
		
		boolean isSuccess = false;
		if (output.getString("//affectedRows").equalsIgnoreCase("1")) {
			isSuccess = true;
		}
		JSONObject resp = new JSONObject();
		resp.put("isSuccess", isSuccess);
		WebUtils.sendResponseObject(uh.getResponse(), resp);
	}
	
	public void updateEmployee(UimHelper uh) throws XpcException, XDException, IOException, JSONException, CloudmallException {
		String id = uh.getRequestValue("id");
		String name = uh.getRequestValue("name");
		String address = uh.getRequestValue("address");
		String phone = uh.getRequestValue("phone");
		String position = uh.getRequestValue("position");
		String department = uh.getRequestValue("department");
		
				
		XpcSecurity xpcSecurity = XpcLogin.login(DinaaUtilUrls.USER_TYPE, TtConfig.getInternalLoginName(), TtConfig.getInternalLoginPassword());
		// initiate new JSONObject
		JSONObject inputJson = new JSONObject();
		inputJson.put("id", id);
		inputJson.put("name", name);
		inputJson.put("address", address);
		inputJson.put("phoneNumber", phone);
		inputJson.put("position", position);
		inputJson.put("department", department);
		// put JSONObject into XD
		XD input = new XD(inputJson.toString());
		// call getEmployeeList web API
		XD output = XDS.call(xpcSecurity, "updateEmployee", "update", input);
		System.out.println(output.getString());
		
		boolean isSuccess = false;
		if (output.getString("//affectedRows").equalsIgnoreCase("1")) {
			isSuccess = true;
		}
		JSONObject resp = new JSONObject();
		resp.put("isSuccess", isSuccess);
		WebUtils.sendResponseObject(uh.getResponse(), resp);
	}
	
}
