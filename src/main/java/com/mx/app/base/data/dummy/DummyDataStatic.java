package com.mx.app.base.data.dummy;


public abstract class DummyDataStatic {

    static String[] estados() {
        String[] estados = { "Aguascalientes", "Baja California", "Baja California Sur", "Campeche",
                "Chiapas", "Chihuahua", "Coahuila", "Colima", "Distrito Federal", "Durango",
                "Guanajuato", "Guerrero", "Hidalgo", "Jalisco", "México", "Michoacán", "Morelos",
                "Nayarit", "Nuevo León", "Oaxaca", "Puebla", "Querétaro", "Quintana Roo", "San Luis Potosí",
                "Sinaloa", "Sonora", "Tabasco", "Tamaulipas", "Tlaxcala", "Veracruz", "Yucatán", "Zacatecas" };
        return estados;
    }

    static String[] parentesco() {
        String[] parentesco = { "Mama", "Papa", "Hijo(a)", "Hermano(a)", "Tío(a)", "Abuelo(a)", "Nieto(a)", "Esposo(a)",
                "Primo(a)", "Cuñado(a)", "Nuera", "Yerno", "Otro", "No Tiene" };
        return parentesco;
    }
    
    static String[] estadoCivil() {
        String[] edoCivil = { "Soltero(a)", "Casado(a)", "Viudo(a)", "Divorciado(a)", "Unión Libre", "Concubinato" };
        return edoCivil;
    }
    
    static String[] genero() {
        String[] genero = { "Femenino", "Masculino" };
        return genero;
    }
    
    static String[] religión() {
        String[] religion = { "Católica", "Protestante", "Evangélica", "Testigos de Jehová", "Adventista", "Mormón", "Judaísmo", "Islámica" };
        return religion;
    }
    

}