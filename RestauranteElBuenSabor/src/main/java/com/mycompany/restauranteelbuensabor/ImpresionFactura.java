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
    private static void imprimirTotales(Factura factura) {
        System.out.println(SEPARADOR_FINO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", factura.calcularSubtotalConDescuento());
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", factura.calcularIVA());
        if (factura.calcularPropina() > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", factura.calcularPropina());
        }
        System.out.println(SEPARADOR_FINO);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", factura.calcularTotal());
        System.out.println(SEPARADOR_GRUESO);
    }

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_GRUESO);
        Producto[] productos = Carta.getProductos();
        int indice = 0;
        while (indice < productos.length) {
            System.out.printf("%d. %-22s $%,.0f%n",
                    (indice + 1),
                    productos[indice].getNombre(),
                    productos[indice].getPrecio());
            indice++;
        }
        System.out.println(SEPARADOR_GRUESO);
    }

    public static void mostrarPedido(Pedido pedido) {
        System.out.println("--- PEDIDO ACTUAL ---");
        for (ItemPedido item : pedido.getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }
        System.out.println(SEPARADOR_FINO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", pedido.calcularSubtotal());
    }

    public static void imprimirFacturaCompleta(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumero());
        System.out.println(SEPARADOR_FINO);

        for (ItemPedido item : factura.getPedido().getItems()) {
            System.out.printf("%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal());
        }

        imprimirTotales(factura);
        System.out.println("Gracias por su visita!");
        System.out.println(SEPARADOR_GRUESO);
    }

    public static void imprimirFacturaResumen(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", factura.getNumero());
        System.out.println(SEPARADOR_FINO);
        imprimirTotales(factura);
    }
}
