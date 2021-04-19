package fss.acquisition.merchantonboard.web.rest;

import fss.acquisition.merchantonboard.domain.BankAccount;
import fss.acquisition.merchantonboard.repository.BankAccountRepository;
import fss.acquisition.merchantonboard.service.BankAccountService;
import fss.acquisition.merchantonboard.web.rest.errors.ResourseNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/api")
@Transactional
public class BankAccountResource {

    private final Logger log = LoggerFactory.getLogger(BankAccountResource.class);

    private static final String ENTITY_NAME = "bankAccount";


    @Autowired
    BankAccountService bankAccountService;

    private final BankAccountRepository bankAccountRepository;

    public BankAccountResource(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }


    @PostMapping("/bank-accounts")
    public String createBankAccount(@Valid @RequestBody BankAccount bankAccount) throws ResourseNotFoundException {
        log.debug("REST request to save BankAccount : {}", bankAccount);
         bankAccountService.createBankAccount(bankAccount);
        return "Created Successfully";
    }

    @PutMapping("/bank-accounts/{mid}")
    public String updateBankAccount(
        @PathVariable(value = "mid", required = true) final UUID mid,
        @Valid @RequestBody BankAccount bankAccount
    ) throws ResourseNotFoundException {
        log.debug("REST request to update BankAccount : {}, {}", mid, bankAccount);
        bankAccountService.updateBankAccount(bankAccount);
        return "Updated Successfully";
    }

   /* @PatchMapping(value = "/bank-accounts/{id}", consumes = "application/merge-patch+json")
    public ResponseEntity<BankAccount> partialUpdateBankAccount(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BankAccount bankAccount
    ) throws URISyntaxException {
        log.debug("REST request to partial update BankAccount partially : {}, {}", id, bankAccount);
        if (bankAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bankAccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!bankAccountRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BankAccount> result = bankAccountRepository
            .findById(bankAccount.getId())
            .map(
                existingBankAccount -> {
                    if (bankAccount.getAccountno() != null) {
                        existingBankAccount.setAccountno(bankAccount.getAccountno());
                    }
                    if (bankAccount.getIfsccode() != null) {
                        existingBankAccount.setIfsccode(bankAccount.getIfsccode());
                    }
                    if (bankAccount.getBankname() != null) {
                        existingBankAccount.setBankname(bankAccount.getBankname());
                    }
                    if (bankAccount.getType() != null) {
                        existingBankAccount.setType(bankAccount.getType());
                    }
                    if (bankAccount.getStatus() != null) {
                        existingBankAccount.setStatus(bankAccount.getStatus());
                    }

                    return existingBankAccount;
                }
            )
            .map(bankAccountRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, bankAccount.getId().toString())
        );
    }*/



    @GetMapping("/bank-accounts")
    public List<BankAccount> getAllBankAccounts() {
        log.debug("REST request to get all BankAccounts");
        return bankAccountRepository.findAll();
    }

    @GetMapping("/bank-accounts/{mid}")
    public BankAccount getBankAccount(@PathVariable UUID mid) throws ResourseNotFoundException {
        log.debug("REST request to get BankAccount : {}", mid);
        return bankAccountService.getBankAccount(mid);
    }


    @DeleteMapping("/bank-accounts/{mid}")
    public String deleteBankAccount(@PathVariable UUID mid) {
        log.debug("REST request to delete BankAccount : {}", mid);
        bankAccountRepository.deleteByMid(mid);
        return "Deleted Successfully";
    }
}
