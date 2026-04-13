package Estetica;
import static Estetica.Medida.*;

import processing.core.*;

public class GUI{

    PFont pFont1;

    static Paleta paleta;

    Counter cr, cg, cb;

    public String titol;
    public PImage logo, logoPantalles, footballfield, loadImage, imgMas, imgMenos, fondoPantalla;
    PShape house, fieldicon, football, calendar, alert;
    //Botons
    public BotonsEstat b1, b2, b4, b5, b6, b7, b8;
    public Botons blogin, btable1, btable2, bsignup;
    public Botons bLoadImg, bSave;

    //VARIABLES PANTALLA INICIAL (Redesign)
    public Text_Field nombreEquipoTextField;
    public String currentUserNombre = "";

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
    public Text_Field text1, text2, textAlert;

    //VARIABLES PANTALLA JUGADAS (Interactivas)
    java.util.ArrayList<PImage> resources = new java.util.ArrayList<PImage>();
    public class JugadaState {
        public java.util.ArrayList<DraggableImage> activeImages = new java.util.ArrayList<DraggableImage>();
        public java.util.ArrayList<TacticalArrow> activeArrows = new java.util.ArrayList<TacticalArrow>();
    }
    public java.util.ArrayList<JugadaState> jugadas = new java.util.ArrayList<JugadaState>();
    public Botons[] btnJugadas = new Botons[5];
    public int currentJugada = 0;
    public boolean eraserMode = false;
    public Botons btnEraser;

    // Resource Panel
    String[] resourceFiles = {"Jugador 1.png", "Jugador 2.png", "Porter.png", "Aro.png", "Escalera.png", "conoAzul.png", "conoRojo.png", "porteria.png"};
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
        fondoPantalla = p5.loadImage("fondoPantalla.png");

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
        blogin = new Botons(p5,"LOG IN", p5.width/2-450, p5.height/2+400, 400,75);
        bsignup = new Botons(p5,"SIGN UP", p5.width/2+50, p5.height/2+400, 400,75);
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

        text1 = new Text_Field(p5, p5.width/2-400, p5.height/2+200, 400,100);
        text2 = new Text_Field(p5, p5.width/2+25, p5.height/2+200, 400, 100);
        textAlert = new Text_Field(p5, p5.width/2-350, p5.height/2-350, 1000,800);
        textAlert.setBackgroundColor(paleta.getColorAt(9));
        textAlert.setLimit(1000);
        textAlert.setMultiline(true);
        textAlert.setSelectedColor(paleta.getColorAt(10));

        // INIT PANTALLA JUGADAS
        panelX = marginH + sidebarWidth + 20;
        panelY = 100;
        panelW = 100;
        btnEraser = new Botons(p5, "Eraser: OFF", 0, 0, 100, 40);

        for(int i = 0; i < 5; i++){
            jugadas.add(new JugadaState());
            btnJugadas[i] = new Botons(p5, "J" + (i+1), marginH + sidebarWidth + 300 + i*70, 40, 60, 40);
        }

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
        bLoadImg = new Botons(p5, "Load Image", p5.width/2 - 50, 200, 100, 40);
        bSave    = new Botons(p5, "SAVE", p5.width/2 - 50, 260, 100, 40);

        // Match Buttons (Global +/-) - Centered below stats
        float midX = (marginH + sidebarWidth + p5.width)/2;
        btnMatchPlus = new Botons(p5, "+", midX - 60, 500, 50, 50);
        btnMatchMinus = new Botons(p5, "-", midX + 10, 500, 50, 50);

        // Right Sidebar Inputs
        float rightX = p5.width - 250;
        float startY = 50;
        float gapY = 80;

        // Puntos
        btnPointsPlus3 = new Botons(p5, "+3", rightX + 60, startY, 60, 50);
        btnPointsPlus1 = new Botons(p5, "+1", rightX + 125, startY, 50, 50);
        btnPointsMinus = new Botons(p5, "-", rightX + 180, startY, 50, 50);

