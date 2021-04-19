package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.TaskCategory;
import uz.pdp.appcodingbat.entity.User;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Integer> {
    boolean existsByName(String name);

    boolean existsByNameAndIdNot(String name, Integer id);
}
