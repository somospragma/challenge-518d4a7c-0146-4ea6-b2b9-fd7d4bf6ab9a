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