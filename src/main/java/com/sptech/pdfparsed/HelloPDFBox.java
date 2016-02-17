package com.sptech.pdfparsed;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDCheckbox;
import org.apache.pdfbox.pdmodel.interactive.form.PDChoiceField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextbox;

import java.io.File;
import java.util.List;

public class HelloPDFBox {
    public static void listFields(PDDocument doc) throws Exception {

        PDDocumentCatalog catalog = doc.getDocumentCatalog();
        PDMetadata pdMetadata = catalog.getMetadata();
        PDAcroForm form = catalog.getAcroForm();
        List fields = form.getFields();

        System.out.println("Fields " + fields.get(0).getClass());

        for (Object field : fields) {

            if (field instanceof PDTextbox) {
                PDTextbox pdTextbox = (PDTextbox) field;
                System.out.println("PDTextBox " + pdTextbox.getFullyQualifiedName() + " " + pdTextbox.getValue());
            } else if (field instanceof PDChoiceField) {
                PDChoiceField pdChoiceField = (PDChoiceField) field;
                System.out.println("PDChoice Field " + pdChoiceField.getFullyQualifiedName() + " " + pdChoiceField.getValue());
            } else if (field instanceof PDCheckbox) {
                PDCheckbox pdCheckbox = (PDCheckbox) field;
                System.out.println("PDCheckbox Field " + pdCheckbox.getFullyQualifiedName() + " " + pdCheckbox.getValue());
            } else {
                System.out.print(field);
                System.out.print(" = ");
                System.out.print(field.getClass());
                System.out.println();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Please specify fully qualified path of the PDF form");
            System.exit(-1);
        }
        String fileName = args[0];
        System.out.println("Parsing PDF Form at " + fileName);
        File file = new File(fileName);
        PDDocument doc = PDDocument.load(file);
        listFields(doc);
    }

}
