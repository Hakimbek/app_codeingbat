package uz.pdp.appcodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.TaskCategory;
import uz.pdp.appcodingbat.entity.User;

public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Integer> {
    boolean existsByNameAndLanguageId(String name, Integer language_id);

    boolean existsByNameAndLanguageIdAndIdNot(String name, Integer language_id, Integer id);
}
