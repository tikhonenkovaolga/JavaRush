package com.javarush.task.task32.task3209;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.io.*;

public class Controller {

    private View view;
    private HTMLDocument document;
    private File currentFile;

    public Controller(View view) {
        this.view = view;
    }

    public HTMLDocument getDocument() {
        return document;
    }

    public static void main(String[] args) {
        View view = new View();
        Controller controller = new Controller(view);
        view.setController(controller);
        view.init();
        controller.init();
        //view.showAbout();

    }

    public void init() {
        createNewDocument();

    }

    public void exit() {
        System.exit(0);
    }

    public void resetDocument() {
        if (document != null) {
            document.removeUndoableEditListener(view.getUndoListener());
        }
        HTMLEditorKit documentKit = new HTMLEditorKit();
        document = (HTMLDocument) documentKit.createDefaultDocument();
        document.addUndoableEditListener(view.getUndoListener());
        view.update();

    }

    public void setPlainText(String text) {
        resetDocument();
        StringReader stringReader = new StringReader(text);
        HTMLEditorKit documentKit = new HTMLEditorKit();
        try {
            documentKit.read(stringReader, document, 0);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }

    }

    public String getPlainText() {
        String result = "";
        StringWriter stringWriter = new StringWriter();
        HTMLEditorKit documentKit = new HTMLEditorKit();
        try {
            documentKit.write(stringWriter, document, 0, document.getLength());
        } catch (IOException e) {
            ExceptionHandler.log(e);
        } catch (BadLocationException e) {
            ExceptionHandler.log(e);
        }
        result = stringWriter.toString();
        return result;
    }

    public void createNewDocument() {
        view.selectHtmlTab();
        resetDocument();
        view.setTitle("HTML редактор");
        view.resetUndo();
        currentFile = null;
        init();


    }

    public void openDocument() {

        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());

        if (jFileChooser.showOpenDialog(view) == 0) {
            currentFile = jFileChooser.getSelectedFile();
            resetDocument();
            view.setTitle(currentFile.getName());
            try {
                FileReader fileReader = new FileReader(currentFile);
                HTMLEditorKit documentKit = new HTMLEditorKit();
                documentKit.read(fileReader, document, 0);
                view.resetUndo();
                fileReader.close();


            } catch (Exception e) {
                ExceptionHandler.log(e);


            }

        }

    }

    public void saveDocument() {
        view.selectHtmlTab();
        if (currentFile != null) {
            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                HTMLEditorKit documentKit = new HTMLEditorKit();
                documentKit.write(fileWriter, document, 0, document.getLength());
                fileWriter.close();
            } catch (IOException e) {
                ExceptionHandler.log(e);
            } catch (BadLocationException e) {
                ExceptionHandler.log(e);
            }
        } else saveDocumentAs();
    }

    public void saveDocumentAs() {
        view.selectHtmlTab();
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new HTMLFileFilter());
        if (jFileChooser.showSaveDialog(view) == JFileChooser.APPROVE_OPTION) {
            currentFile = jFileChooser.getSelectedFile();
            view.setTitle(currentFile.getName());

            try {
                FileWriter fileWriter = new FileWriter(currentFile);
                HTMLEditorKit documentKit = new HTMLEditorKit();
                documentKit.write(fileWriter, document, 0, document.getLength());
                fileWriter.close();
            } catch (IOException e) {
                ExceptionHandler.log(e);
            } catch (BadLocationException e) {
                ExceptionHandler.log(e);
            }


        }
    }
}
