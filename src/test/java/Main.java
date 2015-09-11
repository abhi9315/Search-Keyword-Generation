import database.Account;
import database.AccountService;
import model.Extractor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.neo4j.conversion.Result;
import org.springframework.transaction.annotation.Transactional;
import utility.Trie;

import java.util.Iterator;
import java.util.List;

/**
 * Created by rajarshee on 16/7/15.
 */
public class Main {
    @Transactional
    public static void main(String[] args) {
//        String input = "Germany has no scope";
//        Extractor extractor = new Extractor();
//        Trie dict = new Trie();
//        dict.add("programming");
//        dict.add("singing");
//        extractor.extract(dict,input);

        ApplicationContext context = new ClassPathXmlApplicationContext("account.xml");
        AccountService accountService = (AccountService) context.getBean("accountService");
        Account account = new Account();
        account.setName("Rajarshee");
        account.setUsername("rajarsheem");
        account.setPassword("1234");
        accountService.create(account);
        account = new Account();
        account.setUsername("abc");
        account.setName("ABC");
        account.setPassword("abc");
        accountService.create(account);
        List<Account> accounts = accountService.findAll();
        for (Account acc : accounts) {
            System.out.println(acc);
        }



    }
}
