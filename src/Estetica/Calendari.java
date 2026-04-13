package Estetica;

import processing.core.PApplet;
import java.util.Calendar;

public class Calendari {
    // Textos representatius dels mesos
    String[] months = {"Jan","Feb","Mar","Apr","May","Jun",
            "Jul","Aug","Sep","Oct","Nov","Dec"};

    // Informació del calendari
    int any, mes, dia;
    int numDaysMonth, numDaysPrevMonth;
    int dayOfWeek, firstDay;

    // Data seleccionada
    public java.util.ArrayList<String> selectedDates = new java.util.ArrayList<String>();

    public boolean isDateSelected(int d, int m, int y){
        return selectedDates.contains(d + "/" + m + "/" + y);
    }
    public void toggleSelectedDate(int d, int m, int y){
        String val = d + "/" + m + "/" + y;
        if(selectedDates.contains(val)){
            selectedDates.remove(val);
        } else {
            selectedDates.add(val);
        }
    }

    // Calendari actual, i del mes anterior
    Calendar cal, cPrev;

    // Botons del calendari
    DayButtons[] buttons;

    // Dimensions del calendari
    int x, y, w, h;


    // Constructor
    public Calendari(int x, int y, int w, int h){

        this.buttons = new DayButtons[37];

        this.cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);

        this.any = cal.get(Calendar.YEAR);
        this.mes = cal.get(Calendar.MONTH) + 1;
        this.dia = cal.get(Calendar.DATE);

        this.numDaysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==Calendar.SUNDAY){ this.dayOfWeek = 6; }
        else { this.dayOfWeek  = this.dayOfWeek - 2; }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        cPrev = Calendar.getInstance();
        setPrevCalendar(1, this.mes-2, this.any);

        this.numDaysPrevMonth = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.x = x; this.y = y; this.w = w; this.h = h;
        createCalendar(x, y, w, h);
    }

    // Getters
    public boolean isDateSelected(){
        return this.selectedDates.size() > 0;
    }
    public String getSelectedDate(){
        return String.join(", ", this.selectedDates);
    }


    // Setters

    public void setCalendar(int d, int m, int y){
        cal.set(Calendar.YEAR, y);
        cal.set(Calendar.MONTH, m);
        cal.set(Calendar.DATE, d);
    }

    public void setPrevCalendar(int d, int m, int y){
        cPrev.set(Calendar.YEAR, y);
        cPrev.set(Calendar.MONTH, m);
        cPrev.set(Calendar.DATE, d);
    }

    public void setSelectedDate(int d, int m, int y){
        String val = d + "/" + m + "/" + y;
        if(!selectedDates.contains(val)){
            selectedDates.add(val);
        }
    }

    // Va un mes enrera en el Calendari
    public void prevMonth(){

        this.buttons = new DayButtons[37];

        this.mes --;
        if(this.mes==0){
            this.mes = 12;
            this.any--;
        }
        setCalendar(this.dia, this.mes -1, this.any);

        this.numDaysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==Calendar.SUNDAY){ this.dayOfWeek = 6; }
        else { this.dayOfWeek  = this.dayOfWeek - 2; }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        setPrevCalendar(1, this.mes -2, this.any);
        this.numDaysPrevMonth = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        createCalendar(x, y, w, h);
    }

    public void createCalendar(int x, int y, int w, int h){

        float dayWidth  = w / 7;
        float dayHeight = h / 6;
        int numDia = 1;
        int f = 0, nb = 0;

        while(numDia<=numDaysMonth){

            if(firstDay!=1 && f==0){
                int cPrev=0;
                for(int p=firstDay, c=0; p<=numDaysPrevMonth; p++, c++){
                    buttons[nb] = new DayButtons(x + c*dayWidth, y + f*dayHeight, dayWidth, dayHeight, p, mes, any);
                    buttons[nb].setEnabled(false);
                    cPrev++; nb++;
                }
                for(int c=cPrev; c<7; c++){
                    buttons[nb] = new DayButtons(x + c*dayWidth, y + f*dayHeight, dayWidth, dayHeight, numDia, mes, any);
                    buttons[nb].setSelected(isDateSelected(numDia, mes, any));
                    numDia++; nb++;
                }
                f++;
            }
            else {
                for(int c=0; c<7; c++){
                    buttons[nb] = new DayButtons(x + c*dayWidth, y + f*dayHeight, dayWidth, dayHeight, numDia, mes, any);
                    buttons[nb].setSelected(isDateSelected(numDia, mes, any));
                    numDia++; nb++;
                    if(numDia>numDaysMonth){ break; }
                }
                f++;
            }
        }
    }

    // Va un mes endavant en el calendari
    public void nextMonth(){

        this.buttons = new DayButtons[37];

        this.mes ++;
        if(this.mes==13){
            this.mes = 1;
            this.any++;
        }
        setCalendar(this.dia, this.mes-1, this.any);

        this.numDaysMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

        this.dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
        if(dayOfWeek==Calendar.SUNDAY){ this.dayOfWeek = 6; }
        else { this.dayOfWeek  = this.dayOfWeek - 2; }

        cal.set(Calendar.DAY_OF_WEEK, cal.getFirstDayOfWeek());
        this.firstDay = cal.get(Calendar.DATE);

        setPrevCalendar(1, this.mes-2, this.any);

        this.numDaysPrevMonth = cPrev.getActualMaximum(Calendar.DAY_OF_MONTH);

        createCalendar(x, y, w, h);
    }



    // Dibuixa el Calendari
    public void display(PApplet p5){
        p5.pushStyle();
        p5.fill(0); p5.textSize(36); p5.textAlign(p5.LEFT);
        p5.text(months[mes-1]+"/"+any, x+30, y - 30);
        for(DayButtons b : buttons){
            if(b!=null){
                b.display(p5);
            }
        }

        if(selectedDates.size() > 0){
            String dateText = (selectedDates.size() == 1) ? selectedDates.get(0) : selectedDates.size() + " days selected";
            p5.fill(0); p5.textSize(24); p5.textAlign(p5.RIGHT);
            p5.text(dateText, x+w, y - 30);
        }
        p5.popStyle();
    }


    // Comprova si pitjam sobre els botons del Calendari
    public  void checkButtons(PApplet p5){
        for(DayButtons b : buttons){
            if((b!=null)&&(b.enabled)&&(b.mouseOver(p5))){
                toggleSelectedDate(b.dia, b.mes, b.any);
                b.setSelected(!b.selected);
            }
        }
    }

    // Deselecciona tots els botons del Calendari
    public void deselectAll(){
        for(DayButtons b : buttons){
            if(b!=null){
                b.setSelected(false);
            }
        }
    }
}
