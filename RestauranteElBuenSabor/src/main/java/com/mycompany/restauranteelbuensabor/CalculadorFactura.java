package com.mycompany.restauranteelbuensabor;

public class CalculadorFactura {
    public static double calcularTotalFactura() {
        double sub = 0;
        double iva = 0;
        double tot = 0;
        double aux = 0;
        int cont = 0;
        int i = 0;
        while (i < Carta.nombres.length) {
            if (PedidoActual.cantidades[i] > 0) {
                // multiplica precio por cantidad
                sub = sub + Carta.precios[i] * PedidoActual.cantidades[i];
                cont = cont + 1;
            }
            i++;
        }// fin while
        if (cont > 3) {
            if (sub > 0) {
                aux = sub - (sub * 0.05);
                if (aux > 50000) {
                    iva = aux * 0.19;
                    // suma iva al subtotal con descuento
                    tot = aux + iva;
                    tot = tot + (tot * 0.10);
                } else {
                    // suma iva al subtotal
                    iva = aux * 0.19;
                    tot = aux + iva;
                }
            }// fin if sub>0
// version anterior - no borrar
// sub = sub * 1.19;
// if(sub > 40000) sub = sub + (sub*0.10);
// return sub;
        } else {
            if (sub > 50000) {
                iva = sub * 0.19;
                // suma iva al subtotal
                tot = sub + iva;
                tot = tot + (tot * 0.10);
            } else {
                iva = sub * 0.19;
                tot = sub + iva;
            }
        }// fin if-else cont
        Mesa.estadoMesa = 1;
        PedidoActual.total = tot;
        return tot;
    }
}
