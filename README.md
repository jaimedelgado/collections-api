# Collections API
Microservicio realizado con Spring Boot, Spring Security, Swagger 2 y Spring Fox. 
También se utilizar otras librerías útiles como mapstruct, lombok y gson.

Esta api llama al api de unsplash para traer sus colecciones. Este API tiene un max de 50 request por día por lo que no se
podrán realizar demasiadas peticiones. Se ha puesto autenticación básica con usuario y contraseña (vivelibre/v1v3l1br3).
En un futuro se debería solicitar el token oauth2 a unsplash.

En cuanto al api, se quería realizar el siguiente endpoint:
> GET /v1/collections/all  (Deprecado)

Parámetros:
> filter: Filtro aplicado. (Optional; default: null) Ejemplo: id like 9897. Siempre hace un contains.

Sin embargo se ha realizado un endpoint extra que ofrece mejor usabilidad y permite ser usada
sin tener que saber cómo funciona ya que sigue las buenas prácticas reflejadas en https://www.ietf.org/rfc/rfc3986.txt. 
Se recomienda también leer https://www.w3.org/Provider/Style/URI.html.en.

El endpoint quedaría de la siguiente forma:
> GET **/v1/collections**

Parámetros:
> **id**: String (Optional; default: null)
> 
> **title**: String (Optional; default: null)
>
> **description**: String (Optional; default: null)
>
> **coverPhotoId**: String (Optional; default: null)

Existen las operaciones de filtrado OR (usando '&': id=35&id=36), igualdad (=) y contiene (=~ : id=~89) .

---

#### Tecnologías

Apache Maven 3.6.3

Java 11

---

#### Integración con docker

Se ha generado la imagen jaimedelgadolinares/collections-api:latest en dockerhub y se ha sincronizado con la rama develop del repositorio
Github para que siempre que se haga un push se actualice la imagen.

Para probarla sólo hay que ejecutar:
> docker pull jaimedelgadolinares/collections-api:latest

> docker run -p 8080:8080 jaimedelgadolinares/collections-api

---

#### Servicios disponibles
Una vez lanzado el microservicio se podrá acceder al API accediendo a la siguiente URL:
> **local**: http://localhost:8080/swagger-ui.html

---

#### Buenas prácticas

Siempre se realizará el contrato del API antes de implementar nada y deberá ser validado por el equipo.
Para el contrato se tendrán en cuenta las siguientes consideraciones:
- Recursos: Serán las entidades sobre las que actuamos. Las acciones sobre estas entidades se realizarán con los
  métodos HTTP. En inglés, minúsculas y spinal-case. Siempre nombres y nunca acciones ni verbos. URIs cortas para mejorar posicionamiento.

- Path parameters: Siempre seguidos de la entidad a la que hacen referencia. Nunca usar varios seguidos.

- Query parameters: Sólo se usarán para los métodos GET que devuelvan una lista. Su propósito sólo puede ser filtrar, paginar, expandir o similares.

Para la salida de los servicios se usará JSON, camelCase, y se incluirá siempre un objeto data que envolverá la salida de cualquier petición.

Cualquier implementación se realizará con TDD. Primero se creará el test que cubra el requisito, se lanzará el test, y se implementará lo
necesario para que pase el test (y nada más). Este proceso se repetirá hasta cubrir todos los requisitos.

Se deben respetar los objetos de las diferentes capas y nunca saltárselos ni pasar el mismo objeto por varias capas.

Se debe mapear el objeto de salida y de entrada a través de los "mappers".

Todo lo que se diseñe debe tener responsabilidad única, estar lo más desacoplado posible y estar lo más preparado para ser reutilizable.



