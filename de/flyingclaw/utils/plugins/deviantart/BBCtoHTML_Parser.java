package de.flyingclaw.utils.plugins.deviantart;

import java.util.HashMap;
import java.util.Map;

public class BBCtoHTML_Parser {
	
	
	Map<String,String> bbMap = new HashMap<String , String>();
	
	public BBCtoHTML_Parser(){
		fillHashmap();
	}
	
	
	
	
	
	
	
	
	
	public String parse(String html){
		for (Map.Entry<String, String> entry: bbMap.entrySet()) {
            html = html.replaceAll(entry.getKey().toString(), entry.getValue().toString());
        }
		return html;
	}
	
	
	
	
	
	
	
	
	
	
	
	public void fillHashmap(){
		bbMap.put("<hr />", "\\[hr\\]");bbMap.put("<hr/>", "\\[hr\\]");
        bbMap.put("<strong>(.+?)</strong>", "\\[b\\]$1\\[/b\\]");
        bbMap.put("<b>(.+?)</b>", "\\[b\\]$1\\[/b\\]");
        bbMap.put("<span style='font-style:italic;'>(.+?)</span>", "\\[i\\]$1\\[/i\\]");
        bbMap.put("<span style='text-decoration:underline;'>(.+?)</span>", "\\[u\\]$1\\[/u\\]");
        bbMap.put("<em>(.+?)</em>", "\\[i\\]$1\\[/i\\]");
        bbMap.put("<h1>(.+?)</h1>", "\\[h1\\]$1\\[/h1\\]");
        bbMap.put("<h2>(.+?)</h2>", "\\[h2\\]$1\\[/h2\\]");
        bbMap.put("<h3>(.+?)</h3>", "\\[h3\\]$1\\[/h3\\]");
        bbMap.put("<h4>(.+?)</h4>", "\\[h4\\]$1\\[/h4\\]");
        bbMap.put("<h5>(.+?)</h5>", "\\[h5\\]$1\\[/h5\\]");
        bbMap.put("<h6>(.+?)</h6>", "\\[h6\\]$1\\[/h6\\]");
        bbMap.put("<blockquote>(.+?)</blockquote>", "\\[quote\\]$1\\[/quote\\]");
        bbMap.put("<em>(.+?)</em>", "\\[i\\]$1\\[/i\\]");
        bbMap.put("<p>(.+?)</p>", "\\[p\\]$1\\[/p\\]");
        bbMap.put("<div align='(.+?)'>(.+?)</div>", "\\[align=$1\\]$2\\[/align\\]");
        bbMap.put("<span style='color:(.+?);'>(.+?)</span>", "\\[color=$1\\]$1\\[/color\\]");
        bbMap.put("<img src='(.+?)' />", "\\[img\\]$1\\[/img\\]");
        bbMap.put("<img width='(.+?)' height='(.+?)' src='(.+?)' />", "\\[img=$1,$2\\]$3\\[/img\\]");
        bbMap.put("<a href='(.+?)'>(.+?)</a>", "\\[url=$1\\]$2\\[/url\\]");
        bbMap.put("<img(.+?)src=\"(.+?)\"(.+?)/>", "\\[img\\]$2\\[/Img\\]");
        bbMap.put("<a(.+?)href=\"(.+?)\">(.+?)</a>", "\\[url=$2\\]$3\\[/url\\]");
        bbMap.put("<i>(.+?)</i>", "\\[i\\]$1\\[/i\\]");
        bbMap.put("<u>(.+?)</u>", "\\[u\\]$1\\[/u\\]");
        bbMap.put("<sub><sub>(.+?)</sub></sub>", "$1");
        bbMap.put("<em>(.+?)</em>", "\\[i\\]$1\\[/i\\]");
        bbMap.put("<em></em>", "");
        bbMap.put("<sub>(.+?)</sub>", "$1");
        bbMap.put("<ul>(.+?)</ul>", "\\[list\\]$1\\[/list\\]");
        bbMap.put("<li>", "\\[*\\]");
        bbMap.put("<strike>(.+?)</strike>", "\\[s\\]$1\\[/s\\]");
        bbMap.put("<small>(.+?)</small>", "\\[size=xx-small\\]$1\\[/size\\]");
        bbMap.put("<span class(.+?)>", "");
        bbMap.put("img=\"(.+?)\"", "\\[img\\]$1\\[/img\\]");
        bbMap.put("<br />", "\r");bbMap.put("<br/>", "\r");
	}

}
