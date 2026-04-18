package com.mycompany.restauranteelbuensabor;

public class CalculadorFactura {

    private static final double TASA_IVA            = 0.19;
    private static final double TASA_PROPINA        = 0.10;
    private static final double TASA_DESCUENTO      = 0.05;
    private static final double UMBRAL_PROPINA      = 50000;
    private static final int    MIN_ITEMS_DESCUENTO = 3;


    public static double calcularSubtotal() {
        double subtotal = 0;
        int indice = 0;
        while (indice < Carta.nombres.length) {
            if (PedidoActual.cantidades[indice] > 0) {
                subtotal = subtotal + Carta.precios[indice] * PedidoActual.cantidades[indice];
            }
            indice++;
        }
        return subtotal;
    }

    public static double aplicarDescuento(double subtotal) {
        int cantidadItems = 0;
        int indice = 0;
        while (indice < PedidoActual.cantidades.length) {
            if (PedidoActual.cantidades[indice] > 0) {
                cantidadItems = cantidadItems + 1;
            }
            indice++;
        }
        if (cantidadItems > MIN_ITEMS_DESCUENTO) {
            return subtotal - (subtotal * TASA_DESCUENTO);
        }
        return subtotal;
    }
    public static double calcularIVA(double base) {
        return base * TASA_IVA;
    }

    public static double calcularPropina(double base) {
        if (base > UMBRAL_PROPINA) {
            return base * TASA_PROPINA;
        }
        return 0;
    }

    public static double calcularTotal() {
        double subtotal         = calcularSubtotal();
        double conDescuento     = aplicarDescuento(subtotal);
        double iva              = calcularIVA(conDescuento);
        double totalConIva      = conDescuento + iva;
        double propina          = calcularPropina(totalConIva);
        return totalConIva + propina;
    }
}
