package com.pidevv;

import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.mycompany.gui.ListProduitsForm;
import com.mycompany.gui.MesCommandesForm;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SignInForm;
import com.mycompany.gui.SignUpForm;
import com.mycompany.gui.StatistiqueProduits;

public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        updateNetworkThreadCount(2);

        theme = UIManager.initFirstTheme("/theme");

        Toolbar.setGlobalToolbar(true);

        Log.bindCrashProtection(true);

        addNetworkErrorListener(err -> {
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Erreur de connexion", "Une erreur réseau s'est produite lors de la connexion à " + err.getConnectionRequest().getUrl(), "OK", null);
        });        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
        //new SignInForm(theme).show();
        new StatistiqueProduits(theme).show();
    }

    public void stop() {
        current = getCurrentForm();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = getCurrentForm();
        }
    }
    
    public void destroy() {
    }

}
