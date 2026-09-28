# Desarrollo de una aplicación de gestión de préstamos utilizando POO

La empresa 'Fintech Solutions' necesita una aplicación que gestione el ciclo de vida de los préstamos, desde la solicitud hasta la aprobación y el seguimiento. La aplicación debe interactuar con una base de datos para almacenar y recuperar información de los préstamos. Los préstamos tienen atributos como 'monto', 'tasa de interés', 'fecha de vencimiento', y 'estado'. La aplicación debe manejar excepciones como 'solicitud inválida', 'base de datos no disponible', y 'error de conexión'. Además, debe implementar interfaces y clases abstractas para permitir la extensión y el uso de patrones de diseño orientados a objetos. Se espera que la aplicación sea robusta, escalable y fácil de mantener.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Implementación de programación orientada a objetos (POO) - avanzado |
| **Nivel** | senior-l2 |
| **Tipo** | practical |
| **Tiempo estimado** | 2 semanas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Definición de la estructura de la aplicación

**Objetivo:** Definir la estructura básica de la aplicación y sus componentes principales.

**Tiempo estimado:** 3 días

**Instrucciones:**

- Identificar los principales actores del dominio (cliente, préstamo, sistema de gestión de préstamos).
- Definir las clases y sus relaciones en términos del problema de dominio.
- Establecer las interfaces y clases abstractas necesarias para la extensión y el uso de patrones de diseño.

**Entregable:** Diagrama de clases y descripción de las interfaces y clases abstractas.

<details>
<summary>Pistas de conocimiento</summary>

- Considera cómo las clases deben interactuar entre sí y con la base de datos.
- Piensa en cómo las interfaces pueden facilitar la extensión y el mantenimiento del código.

</details>

### Fase 2: Implementación de la lógica de negocios

**Objetivo:** Implementar la lógica de negocios para el manejo de préstamos, incluyendo la interacción con la base de datos.

**Tiempo estimado:** 5 días

**Instrucciones:**

- Implementar las clases definidas en la fase anterior.
- Integrar la aplicación con una base de datos para almacenar y recuperar información de los préstamos.
- Manejar excepciones como 'solicitud inválida', 'base de datos no disponible', y 'error de conexión'.

**Entregable:** Código fuente de las clases implementadas y la integración con la base de datos.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza patrones de diseño para manejar la lógica de negocios de manera eficiente.
- Considera cómo manejar los errores y excepciones de manera efectiva.

</details>

### Fase 3: Pruebas unitarias y refactorización

**Objetivo:** Escribir pruebas unitarias para verificar la correcta implementación de la lógica de negocios y refactorizar el código para mejorar su calidad.

**Tiempo estimado:** 4 días

**Instrucciones:**

- Escribir pruebas unitarias para verificar la correcta implementación de la lógica de negocios.
- Refactorizar el código para mejorar su calidad y mantenibilidad.
- Utilizar herramientas de pruebas unitarias para automatizar el proceso de pruebas.

**Entregable:** Código fuente refactorizado y pruebas unitarias.

<details>
<summary>Pistas de conocimiento</summary>

- Utiliza herramientas de pruebas unitarias para automatizar el proceso de pruebas.
- Considera cómo refactorizar el código para mejorar su calidad y mantenibilidad.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las clases abstractas y cómo se utilizan en la implementación de la lógica de negocios?
- **paraQueSirve**: ¿Para qué sirven las interfaces en la aplicación de gestión de préstamos?
- **comoSeUsa**: ¿Cómo se utiliza la programación orientada a objetos para manejar la lógica de negocios en la aplicación de gestión de préstamos?
- **erroresComunes**: ¿Cuáles son los errores comunes que pueden ocurrir al implementar la lógica de negocios y cómo se manejan?
- **queDecisionesImplica**: ¿Qué decisiones implica la refactorización del código para mejorar su calidad y mantenibilidad?

## Criterios de Evaluacion

- Definir la estructura básica de la aplicación y sus componentes principales.
- Implementar la lógica de negocios para el manejo de préstamos, incluyendo la interacción con la base de datos.
- Escribir pruebas unitarias para verificar la correcta implementación de la lógica de negocios.
- Refactorizar el código para mejorar su calidad y mantenibilidad.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean test-compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
