package wt;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WebGetter {
	public static String getPatName(int id) throws IOException{
		Connection con = Jsoup.connect("http://www.google.ca/patents/US" + id).userAgent("Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/535.21 (KHTML, like Gecko) Chrome/19.0.1042.0 Safari/535.21").timeout(10000);
		Connection.Response resp = con.execute();
		Document doc = con.get();
		return doc.title().substring(0, doc.title().length()-17);
	}
}
