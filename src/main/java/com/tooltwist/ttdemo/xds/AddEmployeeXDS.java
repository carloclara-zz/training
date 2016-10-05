package com.tooltwist.ttdemo.xds;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.dinaa.xpc.XDService;
import com.dinaa.xpc.XpcSecurity;
import com.tooltwist.xdata.XD;
import com.tooltwist.xdata.XDException;

public class AddEmployeeXDS implements XDService {

	@Override
	public void init(XD config) {
	}

	@Override
	public XD service(XpcSecurity credentials, XD input) throws XDException {

		try {

			String url = "http://localhost:4000/insertEmployee";
			String json = Request.Post(url)
					.addHeader("accept", "application/json")
					.connectTimeout(35000)
					.socketTimeout(35000)
					.bodyString(input.getString(), ContentType.APPLICATION_JSON)
					.execute()
					.returnContent()
					.asString();

			XD xd = new XD(json);
			xd.getSelector();
			return xd;

		} catch (UnsupportedEncodingException e) {
			throw new XDException(e);
		} catch (IOException e) {
			throw new XDException(e);
		}
	}
}
