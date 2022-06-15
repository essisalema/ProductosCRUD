

package ProductoCRUD;

import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class vistaProducto {
    public static void main(String[] args) {
        Scanner leer = new Scanner(System.in);
        List<Producto>nuevalista = Producto.getProductos();
        int opcion;
        do{
        System.out.println("1)  Crear Producto ");
        System.out.println("2)  Actualizar Producto");
        System.out.println("3)  EiminarProducto");
        System.out.println("4)  Listar Producto");
        System.out.println("5)  Salir");
        System.out.println("Elegir una opción: ");
        opcion = leer.nextInt();
        
        switch(opcion){
            case 1 :
            {
                try {
                    Producto.crearProducto();
                } catch (Exception ex) {
                    System.err.println(ex.getMessage());
                }
            }
            break;
            
            case 2:
            {
                try {
                    Producto.actualizarProducto();
                } catch (Exception ex) {
                   System.err.println(ex.getMessage());
                }
            }
            break;
            case 3:
            {
                try {
                    Producto.eliminarProducto();
                } catch (Exception ex) {
                       System.err.println(ex.getMessage());
                }
            }
            break;
            case 4: 
                
                for (Producto pr: nuevalista) {
                    System.out.println(pr);
                    
                }
                break;
                
            case 5:
                System.exit(0);

        }
        }while (opcion !=6);
    }

}
