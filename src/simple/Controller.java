package simple;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import java.awt.AWTException;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.swing.JOptionPane;
import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.openqa.selenium.Cookie;
import org.jsoup.Jsoup;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;


public class Controller {
	
	// Declaring public variables for Login details 
		public String LoginPassword;
		public String Loginname;
		public Boolean Loginresult = false;
	
	// Declaring public variables for Checking dates and it's format
	Boolean EndDateResult = false;
	Boolean StartDateResult = false;
	
	
	// Declaring public variables for logging file
	public Logger logger = Logger.getLogger("MyLog");
	public FileHandler fh;
	public static SimpleDateFormat formats = new SimpleDateFormat("dd-mm-HHMMSS");
	
	public static int counterforresetcheck = 0;
	   
	   
	// Declaring public varibales for logging into system using Jsoup and hence user Ageent

	public String UserAgent = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";

	// Declaring public variables to store login cookies
	public Map<String, String> loginCookies = new HashMap<String, String>();
	
	
	// Frame buttons, panels and textfields
	
	//Declaring AnchorPanel
	
	@FXML
	public AnchorPane DatePanel;

	@FXML
	private AnchorPane DownloadPane;

	@FXML
	public AnchorPane uploadsection;

	@FXML
	private AnchorPane AppletRun;

	
	//Declaring Button
	
	@FXML
	public Button Login;

	@FXML
	public Button download;

	@FXML
	public Button Sync;

	@FXML
	public Button Broswe;

	@FXML
	public Button reset;
	
	@FXML
	private Button BrosweIEDriverServer;
	
	//Declaring Checkbox
	
	@FXML
	public CheckBox AutoAck;
	
	@FXML
	private CheckBox IEDriverCheckBoxNo;

	@FXML
	private CheckBox IEDriverCheckBoxYes;
	
	//Declaring DatePicker
	
	@FXML
	public DatePicker startdate;

	@FXML
	public DatePicker enddate;
	
	//Declaring PasswordField
	
	@FXML
	public PasswordField passwordsecure;
	
	//Declaring RadioButton
	
	@FXML
	public RadioButton BG;
	
	@FXML
	public RadioButton Stick;
	

	//Declaring TextField
	@FXML
	public TextField Password;

	@FXML
	public TextField Username;

	@FXML
	public TextField CsvPath;

	@FXML
	public TextField SN;

	@FXML
	private TextField IEDriverServer;

	//Declaring TextFlow
	
	@FXML
	private TextFlow textflowcheck;
	
	
	//Calling very first method when Frame applicaiton is loaded
	

	@FXML
	public void initialize() throws SecurityException, IOException {

		String userHome = System.getProperty("user.home");
		System.out.println("Log is saved at location" + userHome);
		fh = new FileHandler(userHome + "/CrawlerProject_" + formats.format(Calendar.getInstance().getTime()) + ".log");

		// fh = new FileHandler("%h/" + "CrawlerProject_" /*+
		// formats.format(Calendar.getInstance().getTime())*/ + ".log");

		logger.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();
		// fh.setFormatter(formatter);
		fh.setFormatter(new MyCustomFormatter());
		logger.setUseParentHandlers(false);
		logger.info("JavaFX Application started");
		logger.info("Log is saved at location " +System.getProperty("user.home") + "under name CrawlerProject");
	}
	
	
	// Method is called when user enter press Enter key for Password To check if user credentials are correct or not
	
