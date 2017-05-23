package simple;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author AChikhale
 */
public class EnterDates {

	public static String dateFormat = "dd/MM/yyyy";
	public Date FormatedStartDate;
	public Date FormatedEndDate;

	
	
	
	
	Boolean GetStratDate(LocalDate startdate, Logger logger) throws ParseException {
		Boolean temp = false;
		logger.info("Inside Class EnterDates and funciton GetStartdates");
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		Date Todaydate = new Date();
		String todayDate = sdf.format(Todaydate);
		System.out.println(todayDate);

		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		String StartDate = startdate.format(formatters);

		System.out.println(StartDate);

		Date ValidStardDate = new SimpleDateFormat(dateFormat).parse("01/01/1998"); // Tovalidate
																					// Very
																					// first
																					// start
																					// date

		try {
			FormatedStartDate = new SimpleDateFormat(dateFormat).parse(StartDate);
		} catch (Exception e) {
			System.out.println(
					"Start Date is not in correct format \n Date should be in Format of DD/MM/YYYY  Example: 13/03/2017");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("Start Date is not in correct format Date should be in Format of DD/MM/YYYY  Example: 13/03/2017!!!!");
			alert.showAndWait();
			temp = false;
		}
		try {
			temp = true;
			if (FormatedStartDate.before(ValidStardDate)
					|| FormatedStartDate.after(new SimpleDateFormat(dateFormat).parse(todayDate))) {
				System.out.println("You can only enter Start date between 01/01/1998 and Today's Date!!");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setHeaderText(null);
				alert.setContentText("You can only enter Start date between 01/01/1998 and Today's Date!!!!");
				alert.showAndWait();
				temp = false;
			}
			
		} catch (Exception e) {
			System.out.println("Start Date is not Valid");
			System.out.println("You can only enter Start date between 01/01/1998 and Today's Date!!");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("You can only enter Start date between 01/01/1998 and Today's Date!!!!");
			alert.showAndWait();
			temp = false;

		}
		logger.info("Exit (Class EnterDates) and funciton GetStartdates");
		return temp;
		
	}

	Boolean GetEndDate(LocalDate enddate,LocalDate startdate, Logger logger) throws ParseException {
		
		logger.info("Inside CLass Enter dates and method GetEndDate" );
		Boolean temp = false;

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		Date Todaydate = new Date();
		String todayDate = sdf.format(Todaydate);
		System.out.println(todayDate);

		System.out.println(todayDate);
		
		Date ValidStardDate = new SimpleDateFormat(dateFormat).parse("01/01/1998");
		
		DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String EndDate = enddate.format(formatters);
		String StartDate =  startdate.format(formatters);
		System.out.println(EndDate);

		try {
			FormatedEndDate = new SimpleDateFormat(dateFormat).parse(EndDate);
			FormatedStartDate = new SimpleDateFormat(dateFormat).parse(StartDate);
		} catch (Exception e) {
			System.out.println(
					"End Date is not in correct format \n Date should be in Format of DD/MM/YYYY  Example: 13/03/2017");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("End Date is not in correct format \n Date should be in Format of DD/MM/YYYY  Example: 13/03/2017!!!");

			alert.showAndWait();
			
			temp = false;
		}
		try {
			temp = true;
			if (FormatedEndDate.after(new SimpleDateFormat(dateFormat).parse(todayDate))
					|| FormatedEndDate.before(ValidStardDate)) {
				System.out.println("You can only enter End date between 01/01/1998 and Today's Date!! ");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setHeaderText(null);
				alert.setContentText("You can only enter End date between 01/01/1998 and Today's Date!!!!");
				alert.showAndWait();
//				Controller cc = new Controller();
//				cc.enddate.setValue(null);
				//Controller.this.enddate.setValue(null);
				System.out.println("Local date is" + LocalDate.now());
				
				System.out.println("Local date for end date is " + enddate);
				temp = false;
			}
			if (FormatedEndDate.before(FormatedStartDate)) {
				System.out.println("End Date cannot be earlier than Start date");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setHeaderText(null);
				alert.setContentText("End Date cannot be earlier than Start date!!!!");

				alert.showAndWait();
				temp = false;
			}
			
		}

		catch (Exception e) {
			System.out.println("End Date is not Valid");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("End Date is not Valid!!!!");

			alert.showAndWait();
			temp = false;
		}
		logger.info("Exit (CLass EnterDates) and method GetEndDate" );
		return temp;
	}

}
