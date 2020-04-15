package com.report;

import net.sf.jasperreports.engine.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by bimal on 4/13/20.
 */
public class ReportTemplate {

    public static void main(String[] args) {
        try {
            /* User home directory location */
            String userHomeDirectory = System.getProperty("user.home");
            /* Output file location */
            String outputFile = userHomeDirectory + File.separatorChar + "JasperReportTextFieldExample.pdf";

            /* Map to hold Jasper report Parameters */
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("accountName", "Test Account");
            parameters.put("accountAcronym", "test");
            parameters.put("isPA","Yes");
            parameters.put("isCM","No");
            parameters.put("isHP","yes");
            parameters.put("issueTracking","https://www.colorhexa.com/00ff00");
            parameters.put("issueDate","04/15/2020");
            parameters.put("productAggrement","");
            parameters.put("usDataManager","accountManager32");
            parameters.put("dataAnalyticsLead","dataAnalyticsLead43");
            parameters.put("dataScrubLead","dataScrubLead23");
            parameters.put("dataQCLead","dataQCLead65");
            parameters.put("accountManager","Bimal Tamang");
            parameters.put("accountManager","Bimal Tamang");
            parameters.put("clientServiceManager"," ");
            parameters.put("clientProjectManager","Bimal2 Tamang");
            parameters.put("processingManager","Bimal1 Tamang");

            /* Using compiled version(.jasper) of Jasper report to generate PDF */
            String path = "/templatedesign/reportdesign.jrxml";
            JasperReport report = compileTemplate(path);
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, new JREmptyDataSource());

            /* outputStream to create PDF */

            OutputStream outputStream = new FileOutputStream(new File(outputFile));
            /* Write content to PDF file */
            JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

            System.out.println("File Generated: " + outputFile);
        } catch (JRException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private static JasperReport compileTemplate(String resourcePath) throws JRException {
        InputStream is = JasperReportTemplate2.class.getResourceAsStream(resourcePath);
        return JasperCompileManager.compileReport(is);
    }
}
