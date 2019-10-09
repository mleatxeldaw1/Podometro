/**
 * La clase modela un sencillo pod�metro que registra informaci�n
 * acerca de los pasos, distancia, ..... que una persona (hombre o mujer)
 * ha dado en una semana. 
 * 
 * @author
 *  Mitxel Leatxe
 *  1� DAW
 */ 
public class Podometro {
    /** Constantes que indican el sexo de una persona */
    private final char Hombre = 'H';
    private final char Mujer = 'M';
    /** Constantes que definen la zancada de las personas dependiendo de su sexo */
    private final double zancada_Hombre = 0.45;
    private final double zancada_Mujer = 0.41;
    /** Constante que indica el dia de la semana de estas */
    private final int SABADO = 6;
    private final int DOMINGO = 7;
    /** Atributos y variables de instancia */
    private String marca;
    private double altura;
    private char sexo;
    private double longitudZancada;
    /** Variable que dicta los pasos segun los dias */
    private int totalPasosLaborables;
    private int totalPasosSabado;
    private int totalPasosDomingo;
    /** Variable que recoge la distancia total recorrido durante la semana y el fin de semana */
    private double totalDistanciaSemana;
    private double totalDistanciaFinSemana;
    /** Tiempo utilizado y veces caminado de noche */
    private int tiempo;
    private int caminatasNoche;


    /**
     * Inicializa el pod�metro con la marca indicada por el par�metro.
     * El resto de atributos se ponen a 0 y el sexo, por defecto, es mujer
     */
    public Podometro(String queMarca) {
        marca = queMarca;
        char sexo = 'M';
        double altura = 0;
        double longitudZancada = 0;
        int totalPasosLaborales = 0;
        int totalPasosSabado = 0;
        int totalPasosDomingo = 0;
        double totalDistanciaSemana = 0;
        double totalDistanciaFinSemana = 0;
        int tiempo = 0;
        int caminatasNoche = 0;

    }

    /**
     * accesor para la marca
     *  
     */
    public String getMarca() {
        return marca;
    }

    /**
     * Simula la configuraci�n del pod�metro.
     * Recibe como par�metros la altura y el sexo de una persona
     * y asigna estos valores a los atributos correspondiente.
     * Asigna adem�s el valor adecuado al atributo longitudZancada
     * 
     * (leer enunciado)
     *  
     */
    public void configurar(double queAltura, char queSexo) {
        altura = queAltura;
        sexo = queSexo;
        if(sexo == 'H'){
            Math.ceil(longitudZancada = altura * zancada_Hombre);
        }else{
            Math.floor(longitudZancada = altura * zancada_Mujer);
        }

    }

    /**
     *  Recibe cuatro par�metros que supondremos correctos:
     *    pasos - el n� de pasos caminados
     *    dia - n� de d�a de la semana en que se ha hecho la caminata 
     *              (1 - Lunes, 2 - Martes - .... - 6 - S�bado, 7 - Domingo)
     *    horaInicio � hora de inicio de la caminata
     *    horaFina � hora de fin de la caminata
     *    
     *    A partir de estos par�metros el m�todo debe realizar ciertos c�lculos
     *    y  actualizar� el pod�metro adecuadamente  
     *   
     *   (leer enunciado del ejercicio)
     */
    public void registrarCaminata(int pasos, int dia, int horaInicio, int horaFin) {
        tiempo = (horaFin - horaInicio);
        if (horaInicio >= 2100){
            caminatasNoche++;
        }
        switch(dia){
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: 
            /** Suma los pasos dados en la caminata en dias laborales */
            totalPasosLaborables = totalPasosLaborables + pasos;
            break;
            case 6:
            /** Suma los pasos dados el sabado */
            totalPasosSabado = totalPasosSabado + pasos;
            
            break;
            case 7:
            /** Suma los pasos dados el domingo */
            totalPasosDomingo = totalPasosDomingo + pasos;
        }
        totalDistanciaSemana = ((totalPasosLaborables + totalPasosDomingo+totalPasosSabado)*longitudZancada);
        totalDistanciaFinSemana = (totalPasosDomingo + totalPasosSabado)*longitudZancada;
    }
    /**
     * Muestra en pantalla la configuraci�n del pod�metro
     * (altura, sexo y longitud de la zancada)
     * 
     * (ver enunciado)
     *  
     */
    public void printConfiguracion() {

        System.out.println("\n********************************+***"+
        "\nConfiguracion del podometro" + "\n*********************************"
            + "\nAltura: " + (altura * 0.01) + " mtos" + 
            "\nSexo: " + sexo + "\nLongitud zancada: "
            + (longitudZancada * 0.01) + " mtos");

    }

    /**
     * Muestra en pantalla informaci�n acerca de la distancia recorrida,
     * pasos, tiempo total caminado, ....
     * 
     * (leer enunciado)
     *  
     */
    public void printEstad�sticas() {
        double totalSemana = totalDistanciaSemana + totalDistanciaFinSemana;
        int horas = tiempo / 60;
        int minutos = (horas % 100); /** -(horas*40) */
        System.out.println("Estadisticas" + "\n***************************************" + 
            "\nDistancia recorrida toda la semana: " + totalSemana + " Km" + 
            "\nDistancia recorrida fin de semana: " + totalDistanciaFinSemana + " Km" + "\n" + 
            "\nN� pasos d�as laborales: " + totalPasosLaborables + 
            "\nN� pasos SABADO: " + totalPasosSabado + 
            "\nN� pasos DOMINGO: " + totalPasosDomingo + "\n" + 
            "\nN� caminatas realizadas a partir de las 21h.: " + caminatasNoche + "\n" +
            "\nTiempo total caminado en la semana: " + horas + "h. y " + minutos + "m" +
            "\nDia/s con mas pasos caminados: "+ diaMayorNumeroPasos());

    }


    /**
     *  Calcula y devuelve un String que representa el nombre del d�a
     *  en el que se ha caminado m�s pasos - "S�BADO"   "DOMINGO" o  "LABORABLES"
     */
    public String diaMayorNumeroPasos() {
        int diaConMasPasos = Math.max(totalPasosLaborables, totalPasosSabado);
        diaConMasPasos = Math.max(diaConMasPasos, totalPasosDomingo);
        String diaMayorPasos="";
        if(diaConMasPasos == totalPasosLaborables){
            diaMayorPasos= "Laborables";
        }else if(diaConMasPasos == totalPasosSabado){
            diaMayorPasos="Sabado";
        }else{
            diaMayorPasos="Domingo";
        }
       return diaMayorPasos;
        
    }

    /**
     * Restablecer los valores iniciales del pod�metro
     * Todos los atributos se ponen a cero salvo el sexo
     * que se establece a MUJER. La marca no var�a
     *  
     */    
    public void reset() {
        altura = 0;
        sexo = 'M';
        longitudZancada = 0;
        totalPasosLaborables = 0;
        totalPasosSabado = 0;
        totalPasosDomingo = 0;
        totalDistanciaSemana = 0;
        totalDistanciaFinSemana = 0;
        tiempo = 0;
        caminatasNoche = 0;

    }
}
