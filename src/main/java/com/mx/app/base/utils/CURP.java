/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mx.app.base.utils;

import com.mx.app.base.data.dummy.DummyDataGenerator;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.*;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author ECortesHe
 */
public class CURP {

    public CURP() {
    }

    private static final String[] ENTIDAD_FEDERATIVA = {"AGUASCALIENTES", "BAJA CALIFORNIA", "BAJA CALIFORNIA SUR", "CAMPECHE", "COAHUILA",
        "COLIMA", "CHIAPAS", "CHIHUAHUA", "DISTRITO FEDERAL", "DURANGO", "GUANAJUATO", "GUERRERO", "HIDALGO", "JALISCO", "MEXICO", "MICHOACAN",
        "MORELOS", "NAYARIT", "NUEVO LEON", "OAXACA", "PUEBLA", "QUERETARO", "QUINTANA ROO", "SAN LUIS POTOSI", "SINALOA", "SONORA", "TABASCO",
        "TAMAULIPAS", "TLAXCALA", "VERACRUZ", "YUCATAN", "ZACATECAS", "SERV. EXTERIOR MEXICANO", "NACIDO EN EL EXTRANJERO"};

    private static final String[] ENTIDAD_FEDERATIVA_VALOR = {"AS", "BC", "BS", "CC", "CL", "CM", "CS", "CH", "DF", "DG", "GT", "GR", "HG", "JC", "MC",
        "MN", "MS", "NT", "NL", "OC", "PL", "QT", "QR", "SP", "SL", "SR", "TC", "TS", "TL", "VZ", "YN", "ZS", "SM", "NE"};

    private static final char[] VOCALS = {'A', 'E', 'I', 'O', 'U'};

    private static final Random random = new Random();

    public static String generarCURP(String primerApellido, String segundoApellido, String nombres, String sexo,
            String fechaDeNacimiento, String entidadDeNacimiento) {
        // limpiar los datos
        primerApellido = trim(DummyDataGenerator.eliminarAcentos(primerApellido));
        segundoApellido = trim(DummyDataGenerator.eliminarAcentos(segundoApellido));
        nombres = trim(nombres);
        sexo = trim(sexo);
        String valorEntidadDeNacimiento = trim(buscarEntidadFederativaValor(DummyDataGenerator.eliminarAcentos(entidadDeNacimiento)));

        // validar que los datos estan correctors
        String error = validarDatos(primerApellido, segundoApellido, nombres, sexo, fechaDeNacimiento, valorEntidadDeNacimiento);
        if (error != null) {
            try {
                throw new Exception(error);
            } catch (Exception ex) {
                Logger.getLogger(CURP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        // generar Curp
        StringBuilder curp = new StringBuilder();
        curp.append(primerApellido.charAt(0));
        curp.append(primeraVocal(primerApellido.substring(1)));
        curp.append(segundoApellido.charAt(0));
        curp.append(nombres.charAt(0));
        curp.append(fechaDeNacimiento.substring(2, 4));
        curp.append(fechaDeNacimiento.substring(5, 7));
        curp.append(fechaDeNacimiento.substring(8, 10));
        curp.append(sexo); 
        curp.append(valorEntidadDeNacimiento);
        curp.append(primeraLetra(primerApellido.substring(1)));
        curp.append(primeraLetra(segundoApellido.substring(1)));
        curp.append(primeraLetra(nombres.substring(1)));
        curp.append(0);
        curp.append(random.nextInt(10));
        return curp.toString();
    }

    private static String buscarEntidadFederativaValor(String entidadDeNacimiento) {
        int index = Arrays.asList(ENTIDAD_FEDERATIVA).indexOf(entidadDeNacimiento.toUpperCase());
        return Arrays.asList(ENTIDAD_FEDERATIVA_VALOR).get(index);
    }

    private static String trim(String s) {
        return StringUtils.trimToEmpty(s).toUpperCase();
    }

    private static char primeraLetra(String s) {
        int i = StringUtils.indexOfAnyBut(s, VOCALS);
        if (i >= 0) {
            return s.charAt(i);
        }
        return 'X';
    }

    private static char primeraVocal(String s) {
        int i = StringUtils.indexOfAny(s, VOCALS);
        if (i >= 0) {
            return s.charAt(i);
        }
        return 'X';
    }

    private static String validarDatos(String primerApellido, String segundoApellido, String nombres, String sexo,
            String fechaDeNacimiento, String entidadDeNacimiento) {
        if ("".equals(primerApellido)) {
            return "Primer apellido es obligatorio";
        }
        if (!StringUtils.isAlpha(primerApellido)) {
            return "Primer apellido no valido, caracteres validos: A-Z (incluso Ñ)";
        }

        if ("".equals(segundoApellido)) {
            return "Segundo apellido es obligatorio";
        }
        if (!StringUtils.isAlpha(segundoApellido)) {
            return "Segundo apellido no valido, caracteres validos: A-Z (incluso Ñ)";
        }

        if ("".equals(nombres)) {
            return "Nombre(s) es obligatorio";
        }
        if (!StringUtils.isAlphaSpace(nombres)) {
            return "Nombre(s) no valido, caracteres validos: A-Z (incluso Ñ)";
        }

        if (!"H".equals(sexo) && !"M".equals(sexo)) {
            return "Sexo no valido";
        }

        if ("".equals(fechaDeNacimiento)) {
            return "Fecha de nacimiento no valido";
        }

        if ("".equals(entidadDeNacimiento)) {
            return "Entidad de nacimiento es obligatorio";
        }
        if (!Arrays.asList(ENTIDAD_FEDERATIVA_VALOR).contains(entidadDeNacimiento)) {
            return "Entidad de nacimiento necesita ser la abreciacion";
        }

        return null;
    }

}
