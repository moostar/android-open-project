package cn.moostar.lemon.tools;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Apple on 16/7/7.
 */
public class LemonLog {


    private static Logger log = Logger.getLogger("LemonApp");

    public static void info(Object obj,String msg ){
        log.info(msg);
    }

    public static void error(Object obj,String msg) {log.log(Level.WARNING,"===========##########" + msg);}

//    private void configLog(String logFileNamePrefix) {
////        ConsoleHandler consoleHandler =new ConsoleHandler();
////        consoleHandler.setLevel(Level.ALL);
////        log.addHandler(consoleHandler);
////        FileHandler fileHandler = new FileHandler("C:/testlog%g.log");
////        fileHandler.setLevel(Level.INFO);
////        log.addHandler(fileHandler);
////        log.info("aaa");
////        log2.info("bbb");
////        log2.fine("fine");
//    }
}
