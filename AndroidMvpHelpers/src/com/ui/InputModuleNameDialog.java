package com.ui;

import com.MvpGeneratorAction.CodeType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InputModuleNameDialog extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JTextField textField2;
    private JLabel labelModuleName;
    private JLabel authorLabel;

    private OnEnsureClickDelegate mDelegate;
    private CodeType mType = CodeType.Activity;

    public InputModuleNameDialog(OnEnsureClickDelegate delegate, CodeType type) {
        super();
        this.mDelegate = delegate;
        this.mType = type;

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        setSize(450, 200);
        setLocationRelativeTo(null);
        switch (mType){
            case Activity:
                setTitle("创建Activity With Mvp");
                break;
            case Fragment:
                setTitle("创建Fragment With Mvp");
                break;
            default:
                break;
        }


        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        setVisible(false);
        dispose();

        if (mDelegate != null){
            mDelegate.onEnsure(textField1.getText().toString(),textField2.getText().toString());
        }
    }

    private void onCancel() {
        // add your code here if necessary
        setVisible(false);
        dispose();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }

    public interface OnEnsureClickDelegate{
        void onEnsure(String author,String moduleName);
    }
}
