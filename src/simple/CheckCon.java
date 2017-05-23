package simple;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;


public class CheckCon {

	public Boolean Connection(String Username, String passwordsecure, String userAgent, Map<String, String> loginCookies, Logger logger) throws IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				logger.info("Inside (class CheckCon) CheckConnection to check if connection is sucessfull or not");
				Boolean temp = false;
				
				
				Response loginForm =  Jsoup.connect("https://carelink.minimed.eu/patient/j_security_check")
						.referrer("https://carelink.minimed.eu/").userAgent(userAgent).timeout(10 * 1000).followRedirects(true)
						.ignoreHttpErrors(true).execute();

				try {

					org.jsoup.Connection.Response res = Jsoup.connect("https://carelink.minimed.eu/patient/j_security_check")
							.data("j_username", Username).data("j_password", passwordsecure).method(Connection.Method.POST)
							.execute();
					loginCookies = res.cookies();

					temp = true;
				} catch (Exception e) {
					
					temp = false;

				}
				logger.info("Exit (class CheckCon) CheckConnection to check if connection is sucessfull or not");
				return temp;
		
	}

}
