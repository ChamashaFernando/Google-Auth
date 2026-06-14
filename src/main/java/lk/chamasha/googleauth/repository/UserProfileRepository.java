package lk.chamasha.googleauth.repository;


import lk.chamasha.googleauth.model.User;
import lk.chamasha.googleauth.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

    Optional<UserProfile> findByUser(User user);
}