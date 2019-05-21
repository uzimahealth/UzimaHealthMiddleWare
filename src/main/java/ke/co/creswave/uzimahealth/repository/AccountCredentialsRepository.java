package ke.co.creswave.uzimahealth.repository;

import org.springframework.data.repository.CrudRepository;

import ke.co.creswave.uzimahealth.model.AccountCredentials;

public interface AccountCredentialsRepository extends CrudRepository<AccountCredentials, Long>{

	AccountCredentials findByUserName(String userName);

}
