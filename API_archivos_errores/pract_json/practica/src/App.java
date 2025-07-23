import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class App {
    public static void main(String[] args) throws Exception {
        String json = """
                            {
                            "persona1": {
                                "nombre": "Juan",
                                "edad": 30,
                                "ciudad": "Madrid"
                            },
                            "persona2": {
                                "nombre": "Alice",
                                "edad": 10,
                                "ciudad": "New York"
                            }
                            }
                """;
    Gson gson = new Gson();
    Map<String, Persona> personas = gson.fromJson(json, new TypeToken<Map<String, Persona>>(){}.getType());

    Persona alice = personas.get("persona2");
    System.out.println("Nombre: " + alice.getNombre());
    System.out.println("Edad: " + alice.getEdad());
    System.out.println("Ciudad: " + alice.getCiudad());
    }
    public class Persona {
        private String nombre;
        private int edad;
        private String ciudad;

        public String getNombre() { return nombre; }
        public int getEdad() { return edad; }
        public String getCiudad() { return ciudad; }
}
}
