package util;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import pdfHelper.PDFUtil;

public class Helper {


    public static void deleteTempFiles(String path) throws IOException {
        FileUtils.cleanDirectory(new File(path));
    }

    public static String timeStamp() {
        Date now = new Date();
        String Timestamp = now.toString().replace(":", "-").replace(" ", "_");
        return Timestamp;
    }

    public static String getScreenshot(String directory) throws AWTException, IOException, InterruptedException {
        String fileName = System.currentTimeMillis() + ".jpeg";
        String filePath = directory + "//" + fileName;
        Thread.sleep(2000L);
        Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        Robot robot = new Robot();
        BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
        ImageIO.write(bufferedImage, "jpeg", new File(filePath));
        return filePath;
    }

    public static void generatePDFReport(String screenshotsPath, String tempFilesPath, String results, String reportName) throws IOException {
        File screenShotFiles = new File(screenshotsPath);
        File[] listOfScreenshots = screenShotFiles.listFiles();
        if (listOfScreenshots.length == 0) {
            System.out.println("There is no screenshot file present inside screenshots folder");
        }

        assert listOfScreenshots != null;

        File[] var6 = listOfScreenshots;
        int var7 = listOfScreenshots.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            File f = var6[var8];
            PDFUtil.createPDFFromSingleFile(f.getPath(), f.getName().replace(".jpeg", ".pdf"), tempFilesPath);
        }

        PDFUtil.mergePdf(tempFilesPath, results, reportName);
    }
}
