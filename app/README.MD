# Vinilo App

Vinilo App es una aplicación móvil desarrollada para dispositivos Android que permite a los usuarios explorar información musical detallada, incluyendo artistas, álbumes, géneros y premios relacionados. Proporciona una experiencia intuitiva y atractiva para los amantes de la música.

# Funcionalidades Principales

- Explorar álbumes musicales y artistas
- Consultar detalles específicos de álbumes y artistas
- Gestionar y consultar premios relacionados con artistas
- Interfaz amigable y fácil de usar

# Capturas de Pantalla

# Descarga e Instalación APK

1. Descarga el archivo APK desde [enlace aquí].
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

# Tecnologías Utilizadas

- Kotlin
- MVVM
- Android Jetpack (Navigation, ViewModel, LiveData)