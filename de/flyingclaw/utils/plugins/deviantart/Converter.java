package de.flyingclaw.utils.plugins.deviantart;

import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.util.ArrayList;

import de.flyingclaw.utils.Command;

public class Converter implements Command {

	DA pl;
	public Converter(DA p){pl=p;}
	
	@Override
	public void onCommand(String cmd, String[] args) {
		if(cmd.equalsIgnoreCase("da")){
			if(args.length == 1 || args.length >= 1){
				boolean direct 	= false;
				boolean cb		= true;
				for(String a : args){
					if(a.startsWith("cb:")){
						cb = Boolean.valueOf(a.split("cb:")[1]);
					}
					if(a.startsWith("d:")){
						direct = Boolean.valueOf(a.split("d:")[1]);
					}
				}
				if(args[0].equalsIgnoreCase("convert")){
					convert(direct, cb);
				}else{
					pl.gmo(pl.name + "Dieser Befehl existiert nicht.");
				}
			}
		}
	}
	
	public void convert(boolean direct, boolean cb){
		pl.gmo("Links werden konvertiert..");
		ArrayList<String> l = new ArrayList<String>(pl.convertings);
		ArrayList<String> cc = new ArrayList<String>();
		for(String s : l){
			pl.gmo("Links werden konvertiert(%link)..".replaceAll("%link", s));
			ImageUtil iu = null;
			try {
				iu = new ImageUtil(s, pl.parser);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try{					
				String form = MSG.format;
				cc.add(iu.format(form));
				pl.convertings.remove(s);
				
			}catch(Exception e){
				pl.gmo("Fehler!");
			}
		}
		pl.gmo("\n------------------------------------\nKonvertierung erfolgreich!\n------------------------------------");
		String c2b = "";
		for(String s : cc){
			pl.gmo(s);
			c2b = c2b + "\n" + s;
		}
		if(cb){
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(c2b), null);
		}
	}

}
