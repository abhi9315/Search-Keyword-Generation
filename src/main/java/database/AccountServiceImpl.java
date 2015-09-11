package database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by rajarshee on 11/9/15.
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public Account create(Account account) {
        return accountRepo.save(account);
    }

    public Account getAccount(long id) {
        return accountRepo.findOne(id);
    }

    public void delete(Account account) {
        accountRepo.delete(account);
    }

    public void deleteAll () {
        List<Account> all = findAll();
        for (Account account : all) {
            delete(account);
        }
    }

    @Transactional
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        Iterator<Account> accountIterator = accountRepo.findAll().iterator();
        while (accountIterator.hasNext()) {
            accounts.add(accountIterator.next());
        }
        return accounts;
    }
}
