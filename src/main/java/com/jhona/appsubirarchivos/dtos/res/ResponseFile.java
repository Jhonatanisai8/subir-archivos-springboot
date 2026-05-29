package com.jhona.appsubirarchivos.dtos.res;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseFile {
    private String nombre;
    private String url;
    private String tipo;
    private long tamanio;
}
