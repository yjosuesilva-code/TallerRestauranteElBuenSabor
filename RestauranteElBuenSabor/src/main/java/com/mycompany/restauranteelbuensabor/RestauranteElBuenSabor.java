/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

/**
 *
 * @author alfre
 */
public class RestauranteElBuenSabor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcionMenu = 0;
        boolean flag = true;
        int x = 0;
        String aux = "";
        int tmp = 0;
        double m = 0;
        boolean continuar = true;
        System.out.println("========================================");
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println("========================================");
        while (flag) {
            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");
            opcionMenu = sc.nextInt();
            if (opcionMenu == 1) {
                // mostrar carta
                ImpresionFactura.mostrarCarta();
                System.out.println();
            } else if (opcionMenu == 2) {
                // agregar producto
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + Carta.nombres.length + "): ");
                int numeroProducto = sc.nextInt();
                System.out.print("Cantidad: ");
                int cantidad = sc.nextInt();
                if (numeroProducto > 0 && numeroProducto <= Carta.nombres.length) {
                    if (cantidad > 0) {
                        if (Mesa.estadoMesa == 0) {
                            // mesa no activa - pedir numero de mesa
                            System.out.print("Ingrese numero de mesa: ");
                            Mesa.numeroMesaActual = sc.nextInt();
                            if (Mesa.numeroMesaActual > 0) {
                                Mesa.estadoMesa = 1;
                                aux = String.valueOf(Mesa.numeroMesaActual);
                                tmp = Mesa.numeroMesaActual;
                                x = tmp + 1;
                            } else {
                                // mesa invalida pero se continua igual
                                Mesa.numeroMesaActual = 1;
                                Mesa.estadoMesa = 1;
                                aux = "1";
                                tmp = 1;
                                x = 2;
                            }// fin if ms>0
                        }// fin if estadoMesa==0
                        // agrega al pedido
                        PedidoActual.cantidades[numeroProducto - 1] = PedidoActual.cantidades[numeroProducto - 1] + cantidad;
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + Carta.nombres[numeroProducto - 1] + " nombreRestaurante" + cantidad);
                        m = Carta.precios[numeroProducto - 1] * cantidad;
                    } else {
                        if (cantidad == 0) {
                            // cantidad es cero
                            System.out.println("La cantidad no puede ser cero.");
                        } else {
                            // cantidad negativa
                            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
                        }
                    }// fin if cantidad>0
                } else {
                    if (numeroProducto <= 0) {
                        System.out.println("El numero debe ser mayor a cero.");
                    } else {
                        System.out.println("Producto no existe. La carta tiene " + Carta.nombres.length + " productos.");
                    }
                }// fin if numeroProducto>0
                System.out.println();
            } else if (opcionMenu == 3) {
                // ver pedido actual
                System.out.println();
                if (Utilidades.hayProductosEnPedido()) {
                    ImpresionFactura.mostrarPedido();
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                    continuar = true;
                }// fin if hayProductosEnPedido
                System.out.println();
            } else if (opcionMenu == 4) {
                // generar factura
                System.out.println();
                if (Utilidades.hayProductosEnPedido()) {
                    double r = 0;
                    // procesar pedido y generar total
                    r = CalculadorFactura.calcularTotalFactura();
                    tmp = (int) r;
                    aux = "Total calculado: $" + tmp;
                    m = r;
                    // imprimir factura detallada
                    ImpresionFactura.imprimirFacturaCompleta();
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                    // reiniciar variables locales
                    tmp = 0;
                    aux = "";
                    m = 0;
                    continuar = true;
                }// fin if hayProductosEnPedido
            } else if (opcionMenu == 5) {
                // nueva mesa - reiniciar pedido
                System.out.println();
                Utilidades.reiniciar();
                // limpiar variables locales del main
                x = 0;
                tmp = 0;
                aux = "";
                m = 0;
                continuar = true;
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();
            } else if (opcionMenu == 0) {
                // salir
                flag = false;
                System.out.println("Hasta luego!");
            } else {
                // opcion no reconocida
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                Scanner sc2 = new Scanner(System.in);
                x = x + 1;
                if (x > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    x = 0;
                    // limpiar buffer con segundo scanner - innecesario
                    String s2 = sc2.hasNextLine() ? sc2.nextLine() : "";
                }// fin if nombreRestaurante>3
            }// fin if-else opcionMenu
        }// fin while
        sc.close();
    }// fin main
}
