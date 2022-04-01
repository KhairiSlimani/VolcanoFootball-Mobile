/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.BaseForm;
import com.mycompany.services.ServiceUser;


/**
 *
 * @author Khairi
 */
public class ConfirmerUserForm extends BaseForm {
    
    public ConfirmerUserForm(Resources res) 
    {
        super(new BorderLayout());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        tb.setUIID("Container");
        getTitleArea().setUIID("Container");
        Form previous = Display.getInstance().getCurrent();
        tb.setBackCommand("", e -> previous.showBack());
        setUIID("SignIn");

        Label msg = new Label("Entrez votre username");
        Label msg2 = new Label("Et votre code de confirmation");
        Label msg3 = new Label("Pour confirmer votre compte.");

        TextField username = new TextField("", "Username", 20, TextField.EMAILADDR);
        username.setSingleLineTextArea(false);
        
        TextField token = new TextField("", "Code de confirmation", 20, TextField.EMAILADDR);
        token.setSingleLineTextArea(false);


        Button envoyer = new Button("Confirmer");

        Container content = BoxLayout.encloseY(
                new Label("Confirmer mon compte", "LogoLabel"),
                FlowLayout.encloseCenter(msg),
                FlowLayout.encloseCenter(msg2),
                FlowLayout.encloseCenter(msg3),
                new FloatingHint(username),
                createLineSeparator(),
                new FloatingHint(token),
                createLineSeparator()

        );

        content.setScrollableY(true);
        add(BorderLayout.CENTER, content);
        add(BorderLayout.SOUTH, BoxLayout.encloseY(
                envoyer
        ));
        envoyer.requestFocus();
        envoyer.addActionListener((e) -> {

            ServiceUser.getInstance().ConfirmerCompte(username.getText(),token.getText(),res);

        });

    }
    
}
