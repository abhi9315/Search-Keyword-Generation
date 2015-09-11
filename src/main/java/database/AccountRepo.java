package database;

import org.springframework.data.neo4j.repository.GraphRepository;

/**
 * Created by rajarshee on 11/9/15.
 */
public interface AccountRepo extends GraphRepository<Account> {
}
