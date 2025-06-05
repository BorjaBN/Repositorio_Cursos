package AgendaContactos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ExportadorContactos {

    public void exportarContactosJson(List<Contacto> contactos, String rutaArchivo) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // JSON bonito

        mapper.writeValue(new File(rutaArchivo), contactos);
    }
}
