package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Answer;
import uz.pdp.appcodingbat.entity.User;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
