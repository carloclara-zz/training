package com.tooltwist.ttdemo.viewHelpers;

import java.util.HashMap;
import java.util.Properties;

import tooltwist.wbd.ViewHelper;

import com.dinaa.ui.UimData;

public class LinkWidgetViewHelper extends ViewHelper {
	private HashMap<String, Object> scopes = new HashMap<String, Object>();

	public LinkWidgetViewHelper(Properties properties) {
		super(properties);
		scopes.put("label", properties.getProperty("label"));		
		String swt = properties.getProperty("switcher");
		if( swt.equals("url") ){
			scopes.put("url", properties.getProperty("url"));
		} else {
			scopes.put("url", properties.getProperty("navpoint"));
		}			
		String tr = properties.getProperty("target");
		if(tr.equals("samePage")){
			scopes.put("target", "");
		} else {
			scopes.put("target", "_blank");
		}
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void callPreFetch(UimData ud) throws Exception {
		// TODO Auto-generated method stub
		super.callPreFetch(ud);
		scopes.put("placeholder", "placeholder");
	}

	public HashMap<String, Object> getScopes() {
		return scopes;
	}

}
