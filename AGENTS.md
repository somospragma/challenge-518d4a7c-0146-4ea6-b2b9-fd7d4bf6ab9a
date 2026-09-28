# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Desarrollo de una aplicación de gestión de préstamos utilizando POO**.

| | |
|---|---|
| Tema | Implementación de programación orientada a objetos (POO) - avanzado |
| Nivel | senior-l2 |
| Chapter | Calidad de Software |
| Especialidad | Automatizador |
| Stack | Java / Spring Boot 3.4 |
| Patron arquitectonico | hexagonal/clean |
| Tiempo estimado | 2 semanas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz con el runner y el plugin de reportes`
- `src/test/resources/features con los .feature en Gherkin`
- `src/test/java/.../runners con el runner`
- `src/test/java/.../pages o /tasks con Page Objects o Screenplay`
- `src/test/java/.../steps con los step definitions`
- `serenity.conf o config del entorno`

Trampas conocidas:

- Sin parent POM que gestione versiones, TODA dependencia lleva su `<version>` completa de tres segmentos.
- El groupId de Serenity es `net.serenity-bdd`, NO `org.serenity-bdd`. Con el groupId equivocado el artefacto no existe y el build muere resolviendo dependencias.
- Coordenadas exactas de lo mas usado: Selenium `org.seleniumhq.selenium:selenium-java`, Rest Assured `io.rest-assured:rest-assured`, Karate `com.intuit.karate:karate-junit5`, Cucumber `io.cucumber:cucumber-java`.
- JUnit 5 se declara con `junit-jupiter` (agregador) y necesita `maven-surefire-plugin` reciente para ejecutarse.
- Serenity y Cucumber tienen que ser de lineas compatibles entre si; mezclarlas rompe el runner.

Dependencias:

- org.springframework.boot:spring-boot-starter-web 3.4.0
- org.springframework.boot:spring-boot-starter-data-jpa 3.4.0
- com.h2database:h2 2.2.224
- org.projectlombok:lombok 1.18.30
- org.springframework.boot:spring-boot-starter-test 3.4.0
- org.junit.jupiter:junit-jupiter-api 5.10.2
- org.mockito:mockito-core 5.11.0
- org.mockito:mockito-junit-jupiter 5.11.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean test-compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean test-compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Definición de la estructura de la aplicación**: Diagrama de clases y descripción de las interfaces y clases abstractas.
- **Fase 2 — Implementación de la lógica de negocios**: Código fuente de las clases implementadas y la integración con la base de datos.
- **Fase 3 — Pruebas unitarias y refactorización**: Código fuente refactorizado y pruebas unitarias.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `RunCucumberTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — El topic pide TDD/pruebas: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Archivos que la arquitectura declara (2 de 18)

La propuesta arquitectonica del reto los lista y no llegaron al repo. Crealos con implementacion real, respetando la capa en la que viven:

- [ ] `RunCucumberTest.java`
- [ ] `XSteps.java`

