/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.restauranteelbuensabor;

/**
 *
 * @author alfre
 */
public class ImpresionFactura {

    private static final String SEPARADOR_GRUESO = "========================================";
    private static final String SEPARADOR_FINO   = "----------------------------------------";

    private static void imprimirEncabezado() {
        System.out.println(SEPARADOR_GRUESO);
        System.out.println("    RESTAURANTE " + Carta.NOMBRE_RESTAURANTE);
        System.out.println("    " + Carta.DIRECCION);
        System.out.println("    NIT: " + Carta.NIT);
        System.out.println(SEPARADOR_GRUESO);
    }
    private static void imprimirTotales(double conDescuento, double iva, double propina, double total) {
        System.out.println(SEPARADOR_FINO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", conDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", iva);
        if (propina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propina);
        }
        System.out.println(SEPARADOR_FINO);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(SEPARADOR_GRUESO);
    }

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_GRUESO);
        int i = 0;
        while (i < Carta.nombres.length) {
            System.out.printf("%d. %-22s $%,.0f%n",
                    (i + 1), Carta.nombres[i], Carta.precios[i]);
            i++;
        }
        System.out.println(SEPARADOR_GRUESO);
    }

    public static void mostrarPedido() {
        double subtotal = 0;
        int i = 0;
        System.out.println("--- PEDIDO ACTUAL ---");
        while (i < Carta.nombres.length) {
            if (PedidoActual.cantidades[i] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n",
                        Carta.nombres[i],
                        PedidoActual.cantidades[i],
                        (Carta.precios[i] * PedidoActual.cantidades[i]));
                subtotal = subtotal + Carta.precios[i] * PedidoActual.cantidades[i];
            }
            i++;
        }
        System.out.println(SEPARADOR_FINO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public static void imprimirFacturaCompleta() {
        double subtotal     = CalculadorFactura.calcularSubtotal();
        double conDescuento = CalculadorFactura.aplicarDescuento(subtotal);
        double iva          = CalculadorFactura.calcularIVA(conDescuento);
        double propina      = CalculadorFactura.calcularPropina(conDescuento + iva);
        double total        = conDescuento + iva + propina;

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", Carta.numeroFactura);
        System.out.println(SEPARADOR_FINO);

        int indice = 0;
        while (indice < Carta.nombres.length) {
            if (PedidoActual.cantidades[indice] > 0) {
                System.out.printf("%-20s x%-6d $%,.0f%n",
                        Carta.nombres[indice],
                        PedidoActual.cantidades[indice],
                        (Carta.precios[indice] * PedidoActual.cantidades[indice]));
            }
            indice++;
        }

        imprimirTotales(conDescuento, iva, propina, total);
        System.out.println("Gracias por su visita!");
        System.out.println(SEPARADOR_GRUESO);

        Carta.numeroFactura = Carta.numeroFactura + 1;
        Mesa.estadoMesa = 0;
        PedidoActual.total = total;
    }

    public static void imprimirFacturaResumen() {
        double subtotal     = CalculadorFactura.calcularSubtotal();
        double conDescuento = CalculadorFactura.aplicarDescuento(subtotal);
        double iva          = CalculadorFactura.calcularIVA(conDescuento);
        double propina      = CalculadorFactura.calcularPropina(conDescuento + iva);
        double total        = conDescuento + iva + propina;

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", Carta.numeroFactura);
        System.out.println(SEPARADOR_FINO);
        imprimirTotales(conDescuento, iva, propina, total);
    }
}
