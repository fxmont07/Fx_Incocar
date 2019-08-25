package tool;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.Date;
import java.util.GregorianCalendar;

public class Tool {
    private static int MAX_ENGINE_POWER_NOW = 80110;
    public static final Color RED = new Color(238, 123, 112);
    public static final Color MIDNIGHT = new Color(0, 51, 102);
    public static final Color CLAY = new Color (228, 228, 228);

    public static boolean isMatch(String string, String regex) {
        return string.matches(regex);
    }

    public static boolean isAlpha(String string) {
        if (string == null) return true;
        return isMatch(string, "(([a-zA-ZéèàâôêîöïäÈÉÀÔÊÎÏ])+(\\s|\\-)?)*");

    }


    public static boolean isValidCheckNumber(String checkNumber){
        return isMatch(checkNumber,"[0-9]{10}");
    }


    public static boolean isNumTVAValid(String numTVA) {
        return isMatch(numTVA, "([A-Z]{2}|[A-Z]{3})\\d+");
    }

    public static boolean isAlphaNumeric(String string){
        return isMatch(string,"[\\w'éèàâÉÈÀÂ -]+");
    }

    public static boolean isStreetNameValid(String name){
        return isMatch(name,"[\\w \\' éàèêçÉÈÀÊ ]+");}

    public static boolean isNameValid(String name){
        return isMatch(name, "([a-z]|[A-z]|[éèêàç])+(\\s|\\-)?([a-z]|[A-z]|[éèêàç])*");}

    public static boolean isZipCode(String zipCode){
        return isMatch(zipCode, "\\w{4,6}");
    }

    public static boolean isValidEnginePower(Integer enginePower){
        if (enginePower == null) return true;
        return (enginePower >= 0 && enginePower < MAX_ENGINE_POWER_NOW);
    }

    public static boolean isPhoneNumber(String phoneNumber) {
        if(phoneNumber == null ) return true;
        return isMatch(phoneNumber, "[0-9\\/\\s\\+\\.]*");
    }

    public static boolean isDate(String date) {
        if(date == null) return true;
        return isMatch(date, "[0-3][0-9]\\/[01][0-9]\\/[12][0-9][0-9][0-9]");
    }

    public static boolean isPositiveNumber(String string) {
        return (!string.isEmpty() && Integer.parseInt(string) >= 0);
    }

    public static boolean isPositiveDouble(Double number){
        return number > 0;
    }

    public static boolean isPositiveDouble(String number) {
        return (!number.isEmpty() && Double.parseDouble(number) > 0);
    }

    public static boolean isValidChassisNumber(String chassisNumber){
        return isMatch(chassisNumber,"[A-Z0-9]{17}");}

    public static boolean isPositiveNumber(int number) {
        return number >= 0;
    }

    public static boolean isOnlyNumber(String string){
        return isMatch(string, "[0-9]+\\.?[0-9]*");
    }

    /*public static GregorianCalendar stringToGregCalendar(String date){
        if(date.isEmpty()) return null;
        String[] dates = date.split("\\/");
        return new GregorianCalendar(Integer.parseInt(dates[2]),Integer.parseInt(dates[1]),Integer.parseInt(dates[0]));
    }*/

    public static void setDisignColor(JTable table) {
        JTableHeader header = table.getTableHeader();
        table.setBackground(Tool.CLAY);
        header.setBackground(Tool.MIDNIGHT);
        header.setForeground(Color.WHITE);
    }
}


