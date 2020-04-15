package com.report;

/**
 * Created by bimal on 4/13/20.
 */

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import java.io.InputStream;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;

import com.report.Templates;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;

import static net.sf.dynamicreports.report.builder.DynamicReports.cmp;
import static net.sf.dynamicreports.report.builder.DynamicReports.col;
import static net.sf.dynamicreports.report.builder.DynamicReports.report;
import static net.sf.dynamicreports.report.builder.DynamicReports.stl;
import static net.sf.dynamicreports.report.builder.DynamicReports.type;

/**
 * <p>JasperReportTemplate2 class.</p>
 *
 * @author Ricardo Mariaca
 *
 */
public class JasperReportTemplate2 {

    /**
     * <p>Constructor for JasperReportTemplate2.</p>
     */
    public JasperReportTemplate2() {
        build();
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(String[] args) {
        new JasperReportTemplate2();
    }

    private void build() {

        InputStream is = JasperReportTemplate2.class.getResourceAsStream("/templatedesign/templatedesign2.jrxml");
        System.out.println(is);
        try {
            report().setTemplate(Templates.reportTemplate)
                    .setTemplateDesign(is)
                    .columns(col.column("Item", "item", type.stringType()), col.column("Quantity", "quantity", type.integerType()), col.column("Unit price", "unitprice", type.integerType()))
                    .title(Templates.createTitleComponent("JasperTemplateDesign2"), cmp.subreport(createDynamicSubreport()), cmp.subreport(createStaticSubreport()),
                            cmp.subreport(createStaticAndDynamicSubreport()))
                    .setDataSource(createDataSource())
                    .show();
        } catch (DRException | JRException e) {
            e.printStackTrace();
        }
    }

    private JasperReportBuilder createDynamicSubreport() {
        return createSubreport("Subreport - dynamic design");
    }

    private JasperReportBuilder createStaticAndDynamicSubreport() throws DRException {
        InputStream is = JasperReportTemplate2.class.getResourceAsStream("/templatedesign/subreporttemplatedesign.jrxml");
        JasperReportBuilder report = createSubreport("Subreport - static and dynamic design");
        report.setTemplateDesign(is);
        return report;
    }

    private JasperReportBuilder createSubreport(String title) {
        StyleBuilder style = stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.CENTER).setVerticalTextAlignment(VerticalTextAlignment.MIDDLE).setBorder(stl.pen1Point());

        JasperReportBuilder report = report();
        report.setTemplate(Templates.reportTemplate).title(cmp.horizontalList(cmp.gap(30, 47), cmp.text(title).setStyle(style), cmp.gap(30, 47)));

        return report;
    }

    private JasperReport createStaticSubreport() throws JRException {
        InputStream is = JasperReportTemplate2.class.getResourceAsStream("/templatedesign/subreport.jrxml");
        return JasperCompileManager.compileReport(is);
    }

    private JRDataSource createDataSource() {
        DRDataSource dataSource = new DRDataSource("item", "quantity", "unitprice");
        //IntStream.range(0, 5).forEach(i -> dataSource.add("Book", (int) (Math.random() * 10) + 1, (int) (Math.random() * 100) + 1));
        for(int i=0;i<5;i++){
            dataSource.add("Book", (int) (Math.random()*10)+1, (int)(Math.random()*100)+1);
        }
        return dataSource;
    }
}
