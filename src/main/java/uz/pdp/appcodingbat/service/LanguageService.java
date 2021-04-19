package uz.pdp.appcodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appcodingbat.entity.Language;
import uz.pdp.appcodingbat.payload.LanguageDto;
import uz.pdp.appcodingbat.payload.Result;
import uz.pdp.appcodingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;


    /**
     * GET LANGUAGES LIST
     *
     * @return LANGUAGES LIST
     */
    public List<Language> get() {
        return languageRepository.findAll();
    }


    /**
     * GET ONE LANGUAGE BY ID
     *
     * @param id INTEGER
     * @return ONE LANGUAGE
     */
    public Language getById(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }


    /**
     * DELETE LANGUAGE BY ID
     *
     * @param id INTEGER
     * @return RESULT
     */
    public Result delete(Integer id) {
        try {
            languageRepository.deleteById(id);
            return new Result("Successfully deleted", true);
        } catch (Exception e) {
            return new Result("Error", false);
        }
    }


    /**
     * ADD LANGUAGE
     *
     * @param languageDto NAME (String)
     * @return RESULT
     */
    public Result add(LanguageDto languageDto) {
        boolean existsByName = languageRepository.existsByName(languageDto.getName());
        if (existsByName) {
            return new Result("Language already exist", false);
        }

        Language language = new Language(languageDto.getName());
        languageRepository.save(language);
        return new Result("Successfully added", true);
    }


    /**
     * EDIT LANGUAGE BY ID
     *
     * @param id          INTEGER
     * @param languageDto NAME (String)
     * @return RESULT
     */
    public Result edit(Integer id, LanguageDto languageDto) {
        boolean existsByNameAndIdNot = languageRepository.existsByNameAndIdNot(languageDto.getName(), id);
        if (existsByNameAndIdNot) {
            return new Result("Language already exist", false);
        }

        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent()) {
            return new Result("Language not found", false);
        }

        Language editedLanguage = optionalLanguage.get();
        editedLanguage.setName(languageDto.getName());
        languageRepository.save(editedLanguage);
        return new Result("Successfully added", true);
    }
}
