/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ceiba.biblioteca.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author JUAN S GOMEZ URIBE
 */
public class Utils {
    public static Date sumarDiasHabiles(Date fechaInicial, int diasHabiles) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fechaInicial);

        int diasSumados = 0;

        while (diasSumados < diasHabiles) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
            int diaSemana = calendar.get(Calendar.DAY_OF_WEEK);

            if (diaSemana != Calendar.SATURDAY && diaSemana != Calendar.SUNDAY) {
                diasSumados++;
            }
        }

        return calendar.getTime();
    }
}
