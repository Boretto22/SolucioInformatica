package Estetica;
import static Estetica.Medida.*;

import processing.core.*;

public class GUI{

    PFont pFont1;

    static Paleta paleta;

    Counter cr, cg, cb;

    public String titol;
    public PImage logo, logoPantalles, footballfield, loadImage, imgMas, imgMenos;
    PShape house, fieldicon, football, calendar, alert;
    //Botons
    public BotonsEstat b1, b2, b4, b5, b6, b7, b8;
    public Botons blogin, btable1, btable2, bsignup;
    public Botons bLoadImg;

    //VARIABLES PANTALLA INICIAL (Redesign)
    public Text_Field nombreEquipoTextField;

    // Counters
    public int pj = 0, pg = 0, pe = 0, pp = 0;
    public int selectedMatchStat = -1; // 0=PJ, 1=PG, 2=PE, 3=PP
    public Botons btnMatchPlus, btnMatchMinus;

    // Stats
    public int statsPuntos = 0;
    public int statsGoles = 0;
    public int statsAsistencias = 0;
    public int statsAmarillas = 0;
    public int statsRojas = 0;

    public Botons btnPointsPlus3, btnPointsPlus1, btnPointsMinus;
    public Botons btnGoalsPlus, btnGoalsMinus;
    public Botons btnAssistsPlus, btnAssistsMinus;
    public Botons btnYellowPlus, btnYellowMinus;
    public Botons btnRedPlus, btnRedMinus;

    //Enumerat de les pantalles de l'app
    public enum PANTALLA {LOGIN, INICIAL, CALENDAR, JUGADAS, ENTRENAMIENTO, ALERTAS};

    //Pantalla actual
    public PANTALLA pantallaActual;

    //Text Field
    public Text_Field text1, textAlert;

    //VARIABLES PANTALLA JUGADAS (Interactivas)
    java.util.ArrayList<PImage> resources = new java.util.ArrayList<PImage>();
    java.util.ArrayList<DraggableImage> activeImages = new java.util.ArrayList<DraggableImage>();
    java.util.ArrayList<TacticalArrow> activeArrows = new java.util.ArrayList<TacticalArrow>();

    // Resource Panel
    String[] resourceFiles = {"Jugador 1.png", "Jugador 2.png", "Porter.png", "Aro.png", "Escalera.png", "porteria.png"};
    float panelX, panelY, panelW;

    // Interaction
    DraggableImage currentDrag = null;
    TacticalArrow currentArrow = null;
    PVector arrowStart = null;

    //Dimensions botons
    float buttonW = 120, buttonH = 60;
    //Dimensions botons counter
    float counterW = 200, counterH = 80;

    //Taula paginada
    PagedTable t;

    // Dimensions de la taula
    float tableW = 1400, tableH = 600;

    // Número de files (capçalera inclosa) i columnes de la taula
    int files = 5, columnes = 5;

