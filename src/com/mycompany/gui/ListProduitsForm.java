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
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Produit;
import com.mycompany.services.ServiceFavori;
import com.mycompany.services.ServiceProduit;
import java.util.ArrayList;


/**
 *
 * @author Khairi
 */
public class ListProduitsForm extends BaseForm {

    Form current;

    public ListProduitsForm (Resources res)
    {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Liste des Produits");
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
        RadioButton produits = RadioButton.createToggle("Notre Boutique", barGroup);
        produits.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        produits.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();

            final Dialog ipDlg = ip.showInifiniteBlocking();
        
            new ListProduitsForm(res).show();
                   
            refreshTheme();
        });


        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, produits),
                FlowLayout.encloseBottom(arrow)
        ));

        produits.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(produits, arrow);
        });
        bindButtonSelection(produits, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        // End Design

        ArrayList<Produit>list = ServiceProduit.getInstance().AfficherProduits();
        
        for(Produit p : list)
        {
            String urlImage = "http://localhost/VolcanoFootball/uploads/"+p.getPhoto();
            Image placeHolder = Image.createImage(120, 90);
            EncodedImage enc = EncodedImage.createFromImage(placeHolder, false);
            URLImage urlim = URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
            addProduit(urlim,p,res);
            ScaleImageLabel image = new ScaleImageLabel(urlim);
            Container containerImg = new Container();
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

    private void addProduit(Image img,Produit p, Resources res) {


        int height = Display.getInstance().convertToPixels(16f);
        int width = Display.getInstance().convertToPixels(12f);

        Button image = new Button(img.fill(width, height));
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
          
        Label nomTxt = new Label("Nom du Produit: "+p.getNom(),"NewsTopLine2");
        Label typeTxt = new Label("Type: "+p.getType(),"NewsTopLine2");
        Label prixTxt = new Label("Prix: "+p.getPrix()+" DT","NewsTopLine2");
        Label margin = new Label("","NewsTopLine2");

        Label lDetailProduit = new Label("Détails du produit");
        lDetailProduit.setUIID("NewsTopLine");
        Style DetailProduitStyle = new Style(lDetailProduit.getUnselectedStyle());
        DetailProduitStyle.setFgColor(0xf7ad02);
        lDetailProduit.setTextPosition(RIGHT);

        lDetailProduit.addPointerPressedListener(l -> {
            
            new DetailProduit(res,p).show();
            refreshTheme();
            

        });

        Label lFavori = new Label(" ");
        lFavori.setUIID("NewsTopLine");
        Style favoriStyle = new Style(lFavori.getUnselectedStyle());
        favoriStyle.setFgColor(0xf7ad02);

        FontImage favoriImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, favoriStyle);
        lFavori.setIcon(favoriImage);
        lFavori.setTextPosition(RIGHT);

        lFavori.addPointerPressedListener(l -> {
            ServiceFavori.getInstance().AjouterFavori(SessionManager.getId(), p.getId());
        });

        cnt.add(BorderLayout.CENTER, BoxLayout.encloseY(

            BoxLayout.encloseX(nomTxt),
            BoxLayout.encloseX(typeTxt),
            BoxLayout.encloseX(prixTxt),
            BoxLayout.encloseX(margin, lDetailProduit, lFavori)

        ));

        add(cnt);
           
    }

    
}
