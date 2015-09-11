package database;

import org.springframework.data.neo4j.conversion.Result;

import java.util.List;

/**
 * Created by rajarshee on 11/9/15.
 */
public interface AccountService {
    Account create(Account account);
    Account getAccount(long id);
    List<Account> findAll();
    void deleteAll();
    void delete(Account account);
}
