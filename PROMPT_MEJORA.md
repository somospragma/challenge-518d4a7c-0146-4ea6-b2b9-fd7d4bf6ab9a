# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Archivos que la arquitectura del reto declara y no estan

Creálos con implementacion real, en la capa que les corresponde:

- `RunCucumberTest.java`
- `XSteps.java`

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/fintech/loans/steps/XSteps.java` — `io.cucumber.java8`: El import io.cucumber.java8.En pertenece a io.cucumber.java8, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setCustomerId`: Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setAmount`: Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setInterestRate`: Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setTermMonths`: Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setStatus`: Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setStartDate`: Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setMonthlyPayment`: Se invoca `setMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setDueDate`: Se invoca `setDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.getLoanId`: Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.isEmpty`: Se invoca `isEmpty` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getLoanId`: Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getCustomerId`: Se invoca `getCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getAmount`: Se invoca `getAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getInterestRate`: Se invoca `getInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getTermMonths`: Se invoca `getTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getStatus`: Se invoca `getStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getStartDate`: Se invoca `getStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getDueDate`: Se invoca `getDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getMonthlyPayment`: Se invoca `getMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setLoanId`: Se invoca `setLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setCustomerId`: Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setAmount`: Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setInterestRate`: Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setTermMonths`: Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setStatus`: Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `LoanStatus.name`: Se invoca `name` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setStartDate`: Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setDueDate`: Se invoca `setDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setMonthlyPayment`: Se invoca `setMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `Loan.getLoanId`: Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `LoanRepository.save`: Se invoca `save` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `LoanRepository.findById`: Se invoca `findById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `LoanRepository.deleteById`: Se invoca `deleteById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `LoanRepository.findByDueDate`: Se invoca `findByDueDate` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setCustomerId`: Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setAmount`: Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setInterestRate`: Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setTermMonths`: Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setStatus`: Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.getLoanId`: Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.getCustomerId`: Se invoca `getCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.getAmount`: Se invoca `getAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setStartDate`: Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setLoanId`: Se invoca `setLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setCustomerId`: Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setAmount`: Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setInterestRate`: Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setTermMonths`: Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setStatus`: Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setStartDate`: Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setMonthlyPayment`: Se invoca `setMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.getCustomerId`: Se invoca `getCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.isPresent`: Se invoca `isPresent` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.get`: Se invoca `get` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.size`: Se invoca `size` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setDueDate`: Se invoca `setDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `LoanRepository.save`: Se invoca `save` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.getLoanId`: Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `LoanRepository.findById`: Se invoca `findById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.isPresent`: Se invoca `isPresent` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.get`: Se invoca `get` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.size`: Se invoca `size` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `LoanRepository.findByDueDate`: Se invoca `findByDueDate` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setStatus`: Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.getStatus`: Se invoca `getStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setLoanId`: Se invoca `setLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setCustomerId`: Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setAmount`: Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setInterestRate`: Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setTermMonths`: Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setStartDate`: Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setDueDate`: Se invoca `setDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setMonthlyPayment`: Se invoca `setMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

## Como saber que terminaste

```bash
mvn clean test-compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología QA Automation, Senior

### Brecha de conocimiento
Demuestra un dominio avanzado en Programación Orientada a Objetos en temas clave como: conexiones a base de datos, manejo de excepciones, interfaces, clases abstractas, colecciones, uso de bibliotecas/frameworks, patrones de diseño orientados a objetos, herramientas de pruebas unitarias

### Misión / candidato
Candidata con experiencia en automatización y calidad de software

### Reto
- Tema: Implementación de programación orientada a objetos (POO) - avanzado
- Seniority: senior-l2
- Tipo: practical
- Título: Desarrollo de una aplicación de gestión de préstamos utilizando POO
- Tiempo estimado: 2 semanas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Definición de la estructura de la aplicación — objetivo: Definir la estructura básica de la aplicación y sus componentes principales. — entregable (NO resolver): Diagrama de clases y descripción de las interfaces y clases abstractas.
- Fase 2: Implementación de la lógica de negocios — objetivo: Implementar la lógica de negocios para el manejo de préstamos, incluyendo la interacción con la base de datos. — entregable (NO resolver): Código fuente de las clases implementadas y la integración con la base de datos.
- Fase 3: Pruebas unitarias y refactorización — objetivo: Escribir pruebas unitarias para verificar la correcta implementación de la lógica de negocios y refactorizar el código para mejorar su calidad. — entregable (NO resolver): Código fuente refactorizado y pruebas unitarias.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: src/main/java/com/fintech/loans/LoansApplication.java ===
package com.fintech.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import com.fintech.loans.application.services.LoanService;
import com.fintech.loans.infrastructure.adapters.LoanJpaAdapter;
import com.fintech.loans.infrastructure.config.GlobalExceptionHandler;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.fintech.loans.infrastructure.persistence")
@EntityScan(basePackages = "com.fintech.loans.infrastructure.persistence")
@EnableConfigurationProperties
@EnableAsync
@ComponentScan(basePackages = {"com.fintech.loans"})
public class LoansApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(LoansApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(LoansApplication.class);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public LoanService loanService(LoanJpaAdapter loanJpaAdapter) {
        return new LoanService(loanJpaAdapter);
    }

    @Bean
    public GlobalExceptionHandler globalExceptionHandler() {
        return new GlobalExceptionHandler();
    }
}

// === ARCHIVO: src/main/resources/application.yml ===
spring:
  application:
    name: loans-service
  datasource:
    url: jdbc:h2:mem:loansdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
    driver-class-name: org.h2.Driver
    username: sa
    password: password
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 5
  h2:
    console:
      enabled: true
      path: /h2-console
  jpa:
    database-platform: org.hibernate.dialect.H2Dialect
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        format_sql: true
        use_sql_comments: true
server:
  port: 8080
  error:
    include-message: always
    include-binding-errors: always
logging:
  level:
    org:
      springframework:
        web: DEBUG
      hibernate:
        SQL: DEBUG
        type:
          descriptor:
            sql:
              BasicBinder: TRACE
    com:
      fintech: DEBUG
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.4.0</version>
        <relativePath/>
    </parent>

    <groupId>com.fintech</groupId>
    <artifactId>loans</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>loans</name>
    <description>Gestión de préstamos para Fintech Solutions</description>

    <properties>
        <java.version>21</java.version>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>

    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-core</artifactId>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>org.mockito</groupId>
            <artifactId>mockito-junit-jupiter</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.2.5</version>
                <configuration>
                    <includes>
                        <include>**/*Test.java</include>
                        <include>**/*Steps.java</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <repositories>
        <repository>
            <id>spring-milestones</id>
            <name>Spring Milestones</name>
            <url>https://repo.spring.io/milestone</url>
        </repository>
    </repositories>
</project>

// === ARCHIVO: src/main/java/com/fintech/loans/application/ports/in/LoanServicePort.java ===
package com.fintech.loans.application.ports.in;

import com.fintech.loans.domain.model.Loan;
import java.util.List;
import java.util.Optional;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Puerto de entrada para operaciones de negocio relacionadas con préstamos.
 * Define los contratos que la capa de aplicación expone al exterior.
 */
public interface LoanServicePort {
    /**
     * Crea un nuevo préstamo con los datos proporcionados.
     * @param customerId Identificador del cliente
     * @param amount Monto del préstamo
     * @param interestRate Tasa de interés anual
     * @param termMeses Plazo en meses
     * @return Loan creado con estado y fechas calculadas
     * @throws com.fintech.loans.domain.exception.InvalidLoanRequestException si los datos son inválidos
     */
    Loan createLoan(String customerId, BigDecimal amount, BigDecimal interestRate, int termMeses);

    /**
     * Obtiene un préstamo por su identificador único.
     * @param loanId Identificador del préstamo
     * @return Optional con el préstamo si existe
     */
    Optional<Loan> getLoanById(String loanId);

    /**
     * Obtiene todos los préstamos de un cliente.
     * @param customerId Identificador del cliente
     * @return Lista de préstamos activos
     */
    List<Loan> getLoansByCustomer(String customerId);

    /**
     * Actualiza el estado de un préstamo.
     * @param loanId Identificador del préstamo
     * @param newStatus Nuevo estado
     * @return Loan actualizado
     * @throws com.fintech.loans.domain.exception.InvalidLoanRequestException si el estado no es válido
     */
    Loan updateLoanStatus(String loanId, String newStatus);

    /**
     * Calcula el pago mensual para un préstamo.
     * @param loanId Identificador del préstamo
     * @return Monto del pago mensual
     * @throws com.fintech.loans.domain.exception.InvalidLoanRequestException si el préstamo no existe
     */
    BigDecimal calculateMonthlyPayment(String loanId);

    /**
     * Obtiene préstamos que vencen en una fecha específica.
     * @param dueDate Fecha de vencimiento
     * @return Lista de préstamos que vencen en esa fecha
     */
    List<Loan> getLoansDueOn(LocalDate dueDate);
}

// === ARCHIVO: src/main/java/com/fintech/loans/application/ports/out/LoanPersistencePort.java ===
package com.fintech.loans.application.ports.out;

import com.fintech.loans.domain.model.Loan;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

/**
 * Puerto de salida para operaciones de persistencia de préstamos.
 * Define los contratos que la capa de aplicación necesita de la infraestructura.
 */
public interface LoanPersistencePort {
    /**
     * Guarda un préstamo en el repositorio.
     * @param loan Préstamo a guardar
     * @return Préstamo guardado
     * @throws com.fintech.loans.domain.exception.DatabaseUnavailableException si hay error de conexión
     */
    Loan save(Loan loan);

