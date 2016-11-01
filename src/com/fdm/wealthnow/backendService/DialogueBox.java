package com.fdm.wealthnow.backendService;

import javax.swing.JOptionPane;

public class DialogueBox {
    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args){
    	DialogueBox.infoBox("YOUR INFORMATION HERE", "TITLE BAR MESSAGE");
}

}
