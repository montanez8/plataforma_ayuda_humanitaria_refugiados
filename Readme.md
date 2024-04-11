# **CampusLans - API de Gestión para ONG de Ayuda Humanitaria 🏆**

Este proyecto es una API RESTful desarrollada con Spring Boot para administrar una organización no gubernamental (ONG) que se dedica a proporcionar ayuda material (medicamentos y alimentos) y ayuda humanitaria (personal sanitario) a campos de refugiados. La ONG obtiene sus ingresos de las cuotas de los socios y opera a nivel de múltiples sedes.

## **Tabla de Contenidos 📚**

1. [Problema](#1-problema-🔥)
2. [Solución](#2-solución-🏆)
3. [Requisitos Funcionales](#3-requisitos-funcionales)
4. [Informes](#4-informes)
5. [Gestión de Envíos de Ayuda](#5-gestión-de-envíos-de-ayuda)
6. [Rabbit](#6-opcional---rabbit)
7. [Instalación y Uso](#7-instalación-y-uso)

## **3. Requisitos Funcionales 📋**

- **Gestión de Socios:** Registrar, consultar, actualizar, eliminar y listar socios por tipo de cuota.
- **Gestión de Sedes:** Registrar, consultar, actualizar, eliminar y listar sedes.
- **Gestión de Voluntarios:** Registrar, consultar, actualizar, eliminar y listar voluntarios por profesión y sede.

## **4. Informes 📊**

- **Informe de Cuotas de Socios**
- **Informe de Sedes y Directores**
- **Informe de Voluntarios**
- **Informe de Envíos de Ayuda Material**
- **Informe de Envíos de Ayuda Humanitaria**

## **5. Gestión de Envíos de Ayuda 📦**

- Crear un nuevo envío: datos del envío, id de refugio, detalles del envío y sedes.
- Obtener un envío por id: datos del envío, detalles del envío, datos completos del refugio y datos completos de las sedes.

## **6. - Rabbit 🐰**

Implementar una arquitectura de tipo event-driven utilizando RabbitMQ para gestionar las notificaciones de envío a los refugios.

## **7. Instalación y Uso ⚙️**

**Requisitos previos:**

1. **Java Development Kit (JDK)**: Asegúrate de tener instalada la versión 8 o posterior de JDK en tu sistema. Puedes descargar la última versión de JDK desde el sitio web oficial de Oracle: <https://www.oracle.com/java/technologies/javase-downloads.html>
2. **Apache Maven**: Asegúrate de tener instalado Apache Maven en tu sistema. Maven es una herramienta de automatización de construcción utilizada principalmente para proyectos Java. Puedes descargar la última versión de Maven desde el sitio web oficial de Apache: <https://maven.apache.org/download.cgi>
3. **Entorno de Desarrollo Integrado (IDE)**: Puedes utilizar cualquier IDE de tu preferencia, pero recomendamos usar IntelliJ IDEA o Eclipse, ya que tienen un excelente soporte para proyectos de Spring Boot.

**Instalación:**

1. **Clona el repositorio**: Comienza clonando el repositorio del proyecto en tu máquina local utilizando el siguiente comando:

```bash
git clone https://github.com/montanez8/plataforma_ayuda_humanitaria_refugiados.git
``

2. **Importa el proyecto**: Importa el proyecto clonado en tu IDE preferido. Si estás utilizando IntelliJ IDEA, sigue estos pasos:
   1. Abre IntelliJ IDEA y haz clic en `File` > `New` > `Project from Version Control`.
   2. Selecciona `Git` como el sistema de control de versiones y escribe la URL del repositorio que clonaste anteriormente.
   3. Elige el directorio donde deseas almacenar los archivos del proyecto y haz clic en `Clone`.
   4. Una vez que el proyecto haya sido clonado, selecciona `File` > `Open` y navega hasta el directorio del proyecto para abrirlo en IntelliJ IDEA.

```
mvn clean install
```

Este comando descargará todas las dependencias requeridas y compilará el proyecto.

**Uso:**

1. **Ejecuta la aplicación**: Para ejecutar la aplicación de Spring Boot, navega hasta el directorio raíz del proyecto y ejecuta el siguiente comando:

```bash
mvn spring-boot:run
```

Alternativamente, puedes ejecutar la aplicación directamente desde tu IDE haciendo clic derecho en la clase principal (la que está anotada con `@SpringBootApplication`) y seleccionando `Run`.

2. **Accede a la API**: Una vez que la aplicación esté en ejecución, puedes acceder a la API utilizando tu cliente HTTP preferido, como Postman o curl. La API estará disponible en `http://localhost:8080`. Puedes consultar la documentación de la API (si está disponible) para obtener información sobre los endpoints disponibles y su uso.

3. **Detén la aplicación**: Para detener la aplicación de Spring Boot, presiona `Ctrl+C` en la terminal donde se está ejecutando la aplicación, o haz clic en el botón de detener en tu IDE.

## **8. Documentación 📝**

La documentación de la API está disponible a través de Swagger, una herramienta de documentación de código abierto para APIs RESTful. Puedes acceder a la documentación de la API mientras la aplicación está en ejecución, visitando el siguiente enlace en tu navegador:

`http://localhost:8080/doc/swagger-ui/index.html`

Aquí encontrarás información detallada sobre los endpoints disponibles, los métodos HTTP soportados, los parámetros de entrada, los modelos de datos y las respuestas esperadas. Además, Swagger te permite probar los endpoints directamente desde la interfaz de usuario, facilitando la exploración y prueba de la API.
