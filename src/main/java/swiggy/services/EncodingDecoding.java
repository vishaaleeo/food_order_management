package swiggy.services;

import java.util.Random;

public class EncodingDecoding {


    public String encode(Integer identifier) {

        Random random=new Random();
        int firstSetLength=random.nextInt(5)+1;

        int secondSetLength=random.nextInt(5)+1;

        String firstSet=getRandomString(firstSetLength);
        String secondSet=getRandomString(secondSetLength);


        return firstSet+Integer.toHexString(identifier)+secondSet+firstSetLength+secondSetLength;


    }

    public String getRandomString(int length) {

        String randomString="";

        Random random=new Random();
        for(int i=1;i<=length;i++) {
            char c=(char)(random.nextInt(27)+'a');
            randomString+=c;

        }
        return randomString;
    }


    public Integer decode(String encodedString) {
        int length=encodedString.length();

        Integer firstSetLength=Integer.parseInt(String.valueOf(encodedString.charAt(length-2)));

        Integer secondSetLength=Integer.parseInt(String.valueOf(encodedString.charAt(length-1)));

return 1;
    }
}
