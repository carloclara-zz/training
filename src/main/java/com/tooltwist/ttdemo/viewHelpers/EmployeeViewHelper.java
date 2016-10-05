package com.tooltwist.ttdemo.viewHelpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import com.amazonaws.util.json.JSONException;
import com.amazonaws.util.json.JSONObject;
import com.dinaa.data.XData;
import com.dinaa.ui.UimData;
import com.dinaa.util.DinaaUtilUrls;
import com.dinaa.xpc.XDS;
import com.dinaa.xpc.XpcException;
import com.dinaa.xpc.XpcLogin;
import com.dinaa.xpc.XpcSecurity;
import com.tooltwist.xdata.XD;
import com.tooltwist.xdata.XDException;
import com.tooltwist.xdata.XSelector;

import tooltwist.misc.JspHelper;
import tooltwist.misc.TtConfig;
import tooltwist.wbd.ViewHelper;

public class EmployeeViewHelper extends ViewHelper {
	private HashMap<String, Object> scopes = new HashMap<String, Object>();
	private List<HashMap<String, Object>> employeeList = new ArrayList<HashMap<String, Object>>();

	public EmployeeViewHelper(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public XData preFetch(UimData ud) throws JSONException  {
		if (!(ud instanceof JspHelper)) {
			return null;
		}

		JspHelper uh = (JspHelper) ud;
		HttpServletRequest request = uh.getRequest();

		try {
		XpcSecurity	xpcSecurity = XpcLogin.login(DinaaUtilUrls.USER_TYPE, TtConfig.getInternalLoginName(), TtConfig.getInternalLoginPassword());
		// initiate new JSONObject
		JSONObject inputJson = new JSONObject();
		inputJson.put("id", 10);
		// put JSONObject into XD
		XD input = new XD(inputJson.toString());
		// call getEmployeeList web API
		XD output = XDS.call(xpcSecurity, "getEmployeeList", "get", input);
		// select employee list
		XSelector select = output.select("employeeList");
		for (select.first(); select.next();) {
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			String employeeId = select.getString("//employee_id");
			String name = select.getString("//name");
			String address = select.getString("//address");
			String phoneNumber = select.getString("//phone_number");
			String position = select.getString("//position");
			String department = select.getString("//department");

			hashMap.put("employeeId", employeeId);
			hashMap.put("name", name);
			hashMap.put("address", address);
			hashMap.put("phoneNumber", phoneNumber);
			hashMap.put("position", position);
			hashMap.put("department", department);
			getEmployeeList().add(hashMap);
		}
		
		scopes.put("employeeList", getEmployeeList());
		} catch (XpcException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XDException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public HashMap<String, Object> getScopes() {
		return scopes;
	}

	public List<HashMap<String, Object>> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(List<HashMap<String, Object>> employeeList) {
		this.employeeList = employeeList;
	}

}
