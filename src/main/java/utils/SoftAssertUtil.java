package utils;

import org.testng.asserts.SoftAssert;

public class SoftAssertUtil {

    private static SoftAssert softAssertInstance;
    private SoftAssertUtil(){}
    public static SoftAssert getInstance(){
            if(softAssertInstance == null){
                softAssertInstance = new SoftAssert();
            }
            return softAssertInstance;
    }

    public static void assertEquals(Object expected, Object actual){
        getInstance().assertEquals(expected, actual );
        System.out.println("SoftAssertUtil - AssertEquals method.");
    }

    public static void assertAll(){
        getInstance().assertAll("Some of the tests failed, please check the expected and actual results");
    }
}
