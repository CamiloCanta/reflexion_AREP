package reflexion;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ComponentLoader {

    private static Map<String, Method> servicios = new HashMap<>();

    public static void main(String[] args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {
        cargarComponentes(args);

        System.out.println(ejecutar("/hello", "?name=camilo&sn=cantillo"));
    }

    public static String ejecutar(String ruta, String param) throws InvocationTargetException, IllegalAccessException {
        return servicios.get(ruta).invoke(null, (Object) param) + "";
    }
    public static void cargarComponentes(String[] args) throws ClassNotFoundException {
        for(String arg:args){
            Class c = Class.forName(arg);
            if(c.isAnnotationPresent(Componente.class)){
                Method[] metodos = c.getDeclaredMethods();
                for(Method m: metodos){
                    if(m.isAnnotationPresent(GetMapping.class)){
                        //Extraer el valor del parámetro
                        String ruta = m.getAnnotation(GetMapping.class).value();
                        //Extraer el nombre del método
                        System.out.println("Cargando método: " + m.getName());
                        //Crear la lista de tipos del método
                        servicios.put(ruta, m);
                        //Obtener el método
                        //Agregar método a la tabla de objetos ejecutables
                    }
                }
            }
        }
    }
}