	@FXML
	void getpasswordsecure(KeyEvent event) throws IOException {
		logger.info("Inside code for Password Text box Enter key presses");
		
		try{
		if (event.getCode() == KeyCode.ENTER) {
			
			if (passwordsecure.getText() != null && Username.getText() != null) {
				logger.info("Password and Username entered");
				
				CheckCon con = new CheckCon();
				
				//Loginresult =	con.Connection(Username.getText(),passwordsecure.getText(),UserAgent,loginCookies,logger);
				Loginresult = CheckConnection();
				if (Loginresult) {
					
					// System.out.println(Login.isDisable()); //
					// setDisable(true);
					passwordsecure.setText(null);
					Username.setText(null);
					passwordsecure.setDisable(true);
					Username.setDisable(true);
					startdate.setDisable(false);
					//enddate.setDisable(false);
					uploadsection.setVisible(true);

					TextFlow flow = new TextFlow(new Text(""), createAccount());

					textflowcheck.getChildren().add(flow);
					logger.info("Sucessfully logged in..");

				} else {
					

					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setHeaderText(null);
					alert.setContentText("Wrong User name or password!!!");
					logger.info("Wrong User name or password");
					alert.showAndWait();

				}
			}
		}
		logger.info("Sucessfully passed code for Password Text box Enter key presses");
		}
		catch (Exception e)
		{
			logger.info("Inside catch for code for Password Text box Enter key presses, There is an issue here. Please check");
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Message");
			alert.setHeaderText(null);
			alert.setContentText("Wrong User name or password!!!");
			logger.info("Wrong User name or password");
			alert.showAndWait();
		}

	}
	
	
	// Method is called when user login button is pressed To check if user credentials are correct or not
		@FXML
		public void login() throws IOException {
			logger.info("Inside Login Button Metod");
			
			if (passwordsecure.getText() != null && Username.getText() != null) {
				CheckCon con = new CheckCon();
				//Loginresult =	con.Connection(Username.getText(),passwordsecure.getText(),UserAgent,loginCookies,logger);
				Loginresult = CheckConnection();
				if (Loginresult) {
					logger.info("Sucessfully logged in..");
					
					// System.out.println(Login.isDisable()); // setDisable(true);
					passwordsecure.setText(null);
					Username.setText(null);
					passwordsecure.setDisable(true);
					Username.setDisable(true);
					startdate.setDisable(false);
					//enddate.setDisable(false);
					uploadsection.setVisible(true);
					
					
					logger.info("Before setting TextFlow link (Inside Login method) for selinium download");
					TextFlow flow = new TextFlow(new Text(""), createAccount());// new
																				// Hyperlink("Click
																				// here"));

					textflowcheck.getChildren().add(flow);
					logger.info("After setting TextFlow link (Inside Login method) for selinium download");
					
				} else {
					
					logger.info("Wrong User name or password");
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Message");
					alert.setHeaderText(null);
					alert.setContentText("Wrong User name or password!!!");

					alert.showAndWait();

				}
			}
			
		}
		
		
		//// Login sucessfull or not with password textfield Enter key pressed
		//// function
		@FXML
		public void handleEnterPressed(KeyEvent event) throws IOException {
			logger.info("Inside handleEnterPressed for Key Enter");
			if (event.getCode() == KeyCode.ENTER) {
				if (passwordsecure.getText() != null && Username.getText() != null) {
					CheckCon con = new CheckCon();
					//Loginresult =	con.Connection(Username.getText(),passwordsecure.getText(),UserAgent,loginCookies,logger);
					Loginresult = CheckConnection();
					if (Loginresult) {
						logger.info("Sucessfully logged in..");
						
						// System.out.println(Login.isDisable()); //
						// setDisable(true);
						passwordsecure.setText(null);
						Username.setText(null);
						passwordsecure.setDisable(true);
						Username.setDisable(true);
						startdate.setDisable(false);
						//enddate.setDisable(false);
						uploadsection.setVisible(true);

						TextFlow flow = new TextFlow(new Text(""), createAccount());// new
																					// Hyperlink("Click
																					// here"));
						textflowcheck.getChildren().add(flow);

					} else {
						
						logger.info("Wrong User name or password");
						Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Message");
						alert.setHeaderText(null);
						alert.setContentText("Wrong User name or password!!!");

						alert.showAndWait();

					}
				}
			}
			logger.info("Exit handleEnterPressed for Key Enter");
		}
	
		
		// Reset logged in user and start again
		@FXML
		void getreset(ActionEvent event) {
			logger.info("Inside reset button method");
			loginCookies = null;
			passwordsecure.setText(null);
			Username.setText(null);

			passwordsecure.setDisable(false);
			Username.setDisable(false);
			startdate.setValue(null);
			enddate.setValue(null);
			startdate.setDisable(true);
			enddate.setDisable(true);
			uploadsection.setVisible(false);
			CsvPath.setText(null);
			CsvPath.setDisable(true);
			Broswe.setDisable(true);
			download.setDisable(true);
			textflowcheck.getChildren().clear();
			counterforresetcheck = 0;
			logger.info("Exit reset button method");
			
			}
	
		
		// Startdate validation
		@FXML
		void getstartdate(ActionEvent event) throws ParseException {
			logger.info("Indise Start method to check validation of Start date");
			enddate.setDisable(true);
			StartDateResult = false;
			logger.info("Inside getstartdate");
			System.out.println(startdate.getValue());
			EnterDates dates = new EnterDates();
			if (startdate.getValue() != null) {
				logger.info("Start date is not null");
				StartDateResult = dates.GetStratDate(startdate.getValue(),logger);
			}



			if (startdate.getValue() != null && enddate.getValue() != null) {

				EndDateResult = dates.GetEndDate(enddate.getValue(), startdate.getValue(),logger);
			}

			if (StartDateResult && EndDateResult) {
				CsvPath.setDisable(false);
				// Browse.setVisible(true);
				// download.setVisible(true);
				Broswe.setDisable(false);
				download.setDisable(false);
			} else {
				CsvPath.setDisable(true);
				Broswe.setDisable(true);
				download.setDisable(true);
				enddate.setDisable(false);
			}
			if(!StartDateResult){
				
				startdate.setValue(null);
				enddate.setValue(null);
				CsvPath.setText(null);		
				enddate.setDisable(true);
			}
			else
			{
				
				enddate.setDisable(false);
			}
			logger.info("Exisiting getstartdate");

		}
		
