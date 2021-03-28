package com.zolve.wallet.repo;

import com.zolve.wallet.bo.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Long> {
}
