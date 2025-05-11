# Vinilo App

Vinilo App es una aplicación móvil desarrollada para dispositivos Android que permite a los usuarios explorar información musical detallada, incluyendo artistas, álbumes, géneros y premios relacionados. Proporciona una experiencia intuitiva y atractiva para los amantes de la música.

# Funcionalidades Principales

- Explorar álbumes musicales y artistas
- Consultar detalles específicos de álbumes y artistas
- Gestionar y consultar premios relacionados con artistas
- Interfaz amigable y fácil de usar

# Descarga e Instalación APK

1. Descarga el archivo APK desde [Enlace APK](APK/Entrega%202).
2. En tu dispositivo Android, ve a Configuración > Seguridad y habilita la opción Fuentes desconocidas.
3. Navega hasta la carpeta donde se descargó el archivo APK.
4. Pulsa sobre el archivo APK para instalar.

# Guía de Uso

## Primeros Pasos

1. Abre la aplicación.
2. Explora álbumes y artistas desde la pantalla principal.

## Navegación

1. Para observar los diferentes artistas pesiona el primer ícono del menú inferior, al abrir la aplicación podrás ver este contenido por defecto seleccionado.
2. Para observar el listado de álbumes, haz click sobre el segundo ícono del menú inferior. Allí observarás el listado de álbumes.
3. Para observar el detalle de alguno de los álbumes listados, presiona sobre la carátula (imagen de cada álbum). Esta acción abrirá una nueva vista con la información detallada de este álbum.

# Cómo Ejecutar el Código Fuente (Desarrolladores)

- Requisitos previos
- Android Studio (última versión recomendada)
- JDK 17 (recomendado)
- Git (opcional, para clonar el repositorio)
- Instalación y ejecución

# Clona el repositorio:

- git clone (HTTPS) https://github.com/privera2505/TSDC_MOVIL_OOEP.git | SSH git@github.com:privera2505/TSDC_MOVIL_OOEP.git
- Abre Android Studio.
- Selecciona Open y abre la carpeta clonada del proyecto.
- Espera que gradle sincronice y descargue las dependencias necesarias.
- Ejecuta la aplicación seleccionando tu dispositivo virtual o físico y presionando el botón Run.

# Estructura de archivos
````
ViniloApp/
├── .gradle/
├── .idea/
├── app/
│   ├── build/
│   ├── src/
│   ├── .gitignore
│   ├── build.gradle.kts
│   ├── proguard-rules.pro
│   └── README.MD
├── build/
│   └── reports/
│       └── problems/
│           └── problems-report.html
├── gradle/
│   ├── wrapper/
│   └── libs.versions.toml
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
├── local.properties
├── README.md
└── settings.gradle.kts
````

# Tecnologías Utilizadas

- Kotlin
- MVVM
- Android Jetpack (Navigation, ViewModel, LiveData)

# Servicios Backend

Para el desarrollo de esta aplicación se consume un servicio Rest a travez del [Vinilo Backend](https://github.com/TheSoftwareDesignLab/BackVynils) el cual se configuró por medio de un archivo ubicado en:

````
app/src/main/java/com/vinilo/network/apiClient.kt
````

En la variable **BASE_URL** se puede setear y apuntar a la URL base del proyecto de backend, bien sea en un docker (local) o de forma remota.

En este caso el equipo subio de forma remota el proyecto del backend y base de datos. La URL base que utilizamos con el servicio de **Railway** es:

````
https://backvynils-production.up.railway.app/
````
**Nota:** Indispensable mantener el slash al final para que funcionen los path de los llamados en los archivos de servicios.

# Pruebas E2E

Para ejecutar las pruebas end-to-end (E2E) de la aplicación, asegúrate de estar ubicado en la carpeta raíz del proyecto. Una vez allí, abre una terminal y ejecuta el siguiente comando:

````
./gradlew connectedAndroidTest
````
Este comando se encarga de compilar y ejecutar todas las pruebas instrumentadas que se encuentran en la carpeta androidTest. Es importante que tengas un emulador o dispositivo físico conectado y en ejecución, ya que estas pruebas requieren un entorno Android para ejecutarse.

# Resultados de pruebas

- [Resultados entrega 1](/app/src/androidTest/results/entrega-1/Entrega1.md)
- [Resultados entrega 2](/app/src/androidTest/results/entrega-2/Entrega2.md)
