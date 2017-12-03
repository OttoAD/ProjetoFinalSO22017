package projetofinal.so;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogBuffer {
	
	private static Logger logger = null;
	
	private LogBuffer () {}
	
	public static Logger getLogger() {
		if (logger == null) {
			System.setProperty("java.util.logging.SimpleFormatter.format", "[%4$-7s] %5$s %n");
			logger = Logger.getLogger("DebugLog");
			logger.setLevel(Level.ALL);
		}
		return logger;
	}

}
