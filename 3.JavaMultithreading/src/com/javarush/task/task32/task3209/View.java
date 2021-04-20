package com.javarush.task.task32.task3209;

import com.javarush.task.task32.task3209.listeners.FrameListener;
import com.javarush.task.task32.task3209.listeners.TabbedPaneChangeListener;
import com.javarush.task.task32.task3209.listeners.UndoListener;

import javax.swing.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class View extends JFrame implements ActionListener {

    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();
    private UndoManager undoManager = new UndoManager();
    private UndoListener undoListener = new UndoListener(undoManager);

    public UndoListener getUndoListener() {
        return undoListener;
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public Controller getController() {
        return controller;
    }

    public View() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
        catch (ClassNotFoundException e){
            ExceptionHandler.log(e);
        } catch (IllegalAccessException e) {
            ExceptionHandler.log(e);
        } catch (InstantiationException e) {
            ExceptionHandler.log(e);
        }
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command){
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
        }
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        addWindowListener(frameListener);
        this.setVisible(true);
    }

    public void exit() {
        controller.exit();
    }

    public void initMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();
        MenuHelper.initFileMenu(this, jMenuBar);
        MenuHelper.initEditMenu(this, jMenuBar);
        MenuHelper.initStyleMenu(this, jMenuBar);
        MenuHelper.initAlignMenu(this, jMenuBar);
        MenuHelper.initColorMenu(this, jMenuBar);
        MenuHelper.initFontMenu(this, jMenuBar);
        MenuHelper.initHelpMenu(this, jMenuBar);
        getContentPane().add(jMenuBar, BorderLayout.NORTH);

    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPane1 = new JScrollPane(htmlTextPane);
        tabbedPane.add("HTML", jScrollPane1);
        JScrollPane jScrollPane2 = new JScrollPane(plainTextPane);
        tabbedPane.add("Текст", jScrollPane2);
        final int weight = 10;
        final int height = 20;
        Dimension dimension = new Dimension(weight, height);
        tabbedPane.setPreferredSize(dimension);
        TabbedPaneChangeListener listener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(listener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }

    public void selectedTabChanged() {

        if (tabbedPane.getSelectedIndex() == 0){
            String text = plainTextPane.getText();
            controller.setPlainText(text);

        }
        else if (tabbedPane.getSelectedIndex() == 1){
            String text = controller.getPlainText();
            plainTextPane.setText(text);
        }

       resetUndo();
    }

    public boolean canUndo(){

        return   undoManager.canUndo();
    
    }



    public boolean canRedo() {

        return undoManager.canRedo();

    }

    public void undo(){
        try{
        undoManager.undo();
        }
        catch (Exception e){
            ExceptionHandler.log(e);
        }

    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo(){
        undoManager.discardAllEdits();
    }

    public boolean isHtmlTabSelected(){

        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();

    }

    public void update(){

            htmlTextPane.setDocument(controller.getDocument());


    }

    public void showAbout(){
        JOptionPane.showMessageDialog(null, "Program is good", "Information", JOptionPane.INFORMATION_MESSAGE);
    }


}
