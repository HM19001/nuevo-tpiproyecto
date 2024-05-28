Feature: Probar REST API para TipoAtributo
Los usuarios deberian poder hacer peticiones GET y POST para consumir un recurso lanzado mediante un contenedor

Scenario: CRUD para TipoAtributo
When Se tiene un servidor con la aplicacion desplegada
Then los usuarios hacen POST enviando un TipoAtributo en formato JSON como payload, y el servidor deberia crear un recurso nuevo y devolver como respuesta un estado 201 con una cabecera location apuntando al recurso recion creado.
