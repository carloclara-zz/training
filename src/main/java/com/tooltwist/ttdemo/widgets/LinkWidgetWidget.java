package com.tooltwist.ttdemo.widgets;

import java.util.Properties;

import com.dinaa.ui.UimData;

import tooltwist.ecommerce.AutomaticUrlParametersMode;
import tooltwist.ecommerce.RoutingUIM;
import tooltwist.repository.ToolTwist;
import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.CodeInsertionPosition;
import tooltwist.wbd.GenericMustacheWidget;
import tooltwist.wbd.JavascriptCodeInserter;
import tooltwist.wbd.JavascriptLinkInserter;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdNavPointProperty;
import tooltwist.wbd.WbdRadioTextProperty;
import tooltwist.wbd.WbdStringProperty;
import tooltwist.wbd.WbdWidget;

public class LinkWidgetWidget extends GenericMustacheWidget {
	
	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		super.init(instance);
		instance.defineProperty(new WbdStringProperty("label", null, "Label", ""));
		instance.defineProperty(new WbdStringProperty("url", null, "URL", ""));
		instance.defineProperty(new WbdNavPointProperty("navpoint", null, "Navpoint", ""));
		instance.defineProperty(new WbdRadioTextProperty("switcher", null, "Switcher", "url,navpoint", "navpoint"));
		instance.defineProperty(new WbdRadioTextProperty("target", null, "Target", "samePage,newPage", "samePage"));
	}
	
	@Override
	public Properties getPropertiesForViewHelper(WbdGenerator generator, WbdWidget instance, UimData ud) throws WbdException {
		String label = instance.getFinalProperty(generator, "label");
		String url = instance.getFinalProperty(generator, "url");
		String navpoint = instance.getFinalProperty(generator, "navpoint");
		String switcher = instance.getFinalProperty(generator, "switcher");
		String target = instance.getFinalProperty(generator, "target");
		Properties properties = new Properties();
		properties.setProperty("label", label);
		properties.setProperty("url", url);
		properties.setProperty("navpoint", RoutingUIM.navpointUrl(ud, navpoint, AutomaticUrlParametersMode.NO_AUTOMATIC_URL_PARAMETERS));
		properties.setProperty("switcher", switcher);
		properties.setProperty("target", target);
		
		return properties;
	}
	
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException {
		// The normal code inserters are defined here
		super.getCodeInserters(generator, instance, ud, codeInserterList);

		// Other code inserters are defined here
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN) {

			CodeInserter[] arr = {

			};
			codeInserterList.add(arr);

		} else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER) {

			CodeInserter[] arr = {
				new StylesheetCodeInserter(generator, instance, "cssHeader.css"), 
				new JavascriptCodeInserter(generator, instance, "jsHeader.js", CodeInsertionPosition.BOTTOM),
				new JavascriptLinkInserter(ToolTwist.getWebapp())
			};
			codeInserterList.add(arr);
		}
	}

}
