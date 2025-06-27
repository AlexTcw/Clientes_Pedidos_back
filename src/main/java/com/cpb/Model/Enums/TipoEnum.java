package com.cpb.Model.Enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum TipoEnum {
    PORCENTAJE("PORCENTAJE"),
    VALOR_FIJO("VALOR FIJO");

    private final String value;

    TipoEnum(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static TipoEnum fromValue(String value) {
        for (TipoEnum tipo : TipoEnum.values()) {
            if (tipo.value.equalsIgnoreCase(value)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("No enum constant TipoEnum." + value);
    }
}
