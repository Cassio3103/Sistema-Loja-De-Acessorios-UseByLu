package com.usebylu.auxiliar;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.usebylu.exception.EstadoInvalidoException;
import lombok.Getter;

@Getter
public enum Estado {

    AC("Acre"), AL("Alagoas"), AP("Amapá"),
    AM("Amazonas"), BA("Bahia"), CE("Ceará"), DF("Distrito Federal"),
    ES("Espírito Santo"), GO("Goiás"), MA("Maranhão"),
    MT("Mato Grosso"), MS("Mato Grosso do Sul"), MG("Minas Gerais"),
    PA("Pará"), PB("Paraíba"),
    PR("Paraná"), PE("Pernambuco"), PI("Piauí"), RJ("Rio de Janeiro"),
    RN("Rio Grande do Norte"), RS("Rio Grande do Sul"),
    RO("Rondônia"), RR("Roraima"), SC("Santa Catarina"),
    SP("São Paulo"),
    SE("Sergipe"), TO("Tocantins");

    Estado(String nomeCompleto){
        this.nomeCompleto = nomeCompleto;
    }

    private final String nomeCompleto;

    @JsonCreator
    public static Estado fromString(String valor) {
        if (valor == null) {
            throw new EstadoInvalidoException("Estado não pode ser nulo");
        }

        valor = valor.trim();

        for (Estado estado : Estado.values()) {
            if (estado.name().equalsIgnoreCase(valor)
                    || estado.getNomeCompleto().equalsIgnoreCase(valor)) {
                return estado;
            }
        }

        throw new EstadoInvalidoException(
                "Estado inválido: " + valor + ". Use a sigla (ex: RN) ou nome completo."
        );
    }

}
