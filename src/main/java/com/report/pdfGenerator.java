package com.report;

/**
 * Created by bimal on 4/9/20.
 */
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.PenBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.StretchType;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.jasperreports.engine.JRDataSource;

import java.awt.*;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
public class pdfGenerator {
    private void build(){
        try{

            StyleBuilder boldStyle = stl.style().bold();
            StyleBuilder boldCenteredStyle = stl.style(boldStyle).
                    setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
            StyleBuilder columnTitleStyle = stl.style(boldCenteredStyle).
                    setBorder(stl.penThin()).setBackgroundColor(Color.getHSBColor(00,44,55));

            TextColumnBuilder<String> itemColumn = col.column("Item","item",type.stringType())
                    .setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                    .setTitleFixedHeight(15);
            TextColumnBuilder<Integer> quantityColumn = col.column("Quantity","quantity",type.integerType())
                    .setFixedWidth(50).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER)
                    .setTitleFixedHeight(15);

            report()
                    .setColumnTitleStyle(columnTitleStyle)
                    .columns(itemColumn,quantityColumn)
                    .title(cmp.rectangle().setHeight(30).setWidth(40).setStretchType(StretchType.NO_STRETCH),cmp.text("Getting Started").setStyle(boldCenteredStyle))
                    .setDataSource(createDataSource())
                    .show();

        }catch(Exception exception){
            exception.printStackTrace();
        }
    }

    private JRDataSource createDataSource(){
        DRDataSource dataSource = new DRDataSource("item","quantity");
        dataSource.add("DVD", 1);
        dataSource.add("DVD1", 2);
        return dataSource;
    }

    public static void main(String[] args){
        new pdfGenerator().build();
    }
}
