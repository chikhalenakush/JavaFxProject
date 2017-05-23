package simple;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CSVDataer {
	void GenerateDocument(String UserAgent, Map<String, String> logincookies, String startdate, String enddate, String path, Logger logger) throws IOException {
		logger.info("Inside CSVDataer class for GenerateDocument method" );
       try {
            Connection.Response doc4 = Jsoup.connect("https://carelink.minimed.eu/patient/main/selectCSV.do?t=11?t=11?t=11?t=11").timeout(60000).ignoreContentType(false)
                    .userAgent(UserAgent)
                    .cookies(logincookies)
                    .header("Content-Type", "text/csv; charset=UTF-8")
                    .header("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .header("Content-length", "101")
                    .data("report", "11")
                    .data("listSeparator", ",")
                    // .data("customerID","50577452") // customer Id can be optional.
                    .data("datePicker2", startdate) // start date
                    .data("datePicker1", enddate) // End date
                    .header("X-Requested-With", "XMLHttpRequest")
                    .method(Connection.Method.GET)
                    .execute();

            
            
            String Path = path + File.separator + "careLink-Export";

            
           
            try {
            	
				PrintWriter pw1 = new PrintWriter(new File(Path + (new Date().getTime()) + ".csv"));
				pw1.write(doc4.body());
				pw1.close();
      System.out.println("Export Sucessfull!");
      Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Message");
		alert.setHeaderText(null);
		alert.setContentText("Export Sucessfull!!!!");
		alert.showAndWait();
		logger.info("Exit CSVDataer class for GenerateDocument method" );
			} catch (Exception e) {
				logger.info("Inside catch for CSVDataer class for GenerateDocument method" );
				// TODO Auto-generated catch block
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setHeaderText(null);
				alert.setContentText("Please check if Path is correct or folder hasn necessary permission to save csv file!!!");
				alert.showAndWait();
			}
            
        } catch (IOException e) {
            System.out.println("There is an issue Downloading File. Please try again after some time!!");
        }
    }

	


}
