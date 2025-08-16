package med.voll.api.direccion;

public record Direccion(
    String calle,
    String numero,
    String complemento,
    String barrio,
    String ciudad,
    String codigoPostal,
    String estado
) {}
