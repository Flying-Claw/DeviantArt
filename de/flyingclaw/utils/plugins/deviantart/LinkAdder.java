package de.flyingclaw.utils.plugins.deviantart;

import de.flyingclaw.utils.*;

public class LinkAdder implements InputListener {

	@Override
	public void listenToInput(String in) {
		if(in.startsWith("http") && in.contains("deviantart")){
			plugin.convertings.add(in);
			plugin.gmo(plugin.name + "Link hinzugefügt.");
		}
	}
	
	DA plugin;
	public LinkAdder(DA p){
		this.plugin = p;
	}

}
