# Plan de Corrección: Botón de Renovación

Se corregirá el botón "Confirmar Renovación" en la pantalla `RenovarReservaScreen`. El fallo se debe a que la fecha contiene barras diagonales (`/`), las cuales son interpretadas por el sistema de navegación como separadores de rutas, causando que no se encuentre el destino.

## User Review Required

> [!IMPORTANT]
> Para que el botón funcione sin cambiar el formato de la fecha, es **técnicamente obligatorio** usar `Uri.encode()`. Esto permite que el sistema de navegación trate la fecha como un único valor y no como múltiples carpetas o segmentos.
>
> Si no se desea usar `Uri.encode()`, la única alternativa sería cambiar las fechas a un formato sin barras (ej. `15-06-2026`).

## Proposed Changes

### [Component] UI Screens
Codificación de los argumentos de navegación para permitir caracteres especiales.

#### [MODIFY] [misReservas.kt](file:///C:/Users/Juan Diego Cardona/AndroidStudioProjects/NavegationCompose/app/src/main/java/com/example/navegationcompose/ui/screen/misReservas.kt)
*   Codificar `nombre`, `fecha` y `código` al navegar al detalle.

#### [MODIFY] [detalleReserva.kt](file:///C:/Users/Juan Diego Cardona/AndroidStudioProjects/NavegationCompose/app/src/main/java/com/example/navegationcompose/ui/screen/detalleReserva.kt)
*   Codificar los parámetros al navegar a la pantalla de renovación.

#### [MODIFY] [renovarReserva.kt](file:///C:/Users/Juan Diego Cardona/AndroidStudioProjects/NavegationCompose/app/src/main/java/com/example/navegationcompose/ui/screen/renovarReserva.kt)
*   Codificar `nombreLibro` y `nuevaFecha` en el botón "Confirmar Renovación".

## Verification Plan

### Manual Verification
1.  Navegar a la pantalla de renovación.
2.  Presionar "Confirmar Renovación".
3.  Verificar que la aplicación navegue correctamente a la pantalla de éxito mostrando la fecha con barras.
