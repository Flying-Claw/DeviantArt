package de.flyingclaw.utils.plugins.deviantart;

import java.util.ArrayList;

import de.flyingclaw.utils.*;

public class DA implements Plugin {

	public ArrayList<String> convertings		= new ArrayList<String>();
	
	public BBCtoHTML_Parser parser = new BBCtoHTML_Parser();
	String name;
	LinkAdder la;
	Converter c;
	PluginManager manager;
	
	@Override public PluginManager getManager() {return manager;}
	@Override public void setManager(PluginManager arg0) {manager = arg0;}
	
	@Override public String getName() {return "DeviantArt";}
	@Override public String getVersion() {return "v1.0";}


	@Override
	public void onEnable() {
		try {
			gm().saveConfig(this);
		} catch (Exception e) {}
		//gC().addDefaults(new String[]{"Toggle_Deviation", "Error"}, new String[]{"Deviation toggled.", "Error!"});
		name = "[" + getName() + " " + getVersion() + "]";
		gmo(name + "Erfolgreich aktiviert!");
		la 	= new LinkAdder(this);
		c	= new Converter(this);
		gm().registerInputListener(la);
		gm().registerCommand(c);
	}
	
	@Override
	public void onDisable() {
		gmo(name + "Erfolgreich de-aktiviert!");
		gm().unregisterInputListener(la);
		gm().unregisterCommand(c);
	}
	
	
	void gmo(Object o){
		getManager().out(o);
	}
	PluginManager gm(){
		return manager;
	}
	public Configuration gC(){
		return getManager().getConfig(this);
	}


}
