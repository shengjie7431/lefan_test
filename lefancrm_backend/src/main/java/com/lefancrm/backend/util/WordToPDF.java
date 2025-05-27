package com.lefancrm.backend.util;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.*;

public class WordToPDF {

    /** * 获取license * * @return */
    private static boolean getLicense() {
        boolean result = false;
        try {
            // 凭证
            String licenseStr =
                    "<License>\n"
                            + " <Data>\n"
                            + " <Products>\n"
                            + " <Product>Aspose.Total for Java</Product>\n"
                            + " <Product>Aspose.Words for Java</Product>\n"
                            + " </Products>\n"
                            + " <EditionType>Enterprise</EditionType>\n"
                            + " <SubscriptionExpiry>20991231</SubscriptionExpiry>\n"
                            + " <LicenseExpiry>20991231</LicenseExpiry>\n"
                            + " <SerialNumber>8bfe198c-7f0c-4ef8-8ff0-acc3237bf0d7</SerialNumber>\n"
                            + " </Data>\n"
                            + " <Signature>0nRuwNEddXwLfXB7pw66G71MS93gW8mNzJ7vuh3Sf4VAEOBfpxtHLCotymv1PoeukxYe31K441Ivq0Pkvx1yZZG4O1KCv3Omdbs7uqzUB4xXHlOub4VsTODzDJ5MWHqlRCB1HHcGjlyT2sVGiovLt0Grvqw5+QXBuinoBY0suX0=</Signature>\n"
                            + "</License>";
            InputStream license = new ByteArrayInputStream(
                    licenseStr.getBytes("UTF-8"));
            License asposeLic = new License();
            asposeLic.setLicense(license);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Boolean doc2pdf(String inPath,String outPath){
        if (!getLicense()){
            return false;
        }
        FileOutputStream os = null;
        try {
            File file = new File(outPath);
            os = new FileOutputStream(file);
            Document doc = new Document(inPath);
            doc.save(os, SaveFormat.PDF);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

    public static void main(String[] args) {
        doc2pdf("E:\\李贤丰.doc","E:\\李贤丰.pdf");
    }

}
