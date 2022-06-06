package sat.recruitment.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sat.recruitment.api.entity.User;


@Repository
public interface IUserRepository extends JpaRepository<User, Long> {



}