    // Títols de les columnes
    String[] headers = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};

    // Amplades de les columnes
    float[] colWidths = {20, 20, 20, 20, 20};

    // Dades de la taula
    String[][] info = {
            {"Campo:", "Descanso", "Descanso", "Campo:", "Campo:"},
            {"Rondos", "-", "-", "Juegos", "Rondos"},
            {"Juego", "-", "-", "Rondos", "Juego"},
            {"Posesion", "-", "-", "Posesion", "Automatismos"},
            {"Automatismo", "-", "-", "Fisico", "Jugadas"},
            {"Definición", "-", "-", "Definición", "Definición"},

            {"Pagina 2", "Descanso", "Descanso", "Campo:", "Campo:"},
            {"Rondos", "P2", "-", "Juegos", "Rondos"},
            {"Juego", "-", "P2", "Rondos", "Juego"},
            {"Posesion", "-", "-", "P2", "Automatismos"},
            {"Automatismo", "-", "-", "Fisico", "P2"},
            {"Definición", "-", "-", "Definición", "Definición"},

            {"Pagina 3", "Descanso", "Descanso", "Campo:", "Campo:"},
            {"Rondos", "P3", "-", "Juegos", "Rondos"},
            {"Juego", "-", "P3", "Rondos", "Juego"},
            {"Posesion", "-", "-", "P3", "Automatismos"},
            {"Automatismo", "-", "-", "Fisico", "P3"},
            {"Definición", "-", "-", "Definición", "Definición"},
    };

    // Delegacio paginacio
    public void nextPage() {
        if (t != null) t.nextPage();
    }

    public void prevPage() {
        if (t != null) t.prevPage();
    }

    //CalendariPlus
    public CalendariPlus c;
    public String dataCalendari = "";

    public GUI(PApplet p5){

        logo = p5.loadImage("LogoApp.png");
        logoPantalles = p5.loadImage("Logo.png");
        house = p5.loadShape("house-solid-full.svg");
        fieldicon = p5.loadShape("football-field.svg");
        football = p5.loadShape("football.svg");
        calendar = p5.loadShape("calendar.svg");
        alert = p5.loadShape("alert.svg");
        pFont1 = p5.createFont("Roboto-Black.ttf", 30);
        pantallaActual = PANTALLA.LOGIN;
        paleta = new Paleta(p5);
        footballfield = p5.loadImage("Football_field.png");
        imgMas = p5.loadImage("iconaMes.png");
        imgMenos = p5.loadImage("iconaMenys.png");

        t = new PagedTable(p5, files, columnes);
        t.setHeaders(headers);
        t.setData(info);
        t.setColumnWidths(colWidths);
        btable1 = new Botons(p5, "NEXT", p5.width/2+725, p5.height/2+340, buttonW, buttonH);
        btable2 = new Botons(p5, "PREV", p5.width/2+555, p5.height/2+340, buttonW, buttonH);

        // Crea el Calendari
        c = new CalendariPlus(p5,p5.width/2-550,p5.height/2-275,1400,800);

        b1 = new BotonsEstat(p5,"ENTRENADOR", p5.width/2-300,p5.height/2,250,100);
        b2 = new BotonsEstat(p5, "JUGADOR", p5.width/2+50, p5.height/2, 250, 100);
        blogin = new Botons(p5,"LOG IN", p5.width/2-450, p5.height/2+350, 400,75);
        bsignup = new Botons(p5,"SIGN UP", p5.width/2+50, p5.height/2+350, 400,75);
        b4 = new BotonsEstat(p5,house, marginH+25,2*marginV+150, 65,65);
        b4.setTextBoto(p5, "Home");
        b5 = new BotonsEstat(p5,fieldicon, marginH+25,2*marginV+250, 65,65);
        b5.setTextBoto(p5, "Jugadas");
        b6 = new BotonsEstat(p5,football, marginH+25,2*marginV+350, 65,65);
        b6.setTextBoto(p5, "Entrenamientos");
        b7 = new BotonsEstat(p5,calendar, marginH+25,2*marginV+450, 65,65);
        b7.setTextBoto(p5, "Jornadas");
        b8 = new BotonsEstat(p5,alert, marginH+25,2*marginV+550, 65,65);
        b8.setTextBoto(p5, "Alertas");

        bLoadImg = new Botons(p5, "Load Image", p5.width/2-100, p5.height/2, 200,50);

        //Botons counter
        cr = new Counter(p5, imgMas, imgMenos, p5.width/4, p5.height/4, counterW, counterH);
        cg = new Counter(p5, imgMas, imgMenos, p5.width/4, p5.height/2, counterW, counterH);
        cb = new Counter(p5, imgMas, imgMenos, p5.width/4, 3*p5.height/4, counterW, counterH);

        text1 = new Text_Field(p5, p5.width/2-200, p5.height/2+200, 400,100);
        textAlert = new Text_Field(p5, p5.width/2-350, p5.height/2-350, 1000,800);
        textAlert.setBackgroundColor(paleta.getColorAt(9));
        textAlert.setLimit(1000);
        textAlert.setMultiline(true);
        textAlert.setSelectedColor(paleta.getColorAt(10));

        // INIT PANTALLA JUGADAS
        panelX = marginH + sidebarWidth + 20;
        panelY = 100;
        panelW = 100;

        for(String filename : resourceFiles){
            PImage img = p5.loadImage("Data/Jugadas/" + filename);
            if(img != null){
                resources.add(img);
            } else {
                PApplet.println("Error loading resource: " + filename);
            }
        }

        // INIT PANTALLA INICIAL
        // Header
        nombreEquipoTextField = new Text_Field(p5, 340, 20, 300, 50);
        nombreEquipoTextField.setText("Nombre del Equipo");
        // Image Container reuses bLoadImg, positioned later in dibujoPantallaInicial or updated here?
        // Let's reset bLoadImg position here or update it in display. Update here for safety.
        bLoadImg = new Botons(p5, "Load Image", p5.width/2 - 50, 200, 100, 40);

        // Match Buttons (Global +/-) - Centered below stats
        float midX = (marginH + sidebarWidth + p5.width)/2;
        btnMatchPlus = new Botons(p5, "+", midX - 60, 500, 50, 50);
        btnMatchMinus = new Botons(p5, "-", midX + 10, 500, 50, 50);

        // Right Sidebar Inputs
        float rightX = p5.width - 250;
        float startY = 50;
        float gapY = 80;

        // Puntos
        btnPointsPlus3 = new Botons(p5, "+3", rightX + 60, startY, 50, 40);
        btnPointsPlus1 = new Botons(p5, "+1", rightX + 115, startY, 40, 40);
        btnPointsMinus = new Botons(p5, "-", rightX + 160, startY, 40, 40);

        // Goles
        btnGoalsPlus = new Botons(p5, "+", rightX + 80, startY + gapY, 40, 40);
        btnGoalsMinus = new Botons(p5, "-", rightX + 130, startY + gapY, 40, 40);

        // Asistencias
        btnAssistsPlus = new Botons(p5, "+", rightX + 80, startY + 2*gapY, 40, 40);
        btnAssistsMinus = new Botons(p5, "-", rightX + 130, startY + 2*gapY, 40, 40);

        // Amarillas
        btnYellowPlus = new Botons(p5, "+", rightX + 80, startY + 3*gapY, 40, 40);
        btnYellowMinus = new Botons(p5, "-", rightX + 130, startY + 3*gapY, 40, 40);

        // Rojas
        btnRedPlus = new Botons(p5, "+", rightX + 80, startY + 4*gapY, 40, 40);
        btnRedMinus = new Botons(p5, "-", rightX + 130, startY + 4*gapY, 40, 40);
    }
    //Pantalles GUI

    public void dibujoPantallaLogIn(PApplet p5){
        p5.background (paleta.getColorAt(0));
        logoLogIn(p5, logo);
        b1.display(p5);
        b2.display(p5);
        blogin.display(p5);
        bsignup.display(p5);
        text1.display(p5);
        p5.pushMatrix();
        p5.fill(0);
        p5.textFont(pFont1);
        p5.text("USERNAME", p5.width/2-200, p5.height/2+180);
        p5.popMatrix();

    }

    public void dibujoPantallaInicial(PApplet p5){
        p5.background(paleta.getColorAt(0));
        logoPantallas(p5, logoPantalles);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
        
        nombreEquipoTextField.display(p5);
        p5.fill(0);
        p5.textAlign(p5.LEFT);
        p5.textSize(16);
        p5.text("Nombre del equipo", nombreEquipoTextField.x, nombreEquipoTextField.y - 5);

        // Image Container
        float imgX = (marginH + sidebarWidth + p5.width)/2;
        float imgY = 150;
        p5.noFill();
        p5.stroke(0);
        p5.rectMode(PConstants.CENTER);
        p5.rect(imgX, imgY, 150, 150);
        if(loadImage != null){
            p5.imageMode(PConstants.CENTER);
            p5.image(loadImage, imgX, imgY, 140, 140);
        }
        p5.rectMode(PConstants.CORNER);
        // Load Img Button
        bLoadImg.x = imgX - 50;
        bLoadImg.y = imgY + 80;
        bLoadImg.display(p5);
        // Central Match Counters (PJ, PG, PE, PP)
        float startX = (marginH + sidebarWidth + p5.width)/2 - 220; // Centered roughly
        float startY = 400;
        float boxW = 100, boxH = 50;

        String[] headers = {"PJ", "PG", "PE", "PP"};
        int[] values = {pj, pg, pe, pp};

        p5.textSize(20);
        p5.textAlign(p5.CENTER, p5.BOTTOM);

        for(int i=0; i<4; i++){
            float bx = startX + i*110;

            // Header
            p5.fill(0);
            p5.text(headers[i], bx + boxW/2, startY - 5);

            // Box
            if(selectedMatchStat == i) p5.strokeWeight(4); else p5.strokeWeight(1);
            p5.fill(255);
            p5.rect(bx, startY, boxW, boxH);

            // Value
            p5.fill(0);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text(values[i], bx + boxW/2, startY + boxH/2);
        }
        p5.strokeWeight(1);

        // Plus/Minus Buttons
        btnMatchPlus.display(p5);
        btnMatchMinus.display(p5);


        // Right Sidebar Stats
        float rightX = p5.width - 250;
        float sY = 50;
        float gY = 80;
        int txtSize = 20;

        drawStatRow(p5, "Puntos", statsPuntos, rightX, sY, null); // Manual buttons for points
        btnPointsPlus3.display(p5);
        btnPointsPlus1.display(p5);
        btnPointsMinus.display(p5);

        drawStatRow(p5, "Goles", statsGoles, rightX, sY + gY, null);
        btnGoalsPlus.display(p5);
        btnGoalsMinus.display(p5);

        drawStatRow(p5, "Asistencias", statsAsistencias, rightX, sY + 2*gY, null);
        btnAssistsPlus.display(p5);
        btnAssistsMinus.display(p5);

        drawStatRow(p5, "Amarillas", statsAmarillas, rightX, sY + 3*gY, null);
        btnYellowPlus.display(p5);
        btnYellowMinus.display(p5);

        drawStatRow(p5, "Rojas", statsRojas, rightX, sY + 4*gY, null);
        btnRedPlus.display(p5);
        btnRedMinus.display(p5);
    }

    void drawStatRow(PApplet p5, String label, int val, float x, float y, Botons[] btns){
        p5.fill(0);
        p5.textAlign(p5.LEFT, p5.BOTTOM);
        p5.textSize(18);
        p5.text(label, x, y);

        p5.fill(255);
        p5.stroke(0);
        p5.rect(x, y + 5, 50, 30);

        p5.fill(0);
        p5.textAlign(p5.CENTER, p5.CENTER);
        p5.text(val, x + 25, y + 20);
    }

    // Pantalla inicial interaccions
    public void checkMousePantallaInicial(PApplet p5){
        // Match Counters Selection
        float startX = (marginH + sidebarWidth + p5.width)/2 - 220;
        float startY = 400;
        float boxW = 100, boxH = 50;

        for(int i=0; i<4; i++){
            float bx = startX + i*110;
            if(p5.mouseX > bx && p5.mouseX < bx+boxW && p5.mouseY > startY && p5.mouseY < startY+boxH){
                selectedMatchStat = i;
                return; // Selected, done
            }
        }

        // Match Buttons
        if(btnMatchPlus.mouseOverButton(p5)){
            if(selectedMatchStat == 0) pj++;
            else if(selectedMatchStat == 1) pg++;
            else if(selectedMatchStat == 2) pe++;
            else if(selectedMatchStat == 3) pp++;
        }
        if(btnMatchMinus.mouseOverButton(p5)){
            if(selectedMatchStat == 0) pj--;
            else if(selectedMatchStat == 1) pg--;
            else if(selectedMatchStat == 2) pe--;
            else if(selectedMatchStat == 3) pp--;
        }

        // Right Sidebar Buttons
        if(btnPointsPlus3.mouseOverButton(p5)) statsPuntos += 3;
        if(btnPointsPlus1.mouseOverButton(p5)) statsPuntos += 1;
        if(btnPointsMinus.mouseOverButton(p5)) statsPuntos--;

        if(btnGoalsPlus.mouseOverButton(p5)) statsGoles++;
        if(btnGoalsMinus.mouseOverButton(p5)) statsGoles--;

        if(btnAssistsPlus.mouseOverButton(p5)) statsAsistencias++;
        if(btnAssistsMinus.mouseOverButton(p5)) statsAsistencias--;

        if(btnYellowPlus.mouseOverButton(p5)) statsAmarillas++;
        if(btnYellowMinus.mouseOverButton(p5)) statsAmarillas--;

        if(btnRedPlus.mouseOverButton(p5)) statsRojas++;
        if(btnRedMinus.mouseOverButton(p5)) statsRojas--;

        // Text Field
        nombreEquipoTextField.isPressed(p5);

    }

    public void dibujoPantallaCalendar(PApplet p5){
        p5.background(paleta.getColorAt(0));
        logoPantallas(p5, logoPantalles);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
        c.display(p5);
        p5.text(dataCalendari, 190, 45);
    }

    public void dibujoPantallaJugadas(PApplet p5){
        p5.background(paleta.getColorAt(0));
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
        logoPantallas(p5, logoPantalles);

        // FootballField
        float fieldVisualWidth = footballFieldHeight + 300;
        float fieldX = marginH + sidebarWidth + 200 + fieldVisualWidth / 2;
        drawFootballField(p5, footballfield, fieldX, p5.height/2);


        // Elements actius
        for(TacticalArrow a : activeArrows){
            a.display(p5);
        }
        if(currentArrow != null){
            currentArrow.display(p5);
        }

        for(DraggableImage d : activeImages){
            d.display(p5);
        }

        // panel esquerra
        float rX = marginH + sidebarWidth + 30; // Left of field
        float rY = 150;
        float rSize = 60;
        float spacing = 80;

        p5.pushStyle();
        p5.fill(200, 100); // Semi-transparent background
        p5.noStroke();
        p5.rect(rX - 10, rY - 10, rSize + 20, spacing * resources.size() + 20, 10);
        p5.popStyle();

        for(int i=0; i<resources.size(); i++){
            p5.imageMode(PConstants.CENTER);
            PImage icon = resources.get(i);
            float ar = (icon != null && icon.height != 0) ? (float)icon.width/icon.height : 1;
            // Fit to rSize
            float dw = rSize;
            float dh = rSize;
            if(ar > 1) dh = dw / ar;
            else dw = dh * ar;

            p5.image(icon, rX + rSize/2, rY + i*spacing + rSize/2, dw, dh);
        }

    }

    // Interactccions amb pantalla jugadas
    public void checkMousePressedJugadas(PApplet p5){
        // Check Panel (Clone)
        float rX = marginH + sidebarWidth + 30;
        float rY = 150;
        float rSize = 60;
        float spacing = 80;

        for(int i=0; i<resources.size(); i++){
            float itemY = rY + i*spacing + rSize/2;
            float itemX = rX + rSize/2;
            if(p5.dist(p5.mouseX, p5.mouseY, itemX, itemY) < rSize/2){
                // Clon
                PImage original = resources.get(i);
                // Size
                DraggableImage newImg = new DraggableImage(original, p5.mouseX, p5.mouseY, 50, 50 * ((float)original.height/original.width));
                activeImages.add(newImg);
                currentDrag = newImg;
                currentDrag.dragging = true;
                return;
            }
        }

        // Check Existing Images
        for(int i=activeImages.size()-1; i>=0; i--){
            DraggableImage d = activeImages.get(i);
            if(d.contains(p5.mouseX, p5.mouseY)){
                currentDrag = d;
                currentDrag.dragging = true;
                return; // Handled
            }
        }

        // Field (Arrow)
        arrowStart = new PVector(p5.mouseX, p5.mouseY);
        currentArrow = new TacticalArrow(arrowStart, new PVector(p5.mouseX, p5.mouseY), p5.color(0));
    }

    public void checkMouseDraggedJugadas(PApplet p5){
        if(currentDrag != null){
            currentDrag.setPosition(p5.mouseX, p5.mouseY);
        } else if (currentArrow != null){
            currentArrow.setEnd(new PVector(p5.mouseX, p5.mouseY));
        }
    }

    public void checkMouseReleasedJugadas(PApplet p5){
        if(currentDrag != null){
            currentDrag.dragging = false;
            currentDrag = null;
        }
        if(currentArrow != null){
            // Si fletxa molt petita ingnorar
            if(PApplet.dist(currentArrow.start.x, currentArrow.start.y, currentArrow.end.x, currentArrow.end.y) > 10){
                activeArrows.add(currentArrow);
            }
            currentArrow = null;
            arrowStart = null;
        }
    }

    public void dibujoPantallEntrenamientos(PApplet p5){
        p5.background(paleta.getColorAt(0));
        logoPantallas(p5, logoPantalles);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
        t.display(p5, p5.width/2-550, p5.height/2-275, tableW, tableH);
        btable1.display(p5);
        btable2.display(p5);
        p5.textFont(pFont1);
        p5.pushMatrix();
        p5.fill(0);
        p5.textSize(75);
        p5.text("ENTRENAMIENTOS DEL MES", p5.width/2-300, p5.height/2-365);
        p5.popMatrix();
    }

    public void dibujoPantallaAlerta(PApplet p5){
        p5.background(paleta.getColorAt(0));
        logoPantallas(p5, logoPantalles);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
        textAlert.display(p5);
    }

    //Zones de la GUI

    public void logoLogIn(PApplet p5, PImage logo) {
        p5.imageMode(PConstants.CENTER);
        p5.image(logo, p5.width/2, p5.height/2 - 265, logoWidth/2+450, logoHeight/2+450);
    }

    public void logoPantallas(PApplet p5, PImage logoPantalles) {
        p5.imageMode(PConstants.CENTER);
        p5.image(logoPantalles, p5.width/2-900, p5.height/2-465, logoPantallesWidth/2+50, logoPantallesHeight/2+50);
    }

    public void drawFootballField(PApplet p5, PImage footballfield, float x, float y){
        p5.imageMode(PConstants.CENTER);
        p5.pushMatrix();
        p5.translate(x, y);
        p5.rotate(PConstants.HALF_PI);
        p5.image(footballfield, 0, 0, footballFieldWidth+100, footballFieldHeight+500);
        p5.popMatrix();
    }


    public static void sideBar(PApplet p5){
        p5.noStroke();
        p5.fill (paleta.getColorAt(4));
        p5.rect(marginH, 2*marginV + 100, sidebarWidth, sidebarHeight, 20);
    }

   }
