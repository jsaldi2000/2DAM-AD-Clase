package practica19.db;

import practica19.javabeans.Producto;

public class GeneradorProductosPrueba {

    // Crear una instancia de AccesoProductosDB
    private static final AccesoProductosDB accesoDB = new AccesoProductosDB();

    public static void main(String[] args) {
        insertarProductosPrueba();
    }

    public static void insertarProductosPrueba() {
        Producto[] productos = {
            new Producto("Smartphone Samsung Galaxy S23", 899.99, 20, 0.168, 15.1, "EAN1234567891", 95.0),
            new Producto("Laptop MacBook Air M2", 1199.99, 10, 1.24, 30.4, "EAN1234567892", 98.0),
            new Producto("Auriculares Sony WH-1000XM5", 349.99, 15, 0.25, 18.0, "EAN1234567893", 90.0),
            new Producto("Cámara Canon EOS R50", 799.99, 5, 0.9, 12.5, "EAN1234567894", 85.0),
            new Producto("Monitor LG UltraGear 27'", 349.99, 8, 3.6, 68.0, "EAN1234567895", 88.0),
            new Producto("Reloj Inteligente Apple Watch Series 9", 449.99, 12, 0.06, 4.5, "EAN1234567896", 97.0),
            new Producto("Consola PlayStation 5", 499.99, 6, 4.5, 39.0, "EAN1234567897", 99.0),
            new Producto("Tablet iPad Pro 11'", 799.99, 7, 0.47, 24.7, "EAN1234567898", 94.0),
            new Producto("Bicicleta Eléctrica Xiaomi Mi Smart", 999.99, 3, 23.0, 180.0, "EAN1234567899", 92.0),
            new Producto("Aspiradora Robot Roomba 980", 649.99, 5, 4.0, 35.3, "EAN1234567810", 89.0)
        };

        for (int i = 0; i < productos.length; i++) {
            accesoDB.insertarProducto(productos[i]);
            System.out.printf("Producto %d insertado: %s - %.2f €\n", i + 1, productos[i].getNombre(), productos[i].getPrecio());
        }

        System.out.println("10 productos insertados con éxito.");
        accesoDB.cerrarDB();
    }
}
