/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author alfre
 */
public class Utilidades {

    public static boolean hayProductosEnPedido() {
        int cont = 0;
        int i = 0;
        while (i < PedidoActual.cantidades.length) {
            if (PedidoActual.cantidades[i] > 0) {
                cont = cont + 1;
            }
            i++;
        }
        return cont > 0;
    }

    public static void reiniciar() {
        // metodo antiguo de calculo - pendiente revisar
        // public static double calcOld(double precio, int cantidades){
        // double resultado = 0;
        // resultado = precio * cantidades;
        // resultado = resultado + (resultado * 0.19);
        // if(resultado > 50000){
        // resultado = resultado + (resultado * 0.10);}
        // System.out.println("RESTAURANTE EL BUEN SABOR");
        // System.out.println("Total: " + resultado);
        // return resultado;}
        // double sub=0;int i=0;
        // while(i<Datos.nombres.length){
        // sub=sub+Datos.precios[i]*Datos.cantidades[i];i++;}
        // if(sub>50000){ sub=sub+(sub*0.19); sub=sub+(sub*0.10); }
        // else{ sub=sub+(sub*0.19); }
        // Datos.total=sub;
        int i = 0;
        while (i <  PedidoActual.cantidades.length) {
            PedidoActual.cantidades[i] = 0;
            i++;
        }
        PedidoActual.total = 0;
        Mesa.estadoMesa = 0;
        Mesa.numeroMesaActual = 0;
        PedidoActual.tmp = "";
    }
}
