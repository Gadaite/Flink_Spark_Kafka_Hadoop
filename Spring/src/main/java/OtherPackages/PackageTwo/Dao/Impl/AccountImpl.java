package OtherPackages.PackageTwo.Dao.Impl;

import OtherPackages.PackageTwo.Dao.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountImpl implements Account {
    @Override
    public void Save() {
        System.out.println("Account dao Save");
    }
}
