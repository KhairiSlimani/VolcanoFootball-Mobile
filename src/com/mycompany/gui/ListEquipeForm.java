/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Equipe;
import com.mycompany.services.ServiceEquipe;
import java.util.ArrayList;

/**
 *
 * @author jrady
 */
public class ListEquipeForm  extends BaseForm {
    
     Form current;
      
      public ListEquipeForm (Resources res)
    {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste des Equipes");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {

        });

        Tabs swipe = new Tabs();

        Label s1 = new Label();
        Label s2 = new Label();

        addTab(swipe,s1,res.getImage("back-logo.jpeg"),"","",res);

        // Design

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton equipes = RadioButton.createToggle("Equipe", barGroup);
        equipes.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        equipes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();

            final Dialog ipDlg = ip.showInifiniteBlocking();
        
            new ListEquipeForm(res).show();
                   
            refreshTheme();
        });


        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, equipes),
                FlowLayout.encloseBottom(arrow)
        ));

        equipes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(equipes, arrow);
        });
        bindButtonSelection(equipes, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        // End Design

        ArrayList<Equipe>list = ServiceEquipe.getInstance().AfficherEquipes();
        
        for(Equipe e : list)
        {
            String urlImage = "http://localhost/VolcanoFootball/uploads/"+e.getDrapeau_equipe();
            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            addEquipe(urlim,e,res);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            
            image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
            
            
            
        }
        
         
    }
       private void addTab(Tabs swipe, Label spacer ,Image image, String string, String text, Resources res) {

        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
        if(image.getHeight() < size)
        {
            image = image.scaledHeight(size);
        }

        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2)
        {
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }

        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label overLay = new Label("","ImageOverlay");

        Container page1 = LayeredLayout.encloseIn(
            imageScale,
            overLay,
            BorderLayout.south(
                BoxLayout.encloseY(
                    new SpanLabel(text, "LargeWhiteText"),
                    spacer
                )
            )
        );

        swipe.addTab("",res.getImage("back-logo.jpeg"), page1);


    }
       public void bindButtonSelection(Button btn , Label l)
    {
        btn.addActionListener(e -> {

            if(btn.isSelected())
            {
                updateArrowPosition(btn,l);
            }

        });
    }

    private void updateArrowPosition(Button btn, Label l) {

        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }
    
    private void addEquipe(Image img,Equipe j, Resources res) {


        int height = Display.getInstance().convertToPixels(16f);
        int width = Display.getInstance().convertToPixels(12f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        //Label idTxt = new Label("ID : "+j.getId(),"NewsTopLine2");
        Label nomTxt = new Label("Nom : "+j.getNom_equipe(),"NewsTopLine2");
        Label nomentreneurTxt = new Label("Nom Entreneur : "+j.getNom_entreneur(),"NewsTopLine2");
        Label DatecreationTxt = new Label("Date Creation : "+j.getDate_creation(),"NewsTopLine2");
        Label lDetailEquipe = new Label("Détails Equipe");
        lDetailEquipe.setUIID("NewsTopLine");
        Style DetailEquipeStyle = new Style(lDetailEquipe.getUnselectedStyle());
        DetailEquipeStyle.setFgColor(0xf7ad02);
        lDetailEquipe.setTextPosition(RIGHT);

        lDetailEquipe.addPointerPressedListener(l -> {
            
            new DetailEquipe(res,j.getId()).show();
            refreshTheme();
            

        });
        SpanLabel d = new SpanLabel("-----------------------");
       


        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(
            
            BoxLayout.encloseX(nomTxt),
            BoxLayout.encloseX(nomentreneurTxt),
            BoxLayout.encloseX(DatecreationTxt),
            BoxLayout.encloseX(lDetailEquipe),
            BoxLayout.encloseX(d)
            
          
        ));

        add(cnt);
        
           
    }

    
}
