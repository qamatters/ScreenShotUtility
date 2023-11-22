package comMain;


import UI.uiHelper;
import util.Helper;

public class mainClass {
    static String tempFilesPath = "Files//Temp//";
    static String results = "Files//ScreenshotReport//";
    static String screenshots = "Files//Screenshots";
    static String reportName = "TestArtifacts_" + Helper.timeStamp();

    public mainClass() {
    }

    public static void main(String[] args) {
        uiHelper.launchUI(screenshots, tempFilesPath, results, reportName);
    }
}
