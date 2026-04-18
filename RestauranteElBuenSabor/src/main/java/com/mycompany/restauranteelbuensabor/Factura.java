package com.mycompany.restauranteelbuensabor;

public class Factura {

    private final Pedido pedido;
    private final int numero;

    public Factura(Pedido pedido, int numero) {
        this.pedido = pedido;
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public double calcularSubtotal() {
        return CalculadorFactura.calcularSubtotal(pedido);
    }

    public double calcularSubtotalConDescuento() {
        return CalculadorFactura.aplicarDescuento(calcularSubtotal(), pedido.contarItemsDiferentes());
    }

    public double calcularIVA() {
        return CalculadorFactura.calcularIVA(calcularSubtotalConDescuento());
    }

    public double calcularPropina() {
        return CalculadorFactura.calcularPropina(calcularSubtotalConDescuento() + calcularIVA());
    }

    public double calcularTotal() {
        return calcularSubtotalConDescuento() + calcularIVA() + calcularPropina();
    }
}