		// end date Validation
		@FXML
		void getenddate(ActionEvent event) throws ParseException {
			logger.info("Inside getenddate method");
			EndDateResult = false;
			if (startdate.getValue() != null && enddate.getValue() != null) {
				EnterDates dates = new EnterDates();

				EndDateResult = dates.GetEndDate(enddate.getValue(), startdate.getValue(),logger);
				if (StartDateResult && EndDateResult) {
					CsvPath.setDisable(false);
					Broswe.setDisable(false);
					download.setDisable(false);
				} else {
					CsvPath.setDisable(true);
					Broswe.setDisable(true);
					download.setDisable(true);
				}

			}
			if(!EndDateResult){
				enddate.setValue(null);
				CsvPath.setText(null);
				CsvPath.setDisable(true);
				Broswe.setDisable(true);
				download.setDisable(true);
			}
			logger.info("Exit getenddate method");

		}
		
	
		// Location to download CSV File
		@FXML

		void getBrowse(ActionEvent event) {
			logger.info("Inside getBrowse for CSV");

			DirectoryChooser directoryChooser = new DirectoryChooser();

			File select = directoryChooser.showDialog(null);

			if (select == null) {
				CsvPath.setText("");
			} else {
				CsvPath.setText(select.getAbsolutePath());
			}
			logger.info("Exit getBrowse for CSV");
		}
		
		
		// Download functionality clicking download button
		@FXML
		public void getdownloadfile() throws IOException {
			logger.info("Inside CSV Download Method for button Download");
			if (StartDateResult && EndDateResult) {
			if ((Loginresult) && (!(CsvPath.getText().trim().equals("")))) {
				CSVDataer downl = new CSVDataer();
				DateTimeFormatter formatters = DateTimeFormatter.ofPattern("dd/MM/yyyy");
				String EndDate = enddate.getValue().format(formatters);
				String StartDate = startdate.getValue().format(formatters);
				// downl.GetnewDocument(UserAgent, loginCookies, StartDate, EndDate,
				// CsvPath.getText());
				downl.GenerateDocument(UserAgent, loginCookies, StartDate, EndDate, CsvPath.getText(),logger);
				// downl.GenerateDocument(UserAgent, loginCookies, StartDate,
				// EndDate, CsvPath.getText());
				System.out.println("Inside Login" + Loginresult);
				System.out.println("CsvPath " + CsvPath.getText());
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setHeaderText(null);
				alert.setContentText("Please enter Path!!!");
				alert.showAndWait();
			}
			}
			else
			{
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Message");
				alert.setHeaderText(null);
				alert.setContentText("Please Select Correct Dates!!!");
				alert.showAndWait();
			}

			
			
			logger.info("Exit CSV Download Method for button Download");
		}
		
	@FXML
	void handleIEDriverServerTextFiled(ActionEvent event) throws IOException {
		logger.info("Inside handleIEDriverServerTextFiled for loading selinium IE driver");
		 
		if (IEDriverServer.getText() != null) {
			AppletRun.setDisable(false);
		} else {
			AppletRun.setDisable(true);
		}
		logger.info("Exit handleIEDriverServerTextFiled for loading selinium IE driver");
	}

	@FXML
	void handleAutoAck(ActionEvent event) throws IOException {
		
		logger.info("Inside Autocheck Method");		 
		 
		if (AutoAck.isSelected()) {
			logger.info("Autocheck is checked");
			SN.setText(null);
			BG.setSelected(false);
			Stick.setSelected(false);
			SN.setDisable(true);
			BG.setDisable(true);
			Stick.setDisable(true);
			Sync.setDisable(false);
		} else {
			logger.info("Autocheck is not checked");
			SN.setDisable(false);
			BG.setDisable(false);
			Stick.setDisable(false);
			if (BG.selectedProperty().getValue() == false && Stick.selectedProperty().getValue() == false) {
				Sync.setDisable(true);
			} else {
				Sync.setDisable(false);
			}
		}
		logger.info("Exist Autocheck Method");
	}

