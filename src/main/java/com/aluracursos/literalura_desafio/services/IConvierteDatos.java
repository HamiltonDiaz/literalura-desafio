package com.aluracursos.literalura_desafio.services;

public interface IConvierteDatos {
    //T= Obejeto genérico
    <T> T obtenerDatos (String json, Class<T> clase);
}
