package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.entity.TaskCategory;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.payload.TaskCategoryDto;
import uz.pdp.appcodingbat.repository.LanguageRepository;
import uz.pdp.appcodingbat.repository.TaskCategoryRepository;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TaskCategoryService {
    @Autowired
    TaskCategoryRepository taskCategoryRepository;

    @Autowired
    LanguageRepository languageRepository;


    /**
     * GET TASK CATEGORY LIST
     *
     * @return TASK CATEGORY LIST
     */
    public List<TaskCategory> get() {
        return taskCategoryRepository.findAll();
    }


    /**
     * GET ONE TASK CATEGORY BY ID
     *
     * @param id INTEGER
     * @return ONE TASK CATEGORY
     */
    public TaskCategory getById(Integer id) {
        Optional<TaskCategory> optionalTaskCategory = taskCategoryRepository.findById(id);
        return optionalTaskCategory.orElse(null);
    }


    /**
     * DELETE TASK CATEGORY BY ID
     *
     * @param id INTEGER
     * @return RESULT
     */
    public Result delete(Integer id) {
        try {
            taskCategoryRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        } catch (Exception e) {
            return new Result("Error", false);
        }
    }


    /**
     * ADD TASK CATEGORY
     *
     * @param taskCategoryDto NAME (String), INFO (String), LIST<Integer>
     * @return RESULT
     */
    public Result add(TaskCategoryDto taskCategoryDto) {
        boolean existsByName = taskCategoryRepository.existsByName(taskCategoryDto.getName());
        if (existsByName) {
            return new Result("TaskCategory already exist", false);
        }

        List<Language> languageList = languageRepository.findAllById(taskCategoryDto.getLanguages());
        Set<Language> languages = new LinkedHashSet<>(languageList);

        TaskCategory taskCategory = new TaskCategory(taskCategoryDto.getName(),
                                         taskCategoryDto.getInfo(), languages);
        taskCategoryRepository.save(taskCategory);
        return new Result("Successfully added", true);
    }


    /**
     * EDIT TASK CATEGORY BY ID
     *
     * @param id          INTEGER
     * @param taskCategoryDto NAME (String), INFO (String), LIST<Integer>
     * @return RESULT
     */
    public Result edit(Integer id, TaskCategoryDto taskCategoryDto) {
        boolean existsByNameAndIdNot = taskCategoryRepository.existsByNameAndIdNot(taskCategoryDto.getName(), id);
        if (existsByNameAndIdNot) {
            return new Result("TaskCategory already exist", false);
        }

        Optional<TaskCategory> optionalTaskCategory = taskCategoryRepository.findById(id);
        if (!optionalTaskCategory.isPresent()) {
            return new Result("TaskCategory not found",false);
        }

        TaskCategory taskCategory = optionalTaskCategory.get();
        taskCategory.setName(taskCategoryDto.getName());
        taskCategory.setInfo(taskCategoryDto.getInfo());
        taskCategoryRepository.save(taskCategory);
        return new Result("Successfully edited", true);
    }
}