    /**
     * Busca un préstamo por su identificador.
     * @param loanId Identificador del préstamo
     * @return Optional con el préstamo si existe
     */
    Optional<Loan> findById(String loanId);

    /**
     * Busca todos los préstamos de un cliente.
     * @param customerId Identificador del cliente
     * @return Lista de préstamos
     */
    List<Loan> findByCustomerId(String customerId);

    /**
     * Elimina un préstamo.
     * @param loanId Identificador del préstamo
     */
    void deleteById(String loanId);

    /**
     * Busca préstamos que vencen en una fecha específica.
     * @param dueDate Fecha de vencimiento
     * @return Lista de préstamos
     */
    List<Loan> findByDueDate(LocalDate dueDate);

    /**
     * Actualiza el estado de un préstamo.
     * @param loanId Identificador del préstamo
     * @param newStatus Nuevo estado
     * @return Préstamo actualizado
     */
    Loan updateStatus(String loanId, String newStatus);
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/model/Loan.java ===
package com.fintech.loans.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entidad de dominio que representa un préstamo.
 * Contiene la lógica de negocio y validaciones asociadas.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private String loanId;
    private String customerId;
    private BigDecimal amount;
    private BigDecimal interestRate;
    private int termMonths;
    private String status;
    private LocalDate startDate;
    private LocalDate dueDate;
    private BigDecimal monthlyPayment;

    /**
     * Calcula el pago mensual basado en el monto, tasa de interés y plazo.
     * Usa la fórmula de amortización francesa.
     * @return Monto del pago mensual
     */
    public BigDecimal calculateMonthlyPayment() {
        if (amount == null || interestRate == null || termMonths <= 0) {
            throw new IllegalArgumentException("Datos insuficientes para calcular pago mensual");
        }
        
        BigDecimal monthlyRate = interestRate.divide(BigDecimal.valueOf(12), 10, BigDecimal.ROUND_HALF_UP)
                                         .divide(BigDecimal.valueOf(100), 10, BigDecimal.ROUND_HALF_UP);
        BigDecimal denominator = BigDecimal.ONE.subtract(
            BigDecimal.ONE.divide(BigDecimal.ONE.add(monthlyRate).pow(termMonths), 10, BigDecimal.ROUND_HALF_UP)
        );
        
        this.monthlyPayment = amount.multiply(monthlyRate).divide(denominator, 2, BigDecimal.ROUND_HALF_UP);
        return this.monthlyPayment;
    }

    /**
     * Calcula la fecha de vencimiento basada en la fecha de inicio y el plazo.
     * @return Fecha de vencimiento
     */
    public LocalDate calculateDueDate() {
        if (startDate == null) {
            throw new IllegalArgumentException("Fecha de inicio no puede ser nula");
        }
        this.dueDate = startDate.plusMonths(termMonths);
        return this.dueDate;
    }