### 2. Referencias colgando (77)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/fintech/loans/steps/XSteps.java` — `io.cucumber.java8`
      El import io.cucumber.java8.En pertenece a io.cucumber.java8, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setCustomerId`
      Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setAmount`
      Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setInterestRate`
      Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setTermMonths`
      Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setStatus`
      Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setStartDate`
      Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setMonthlyPayment`
      Se invoca `setMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.setDueDate`
      Se invoca `setDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.getLoanId`
      Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/application/services/LoanService.java` — `Loan.isEmpty`
      Se invoca `isEmpty` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getLoanId`
      Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getCustomerId`
      Se invoca `getCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getAmount`
      Se invoca `getAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getInterestRate`
      Se invoca `getInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getTermMonths`
      Se invoca `getTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getStatus`
      Se invoca `getStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getStartDate`
      Se invoca `getStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getDueDate`
      Se invoca `getDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.getMonthlyPayment`
      Se invoca `getMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setLoanId`
      Se invoca `setLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setCustomerId`
      Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setAmount`
      Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setInterestRate`
      Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setTermMonths`
      Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setStatus`
      Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `LoanStatus.name`
      Se invoca `name` sobre `LoanStatus`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setStartDate`
      Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setDueDate`
      Se invoca `setDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java` — `Loan.setMonthlyPayment`
      Se invoca `setMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `Loan.getLoanId`
      Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `LoanRepository.save`
      Se invoca `save` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `LoanRepository.findById`
      Se invoca `findById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `LoanRepository.deleteById`
      Se invoca `deleteById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java` — `LoanRepository.findByDueDate`
      Se invoca `findByDueDate` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setCustomerId`
      Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setAmount`
      Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setInterestRate`
      Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setTermMonths`
      Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setStatus`
      Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.getLoanId`
      Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.getCustomerId`
      Se invoca `getCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.getAmount`
      Se invoca `getAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/domain/model/LoanTest.java` — `Loan.setStartDate`
      Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setLoanId`
      Se invoca `setLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setCustomerId`
      Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setAmount`
      Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setInterestRate`
      Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setTermMonths`
      Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setStatus`
      Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setStartDate`
      Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setMonthlyPayment`
      Se invoca `setMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.getCustomerId`
      Se invoca `getCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.isPresent`
      Se invoca `isPresent` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.get`
      Se invoca `get` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.size`
      Se invoca `size` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java` — `Loan.setDueDate`
      Se invoca `setDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `LoanRepository.save`
      Se invoca `save` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.getLoanId`
      Se invoca `getLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `LoanRepository.findById`
      Se invoca `findById` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.isPresent`
      Se invoca `isPresent` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.get`
      Se invoca `get` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.size`
      Se invoca `size` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `LoanRepository.findByDueDate`
      Se invoca `findByDueDate` sobre `LoanRepository`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setStatus`
      Se invoca `setStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.getStatus`
      Se invoca `getStatus` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setLoanId`
      Se invoca `setLoanId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setCustomerId`
      Se invoca `setCustomerId` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setAmount`
      Se invoca `setAmount` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setInterestRate`
      Se invoca `setInterestRate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setTermMonths`
      Se invoca `setTermMonths` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setStartDate`
      Se invoca `setStartDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setDueDate`
      Se invoca `setDueDate` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java` — `Loan.setMonthlyPayment`
      Se invoca `setMonthlyPayment` sobre `Loan`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.

### Presentes (21)

- `src/main/java/com/fintech/loans/LoansApplication.java`
- `src/main/resources/application.yml`
- `pom.xml`
- `src/main/java/com/fintech/loans/application/ports/in/LoanServicePort.java`
- `src/main/java/com/fintech/loans/application/ports/out/LoanPersistencePort.java`
- `src/main/java/com/fintech/loans/domain/model/Loan.java`
- `src/main/java/com/fintech/loans/domain/model/LoanStatus.java`
- `src/main/java/com/fintech/loans/domain/exception/InvalidLoanRequestException.java`
- `src/main/java/com/fintech/loans/domain/exception/DatabaseUnavailableException.java`
- `src/main/java/com/fintech/loans/domain/exception/ConnectionErrorException.java`
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanRepository.java`
- `src/main/java/com/fintech/loans/application/services/LoanService.java`
- `src/main/java/com/fintech/loans/infrastructure/persistence/LoanEntity.java`
- `src/main/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapter.java`
- `src/main/java/com/fintech/loans/infrastructure/config/GlobalExceptionHandler.java`
- `src/test/java/com/fintech/loans/domain/model/LoanTest.java`
- `src/test/java/com/fintech/loans/application/services/LoanServiceTest.java`
- `src/test/java/com/fintech/loans/infrastructure/adapters/LoanJpaAdapterTest.java`
- `src/test/java/com/fintech/loans/runners/RunCucumberTest.java`
- `src/test/java/com/fintech/loans/steps/XSteps.java`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/main/java/com/fintech/loans`
- `src/main/java/com/fintech/loans/application`
- `src/main/java/com/fintech/loans/application/ports`
- `src/main/java/com/fintech/loans/application/services`
- `src/main/java/com/fintech/loans/domain`
- `src/main/java/com/fintech/loans/domain/model`
- `src/main/java/com/fintech/loans/domain/exception`
- `src/main/java/com/fintech/loans/infrastructure`
- `src/main/java/com/fintech/loans/infrastructure/adapters`
- `src/main/java/com/fintech/loans/infrastructure/config`
- `src/main/java/com/fintech/loans/infrastructure/persistence`
- `src/test/java/com/fintech/loans`
- `src/test/java/com/fintech/loans/application`
- `src/test/java/com/fintech/loans/domain`
- `src/test/java/com/fintech/loans/infrastructure`

## Verificacion

```bash
mvn clean test-compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **hexagonal/clean**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Calidad de Software, Especialidad Automatizador, Tecnología QA Automation, Senior
- Brecha que el reto ataca: Demuestra un dominio avanzado en Programación Orientada a Objetos en temas clave como: conexiones a base de datos, manejo de excepciones, interfaces, clases abstractas, colecciones, uso de bibliotecas/frameworks, patrones de diseño orientados a objetos, herramientas de pruebas unitarias
- Mision: Candidata con experiencia en automatización y calidad de software

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
