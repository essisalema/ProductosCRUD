

package ProductoCRUD;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Producto implements Serializable {

     private String id;
    private String nombre;
    private String stock;
    private double precioUnitario;
    private static List<Producto> productos = new ArrayList<>();
    private static File fichero = new File("Memoria_Producto");

    public Producto(String id, String nombre, String stock, double precioUnitario) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precioUnitario = precioUnitario;
    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", nombre=" + nombre + ", stock=" + stock + ", precioUnitario=" + precioUnitario + '}';
    }

    public static List<Producto> getProductos() {
        try {
            leerArchivo();
        } catch (IOException e) {
          Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, e);
        }
        return productos;
    }

    public static void crearProducto() throws Exception {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el ID  del Producto: ");
        String id = leer.next();
        System.out.println("Ingrese el nombre del Producto: ");
        String nombre = leer.next();
        System.out.println("Ingrese si existe  stock del Producto (SI/No) : ");
        String stock = leer.next();
        System.out.println("Ingrese el precio Unitario del Producto: ");
        double precioU = leer.nextInt();
        Producto productoRepetido = searchProductoByCodigo(id);

        if (productoRepetido != null) {
            throw new Exception("-Producto ya Existe-");
        }
        productos.add(new Producto(id, nombre, stock, precioU));
        System.out.println("-Producto Guardado-");
        escribirArchivo();
    }

    public static void eliminarProducto() throws Exception {
        Scanner leer = new Scanner(System.in);
        System.out.println("ingrese el id del Producto a Eliminar: ");
        String id = leer.next();
        Producto productoEliminar = searchProductoByCodigo(id);
        if (productoEliminar == null) {
            throw new Exception("-Producto no Existe-");
        }
        productos.remove(productoEliminar);
        System.out.println("-Producto Eliminado-");
        escribirArchivo();
    }

    public static void actualizarProducto() throws Exception {
        Scanner leer = new Scanner(System.in);
        System.out.println("Ingrese el id del Producto a Actualizar: ");
        String id = leer.next();
        Producto productoActualizar = searchProductoByCodigo(id);
        if (productoActualizar == null) {
            throw new Exception("-Producto no Existe-");
        }
        System.out.println("Ingrese el nuevo nombre del Producto: ");
        String nuevoNombre = leer.next();
        System.out.println("Ingrese el nuevo stock (SI/NO)");
        String nuevoStock = leer.next();
        System.out.println("Ingrese el nuevo precioUnitario: ");
        double nuevoPrecio = leer.nextDouble();
        productos.set(productos.indexOf(productoActualizar), new Producto(productoActualizar.id, nuevoNombre, nuevoStock, nuevoPrecio));
        System.out.println("-Producto Actualizado-");
        escribirArchivo();
    }
    
       private static Producto searchProductoByCodigo(String id) {
        for (Producto pr : productos) {
            if (pr.id.equalsIgnoreCase(id)) {
                return pr;
            }

        }
        return null;
    }

    private static void escribirArchivo() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fichero));
            oos.writeObject(productos);
        } catch (FileNotFoundException e) {
          Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
          Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, e);
        }

    }

    private static void leerArchivo() throws IOException {
        if (!fichero.exists()) {
            fichero.createNewFile();
        }
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fichero));
            while (true) {
                productos = (List<Producto>) ois.readObject();
            }
        } catch (EOFException e) {
        } catch (ClassNotFoundException e) {
          Logger.getLogger(Producto.class.getName()).log(Level.SEVERE, null, e);
        }
    }


}