    /**
     * Valida que el préstamo tenga datos consistentes.
     * @throws com.fintech.loans.domain.exception.InvalidLoanRequestException si hay datos inválidos
     */
    public void validate() {
        if (customerId == null || customerId.isBlank()) {
            throw new IllegalArgumentException("Customer ID no puede ser vacío");
        }
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Monto debe ser positivo");
        }
        if (interestRate == null || interestRate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Tasa de interés debe ser positiva");
        }
        if (termMonths <= 0) {
            throw new IllegalArgumentException("Plazo debe ser positivo");
        }
        if (status == null || status.isBlank()) {
            throw new IllegalArgumentException("Estado no puede ser vacío");
        }
    }

    /**
     * Genera un nuevo identificador único para el préstamo.
     */
    public void generateId() {
        this.loanId = UUID.randomUUID().toString();
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/model/LoanStatus.java ===
package com.fintech.loans.domain.model;

/**
 * Enumeración que define los estados posibles del ciclo de vida de un préstamo.
 * Cada estado representa una fase en el proceso de gestión del préstamo.
 */
public enum LoanStatus {
    /**
     * Estado inicial cuando el préstamo está siendo evaluado.
     * En este estado se validan los requisitos y se verifica la elegibilidad del cliente.
     */
    PENDING("Pendiente de revisión", true),
    
    /**
     * Estado cuando el préstamo ha sido aprobado y está activo.
     * El cliente ha cumplido con todos los requisitos y el préstamo está en vigor.
     */
    APPROVED("Aprobado", false),
    
    /**
     * Estado cuando el préstamo ha sido rechazado.
     * Puede ser por incumplimiento de requisitos, riesgo crediticio u otros factores.
     */
    REJECTED("Rechazado", false),
    
    /**
     * Estado cuando el préstamo está siendo pagado regularmente.
     * El cliente está al día con sus pagos mensuales.
     */
    ACTIVE("Activo", false),
    
    /**
     * Estado cuando el cliente ha incumplido con los pagos.
     * El préstamo está en mora y requiere gestión de cobranza.
     */
    DEFAULTED("En mora", false),
    
    /**
     * Estado cuando el préstamo ha sido completamente pagado.
     * Todas las cuotas han sido abonadas y no queda saldo pendiente.
     */
    PAID_OFF("Pagado", false),
    
    /**
     * Estado cuando el préstamo ha sido cancelado antes de completarse.
     * Puede ser por solicitud del cliente o por auditoría.
     */
    CANCELLED("Cancelado", false),
    
    /**
     * Estado temporal cuando el préstamo está en proceso de renovación.
     * Se está evaluando una extensión o refinanciamiento.
     */
    RENEGOTIATION("En renegociación", true);

    private final String displayName;
    private final boolean isTransitional;

    LoanStatus(String displayName, boolean isTransitional) {
        this.displayName = displayName;
        this.isTransitional = isTransitional;
    }

    /**
     * Obtiene el nombre para mostrar del estado.
     * @return Representación legible del estado
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Indica si el estado es transicional (temporal).
     * Los estados transicionales son aquellos por los que el préstamo pasa
     * durante su procesamiento pero no representan un estado final.
     * @return true si el estado es transicional
     */
    public boolean isTransitional() {
        return isTransitional;
    }

    /**
     * Verifica si el estado representa un estado terminal del préstamo.
     * Los estados terminales son aquellos donde el ciclo de vida del préstamo
     * ha finalizado (aprobado, rechazado, pagado, cancelado, en mora).
     * @return true si el estado es terminal
     */
    public boolean isTerminal() {
        return this == APPROVED || this == REJECTED || this == PAID_OFF 
               || this == CANCELLED || this == DEFAULTED;
    }

    /**
     * Verifica si el estado permite modificaciones en el préstamo.
     * Algunos estados no permiten cambios porque representan
     * estados finales o en proceso de auditoría.
     * @return true si el préstamo puede modificarse en este estado
     */
    public boolean allowsModification() {
        return this == PENDING || this == ACTIVE || this == RENEGOTIATION;
    }

    /**
     * Obtiene los estados válidos para transición desde el estado actual.
     * Define qué transiciones son permitidas en el flujo del préstamo.
     * @return Array de estados a los que se puede transitar
     */
    public LoanStatus[] getAllowedTransitions() {
        return switch (this) {
            case PENDING -> new LoanStatus[]{APPROVED, REJECTED, CANCELLED};
            case APPROVED -> new LoanStatus[]{ACTIVE, CANCELLED};
            case ACTIVE -> new LoanStatus[]{DEFAULTED, PAID_OFF, RENEGOTIATION};
            case DEFAULTED -> new LoanStatus[]{PAID_OFF, CANCELLED, RENEGOTIATION};
            case RENEGOTIATION -> new LoanStatus[]{ACTIVE, DEFAULTED, CANCELLED};
            case PAID_OFF, REJECTED, CANCELLED -> new LoanStatus[]{};
        };
    }

    /**
     * Valida si una transición a un nuevo estado es válida.
     * @param newStatus El estado al que se desea transitar
     * @return true si la transición es válida
     */
    public boolean canTransitionTo(LoanStatus newStatus) {
        if (newStatus == null) {
            return false;
        }
        LoanStatus[] allowed = getAllowedTransitions();
        for (LoanStatus status : allowed) {
            if (status == newStatus) {
                return true;
            }
        }
        return false;
    }

    /**
     * Busca un estado por su nombre ignorando mayúsculas/minúsculas.
     * @param name Nombre del estado a buscar
     * @return El estado encontrado o null si no existe
     */
    public static LoanStatus findByName(String name) {
        if (name == null || name.isBlank()) {
            return null;
        }
        for (LoanStatus status : values()) {
            if (status.name().equalsIgnoreCase(name)) {
                return status;
            }
        }
        return null;
    }

    /**
     * Obtiene el estado por su nombre para mostrar.
     * @param displayName Nombre para mostrar del estado
     * @return El estado encontrado o null si no existe
     */
    public static LoanStatus findByDisplayName(String displayName) {
        if (displayName == null || displayName.isBlank()) {
            return null;
        }
        for (LoanStatus status : values()) {
            if (status.displayName.equalsIgnoreCase(displayName)) {
                return status;
            }
        }
        return null;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/exception/InvalidLoanRequestException.java ===
package com.fintech.loans.domain.exception;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Excepción específica para manejar solicitudes de préstamo inválidas.
 * Se lanza cuando los datos proporcionados para crear un préstamo no cumplen
 * con las reglas de negocio establecidas por la entidad financiera.
 * 
 * Esta excepción incluye información detallada sobre los errores de validación
 * para facilitar el diagnóstico y la corrección por parte del cliente.
 */
public class InvalidLoanRequestException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Código de error interno para categorización.
     */
    private final String errorCode;
    
    /**
     * Timestamp de cuando ocurrió el error.
     */
    private final LocalDateTime timestamp;
    
    /**
     * Mapa de errores de validación detallados.
     * Cada entrada representa un campo específico que failed la validación.
     */
    private final Map<String, String> validationErrors;
    
    /**
     * Constructor básico con mensaje de error.
     * @param message Descripción general del error
     */
    public InvalidLoanRequestException(String message) {
        super(message);
        this.errorCode = "LOAN_REQUEST_INVALID";
        this.timestamp = LocalDateTime.now();
        this.validationErrors = new HashMap<>();
    }
    
    /**
     * Constructor con código de error personalizado.
     * @param message Descripción del error
     * @param errorCode Código para categorización interna
     */
    public InvalidLoanRequestException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.validationErrors = new HashMap<>();
    }
    
    /**
     * Constructor completo con todos los detalles del error.
     * @param message Descripción del error
     * @param errorCode Código para categorización
     * @param cause Causa original de la excepción
     */
    public InvalidLoanRequestException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.validationErrors = new HashMap<>();
    }
    
    /**
     * Constructor con errores de validación específicos.
     * Útil cuando se detectan múltiples errores en los datos de entrada.
     * @param message Descripción general del error
     * @param errors Mapa de errores de validación campo -> descripción
     */
    public InvalidLoanRequestException(String message, Map<String, String> errors) {
        super(message);
        this.errorCode = "LOAN_REQUEST_VALIDATION_FAILED";
        this.timestamp = LocalDateTime.now();
        this.validationErrors = errors != null ? new HashMap<>(errors) : new HashMap<>();
    }
    
    /**
     * Constructor completo con causa y errores de validación.
     * @param message Descripción del error
     * @param errorCode Código para categorización
     * @param errors Mapa de errores de validación
     * @param cause Causa original
     */
    public InvalidLoanRequestException(String message, String errorCode, 
                                        Map<String, String> errors, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
        this.validationErrors = errors != null ? new HashMap<>(errors) : new HashMap<>();
    }
    
    /**
     * Obtiene el código de error interno.
     * @return Código de error
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * Obtiene el timestamp de cuando ocurrió el error.
     * @return Fecha y hora del error
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    /**
     * Obtiene el mapa de errores de validación.
     * @return Copia del mapa de errores para evitar modificación
     */
    public Map<String, String> getValidationErrors() {
        return new HashMap<>(validationErrors);
    }
    
    /**
     * Agrega un error de validación específico.
     * @param field Campo que failed la validación
     * @param errorMessage Descripción del error
     */
    public void addValidationError(String field, String errorMessage) {
        this.validationErrors.put(field, errorMessage);
    }
    
    /**
     * Verifica si hay errores de validación específicos.
     * @return true si existen errores de validación registrados
     */
    public boolean hasValidationErrors() {
        return !validationErrors.isEmpty();
    }
    
    /**
     * Crea una excepción para monto de préstamo inválido.
     * @param amount Monto proporcionado
     * @param minAmount Monto mínimo permitido
     * @param maxAmount Monto máximo permitido
     * @return Nueva instancia de la excepción
     */
    public static InvalidLoanRequestException invalidAmount(BigDecimal amount, 
                                                            BigDecimal minAmount, 
                                                            BigDecimal maxAmount) {
        Map<String, String> errors = new HashMap<>();
        errors.put("amount", String.format("Monto %.2f fuera del rango válido [%s - %s]", 
                amount, minAmount, maxAmount));
        return new InvalidLoanRequestException(
            "El monto del préstamo debe estar entre " + minAmount + " y " + maxAmount,
            "INVALID_AMOUNT",
            errors
        );
    }
    
    /**
     * Crea una excepción para tasa de interés inválida.
     * @param rate Tasa proporcionada
     * @param minRate Tasa mínima permitida
     * @param maxRate Tasa máxima permitida
     * @return Nueva instancia de la excepción
     */
    public static InvalidLoanRequestException invalidInterestRate(BigDecimal rate, 
                                                                   BigDecimal minRate, 
                                                                   BigDecimal maxRate) {
        Map<String, String> errors = new HashMap<>();
        errors.put("interestRate", String.format("Tasa %.2f%% fuera del rango válido [%.2f%% - %.2f%%]", 
                rate, minRate, maxRate));
        return new InvalidLoanRequestException(
            "La tasa de interés debe estar entre " + minRate + "% y " + maxRate + "%",
            "INVALID_INTEREST_RATE",
            errors
        );
    }
    
    /**
     * Crea una excepción para plazo inválido.
     * @param term Plazo proporcionado
     * @param minTerm Plazo mínimo permitido
     * @param maxTerm Plazo máximo permitido
     * @return Nueva instancia de la excepción
     */
    public static InvalidLoanRequestException invalidTerm(int term, int minTerm, int maxTerm) {
        Map<String, String> errors = new HashMap<>();
        errors.put("termMonths", String.format("Plazo %d meses fuera del rango válido [%d - %d]", 
                term, minTerm, maxTerm));
        return new InvalidLoanRequestException(
            "El plazo del préstamo debe estar entre " + minTerm + " y " + maxTerm + " meses",
            "INVALID_TERM",
            errors
        );
    }
    
    /**
     * Crea una excepción para cliente inválido o no encontrado.
     * @param customerId Identificador del cliente
     * @return Nueva instancia de la excepción
     */
    public static InvalidLoanRequestException invalidCustomer(String customerId) {
        Map<String, String> errors = new HashMap<>();
        errors.put("customerId", "Cliente no válido o no encontrado: " + customerId);
        return new InvalidLoanRequestException(
            "El cliente especificado no es válido o no existe",
            "INVALID_CUSTOMER",
            errors
        );
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/exception/DatabaseUnavailableException.java ===
package com.fintech.loans.domain.exception;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Excepción específica para manejar errores de disponibilidad de la base de datos.
 * Se lanza cuando la aplicación no puede establecer conexión con la base de datos
 * o cuando una operación de base de datos falla por razones de conectividad,
 * timeouts o errores de servidor.
 * 
 * Esta excepción proporciona información detallada sobre el tipo de error de base de datos
 * para facilitar el diagnóstico y las acciones de recuperación.
 */
public class DatabaseUnavailableException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    /**
     * Enumeración de tipos de errores de base de datos.
     */
    public enum DatabaseErrorType {
        CONNECTION_TIMEOUT("Tiempo de conexión agotado", "No se pudo establecer conexión con la base de datos"),
        CONNECTION_REFUSED("Conexión rechazada", "El servidor de base de datos rechazó la conexión"),
        SERVER_UNAVAILABLE("Servidor no disponible", "El servidor de base de datos no está disponible"),
        QUERY_TIMEOUT("Tiempo de consulta agotado", "La consulta excedió el tiempo máximo de ejecución"),
        DEADLOCK("Deadlock detectado", "Se detectó un deadlock en la base de datos"),
        POOL_EXHAUSTED("Pool de conexiones agotado", "No hay conexiones disponibles en el pool"),
        SCHEMA_ERROR("Error de esquema", "El esquema de la base de datos no es válido"),
        DATA_INTEGRITY("Violación de integridad", "La operación viola restricciones de integridad"),
        UNKNOWN("Error desconocido", "Error de base de datos no categorizado");
        
        private final String title;
        private final String description;
        
        DatabaseErrorType(String title, String description) {
            this.title = title;
            this.description = description;
        }
        
        public String getTitle() {
            return title;
        }
        
        public String getDescription() {
            return description;
        }
    }
    
    private final DatabaseErrorType errorType;
    private final LocalDateTime timestamp;
    private final String sqlState;
    private final int vendorCode;
    private final String query;
    private final Map<String, Object> context;
    
    /**
     * Constructor básico con mensaje de error.
     * @param message Descripción del error
     */
    public DatabaseUnavailableException(String message) {
        super(message);
        this.errorType = DatabaseErrorType.UNKNOWN;
        this.timestamp = LocalDateTime.now();
        this.sqlState = null;
        this.vendorCode = 0;
        this.query = null;
        this.context = new HashMap<>();
    }
    
    /**
     * Constructor con tipo de error específico.
     * @param message Descripción del error
     * @param errorType Tipo de error de base de datos
     */
    public DatabaseUnavailableException(String message, DatabaseErrorType errorType) {
        super(message);
        this.errorType = errorType != null ? errorType : DatabaseErrorType.UNKNOWN;
        this.timestamp = LocalDateTime.now();
        this.sqlState = null;
        this.vendorCode = 0;
        this.query = null;
        this.context = new HashMap<>();
    }
    
    /**
     * Constructor con causa original del error.
     * @param message Descripción del error
     * @param cause Excepción original que causó el error
     */
    public DatabaseUnavailableException(String message, Throwable cause) {
        super(message, cause);
        this.errorType = classifyError(cause);
        this.timestamp = LocalDateTime.now();
        this.sqlState = extractSqlState(cause);
        this.vendorCode = extractVendorCode(cause);
        this.query = null;
        this.context = new HashMap<>();
    }
    
    /**
     * Constructor completo con todos los detalles del error.
     * @param message Descripción del error
     * @param errorType Tipo de error de base de datos
     * @param cause Excepción original
     */
    public DatabaseUnavailableException(String message, DatabaseErrorType errorType, Throwable cause) {
        super(message, cause);
        this.errorType = errorType != null ? errorType : classifyError(cause);
        this.timestamp = LocalDateTime.now();
        this.sqlState = extractSqlState(cause);
        this.vendorCode = extractVendorCode(cause);
        this.query = null;
        this.context = new HashMap<>();
    }
    
    /**
     * Constructor con información de la consulta que falló.
     * @param message Descripción del error
     * @param errorType Tipo de error
     * @param query La consulta SQL que falló
     * @param cause Causa original
     */
    public DatabaseUnavailableException(String message, DatabaseErrorType errorType, 
                                         String query, Throwable cause) {
        super(message, cause);
        this.errorType = errorType != null ? errorType : classifyError(cause);
        this.timestamp = LocalDateTime.now();
        this.sqlState = extractSqlState(cause);
        this.vendorCode = extractVendorCode(cause);
        this.query = query;
        this.context = new HashMap<>();
    }
    
    /**
     * Clasifica el tipo de error basándose en la excepción original.
     * @param cause Excepción original
     * @return Tipo de error clasificado
     */
    private static DatabaseErrorType classifyError(Throwable cause) {
        if (cause == null) {
            return DatabaseErrorType.UNKNOWN;
        }
        
        String exceptionClass = cause.getClass().getSimpleName().toLowerCase();
        String message = cause.getMessage() != null ? cause.getMessage().toLowerCase() : "";
        
        if (exceptionClass.contains("timeout") || message.contains("timeout")) {
            if (message.contains("connection")) {
                return DatabaseErrorType.CONNECTION_TIMEOUT;
            }
            return DatabaseErrorType.QUERY_TIMEOUT;
        }
        
        if (exceptionClass.contains("refused") || message.contains("refused")) {
            return DatabaseErrorType.CONNECTION_REFUSED;
        }
        
        if (exceptionClass.contains("unavailable") || message.contains("unavailable")) {
            return DatabaseErrorType.SERVER_UNAVAILABLE;
        }
        
        if (exceptionClass.contains("deadlock") || message.contains("deadlock")) {
            return DatabaseErrorType.DEADLOCK;
        }
        
        if (exceptionClass.contains("pool") || message.contains("pool")) {
            return DatabaseErrorType.POOL_EXHAUSTED;
        }
        
        if (exceptionClass.contains("integrity") || exceptionClass.contains("constraint")) {
            return DatabaseErrorType.DATA_INTEGRITY;
        }
        
        return DatabaseErrorType.UNKNOWN;
    }
    
    /**
     * Extrae el SQLState de la excepción si está disponible.
     * @param cause Excepción original
     * @return SQLState o null
     */
    private static String extractSqlState(Throwable cause) {
        if (cause instanceof SQLException) {
            return ((SQLException) cause).getSQLState();
        }
        return null;
    }
    
    /**
     * Extrae el código de error del vendor de la excepción.
     * @param cause Excepción original
     * @return Código de vendor o 0
     */
    private static int extractVendorCode(Throwable cause) {
        if (cause instanceof SQLException) {
            return ((SQLException) cause).getErrorCode();
        }
        return 0;
    }
    
    /**
     * Obtiene el tipo de error de base de datos.
     * @return Tipo de error
     */
    public DatabaseErrorType getErrorType() {
        return errorType;
    }
    
    /**
     * Obtiene el timestamp de cuando ocurrió el error.
     * @return Fecha y hora del error
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    /**
     * Obtiene el SQLState de la excepción de base de datos.
     * @return SQLState o null
     */
    public String getSqlState() {
        return sqlState;
    }
    
    /**
     * Obtiene el código de error del vendor.
     * @return Código de vendor
     */
    public int getVendorCode() {
        return vendorCode;
    }
    
    /**
     * Obtiene la consulta SQL que provocó el error.
     * @return Consulta o null
     */
    public String getQuery() {
        return query;
    }
    
    /**
     * Obtiene el contexto adicional del error.
     * @return Mapa de contexto
     */
    public Map<String, Object> getContext() {
        return new HashMap<>(context);
    }
    
    /**
     * Agrega información de contexto al error.
     * @param key Clave del contexto
     * @param value Valor del contexto
     */
    public void addContext(String key, Object value) {
        this.context.put(key, value);
    }
    
    /**
     * Verifica si el error es de conexión.
     * @return true si es un error de conexión
     */
    public boolean isConnectionError() {
        return errorType == DatabaseErrorType.CONNECTION_TIMEOUT ||
               errorType == DatabaseErrorType.CONNECTION_REFUSED ||
               errorType == DatabaseErrorType.SERVER_UNAVAILABLE ||
               errorType == DatabaseErrorType.POOL_EXHAUSTED;
    }
    
    /**
     * Verifica si el error es recuperable.
     * Algunos errores pueden ser temporales y la operación puede reintentarse.
     * @return true si el error es potencialmente recuperable
     */
    public boolean isRecoverable() {
        return isConnectionError() || 
               errorType == DatabaseErrorType.DEADLOCK ||
               errorType == DatabaseErrorType.QUERY_TIMEOUT;
    }
    
    /**
     * Crea una excepción para conexión agotada.
     * @return Nueva instancia de la excepción
     */
    public static DatabaseUnavailableException connectionTimeout() {
        return new DatabaseUnavailableException(
            "Tiempo de conexión con la base de datos agotado",
            DatabaseErrorType.CONNECTION_TIMEOUT
        );
    }
    
    /**
     * Crea una excepción para servidor no disponible.
     * @return Nueva instancia de la excepción
     */
    public static DatabaseUnavailableException serverUnavailable() {
        return new DatabaseUnavailableException(
            "El servidor de base de datos no está disponible",
            DatabaseErrorType.SERVER_UNAVAILABLE
        );
    }
    
    /**
     * Crea una excepción con información de la consulta.
     * @param query Consulta que falló
     * @param cause Causa original
     * @return Nueva instancia
     */
    public static DatabaseUnavailableException queryFailed(String query, Throwable cause) {
        DatabaseUnavailableException ex = new DatabaseUnavailableException(
            "Error al ejecutar la consulta en la base de datos",
            DatabaseErrorType.UNKNOWN,
            query,
            cause
        );
        ex.addContext("sql", query);
        return ex;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/domain/exception/ConnectionErrorException.java ===
package com.fintech.loans.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ConnectionErrorException extends RuntimeException {
    private final String serviceName;
    private final long retryAfterMs;

    public ConnectionErrorException(String message) {
        super(message);
        this.serviceName = "UNKNOWN";
        this.retryAfterMs = 0L;
    }

    public ConnectionErrorException(String message, Throwable cause) {
        super(message, cause);
        this.serviceName = "UNKNOWN";
        this.retryAfterMs = 0L;
    }

    public ConnectionErrorException(String message, String serviceName, long retryAfterMs) {
        super(message);
        this.serviceName = serviceName;
        this.retryAfterMs = retryAfterMs;
    }

    public ConnectionErrorException(String message, String serviceName, long retryAfterMs, Throwable cause) {
        super(message, cause);
        this.serviceName = serviceName;
        this.retryAfterMs = retryAfterMs;
    }

    public String getServiceName() {
        return serviceName;
    }

    public long getRetryAfterMs() {
        return retryAfterMs;
    }

    public boolean isRetryable() {
        return retryAfterMs > 0;
    }

    public String getDetailedMessage() {
        return String.format("ConnectionErrorException{service='%s', retryAfterMs=%d, message='%s'}",
                serviceName, retryAfterMs, getMessage());
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/persistence/LoanRepository.java ===
package com.fintech.loans.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, String> {

    Optional<LoanEntity> findByLoanId(String loanId);

    List<LoanEntity> findByCustomerId(String customerId);

    @Query("SELECT l FROM LoanEntity l WHERE l.dueDate = :dueDate AND l.status = 'ACTIVE'")
    List<LoanEntity> findActiveLoansByDueDate(@Param("dueDate") LocalDate dueDate);

    @Query("SELECT l FROM LoanEntity l WHERE l.status = :status")
    List<LoanEntity> findByStatus(@Param("status") String status);

    @Query("SELECT l FROM LoanEntity l WHERE l.customerId = :customerId AND l.status = 'ACTIVE'")
    List<LoanEntity> findActiveLoansByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT COUNT(l) FROM LoanEntity l WHERE l.customerId = :customerId")
    long countByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT SUM(l.amount) FROM LoanEntity l WHERE l.customerId = :customerId AND l.status = 'ACTIVE'")
    java.math.BigDecimal sumActiveLoansAmountByCustomerId(@Param("customerId") String customerId);

    boolean existsByLoanId(String loanId);

    void deleteByLoanId(String loanId);
}

// === ARCHIVO: src/main/java/com/fintech/loans/application/services/LoanService.java ===
package com.fintech.loans.application.services;

import com.fintech.loans.application.ports.in.LoanServicePort;
import com.fintech.loans.application.ports.out.LoanPersistencePort;
import com.fintech.loans.domain.exception.ConnectionErrorException;
import com.fintech.loans.domain.exception.DatabaseUnavailableException;
import com.fintech.loans.domain.exception.InvalidLoanRequestException;
import com.fintech.loans.domain.model.Loan;
import com.fintech.loans.domain.model.LoanStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class LoanService implements LoanServicePort {

    private static final Logger logger = LoggerFactory.getLogger(LoanService.class);
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("1000.00");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("500000.00");
    private static final BigDecimal MIN_INTEREST_RATE = new BigDecimal("0.01");
    private static final BigDecimal MAX_INTEREST_RATE = new BigDecimal("0.50");
    private static final int MIN_TERM_MONTHS = 6;
    private static final int MAX_TERM_MONTHS = 360;

    private final LoanPersistencePort persistencePort;

    public LoanService(LoanPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public Loan createLoan(String customerId, BigDecimal amount, BigDecimal interestRate, int termMeses) {
        logger.info("Creating loan for customer: {}, amount: {}, rate: {}, term: {}",
                customerId, amount, interestRate, termMeses);

        validateLoanRequest(customerId, amount, interestRate, termMeses);

        Loan loan = new Loan();
        loan.setCustomerId(customerId);
        loan.setAmount(amount);
        loan.setInterestRate(interestRate);
        loan.setTermMonths(termMeses);
        loan.setStatus(LoanStatus.PENDING.name());
        loan.setStartDate(LocalDate.now());
        loan.generateId();

        BigDecimal monthlyPayment = calculateMonthlyPaymentAmount(amount, interestRate, termMeses);
        loan.setMonthlyPayment(monthlyPayment);

        LocalDate dueDate = loan.calculateDueDate();
        loan.setDueDate(dueDate);

        try {
            loan.validate();
            Loan savedLoan = persistencePort.save(loan);
            logger.info("Loan created successfully with ID: {}", savedLoan.getLoanId());
            return savedLoan;
        } catch (Exception e) {
            logger.error("Failed to create loan for customer: {}", customerId, e);
            if (e.getMessage() != null && e.getMessage().contains("database")) {
                throw new DatabaseUnavailableException("Database is currently unavailable", e);
            }
            throw new ConnectionErrorException("Failed to save loan to database", "LoanPersistence", 5000L, e);
        }
    }

    @Override
    public Optional<Loan> getLoanById(String loanId) {
        logger.debug("Retrieving loan by ID: {}", loanId);

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Loan ID cannot be null or empty");
        }

        try {
            Optional<Loan> loan = persistencePort.findById(loanId);
            if (loan.isEmpty()) {
                logger.warn("Loan not found with ID: {}", loanId);
            }
            return loan;
        } catch (Exception e) {
            logger.error("Error retrieving loan by ID: {}", loanId, e);
            throw new ConnectionErrorException("Failed to retrieve loan", "LoanPersistence", 3000L, e);
        }
    }

    @Override
    public List<Loan> getLoansByCustomer(String customerId) {
        logger.debug("Retrieving loans for customer: {}", customerId);

        if (customerId == null || customerId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Customer ID cannot be null or empty");
        }

        try {
            List<Loan> loans = persistencePort.findByCustomerId(customerId);
            logger.info("Found {} loans for customer: {}", loans.size(), customerId);
            return loans;
        } catch (Exception e) {
            logger.error("Error retrieving loans for customer: {}", customerId, e);
            throw new ConnectionErrorException("Failed to retrieve customer loans", "LoanPersistence", 3000L, e);
        }
    }

    @Override
    public Loan updateLoanStatus(String loanId, String newStatus) {
        logger.info("Updating loan status - ID: {}, new status: {}", loanId, newStatus);

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Loan ID cannot be null or empty");
        }

        if (newStatus == null || newStatus.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Status cannot be null or empty");
        }

        try {
            Loan existingLoan = persistencePort.findById(loanId)
                    .orElseThrow(() -> new InvalidLoanRequestException("Loan not found with ID: " + loanId));

            LoanStatus statusEnum = LoanStatus.fromString(newStatus);
            if (statusEnum == null) {
                throw new InvalidLoanRequestException("Invalid loan status: " + newStatus);
            }

            Loan updatedLoan = persistencePort.updateStatus(loanId, newStatus);
            logger.info("Loan status updated successfully - ID: {}, status: {}", loanId, newStatus);
            return updatedLoan;
        } catch (InvalidLoanRequestException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error updating loan status - ID: {}", loanId, e);
            throw new ConnectionErrorException("Failed to update loan status", "LoanPersistence", 5000L, e);
        }
    }

    @Override
    public BigDecimal calculateMonthlyPayment(String loanId) {
        logger.debug("Calculating monthly payment for loan: {}", loanId);

        if (loanId == null || loanId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Loan ID cannot be null or empty");
        }

        try {
            Loan loan = persistencePort.findById(loanId)
                    .orElseThrow(() -> new InvalidLoanRequestException("Loan not found with ID: " + loanId));

            BigDecimal monthlyPayment = loan.calculateMonthlyPayment();
            logger.debug("Monthly payment calculated: {} for loan: {}", monthlyPayment, loanId);
            return monthlyPayment;
        } catch (InvalidLoanRequestException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error calculating monthly payment for loan: {}", loanId, e);
            throw new ConnectionErrorException("Failed to calculate monthly payment", "LoanPersistence", 3000L, e);
        }
    }

    @Override
    public List<Loan> getLoansDueOn(LocalDate dueDate) {
        logger.debug("Retrieving loans due on: {}", dueDate);

        if (dueDate == null) {
            throw new InvalidLoanRequestException("Due date cannot be null");
        }

        try {
            List<Loan> loans = persistencePort.findByDueDate(dueDate);
            logger.info("Found {} loans due on: {}", loans.size(), dueDate);
            return loans;
        } catch (Exception e) {
            logger.error("Error retrieving loans due on: {}", dueDate, e);
            throw new ConnectionErrorException("Failed to retrieve loans by due date", "LoanPersistence", 3000L, e);
        }
    }

    private void validateLoanRequest(String customerId, BigDecimal amount, BigDecimal interestRate, int termMeses) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new InvalidLoanRequestException("Customer ID cannot be null or empty");
        }

        if (amount == null) {
            throw new InvalidLoanRequestException("Loan amount cannot be null");
        }

        if (amount.compareTo(MIN_AMOUNT) < 0) {
            throw new InvalidLoanRequestException(
                    String.format("Loan amount must be at least %s", MIN_AMOUNT));
        }

        if (amount.compareTo(MAX_AMOUNT) > 0) {
            throw new InvalidLoanRequestException(
                    String.format("Loan amount cannot exceed %s", MAX_AMOUNT));
        }

        if (interestRate == null) {
            throw new InvalidLoanRequestException("Interest rate cannot be null");
        }

        if (interestRate.compareTo(MIN_INTEREST_RATE) < 0) {
            throw new InvalidLoanRequestException(
                    String.format("Interest rate must be at least %s%%", MIN_INTEREST_RATE.multiply(BigDecimal.valueOf(100))));
        }

        if (interestRate.compareTo(MAX_INTEREST_RATE) > 0) {
            throw new InvalidLoanRequestException(
                    String.format("Interest rate cannot exceed %s%%", MAX_INTEREST_RATE.multiply(BigDecimal.valueOf(100))));
        }

        if (termMeses < MIN_TERM_MONTHS) {
            throw new InvalidLoanRequestException(
                    String.format("Loan term must be at least %d months", MIN_TERM_MONTHS));
        }

        if (termMeses > MAX_TERM_MONTHS) {
            throw new InvalidLoanRequestException(
                    String.format("Loan term cannot exceed %d months", MAX_TERM_MONTHS));
        }
    }

    private BigDecimal calculateMonthlyPaymentAmount(BigDecimal principal, BigDecimal annualRate, int termMonths) {
        if (principal == null || annualRate == null || termMonths <= 0) {
            throw new InvalidLoanRequestException("Invalid parameters for payment calculation");
        }

        if (annualRate.compareTo(BigDecimal.ZERO) == 0) {
            return principal.divide(BigDecimal.valueOf(termMonths), 2, RoundingMode.HALF_UP);
        }

        BigDecimal monthlyRate = annualRate.divide(BigDecimal.valueOf(12), 6, RoundingMode.HALF_UP);
        BigDecimal onePlusRatePowN = BigDecimal.ONE.add(monthlyRate).pow(termMonths);
        BigDecimal monthlyPayment = principal.multiply(monthlyRate).multiply(onePlusRatePowN)
                .divide(onePlusRatePowN.subtract(BigDecimal.ONE), 2, RoundingMode.HALF_UP);

        return monthlyPayment;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java ===
package com.fintech.loans.infrastructure.persistence;

import com.fintech.loans.domain.model.Loan;
import com.fintech.loans.domain.model.LoanStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "loans")
public class LoanEntity {

    @Id
    @Column(name = "loan_id", length = 36)
    private String loanId;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "interest_rate", nullable = false, precision = 5, scale = 4)
    private BigDecimal interestRate;

    @Column(name = "term_months", nullable = false)
    private int termMonths;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private LoanStatus status;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "monthly_payment", nullable = false, precision = 19, scale = 4)
    private BigDecimal monthlyPayment;

    public LoanEntity() {
    }

    public LoanEntity(String loanId, String customerId, BigDecimal amount, BigDecimal interestRate,
                      int termMonths, LoanStatus status, LocalDate startDate, LocalDate dueDate,
                      BigDecimal monthlyPayment) {
        this.loanId = loanId;
        this.customerId = customerId;
        this.amount = amount;
        this.interestRate = interestRate;
        this.termMonths = termMonths;
        this.status = status;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.monthlyPayment = monthlyPayment;
    }

    public static LoanEntity fromDomain(Loan loan) {
        LoanEntity entity = new LoanEntity();
        entity.loanId = loan.getLoanId();
        entity.customerId = loan.getCustomerId();
        entity.amount = loan.getAmount();
        entity.interestRate = loan.getInterestRate();
        entity.termMonths = loan.getTermMonths();
        entity.status = LoanStatus.fromString(loan.getStatus());
        entity.startDate = loan.getStartDate();
        entity.dueDate = loan.getDueDate();
        entity.monthlyPayment = loan.getMonthlyPayment();
        return entity;
    }

    public Loan toDomain() {
        Loan loan = new Loan();
        loan.setLoanId(this.loanId);
        loan.setCustomerId(this.customerId);
        loan.setAmount(this.amount);
        loan.setInterestRate(this.interestRate);
        loan.setTermMonths(this.termMonths);
        loan.setStatus(this.status.name());
        loan.setStartDate(this.startDate);
        loan.setDueDate(this.dueDate);
        loan.setMonthlyPayment(this.monthlyPayment);
        return loan;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public int getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(int termMonths) {
        this.termMonths = termMonths;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(BigDecimal monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java ===
package com.fintech.loans.infrastructure.adapters;


import com.fintech.loans.domain.model.LoanStatus;
import com.fintech.loans.application.ports.out.LoanPersistencePort;
import com.fintech.loans.domain.exception.DatabaseUnavailableException;
import com.fintech.loans.domain.model.Loan;
import com.fintech.loans.infrastructure.persistence.LoanEntity;
import com.fintech.loans.infrastructure.persistence.LoanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Transactional
public class LoanJpaAdapter implements LoanPersistencePort {

    private static final Logger logger = LoggerFactory.getLogger(LoanJpaAdapter.class);
    private final LoanRepository loanRepository;

    public LoanJpaAdapter(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    @Override
    public Loan save(Loan loan) {
        try {
            logger.info("Persistiendo préstamo con ID: {}", loan.getLoanId());
            LoanEntity entity = LoanEntity.fromDomain(loan);
            LoanEntity savedEntity = loanRepository.save(entity);
            logger.info("Préstamo persistido exitosamente: {}", savedEntity.getLoanId());
            return savedEntity.toDomain();
        } catch (Exception e) {
            logger.error("Error al persistir el préstamo: {}", e.getMessage(), e);
            throw new DatabaseUnavailableException("No se pudo guardar el préstamo en la base de datos", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Loan> findById(String loanId) {
        try {
            logger.debug("Buscando préstamo por ID: {}", loanId);
            return loanRepository.findById(loanId).map(LoanEntity::toDomain);
        } catch (Exception e) {
            logger.error("Error al buscar préstamo por ID {}: {}", loanId, e.getMessage(), e);
            throw new DatabaseUnavailableException("Error al consultar el préstamo", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findByCustomerId(String customerId) {
        try {
            logger.debug("Buscando préstamos para el cliente: {}", customerId);
            List<LoanEntity> entities = loanRepository.findByCustomerId(customerId);
            return entities.stream().map(LoanEntity::toDomain).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error al buscar préstamos del cliente {}: {}", customerId, e.getMessage(), e);
            throw new DatabaseUnavailableException("Error al consultar préstamos del cliente", e);
        }
    }

    @Override
    public void deleteById(String loanId) {
        try {
            logger.info("Eliminando préstamo con ID: {}", loanId);
            loanRepository.deleteById(loanId);
            logger.info("Préstamo eliminado exitosamente: {}", loanId);
        } catch (Exception e) {
            logger.error("Error al eliminar préstamo {}: {}", loanId, e.getMessage(), e);
            throw new DatabaseUnavailableException("Error al eliminar el préstamo", e);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Loan> findByDueDate(LocalDate dueDate) {
        try {
            logger.debug("Buscando préstamos con fecha de vencimiento: {}", dueDate);
            List<LoanEntity> entities = loanRepository.findByDueDate(dueDate);
            return entities.stream().map(LoanEntity::toDomain).collect(Collectors.toList());
        } catch (Exception e) {
            logger.error("Error al buscar préstamos por fecha de vencimiento {}: {}", dueDate, e.getMessage(), e);
            throw new DatabaseUnavailableException("Error al consultar préstamos por fecha", e);
        }
    }

    @Override
    public Loan updateStatus(String loanId, String newStatus) {
        try {
            logger.info("Actualizando estado del préstamo {} a {}", loanId, newStatus);
            LoanEntity entity = loanRepository.findById(loanId)
                    .orElseThrow(() -> new IllegalArgumentException("Préstamo no encontrado: " + loanId));
            entity.setStatus(com.fintech.loans.domain.model.LoanStatus.fromString(newStatus));
            LoanEntity updatedEntity = loanRepository.save(entity);
            logger.info("Estado del préstamo actualizado exitosamente: {}", loanId);
            return updatedEntity.toDomain();
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Error al actualizar estado del préstamo {}: {}", loanId, e.getMessage(), e);
            throw new DatabaseUnavailableException("Error al actualizar el estado del préstamo", e);
        }
    }
}

// === ARCHIVO: src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java ===
package com.fintech.loans.infrastructure.config;

import com.fintech.loans.domain.exception.ConnectionErrorException;
import com.fintech.loans.domain.exception.DatabaseUnavailableException;
import com.fintech.loans.domain.exception.InvalidLoanRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(InvalidLoanRequestException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidLoanRequestException(
            InvalidLoanRequestException ex, WebRequest request) {
        logger.warn("Solicitud de préstamo inválida: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(DatabaseUnavailableException.class)
    public ResponseEntity<Map<String, Object>> handleDatabaseUnavailableException(
            DatabaseUnavailableException ex, WebRequest request) {
        logger.error("Base de datos no disponible: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.SERVICE_UNAVAILABLE, 
                "La base de datos no está disponible en este momento. Por favor, intente más tarde.",
                request.getDescription(false));
    }

    @ExceptionHandler(ConnectionErrorException.class)
    public ResponseEntity<Map<String, Object>> handleConnectionErrorException(
            ConnectionErrorException ex, WebRequest request) {
        logger.error("Error de conexión: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.GATEWAY_TIMEOUT,
                "Error de conexión con el servidor externo. Por favor, intente más tarde.",
                request.getDescription(false));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(
            IllegalArgumentException ex, WebRequest request) {
        logger.warn("Argumento ilegal: {}", ex.getMessage());
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), request.getDescription(false));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGlobalException(
            Exception ex, WebRequest request) {
        logger.error("Error interno del servidor: {}", ex.getMessage(), ex);
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ha ocurrido un error inesperado. Por favor, contacte al administrador.",
                request.getDescription(false));
    }

    private ResponseEntity<Map<String, Object>> buildErrorResponse(
            HttpStatus status, String message, String path) {
        Map<String, Object> errorDetails = new HashMap<>();
        errorDetails.put("timestamp", LocalDateTime.now().toString());
        errorDetails.put("status", status.value());
        errorDetails.put("error", status.getReasonPhrase());
        errorDetails.put("message", message);
        errorDetails.put("path", path);
        return new ResponseEntity<>(errorDetails, status);
    }
}

// === ARCHIVO: src/test/java/com/fintech/loans/domain/model/LoanTest.java ===
package com.fintech.loans.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigDecimal;
import java.time.LocalDate;

@DisplayName("Loan - Tests de la clase de dominio")
class LoanTest {

    @Test
    @DisplayName("Debería crear un préstamo con los valores correctos")
    void shouldCreateLoanWithCorrectValues() {
        // Given
        String customerId = "CUST-001";
        BigDecimal amount = new BigDecimal("10000.00");
        BigDecimal interestRate = new BigDecimal("0.05");
        int termMonths = 12;

        // When
        Loan loan = new Loan();
        loan.setCustomerId(customerId);
        loan.setAmount(amount);
        loan.setInterestRate(interestRate);
        loan.setTermMonths(termMonths);
        loan.setStatus("PENDING");
        loan.generateId();

        // Then
        Assertions.assertNotNull(loan.getLoanId());
        Assertions.assertEquals(customerId, loan.getCustomerId());
        Assertions.assertEquals(amount, loan.getAmount());
    }

    @Test
    @DisplayName("Debería calcular la cuota mensual correctamente")
    void shouldCalculateMonthlyPaymentCorrectly() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("12000.00"));
        loan.setInterestRate(new BigDecimal("0.10"));
        loan.setTermMonths(12);

        // When
        BigDecimal monthlyPayment = loan.calculateMonthlyPayment();

        // Then
        Assertions.assertNotNull(monthlyPayment);
        Assertions.assertTrue(monthlyPayment.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    @DisplayName("Debería calcular la fecha de vencimiento correctamente")
    void shouldCalculateDueDateCorrectly() {
        // Given
        Loan loan = new Loan();
        loan.setStartDate(LocalDate.of(2024, 1, 15));
        loan.setTermMonths(6);

        // When
        LocalDate dueDate = loan.calculateDueDate();

        // Then
        Assertions.assertNotNull(dueDate);
        Assertions.assertEquals(2024, dueDate.getYear());
        Assertions.assertEquals(7, dueDate.getMonthValue());
    }

    @Test
    @DisplayName("Debería validar un préstamo correctamente")
    void shouldValidateLoanCorrectly() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("5000.00"));
        loan.setInterestRate(new BigDecimal("0.03"));
        loan.setTermMonths(24);

        // When
        loan.validate();

        // Then
        Assertions.assertDoesNotThrow(() -> loan.validate());
    }

    @Test
    @DisplayName("Debería rechazar monto negativo o cero")
    void shouldRejectNegativeOrZeroAmount() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(BigDecimal.ZERO);
        loan.setInterestRate(new BigDecimal("0.05"));
        loan.setTermMonths(12);

        // When & Then
        Assertions.assertThrows(Exception.class, () -> loan.validate());
    }

    @Test
    @DisplayName("Debería rechazar tasa de interés negativa")
    void shouldRejectNegativeInterestRate() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("-0.05"));
        loan.setTermMonths(12);

        // When & Then
        Assertions.assertThrows(Exception.class, () -> loan.validate());
    }

    @Test
    @DisplayName("Debería rechazar plazo menor a 1 mes")
    void shouldRejectTermLessThanOneMonth() {
        // Given
        Loan loan = new Loan();
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.05"));
        loan.setTermMonths(0);

        // When & Then
        Assertions.assertThrows(Exception.class, () -> loan.validate());
    }
}

// === ARCHIVO: src/test/java/com/fintech/loans/application/services/LoanServiceTest.java ===
package com.fintech.loans.application.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fintech.loans.application.ports.out.LoanPersistencePort;
import com.fintech.loans.domain.model.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("LoanService - Tests del servicio de préstamos")
@ExtendWith(MockitoExtension.class)
class LoanServiceTest {

    @Mock
    private LoanPersistencePort loanPersistencePort;

    @InjectMocks
    private LoanService loanService;

    @Test
    @DisplayName("Debería crear un préstamo exitosamente")
    void shouldCreateLoanSuccessfully() {
        // Given
        String customerId = "CUST-001";
        BigDecimal amount = new BigDecimal("15000.00");
        BigDecimal interestRate = new BigDecimal("0.08");
        int termMonths = 24;

        Loan savedLoan = new Loan();
        savedLoan.setLoanId("LOAN-001");
        savedLoan.setCustomerId(customerId);
        savedLoan.setAmount(amount);
        savedLoan.setInterestRate(interestRate);
        savedLoan.setTermMonths(termMonths);
        savedLoan.setStatus("PENDING");
        savedLoan.setStartDate(LocalDate.now());
        savedLoan.setMonthlyPayment(new BigDecimal("700.00"));

        when(loanPersistencePort.save(any(Loan.class))).thenReturn(savedLoan);

        // When
        Loan result = loanService.createLoan(customerId, amount, interestRate, termMonths);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(customerId, result.getCustomerId());
        verify(loanPersistencePort, times(1)).save(any(Loan.class));
    }

    @Test
    @DisplayName("Debería obtener un préstamo por ID")
    void shouldGetLoanById() {
        // Given
        String loanId = "LOAN-001";
        Loan loan = new Loan();
        loan.setLoanId(loanId);
        loan.setCustomerId("CUST-001");
        loan.setAmount(new BigDecimal("10000.00"));

        when(loanPersistencePort.findById(loanId)).thenReturn(Optional.of(loan));

        // When
        Optional<Loan> result = loanService.getLoanById(loanId);

        // Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(loanId, result.get().getLoanId());
    }

    @Test
    @DisplayName("Debería obtener préstamos por cliente")
    void shouldGetLoansByCustomer() {
        // Given
        String customerId = "CUST-001";
        List<Loan> loans = Arrays.asList(createLoan("LOAN-001", customerId),
                                         createLoan("LOAN-002", customerId));

        when(loanPersistencePort.findByCustomerId(customerId)).thenReturn(loans);

        // When
        List<Loan> result = loanService.getLoansByCustomer(customerId);

        // Then
        Assertions.assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Debería actualizar el estado de un préstamo")
    void shouldUpdateLoanStatus() {
        // Given
        String loanId = "LOAN-001";
        String newStatus = "APPROVED";
        Loan loan = createLoan(loanId, "CUST-001");

        when(loanPersistencePort.findById(loanId)).thenReturn(Optional.of(loan));
        when(loanPersistencePort.updateStatus(loanId, newStatus)).thenReturn(loan);

        // When
        Loan result = loanService.updateLoanStatus(loanId, newStatus);

        // Then
        Assertions.assertNotNull(result);
        verify(loanPersistencePort, times(1)).updateStatus(loanId, newStatus);
    }

    @Test
    @DisplayName("Debería calcular la cuota mensual de un préstamo")
    void shouldCalculateMonthlyPayment() {
        // Given
        String loanId = "LOAN-001";
        Loan loan = createLoan(loanId, "CUST-001");
        loan.setAmount(new BigDecimal("12000.00"));
        loan.setInterestRate(new BigDecimal("0.10"));
        loan.setTermMonths(12);

        when(loanPersistencePort.findById(loanId)).thenReturn(Optional.of(loan));

        // When
        BigDecimal monthlyPayment = loanService.calculateMonthlyPayment(loanId);

        // Then
        Assertions.assertNotNull(monthlyPayment);
        Assertions.assertTrue(monthlyPayment.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    @DisplayName("Debería obtener préstamos próximos a vencer")
    void shouldGetLoansDueOn() {
        // Given
        LocalDate dueDate = LocalDate.now().plusDays(7);
        List<Loan> loans = Arrays.asList(createLoan("LOAN-001", "CUST-001"),
                                         createLoan("LOAN-002", "CUST-002"));

        when(loanPersistencePort.findByDueDate(dueDate)).thenReturn(loans);

        // When
        List<Loan> result = loanService.getLoansDueOn(dueDate);

        // Then
        Assertions.assertEquals(2, result.size());
    }

    private Loan createLoan(String loanId, String customerId) {
        Loan loan = new Loan();
        loan.setLoanId(loanId);
        loan.setCustomerId(customerId);
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.05"));
        loan.setTermMonths(12);
        loan.setStatus("PENDING");
        loan.setStartDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusMonths(12));
        return loan;
    }
}

// === ARCHIVO: src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java ===
package com.fintech.loans.infrastructure.adapters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fintech.loans.domain.exception.DatabaseUnavailableException;
import com.fintech.loans.domain.model.Loan;
import com.fintech.loans.infrastructure.persistence.LoanRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("LoanJpaAdapter - Tests del adaptador de persistencia JPA")
@ExtendWith(MockitoExtension.class)
class LoanJpaAdapterTest {

    @Mock
    private LoanRepository loanRepository;

    @InjectMocks
    private LoanJpaAdapter loanJpaAdapter;

    @Test
    @DisplayName("Debería guardar un préstamo exitosamente")
    void shouldSaveLoanSuccessfully() {
        // Given
        Loan loan = createLoan("LOAN-001", "CUST-001");
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // When
        Loan result = loanJpaAdapter.save(loan);

        // Then
        assertNotNull(result);
        assertEquals("LOAN-001", result.getLoanId());
        verify(loanRepository, times(1)).save(loan);
    }

    @Test
    @DisplayName("Debería buscar un préstamo por ID")
    void shouldFindLoanById() {
        // Given
        String loanId = "LOAN-001";
        Loan loan = createLoan(loanId, "CUST-001");
        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));

        // When
        Optional<Loan> result = loanJpaAdapter.findById(loanId);

        // Then
        assertTrue(result.isPresent());
        assertEquals(loanId, result.get().getLoanId());
    }

    @Test
    @DisplayName("Debería buscar préstamos por ID de cliente")
    void shouldFindLoansByCustomerId() {
        // Given
        String customerId = "CUST-001";
        List<Loan> loans = Arrays.asList(
            createLoan("LOAN-001", customerId),
            createLoan("LOAN-002", customerId)
        );
        when(loanRepository.findByCustomerId(customerId)).thenReturn(loans);

        // When
        List<Loan> result = loanJpaAdapter.findByCustomerId(customerId);

        // Then
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Debería eliminar un préstamo por ID")
    void shouldDeleteLoanById() {
        // Given
        String loanId = "LOAN-001";
        doNothing().when(loanRepository).deleteById(loanId);

        // When
        loanJpaAdapter.deleteById(loanId);

        // Then
        verify(loanRepository, times(1)).deleteById(loanId);
    }

    @Test
    @DisplayName("Debería buscar préstamos por fecha de vencimiento")
    void shouldFindLoansByDueDate() {
        // Given
        LocalDate dueDate = LocalDate.now().plusDays(7);
        List<Loan> loans = Arrays.asList(
            createLoan("LOAN-001", "CUST-001"),
            createLoan("LOAN-002", "CUST-002")
        );
        when(loanRepository.findByDueDate(dueDate)).thenReturn(loans);

        // When
        List<Loan> result = loanJpaAdapter.findByDueDate(dueDate);

        // Then
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Debería actualizar el estado de un préstamo")
    void shouldUpdateLoanStatus() {
        // Given
        String loanId = "LOAN-001";
        String newStatus = "APPROVED";
        Loan loan = createLoan(loanId, "CUST-001");
        loan.setStatus(newStatus);

        when(loanRepository.findById(loanId)).thenReturn(Optional.of(loan));
        when(loanRepository.save(any(Loan.class))).thenReturn(loan);

        // When
        Loan result = loanJpaAdapter.updateStatus(loanId, newStatus);

        // Then
        assertNotNull(result);
        assertEquals(newStatus, result.getStatus());
    }

    @Test
    @DisplayName("Debería manejar error de base de datos al guardar")
    void shouldHandleDatabaseErrorOnSave() {
        // Given
        Loan loan = createLoan("LOAN-001", "CUST-001");
        when(loanRepository.save(any(Loan.class)))
            .thenThrow(new RuntimeException("Database connection failed"));

        // When & Then
        assertThrows(DatabaseUnavailableException.class, () -> loanJpaAdapter.save(loan));
    }

    @Test
    @DisplayName("Debería manejar error al buscar por ID")
    void shouldHandleErrorOnFindById() {
        // Given
        String loanId = "LOAN-001";
        when(loanRepository.findById(loanId))
            .thenThrow(new RuntimeException("Database unavailable"));

        // When & Then
        assertThrows(DatabaseUnavailableException.class, () -> loanJpaAdapter.findById(loanId));
    }

    private Loan createLoan(String loanId, String customerId) {
        Loan loan = new Loan();
        loan.setLoanId(loanId);
        loan.setCustomerId(customerId);
        loan.setAmount(new BigDecimal("10000.00"));
        loan.setInterestRate(new BigDecimal("0.05"));
        loan.setTermMonths(12);
        loan.setStatus("PENDING");
        loan.setStartDate(LocalDate.now());
        loan.setDueDate(LocalDate.now().plusMonths(12));
        loan.setMonthlyPayment(new BigDecimal("880.00"));
        return loan;
    }
}

// === ARCHIVO: src/test/java/com/fintech/loans/runners/RunCucumberTest.java ===
package com.fintech.loans.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner de Cucumber para ejecutar los escenarios de prueba definidos en Gherkin.
 * Este runner busca los archivos .feature en src/test/resources/features y
 * ejecuta los steps definidos en el paquete com.fintech.loans.steps.
 * 
 * Configuración:
 * - features: ruta donde se encuentran los archivos .feature
 * - glue: paquete donde están las definiciones de pasos
 * - plugin: formatos de reporte (pretty para salida legible, html para reporte visual)
 * - tags: filtros para ejecutar escenarios específicos
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = {"com.fintech.loans.steps"},
    plugin = {"pretty", "html:target/cucumber-reports/cucumber.html",
              "json:target/cucumber-reports/cucumber.json"},
    tags = "@smoke or @regression",
    monochrome = true,
    dryRun = false,
    strict = true
)
public class RunCucumberTest {
    
    /**
     * Constructor por defecto.
     * JUnit crea la instancia del runner automáticamente.
     * No es necesario implementar lógica adicional aquí.
     */
    public RunCucumberTest() {
        super();
    }
}

// === ARCHIVO: src/test/java/com/fintech/loans/steps/XSteps.java ===
package com.fintech.loans.steps;

import io.cucumber.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Step Definitions para los escenarios de prueba en Gherkin.
 * Cada método en esta clase define la implementación de un paso del escenario.
 * Los pasos se mapean mediante expresiones regulares o sintaxis Gherkin.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class XSteps implements En {

    private MockMvc mockMvc;
    private ResultActions lastResponse;
    private String lastRequestBody;
    
    @Autowired
    private WebApplicationContext webApplicationContext;
    
    /**
     * Constructor que inicializa los step definitions y configura el MockMvc.
     * Aquí se definen todos los pasos que los escenarios Gherkin pueden ejecutar.
     */
    public XSteps() {
        
        Before(() -> {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        });
        
        // Step: Dado que el cliente existe en el sistema
        Given("^el cliente con ID \"([^"]+)\" existe en el sistema$", (String customerId) -> {
            // TODO: Implementar verificación de existencia del cliente
            // Por ahora, el test asume que el cliente puede ser creado o ya existe
        });
        
        // Step: Cuando solicito un préstamo con los siguientes datos
        When("^solicito un préstamo con los siguientes datos:$", (io.cucumber.datatable.DataTable dataTable) -> {
            List<Map<String, String>> rows = dataTable.asMaps();
            Map<String, String> loanData = rows.get(0);
            
            String customerId = loanData.get("customerId");
            String amount = loanData.get("amount");
            String interestRate = loanData.get("interestRate");
            String termMonths = loanData.get("termMonths");
            
            lastRequestBody = String.format(
                "{\"customerId\":\"%s\",\"amount\":%s,\"interestRate\":%s,\"termMonths\":%s}",
                customerId, amount, interestRate, termMonths
            );
            
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Cuando solicito un préstamo con monto negativo
        When("^solicito un préstamo con monto negativo$", () -> {
            lastRequestBody = "{\"customerId\":\"CUST001\",\"amount\":-1000,\"interestRate\":0.05,\"termMonths\":12}";
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Cuando solicito un préstamo con tasa de interés inválida
        When("^solicito un préstamo con tasa de interés inválida \(mayor al 100%\)$", () -> {
            lastRequestBody = "{\"customerId\":\"CUST001\",\"amount\":10000,\"interestRate\":1.5,\"termMonths\":12}";
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Cuando solicito un plazo negativo
        When("^solicito un préstamo con plazo negativo$", () -> {
            lastRequestBody = "{\"customerId\":\"CUST001\",\"amount\":10000,\"interestRate\":0.05,\"termMonths\":-6}";
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Entonces el préstamo es creado exitosamente
        Then("^el préstamo es creado exitosamente$", () -> {
            lastResponse.andExpect(status().isCreated())
                .andExpect(jsonPath("$.loanId").exists())
                .andExpect(jsonPath("$.customerId").exists())
                .andExpect(jsonPath("$.status").value("ACTIVE"));
        });
        
        // Step: Y la respuesta contiene los datos del préstamo
        Then("^la respuesta contiene los datos del préstamo$", () -> {
            lastResponse.andExpect(jsonPath("$.amount").exists())
                .andExpect(jsonPath("$.interestRate").exists())
                .andExpect(jsonPath("$.termMonths").exists())
                .andExpect(jsonPath("$.monthlyPayment").exists());
        });
        
        // Step: Entonces recibo un error de solicitud inválida
        Then("^recibo un error de solicitud inválida con código 400$", () -> {
            lastResponse.andExpect(status().isBadRequest());
        });
        
        // Step: Y el mensaje de error indica que el monto debe ser positivo
        Then("^el mensaje de error indica que el monto debe ser positivo$", () -> {
            lastResponse.andExpect(jsonPath("$.error").exists());
        });
        
        // Step: Cuando consulto los préstamos del cliente
        When("^consulto los préstamos del cliente con ID \"([^"]+)\"$", (String customerId) -> {
            lastResponse = mockMvc.perform(get("/api/loans/customer/" + customerId)
                .accept(MediaType.APPLICATION_JSON));
        });
        
        // Step: Entonces obtengo una lista de préstamos
        Then("^obtengo una lista de préstamos$", () -> {
            lastResponse.andExpect(status().isOk())
                .andExpect(jsonPath("$.[*]").isArray());
        });
        
        // Step: Cuando consulto un préstamo por ID
        When("^consulto el préstamo con ID \"([^"]+)\"$", (String loanId) -> {
            lastResponse = mockMvc.perform(get("/api/loans/" + loanId)
                .accept(MediaType.APPLICATION_JSON));
        });
        
        // Step: Entonces obtengo los detalles del préstamo
        Then("^obtengo los detalles del préstamo$", () -> {
            lastResponse.andExpect(status().isOk())
                .andExpect(jsonPath("$.loanId").exists())
                .andExpect(jsonPath("$.amount").exists());
        });
        
        // Step: Cuando actualizo el estado del préstamo
        When("^actualizo el estado del préstamo \"([^"]+)\" a \"([^"]+)\"$", (String loanId, String newStatus) -> {
            lastResponse = mockMvc.perform(post("/api/loans/" + loanId + "/status")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"status\":\"" + newStatus + "\"}"));
        });
        
        // Step: Entonces el estado del préstamo es actualizado
        Then("^el estado del préstamo es actualizado a \"([^"]+)\"$", (String expectedStatus) -> {
            lastResponse.andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value(expectedStatus));
        });
        
        // Step: Cuando calculo la cuota mensual
        When("^calculo la cuota mensual del préstamo \"([^"]+)\"$", (String loanId) -> {
            lastResponse = mockMvc.perform(get("/api/loans/" + loanId + "/monthly-payment")
                .accept(MediaType.APPLICATION_JSON));
        });
        
        // Step: Entonces obtengo el valor de la cuota mensual
        Then("^obtengo el valor de la cuota mensual$", () -> {
            lastResponse.andExpect(status().isOk())
                .andExpect(jsonPath("$.monthlyPayment").exists());
        });
        
        // Step: Cuando solicito un préstamo sin datos requeridos
        When("^solicito un préstamo sin los datos requeridos$", () -> {
            lastRequestBody = "{}";
            lastResponse = mockMvc.perform(post("/api/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(lastRequestBody));
        });
        
        // Step: Entonces el sistema responde con error de validación
        Then("^el sistema responde con error de validación$", () -> {
            lastResponse.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").exists());
        });
    }
}

// === ARCHIVO: README.md ===
# Loans - Gestión de Préstamos para Fintech Solutions

## Descripción

Aplicación Spring Boot para la gestión del ciclo de vida de préstamos bancarios. Implementa arquitectura hexagonal (clean architecture) con separación clara entre dominio, aplicación e infraestructura.

## Tecnologías

- Java 21
- Spring Boot 3.4
- JPA/Hibernate
- H2 (base de datos en memoria)
- JUnit 5 y Mockito
- Cucumber para pruebas BDD

## Compilación

```bash
mvn clean compile
```

## Ejecución de pruebas

### Pruebas unitarias
```bash
mvn test
```

### Pruebas con Cucumber
```bash
mvn test -Dtest=RunCucumberTest
```

### Verificar compilación sin ejecutar
```bash
mvn clean test-compile
```

## Estructura del proyecto

```
src/
├── main/
│   ├── java/com/fintech/loans/
│   │   ├── domain/           # Entidades y excepciones del dominio
│   │   ├── application/      # Puertos y servicios
│   │   └── infrastructure/   # Adaptadores y configuración
│   └── resources/
│       └── application.yml   # Configuración
└── test/
    ├── java/.../runners/     # Runners de Cucumber
    ├── java/.../steps/       # Step definitions
    ├── java/.../domain/      # Tests unitarios
    └── resources/features/   # Archivos .feature
```

## API REST

- `POST /api/loans` - Crear préstamo
- `GET /api/loans/{id}` - Consultar préstamo por ID
- `GET /api/loans/customer/{customerId}` - Lista de préstamos por cliente
- `POST /api/loans/{id}/status` - Actualizar estado
- `GET /api/loans/{id}/monthly-payment` - Calcular cuota mensual

## Configuración

La aplicación usa H2 en memoria. Los datos se pierden al reiniciar.

Para modificar la configuración, editar `src/main/resources/application.yml`.
```