        // Goles
        btnGoalsPlus = new Botons(p5, "+", rightX + 80, startY + gapY, 50, 50);
        btnGoalsMinus = new Botons(p5, "-", rightX + 140, startY + gapY, 50, 50);

        // Asistencias
        btnAssistsPlus = new Botons(p5, "+", rightX + 80, startY + 2*gapY, 50, 50);
        btnAssistsMinus = new Botons(p5, "-", rightX + 140, startY + 2*gapY, 50, 50);

        // Amarillas
        btnYellowPlus = new Botons(p5, "+", rightX + 80, startY + 3*gapY, 50, 50);
        btnYellowMinus = new Botons(p5, "-", rightX + 140, startY + 3*gapY, 50, 50);

        // Rojas
        btnRedPlus = new Botons(p5, "+", rightX + 80, startY + 4*gapY, 50, 50);
        btnRedMinus = new Botons(p5, "-", rightX + 140, startY + 4*gapY, 50, 50);
    }
    //Pantalles GUI

    public void dibujoPantallaLogIn(PApplet p5){
        p5.imageMode(PConstants.CORNER);
        p5.image(fondoPantalla, 0, 0, p5.width, p5.height);
        logoLogIn(p5, logo);
        b1.display(p5);
        b2.display(p5);
        blogin.display(p5);
        bsignup.display(p5);
        text1.display(p5);
        text2.display(p5);
        p5.pushMatrix();
        p5.fill(255);
        p5.textFont(pFont1);
        p5.text("USERNAME", p5.width/2-400, p5.height/2+180);
        p5.text("PASSWORD", p5.width/2+25, p5.height/2+175);
        p5.popMatrix();
    }

    public void dibujoPantallaInicial(PApplet p5){
        p5.imageMode(PConstants.CORNER);
        p5.image(fondoPantalla, 0, 0, p5.width, p5.height);
        logoPantallas(p5, logoPantalles);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);

        // Center-Left Area Layout
        float contentLeft = marginH + sidebarWidth;
        float centerRight = p5.width - 280; // Leaving space for the right panel
        float centerW = centerRight - contentLeft;
        float centerX = contentLeft + centerW / 2;

        // 1. Text Field
        nombreEquipoTextField.x = (int)contentLeft + 50;
        nombreEquipoTextField.y = 150;
        nombreEquipoTextField.w = (int)centerW - 100;
        nombreEquipoTextField.display(p5);

        p5.fill(0);
        p5.textAlign(p5.LEFT);
        p5.textSize(16);
        p5.text("Nombre del equipo", nombreEquipoTextField.x, nombreEquipoTextField.y - 5);

        // 2. LOAD IMG and SAVE buttons
        bLoadImg.x = nombreEquipoTextField.x;
        bLoadImg.y = nombreEquipoTextField.y + 80;
        bLoadImg.w = 150;
        bLoadImg.display(p5);

        bSave.x = bLoadImg.x;
        bSave.y = bLoadImg.y + 60;
        bSave.w = 150;
        bSave.display(p5);

        // 3. Foto Square (Larger, tighter to buttons)
        float imgY = bSave.y + 160; 
        p5.noFill();
        p5.stroke(0);
        p5.rectMode(3); // CENTER
        p5.rect(centerX, imgY, 200, 200);
        
        if (loadImage != null) {
            p5.imageMode(3); // CENTER
            p5.image(loadImage, centerX, imgY, 190, 190);
        } else {
            p5.fill(0);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.textSize(20);
            p5.text("Foto", centerX, imgY);
        }
        p5.rectMode(0); // CORNER
        
        // 4. Nombre Text
        p5.fill(0);
        p5.textAlign(p5.CENTER, p5.TOP);
        p5.textSize(24);
        p5.text(currentUserNombre, centerX, imgY + 90);

        // 5. Match Counters (PJ, PG, PE, PP)
        float startY = imgY + 180;
        float boxW = 120, boxH = 70;
        // Center the 4 boxes relatively to the center width
        float boxesTotalW = 4 * boxW + 3 * 10; // 10px spacing
        float startX = centerX - boxesTotalW / 2;

        String[] headers = {"P.J", "P.G", "P.E", "P.P"};
        int[] values = {pj, pg, pe, pp};

        p5.textSize(20);

        for (int i = 0; i < 4; i++) {
            float bx = startX + i * (boxW + 10);

            // Header
            p5.fill(0);
            p5.textAlign(p5.CENTER, p5.BOTTOM);
            p5.text(headers[i], bx + boxW / 2, startY - 5);

            // Box
            if (selectedMatchStat == i) p5.strokeWeight(4); else p5.strokeWeight(1);
            p5.fill(255);
            p5.rect(bx, startY, boxW, boxH);

            // Value (showing 0 instead of infinity symbol to stick to standard integer display)
            p5.fill(0);
            p5.textAlign(p5.CENTER, p5.CENTER);
            p5.text(values[i] == 0 ? "00" : String.valueOf(values[i]), bx + boxW / 2, startY + boxH / 2);
            
            // Vertical Divider
            if (i < 3) {
                p5.strokeWeight(1);
                p5.stroke(0);
                float lineX = bx + boxW + 5;
                p5.line(lineX, startY - 20, lineX, startY + boxH + 20);
            }
        }
        p5.strokeWeight(1);

        // 6. Plus/Minus Buttons
        btnMatchPlus.x = centerX - 60;
        btnMatchPlus.y = startY + boxH + 30;
        btnMatchPlus.display(p5);

        btnMatchMinus.x = centerX + 10;
        btnMatchMinus.y = startY + boxH + 30;
        btnMatchMinus.display(p5);

        // RIGHT SIDE – Stats Panel
        float rightX = p5.width - 250 - 20; // shifted slightly left for bigger buttons
        float sY = 50;
        float gY = 100;

        drawStatRow(p5, "Puntos", statsPuntos, rightX, sY, null);
        btnPointsPlus3.x = rightX + 60; btnPointsPlus3.y = sY; btnPointsPlus3.display(p5);
        btnPointsPlus1.x = rightX + 115; btnPointsPlus1.y = sY; btnPointsPlus1.display(p5);
        btnPointsMinus.x = rightX + 160; btnPointsMinus.y = sY; btnPointsMinus.display(p5);

        drawStatRow(p5, "Goles", statsGoles, rightX, sY + gY, null);
        btnGoalsPlus.x = rightX + 80; btnGoalsPlus.y = sY + gY; btnGoalsPlus.display(p5);
        btnGoalsMinus.x = rightX + 130; btnGoalsMinus.y = sY + gY; btnGoalsMinus.display(p5);

        drawStatRow(p5, "Asistencias", statsAsistencias, rightX, sY + 2 * gY, null);
        btnAssistsPlus.x = rightX + 80; btnAssistsPlus.y = sY + 2 * gY; btnAssistsPlus.display(p5);
        btnAssistsMinus.x = rightX + 130; btnAssistsMinus.y = sY + 2 * gY; btnAssistsMinus.display(p5);

        drawStatRow(p5, "Targetes amarillas", statsAmarillas, rightX, sY + 3 * gY, null);
        btnYellowPlus.x = rightX + 80; btnYellowPlus.y = sY + 3 * gY; btnYellowPlus.display(p5);
        btnYellowMinus.x = rightX + 130; btnYellowMinus.y = sY + 3 * gY; btnYellowMinus.display(p5);

        drawStatRow(p5, "Targetes rojas", statsRojas, rightX, sY + 4 * gY, null);
        btnRedPlus.x = rightX + 80; btnRedPlus.y = sY + 4 * gY; btnRedPlus.display(p5);
        btnRedMinus.x = rightX + 130; btnRedMinus.y = sY + 4 * gY; btnRedMinus.display(p5);
    }

    void drawStatRow(PApplet p5, String label, int val, float x, float y, Botons[] btns){
        p5.fill(0);
        p5.textAlign(p5.LEFT, p5.BOTTOM);
        p5.textSize(20);
        p5.text(label, x, y - 5);

        p5.fill(255);
        p5.stroke(0);
        p5.rectMode(0); // CORNER
        p5.rect(x, y + 5, 60, 45);

        p5.fill(0);
        p5.textAlign(p5.CENTER, p5.CENTER);
        p5.textSize(22);
        p5.text(val, x + 30, y + 5 + 22.5f);
    }

    // Pantalla inicial interaccions
    public void checkMousePantallaInicial(PApplet p5){
        // Layout calculations matching dibujoPantallaInicial exactly
        float contentLeft = marginH + sidebarWidth;
        float centerRight = p5.width - 280;
        float centerW = centerRight - contentLeft;
        float centerX = contentLeft + centerW / 2;
        
        float inputY = 150;
        float bLoadY = inputY + 80;
        float bSaveY = bLoadY + 60;
        float imgY = bSaveY + 160;
        
        // Match Counters Selection
        float startY = imgY + 180;
        float boxW = 120, boxH = 70;
        float boxesTotalW = 4 * boxW + 3 * 10;
        float startX = centerX - boxesTotalW / 2;

        for(int i=0; i<4; i++){
            float bx = startX + i*(boxW + 10);
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
        p5.imageMode(PConstants.CORNER);
        p5.image(fondoPantalla, 0, 0, p5.width, p5.height);
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

    public void dibujoPantallaJugadas(PApplet p5) {
        p5.imageMode(PConstants.CORNER);
        p5.image(fondoPantalla, 0, 0, p5.width, p5.height);
        sideBar(p5);
        b4.display(p5);
        b5.display(p5);
        b6.display(p5);
        b7.display(p5);
        b8.display(p5);
        logoPantallas(p5, logoPantalles);

        // FootballField
        float fieldVisualWidth = footballFieldHeight + 300;
        float fieldX = marginH + sidebarWidth + 400 + fieldVisualWidth / 2;
        drawFootballField(p5, footballfield, fieldX, p5.height / 2);

        float[] currentFb = getFieldBounds(p5);

        for(int i = 0; i < 5; i++) {
            btnJugadas[i].x = currentFb[2] + 15;
            btnJugadas[i].y = currentFb[1] + 20 + (i * (btnJugadas[i].h + 10));

            btnJugadas[i].display(p5);
            if(i == currentJugada) {
                p5.pushStyle();
                p5.noFill();
                p5.stroke(255, 0, 0);
                p5.strokeWeight(4);
                p5.rect(btnJugadas[i].x, btnJugadas[i].y, btnJugadas[i].w, btnJugadas[i].h, 10);
                p5.popStyle();
            }
        }

        // Elements actius
        // Only draw arrows whose start point is inside the football field
        float[] fb = getFieldBounds(p5);
        fb[0] -= 70; // expand left
        fb[2] += 70; // expand right
        for (TacticalArrow a : jugadas.get(currentJugada).activeArrows) {
            if (a.start.x >= fb[0] && a.start.x <= fb[2] &&
                    a.start.y >= fb[1] && a.start.y <= fb[3]) {
                // Clamp end to field bounds before drawing
                a.end.x = p5.constrain(a.end.x, fb[0], fb[2]);
                a.end.y = p5.constrain(a.end.y, fb[1], fb[3]);
                a.display(p5);
            }
        }
        if (currentArrow != null) {
            currentArrow.display(p5);
        }

        for (DraggableImage d : jugadas.get(currentJugada).activeImages) {
            d.display(p5);
        }

        // panel esquerra
        float rX = marginH + sidebarWidth + 30;
        float rY = 150;
        float rSize = 60;

        float rectX = rX - 10;
        float rectY = rY + 200;
        float rectW = (rSize + 20) * 3;
        int cols = 3;
        float cellSize = rectW / cols;
        int rows = (int) Math.ceil(resources.size() / (float) cols);
        float rectH = rows * cellSize + 170;

        p5.pushStyle();
        p5.fill(200, 100);
        p5.noStroke();
        p5.rect(rectX, rectY, rectW, rectH, 10);
        p5.popStyle();

        p5.imageMode(PConstants.CENTER);
        for (int i = 0; i < resources.size(); i++) {
            int col = i % cols;
            int row = i / cols;
            float imgX = rectX + col * cellSize + cellSize / 2;
            float imgY = rectY + row * cellSize + cellSize / 2 + 5;
            PImage icon = resources.get(i);
            p5.image(icon, imgX, imgY, cellSize - 10, cellSize - 10);
        }

        // Draw Eraser toggle below the panel
        btnEraser.x = rectX;
        btnEraser.y = rectY + rectH + 20;
        btnEraser.w = rectW;
        btnEraser.h = 40;
        btnEraser.textBoto = eraserMode ? "Eraser: ON" : "Eraser: OFF";
        btnEraser.display(p5);

    }

    // Interactccions amb pantalla jugadas
    public void checkMousePressedJugadas(PApplet p5){
        for(int i = 0; i < 5; i++) {
            if(btnJugadas[i].mouseOverButton(p5)) {
                currentJugada = i;
                return;
            }
        }

        if(btnEraser.mouseOverButton(p5)){
            eraserMode = !eraserMode;
            return;
        }

        if(eraserMode){
            // 1. check images
            for(int i=jugadas.get(currentJugada).activeImages.size()-1; i>=0; i--){
                if(jugadas.get(currentJugada).activeImages.get(i).contains(p5.mouseX, p5.mouseY)){
                    jugadas.get(currentJugada).activeImages.remove(i);
                    return;
                }
            }
            // 2. check arrows
            for(int i=jugadas.get(currentJugada).activeArrows.size()-1; i>=0; i--){
                TacticalArrow a = jugadas.get(currentJugada).activeArrows.get(i);
                float d = distToSegment(p5.mouseX, p5.mouseY, a.start.x, a.start.y, a.end.x, a.end.y);
                if(d < 15){
                    jugadas.get(currentJugada).activeArrows.remove(i);
                    return;
                }
            }
            return; // If in eraser mode, prevent normal click actions
        }

        float rX = marginH + sidebarWidth + 30;
        float rY = 150;
        float rSize = 60;

        float rectX = rX - 10;
        float rectY = rY + 200;
        float rectW = (rSize + 20) * 3;
        int cols = 3;
        float cellSize = rectW / cols;

        for(int i = 0; i < resources.size(); i++){
            int col = i % cols;
            int row = i / cols;
            float imgX = rectX + col * cellSize + cellSize / 2;
            float imgY = rectY + row * cellSize + cellSize / 2 + 5;
            if(p5.dist(p5.mouseX, p5.mouseY, imgX, imgY) < cellSize / 2){
                PImage original = resources.get(i);
                DraggableImage newImg = new DraggableImage(original, p5.mouseX, p5.mouseY, 50, 50 * ((float)original.height / original.width));
                jugadas.get(currentJugada).activeImages.add(newImg);
                currentDrag = newImg;
                currentDrag.startDrag();
                return;
            }
        }

        // Check Existing Images
        for(int i=jugadas.get(currentJugada).activeImages.size()-1; i>=0; i--){
            DraggableImage d = jugadas.get(currentJugada).activeImages.get(i);
            if(d.contains(p5.mouseX, p5.mouseY)){
                currentDrag = d;
                currentDrag.startDrag();
                return; // Handled
            }
        }

        // Field (Arrow) — only start inside the football field
        float[] fb = getFieldBounds(p5);
        fb[0] -= 70;
        fb[2] += 70;
        if(p5.mouseX >= fb[0] && p5.mouseX <= fb[2] &&
           p5.mouseY >= fb[1] && p5.mouseY <= fb[3]){
            arrowStart = new PVector(p5.mouseX, p5.mouseY);
            currentArrow = new TacticalArrow(arrowStart, new PVector(p5.mouseX, p5.mouseY), p5.color(0));
        }
    }

    public void checkMouseDraggedJugadas(PApplet p5){
        if(currentDrag != null){
            currentDrag.setPosition(p5.mouseX, p5.mouseY);
        } else if (currentArrow != null){
            // Clamp endpoint to field bounds
            float[] fb = getFieldBounds(p5);
            fb[0] -= 70;
            fb[2] += 70;
            float ex = p5.constrain(p5.mouseX, fb[0], fb[2]);
            float ey = p5.constrain(p5.mouseY, fb[1], fb[3]);
            currentArrow.setEnd(new PVector(ex, ey));
        }
    }

    public void checkMouseReleasedJugadas(PApplet p5){
        if(currentDrag != null){
            currentDrag.stopDrag();
            currentDrag = null;
        }
        if(currentArrow != null){
            // Si fletxa molt petita ingnorar
            if(PApplet.dist(currentArrow.start.x, currentArrow.start.y, currentArrow.end.x, currentArrow.end.y) > 10){
                jugadas.get(currentJugada).activeArrows.add(currentArrow);
            }
            currentArrow = null;
            arrowStart = null;
        }
    }

    public void dibujoPantallEntrenamientos(PApplet p5){
        p5.imageMode(PConstants.CORNER);
        p5.image(fondoPantalla, 0, 0, p5.width, p5.height);
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
        p5.text("ENTRENAMIENTOS DEL MES", p5.width/2+100, p5.height/2-365);
        p5.popMatrix();
    }

    public void dibujoPantallaAlerta(PApplet p5){
        p5.imageMode(PConstants.CORNER);
        p5.image(fondoPantalla, 0, 0, p5.width, p5.height);
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

    /**
     * Returns the axis-aligned bounding box of the football field as drawn on screen.
     * Because the image is rotated HALF_PI the visual w/h axes are swapped.
     * Returns float[] { left, top, right, bottom }.
     */
    float[] getFieldBounds(PApplet p5){
        float fieldVisualWidth  = footballFieldHeight + 500; // rotated: original H becomes screen W
        float fieldVisualHeight = footballFieldWidth  + 100; // rotated: original W becomes screen H
        float fx = marginH + sidebarWidth + 400 + fieldVisualWidth / 2;
        float fy = p5.height / 2f;
        return new float[]{
            fx - fieldVisualWidth  / 2f,  // left
            fy - fieldVisualHeight / 2f,  // top
            fx + fieldVisualWidth  / 2f,  // right
            fy + fieldVisualHeight / 2f   // bottom
        };
    }


    public static void sideBar(PApplet p5){
        p5.noStroke();
        p5.fill (paleta.getColorAt(4));
        p5.rect(marginH, 2*marginV + 100, sidebarWidth, sidebarHeight, 20);
    }

    float distToSegment(float px, float py, float x1, float y1, float x2, float y2) {
        float l2 = PApplet.dist(x1, y1, x2, y2);
        l2 = l2 * l2;
        if (l2 == 0) return PApplet.dist(px, py, x1, y1);
        float t = ((px - x1) * (x2 - x1) + (py - y1) * (y2 - y1)) / l2;
        t = Math.max(0, Math.min(1, t));
        float projX = x1 + t * (x2 - x1);
        float projY = y1 + t * (y2 - y1);
        return PApplet.dist(px, py, projX, projY);
    }

   }
