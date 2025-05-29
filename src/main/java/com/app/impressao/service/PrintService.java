package com.app.impressao.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPrintable;
import org.springframework.stereotype.Service;

import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterJob;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
public class PrintService {

    public void print(byte[] content) {
        try {
            Printable printable = this.getPrintableFile(content);

            PrinterJob pj = PrinterJob.getPrinterJob();

            pj.setPrintable(printable, getPageFormat(pj));

            pj.print();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Printable getPrintableFile(byte[] content) throws IOException {
        ByteArrayInputStream byteArrayContent = new ByteArrayInputStream(content);

        PDDocument document = PDDocument.load(byteArrayContent);

        return new PDFPrintable(document);
    }

    private PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();

        Paper paper = pf.getPaper();

        paper.setImageableArea(0, 0, pf.getWidth(), pf.getHeight());

        pf.setPaper(paper);

        pf = pj.validatePage(pf);

        return pf;
    }
}