	@FXML
	void getBrosweIEDriverServer(ActionEvent event) throws IOException {
		
		logger.info("Inside getBrosweIEDriverServer Method for button Browse");
	
		FileChooser fileChooser = new FileChooser();

		File selectedFile = fileChooser.showOpenDialog(null);
		if (selectedFile != null) {

			IEDriverServer.setText(selectedFile.getAbsolutePath());

		} else {
			IEDriverServer.setText("");

		}
		
		logger.info("Exist getBrosweIEDriverServer Method for button Browse");
	}

	@FXML
	void uploaddocument(ActionEvent event) throws IOException, AWTException, InterruptedException {
	
		logger.info("Inside Sync button clicked method");
		if (IEDriverServer.getText().length() == 0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alert");
			alert.setHeaderText(null);
			alert.setContentText("Please add IEDriver location");
			alert.showAndWait();
			return;
		}

		logger.info("(Inside Sync button clicked method) before SimulateMouse initilized ");
		SimulateMouse sm = new SimulateMouse();

		// System.out.println("I have pressed Sync");
		if (AutoAck.isSelected()) {
			logger.info("AutoAck is checked");
			
			sm.startmagic(IEDriverServer.getText(), "NG0009612X", /*BG.selectedProperty().getValue() */true,/*Stick.selectedProperty().getValue()*/ false, Loginname, LoginPassword,
					AutoAck.isSelected(),logger,counterforresetcheck);

		} else {
			logger.info("AutoAck is unchecked");
			System.out.println(SN.getText());
			if (SN.getText().length() < 10 || SN.getText().length() > 10 || SN.getText().length() == 0) {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert");
				alert.setHeaderText(null);
				alert.setContentText("Sn number must be of 10 characters only");
				alert.showAndWait();
				return;

			}
			if (((BG.isSelected()) || (Stick.isSelected()))) {
			} else {
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Alert");
				alert.setHeaderText(null);
				alert.setContentText("Please Select BG or Stick");
				alert.showAndWait();
				return;
			}

		 sm.startmagic(IEDriverServer.getText(), SN.getText(), BG.selectedProperty().getValue(),
					Stick.selectedProperty().getValue(), Loginname, LoginPassword, AutoAck.isSelected(),logger,counterforresetcheck);
		 logger.info("Exit Sync button clicked method");
		}
	}
	
	@FXML
	void handleBG(ActionEvent event) {
		logger.info("Inside BG Radio button Method");
		if (BG.selectedProperty().getValue()) {
			logger.info("BG Radio button selected");
			Stick.selectedProperty().setValue(false);
			Sync.setDisable(false);
		} else {
			logger.info("BG Radio button unselected");
			Sync.setDisable(true);
		}
		logger.info("Exit handleBG");
	}
	
	
	@FXML
	void handleStick(ActionEvent event) {
		logger.info("Inside Stick Radio button Method");
		if (Stick.selectedProperty().getValue()) {
			logger.info("Stick Radio button selected");
			BG.selectedProperty().setValue(false);
			Sync.setDisable(false);
		} else {
			logger.info("Stick Radio button unselected");
			Sync.setDisable(true);
		}
		logger.info("Exit Stick Radio button Method");
	}


	private Node createAccount() {
		
		logger.info("Inside Text link for Selinium download method");
		Hyperlink createAccount = new Hyperlink("http://www.seleniumhq.org/download/");

		createAccount.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent t) {
				try {
					Desktop.getDesktop().browse(new URI("http://www.seleniumhq.org/download/"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Problem with Link");
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Problem with Link");
					e.printStackTrace();
				}
			}
		});
		logger.info("Exisiting Text link for Selinium download method");

		return createAccount;

	}


	// Function just to connect as a specific user and handle cookies
	private Boolean CheckConnection() throws IOException {
		// TODO Auto-generated method stub
		logger.info("Inside CheckConnection to check if connection is sucessfull or not");
		Boolean temp = false;
		
		Loginname = Username.getText();

		LoginPassword = passwordsecure.getText();

		Response loginForm = Jsoup.connect("https://carelink.minimed.eu/patient/j_security_check")
				.referrer("https://carelink.minimed.eu/").userAgent(UserAgent).timeout(10 * 1000).followRedirects(true)
				.ignoreHttpErrors(true).execute();

		try {

			Connection.Response res = Jsoup.connect("https://carelink.minimed.eu/patient/j_security_check")
					.data("j_username", Loginname).data("j_password", LoginPassword).method(Connection.Method.POST)
					.execute();
			loginCookies = res.cookies();
			

			temp = true;
		} catch (Exception e) {
			
			temp = false;

		}
		logger.info("Exit CheckConnection to check if connection is sucessfull or not");
		return temp;
	}

	}
