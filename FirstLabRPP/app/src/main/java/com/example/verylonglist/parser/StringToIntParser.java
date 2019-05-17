package com.example.verylonglist.parser;

public class StringToIntParser {

    public static String[] hundreds = {" ","one hundred " , "two hundred ", "three hundred ", "four hundred ", "five hundred ", "six hundred ", "seven hundred ", "eight hundred ", "nine hundred "};
    public static String[] decades = {" ","ten" , "twenty ", "thirty ", "forty ", "fifty ", "sixty ", "seventy ", "eighty ", "ninety "};
    public static String[] thousands = {" ","one thousand " , "two thousand ", "three thousand ", "four thousand ", "five thousand ", "six thousand ", "seven thousand ", "eight thousand ", "nine thousand "};
    public static String[] tenDecades = {" ","eleven ", "twelve ","thirteen ","fourteen ","fifteen ","sixteen ","seventeen ","eighteen ","nineteen "};
    public static String[] numerals = {" "," one ","two "," three "," four "," five "," six "," seven "," eight ","nine "};

    public static int numlenghth;

    public static String getParsedString(int number){
        numlenghth = Integer.toString(number).length();

        if(number == 1000000) return "One Million";

        int hundth = (number/100000)%10;
        int thDec = (number/10000)%10;
        int thousd = (number/1000)%10;
        int hundr = (number/100)%10;
        int dec = (number/10)%10;
        int num = (number%10);

        if ((dec == 1)&&(num !=0)) return hundreds[hundth] + decades[thDec] + thousands[thousd] + hundreds[hundr] + tenDecades[num];
        else return  hundreds[hundth] + decades[thDec] + thousands[thousd] + hundreds[hundr] + decades[dec] + numerals[num];

    }
}
