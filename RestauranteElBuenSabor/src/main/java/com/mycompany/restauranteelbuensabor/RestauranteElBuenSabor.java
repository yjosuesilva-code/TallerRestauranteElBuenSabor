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
        boolean flag = true;
        int intentosInvalidos = 0;

        boolean continuar = true;
        System.out.println("========================================");
        System.out.println("    RESTAURANTE " + Carta.NOMBRE_RESTAURANTE);
        System.out.println("    " + Carta.DIRECCION);
        System.out.println("    NIT: " + Carta.NIT);
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
            int opcionMenu = sc.nextInt();
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
                            System.out.print("Ingrese numero de mesa: ");
                            Mesa.numeroMesaActual = sc.nextInt();
                            if (Mesa.numeroMesaActual > 0) {
                                Mesa.estadoMesa = 1;
                            } else {
                                Mesa.numeroMesaActual = 1;
                                Mesa.estadoMesa = 1;
                            }
                        }// fin if estadoMesa==0
                        // agrega al pedido
                        PedidoActual.cantidades[numeroProducto - 1] = PedidoActual.cantidades[numeroProducto - 1] + cantidad;
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + Carta.nombres[numeroProducto - 1] + " x" + cantidad);
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
                }// fin if hayProductosEnPedido
                System.out.println();
            } else if (opcionMenu == 4) {
                // generar factura
                System.out.println();
                if (Utilidades.hayProductosEnPedido()) {
                    ImpresionFactura.imprimirFacturaCompleta();
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                }// fin if hayProductosEnPedido
            } else if (opcionMenu == 5) {
                System.out.println();
                Utilidades.reiniciar();
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();
            } else if (opcionMenu == 0) {
                // salir
                flag = false;
                System.out.println("Hasta luego!");
            } else {
                // opcion no reconocida
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                intentosInvalidos = intentosInvalidos + 1;
                if (intentosInvalidos > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    intentosInvalidos = 0;
                }// fin if nombreRestaurante>3
            }// fin if-else opcionMenu
        }// fin while
        sc.close();
    }// fin main
}
