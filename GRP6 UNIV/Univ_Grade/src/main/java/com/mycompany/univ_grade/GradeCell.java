/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.univ_grade;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 *
 * @author gabriel villena
 */
public class GradeCell extends DefaultCellEditor{
    private JTextField textfield;
    public GradeCell(){
        super(new JTextField());
        textfield = (JTextField) getComponent();
        textfield.addKeyListener(new KeyAdapter (){
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                String text = textfield.getText();
                if (!Character.isDigit(c) && c != KeyEvent.VK_BACK_SPACE){
                    e.consume();
                } else if (!text.isEmpty()){    
                    try {
                        int val = Integer.parseInt(text + c);
                        if (val < 0 || val > 100) {
                            e.consume();
                        }
                    } catch (NumberFormatException ex){
                        e.consume();
                    }
                }
            }
        });
    }
    
    @Override
    public Object getCellEditorValue(){
        String val = textfield.getText();
        double dbVal = 0.0;
        
        if(val.isEmpty()){
            return 0.0;
        }
        
        try {
            dbVal = Double.parseDouble(val);
            
            if (dbVal < 0){
                return 0.0;
            } else if (dbVal > 100){
                return 100.0;   
            }
        } catch (NumberFormatException ex){
            return 0.0;
        }
        
        return dbVal;
    }
}
