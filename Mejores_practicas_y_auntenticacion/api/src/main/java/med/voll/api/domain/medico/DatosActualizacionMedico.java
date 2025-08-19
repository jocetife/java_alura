package med.voll.api.domain.medico;

import med.voll.api.domain.direccion.DatosDireccion;

public record DatosActualizacionMedico(
    Long id,
    String nombre,
    String telefono,
    DatosDireccion direccion
) {}
