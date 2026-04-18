package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean ejecutando = true;
        int intentosInvalidos = 0;
        Pedido pedido = new Pedido();

        System.out.println("========================================");
        System.out.println("    RESTAURANTE " + Carta.NOMBRE_RESTAURANTE);
        System.out.println("    " + Carta.DIRECCION);
        System.out.println("    NIT: " + Carta.NIT);
        System.out.println("========================================");

        while (ejecutando) {
            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");
            int opcionMenu = scanner.nextInt();

            if (opcionMenu == 1) {
                ImpresionFactura.mostrarCarta();
                System.out.println();

            } else if (opcionMenu == 2) {
                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + Carta.cantidadProductos() + "): ");
                int numeroProducto = scanner.nextInt();
                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                if (numeroProducto > 0 && numeroProducto <= Carta.cantidadProductos()) {
                    if (cantidad > 0) {
                        if (Mesa.estadoMesa == 0) {
                            System.out.print("Ingrese numero de mesa: ");
                            Mesa.numeroMesaActual = scanner.nextInt();
                            if (Mesa.numeroMesaActual > 0) {
                                Mesa.estadoMesa = 1;
                            } else {
                                Mesa.numeroMesaActual = 1;
                                Mesa.estadoMesa = 1;
                            }
                        }
                        Producto productoSeleccionado = Carta.getProducto(numeroProducto - 1);
                        pedido.agregarItem(productoSeleccionado, cantidad);
                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + productoSeleccionado.getNombre() + " x" + cantidad);
                    } else {
                        if (cantidad == 0) {
                            System.out.println("La cantidad no puede ser cero.");
                        } else {
                            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
                        }
                    }
                } else {
                    if (numeroProducto <= 0) {
                        System.out.println("El numero debe ser mayor a cero.");
                    } else {
                        System.out.println("Producto no existe. La carta tiene " + Carta.cantidadProductos() + " productos.");
                    }
                }
                System.out.println();

            } else if (opcionMenu == 3) {
                System.out.println();
                if (pedido.tieneProductos()) {
                    ImpresionFactura.mostrarPedido(pedido);
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                }
                System.out.println();

            } else if (opcionMenu == 4) {
                System.out.println();
                if (pedido.tieneProductos()) {
                    Factura factura = new Factura(pedido, Carta.numeroFactura);
                    ImpresionFactura.imprimirFacturaCompleta(factura);
                    Carta.numeroFactura = Carta.numeroFactura + 1;
                    Mesa.estadoMesa = 0;
                    pedido.limpiar();
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                }

            } else if (opcionMenu == 5) {
                System.out.println();
                pedido.limpiar();
                Mesa.estadoMesa = 0;
                Mesa.numeroMesaActual = 0;
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();

            } else if (opcionMenu == 0) {
                ejecutando = false;
                System.out.println("Hasta luego!");

            } else {
                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                intentosInvalidos = intentosInvalidos + 1;
                if (intentosInvalidos > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    intentosInvalidos = 0;
                }
            }
        }

        scanner.close();
    }
}