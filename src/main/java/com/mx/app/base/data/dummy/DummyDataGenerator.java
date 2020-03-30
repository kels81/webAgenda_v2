package com.mx.app.base.data.dummy;

import com.mx.app.base.component.HorarioDia;
import com.mx.app.base.data.Status;
import com.mx.app.base.domain.DiasSemana;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public abstract class DummyDataGenerator {

    private final DataProviderStatic data = new DataProviderStatic();
    private static final ArrayList<Integer> numeros = new ArrayList<>();

    // [ PACIENTE ]
    public static String randomNombrePaciente() {
        String name;
        if (Math.random() < 0.3) {
            name = getNombresMasculino()[(int) (Math.random() * getNombresMasculino().length)];
        } else {
            name = getNombresFemenino()[(int) (Math.random() * getNombresFemenino().length)];
        }
        return name;
    }

    public static String randomApellidos() {
        String[] names = {"Castro", "Angeles", "Urrutia", "Velasco",
            "Hernández", "García", "González", "Martínez", "Rodríguez", "Sánchez",
            "Cruz", "Flores", "Gomez", "Castillo", "Reyes", "Jimenez",
            "Morales", "Vazquez", "Díaz", "Gutierrez", "Cortés", "Pereyra",
            "Rojas", "Molina", "Ortiz", "Silva", "Nuñez", "Luna",
            "Juárez", "Cabrera", "Ríos", "Vega", "Ojeda", "Ponce",
            "Villalba", "Cardozo", "Navarro", "Coronel", "Vázquez", "Rivera",
            "Ramos", "Vargas", "Figueroa", "Correa", "Maldonado", "Miranda"};
        return names[(int) (Math.random() * names.length)];
    }

    public static String randomGenero() {
        String[] names = DummyDataStatic.genero();
        return names[(int) (Math.random() * names.length)];
    }

    public static String findGenero(String nombre) {
        String genero;
        if (Arrays.asList(getNombresMasculino()).contains(nombre)) {
            genero = DummyDataStatic.genero()[1];
        } else {
            genero = DummyDataStatic.genero()[0];
        }
        return genero;
    }

    public static String randomMailCompany() {
        String[] names = {"gmail", "hotmail", "yahoo", "outlook"};
        return names[(int) (Math.random() * names.length)];
    }

    public static LocalDate randomFechaNacimiento() {
        Random r = new Random();
        int daysOld = 0 - r.nextInt(365 * 15 + 365 * 60);
        return LocalDate.now().plusDays(daysOld);
    }

    public static Status randomStatus() {
        Random r = new Random();
        return Status.values()[r.nextInt(Status.values().length)];
    }

    public static String randomLugarNacimiento() {
        String[] estados = DummyDataStatic.estados();
        return estados[(int) (Math.random() * estados.length)];
    }

    public static int randomNumeroTelefonico(int length) {
        //Result Donde guardo el Resultado Length variable que indica el tamaño de mi numero.    
        int result = 0;
        //Array donde guardo los randoms    
        int[] arrayRandom = new int[length];
        //Variable donde genero numeros por separado    
        int pos;
        //Variable donde eligo el Rango de Numeros     
        int nNums = 9;//En este caso del 1 al 9.
        //Creo un objeto Pila 
        Stack<Integer> pila = new Stack<>();
        //For para generar los numeros
        for (int i = 0; i < arrayRandom.length; i++) {
            //Genero un numero random    
            pos = (int) Math.floor(Math.random() * nNums + 1);
            //Lo guardo en el array
            arrayRandom[i] = pos;

            //Si la pila ya contiene el numero 
            while (pila.contains(pos)) {
                //Vuelvo a generar un numero random  hasta que no se repita   
                pos = (int) Math.floor(Math.random() * nNums);
                arrayRandom[i] = pos;
            }
            //Guardo en el Stack (pila)
            pila.push(pos);
        }
        //y los muestro
        //System.out.println("Núm. aleatorios sin repetición:");
//        System.out.println(pila.toString());

        //Lo convierto a un solo numero entero
        //Variable para Conseguir decena/centena/ Etc...
        int multiplicador = 1;
        //Segun el tamaño del numero (length)  
        for (int i = 1; i < length; i++) {
            multiplicador = multiplicador * 10;
        }
        //Convierto el Array de numeros aleatorios en un solo entero      
        for (int i = 0; i < length; i++) {
            //Multiplicando por el mas alto     
            result += (arrayRandom[i] * multiplicador);
            //Decremento el multiplicador        
            multiplicador = multiplicador / 10;
        }
        return result;
    }

    public static String formatTelefono(int numTelefonico, boolean isTelFijo) {
//        return String.valueOf(telefono).replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");  //(123) 456-7890
        String strTelefono = (isTelFijo ? "" : "55").concat(String.valueOf(numTelefonico));
        String strRegexTelefono = isTelFijo ? "(\\d{4})(\\d{4})" : "(\\d{2})(\\d{4})(\\d+)";
        String strReplaceTelefono = isTelFijo ? "$1-$2" : "$1-$2-$3";
        return strTelefono.replaceFirst(strRegexTelefono, strReplaceTelefono);
    }

    public static String eliminarAcentos(String nombre) {
        String normalized = Normalizer.normalize(nombre, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    static String[] getNombresMasculino() {
        String[] namesMale = {"David", "Miguel", "Juan", "Luis", "Bernardo",
            "Jorge", "Matias", "Pedro", "Federico", "Mario", "Jesus",
            "Raymundo", "Mauricio"};
        return namesMale;
    }

    static String[] getNombresFemenino() {
        String[] namesFemale = {"Katherine", "Ana", "Mirna", "Elisa", "Katia",
            "Tania", "Kim", "Samantha", "Linda", "Sara", "Sandra", "Valentina",
            "Emma", "Maria", "Carolina", "Karla", "Roxana", "Ofelia"};
        return namesFemale;
    }

    public static String getAvatar(String genero) {
        String masculino = "Masculino";
        String avatar = genero.equals(masculino) ? "man-" : "woman-";

        Random random = new Random();
        int low = 1;
        int high = genero.equals(masculino) ? 36 : 14;  //ESTOS NUMEROS DEPENDEN DE LOS NOMBRES DE LOS ARCHIVOS SVG
        int result = random.nextInt(high - low) + low;
//        System.out.println("result = " + result);

        if (!numeros.contains(result)) {
            numeros.add(result);
        }

        avatar += String.valueOf(result).concat(".svg");
        //avatar += String.valueOf(numeros.get(numeros.size()-1)).concat(".svg");
        return avatar;
    }

    // [ CONSULTORIO ]
    public static String randomNombreConsultorio() {
        String[] names = {"San Angel", "Condesa", "Coyoacan", "Del Valle"};
        return names[(int) (Math.random() * names.length)];
    }

    public static Map<String, List<LocalTime>> randomHorariosDia() {
        //Result Donde guardo el Resultado Length variable que indica el tamaño de mi numero.    
        Map<String, List<LocalTime>> mapDiasHoras = new HashMap<>();
        String dia = "";
        
        //Array donde guardo los randoms    
        int[] arrayDias = getArrayRandom(2, "dias");  //GENERAR NUMERO DE DIAS POR CONSULTORIO
        for (int i = 0; i < arrayDias.length; i++) {
            dia = new DiasSemana().getDiasSemana().get(arrayDias[i]).getDia();
            List<LocalTime> lstHorarios = new ArrayList<>(); 
            int[] arrayHoras = getArrayRandom(0, "horas");      //GENERAR NUMERO DE HORARIOS POR DIA EN EL CONSULTORIO
            for (int j = 0; j < arrayHoras.length; j++) {
                int hora = arrayHoras[j];
                while (!lstHorarios.contains(LocalTime.of(hora, 0))) {
                    lstHorarios.add(LocalTime.of(hora, 0));
                }
            }
            Collections.sort(lstHorarios);
            mapDiasHoras.put(dia, lstHorarios);
        }

        return mapDiasHoras;
    }

    private static int[] getArrayRandom(int length, String bnd) {
        Random random = new Random();
        int low = 1;
        int high = 5;
        int lengthHrs = random.nextInt(high - low) + low;
        
        int min = bnd.equals("dias") ? 0 : 7;       //Index inicio de diassemana  :  Hora inicio hábil de los horarios
        int max = bnd.endsWith("dias") ? 6 : 22;    //Index fin de diassemana  :  Hora fin hábil de los horarios
        length = (length == 0 ? lengthHrs : length);
        int[] arrayRandom = new int[length];
        
        for (int i = 0; i < length; i++) {
            int idx = ThreadLocalRandom.current().nextInt(min, max);
            arrayRandom[i] = idx;
        }
        return arrayRandom;
    }

}
