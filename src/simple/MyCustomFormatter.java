package simple;

import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyCustomFormatter extends Formatter {

	@Override
	public String format(LogRecord record) {
		// TODO Auto-generated method stub
	        StringBuffer sb = new StringBuffer();
		 
		             sb.append("\n");
		 
		             sb.append(record.getMessage());
		 
		             sb.append("\n");
		 
		             return sb.toString();
		 
		         }

}
