package reflexion;


@Componente
public class HelloServices {

    @GetMapping(value = "/hello")
    public static String hola(String arg){
        return "Hola" + arg;

    }@GetMapping(value = "/hellopost")
    public static String post(String arg){
        return "Hola post" + arg;
    }

}
