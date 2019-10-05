
package utils;


import com.deque.axe.AXE;
import io.qameta.allure.Step;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/**
 * Created by Chandandeep Singh on 29-09-2019.
 */

public class Utils {

    private static Logger LOGGER = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        LOGGER = Logger.getLogger(Utils.class.getName());
    }

    @Step("{0}")
    public static void logToAllure(String message, String type) {
        if (type.equalsIgnoreCase("violations")) {
            LOGGER.warning(message);
        } else {

            LOGGER.info(message);

        }
    }


    public void logMessage(List<String> list, String type) {
        for (int i = 0; i < list.size(); i++)
        {
            logToAllure(list.get(i), type);
        }

    }


    public static List<String> getValue(WebDriver driver, String value, URL scriptUrl) {
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).analyze();
        JSONArray values = responseJSON.getJSONArray(value);

        List<String> list1 = new ArrayList<String>();
        for (int i = 0; i < values.length(); i++) {
            list1.add("Rule " + value + " " + values.getJSONObject(i).getString("help"));
        }

        return list1;

    }


    public static List<String> bypassRules(WebDriver driver, String value, URL scriptUrl, List<String> rules) {


        String rulesbypass = "{\"rules\": {" +"\"" +rules.get(0) + "\" : { enabled: false }," +"\""+ rules.get(1) + "\": { enabled: false } ," +"\"" +rules.get(2) +"\" : { enabled: false } } }";
        System.out.println(rulesbypass);
        JSONObject responseJSON = new AXE.Builder(driver, scriptUrl).options(rulesbypass).analyze();
        JSONArray values = responseJSON.getJSONArray(value);

        List<String> list1 = new ArrayList<String>();
        for (int i = 0; i < values.length(); i++) {
            list1.add("Rule " + value + " " + values.getJSONObject(i).getString("help"));
        }

        return list1;

    }

}

